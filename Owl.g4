/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
grammar Owl;

@header {
// This file is a prt of the Owl Programming Language.
package owl.lang;
}

// Parser Rules
module
returns [AstModule r = new AstModule()]
:
    MODULE n = absoluteName { $r.name = $n.r; } SEMICOLON
    (
        f = function { $r.add($f.r); }
    |   NAME ASSIGN e = expression SEMICOLON
        {
            $r.add(new AstVariable($NAME.text, $e.r));
        }
    )*
;

absoluteName
returns [String r]
:   {
        List<String> pieces = new ArrayList<>();
    }
    NAME { pieces.add($NAME.text); }
    (
        DOT NAME { pieces.add($NAME.text); }
    )*
    {
        $r = String.join(".", pieces);
    }
;

function
returns [AstFunction r = new AstFunction()]
:   FN
    (
        NAME { $r.name = $NAME.text; }
    )?
    (
        LPAREN
        (
            a = argument { $r.addArg($a.r); }
            (
                COMMA a = argument { $r.addArg($a.r); }
            )*
        )?
        RPAREN
    )?
    (
        COLON type = typeInstance { $r.returnType = $type.r; }
    )?
    b = block
    {
        $r.block = $b.r;
    }
;

argument
returns [AstArgument r = new AstArgument()]
:   NAME { $r.name = $NAME.text; }
    (
        COLON t = typeInstance { $r.type = $t.r; }
    )?
;

block
returns [AstBlock r = new AstBlock()]
:   LCURLY
    (
        s = statement { $r.add($s.r); }
    )*
    RCURLY
;

// Expression
exprPrime
returns [AstNode r]
:   NAME { $r = new AstName($NAME.text); }
|   OCT { $r = new AstValue($OCT.text, AstValue.Format.OCT); }
|   DEC { $r = new AstValue($DEC.text, AstValue.Format.DEC); }
|   HEX { $r = new AstValue($HEX.text, AstValue.Format.HEX); }
|   STRING { $r = new AstValue(Util.unquote($STRING.text), AstValue.Format.STRING); }
|   LPAREN e = expression RPAREN { $r = $e.r; }
;

exprApply
returns [AstNode r]
:   x = exprPrime { $r = $x.r; }
    (
        DOT NAME
        {
            $r = new AstField($r, $NAME.text);
        }
    |   {
            AstApply app = new AstApply();
            app.fn = $r;
            $r = app;
        }
        LPAREN
        (
            a = expression { app.add($a.r); }
            (
                COMMA a = expression { app.add($a.r); }
            )*
        )?
        RPAREN
    |   {
            AstApply app = new AstApply();
            app.fn = new AstName("[]");
            app.add($r);
            $r = app;
        }
        LBRACKET
        a = expression { app.add($a.r); }
        (
            COMMA a = expression { app.add($a.r); }
        )*
        RBRACKET
    )*
|   NEW t = typeInstance
    (
        i = exprInit
    )?
    {
        $r = new AstNew($t.r, $i.r);
    }
;

exprInitArg
returns [AstNode r]
:   e = expression
    {
        $r = $e.r;
    }
|   i = exprInit
    {
        $r = $i.r;
    }
;

exprInit
returns [AstGroup r = new AstGroup()]
:   LCURLY
    (
        a = exprInitArg
        {
            $r.add($a.r);
        }
        (
            COMMA a = exprInitArg
            {
                $r.add($a.r);
            }
        )*
    )?
    RCURLY
;

exprCoerce
returns [AstNode r]
:   x = exprApply { $r = $x.r; }
    (
        COLON y = typeInstance { $r = new AstCast($r, $y.r); }
    )?
;

exprUnary
returns [AstNode r]
:   op = (TILDE | PLS | MNS)? x = exprCoerce
    {
        if ($op != null) {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($x.r);
            $r = app;
        } else {
            $r = $x.r;
        }
    }
;

exprMulDiv
returns [AstNode r]
:   x = exprUnary { $r = $x.r; }
    (
        op = (MUL | DIV | MOD) y = exprUnary
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprAddSub
returns [AstNode r]
:   x = exprMulDiv { $r = $x.r; }
    (
        op = (PLS | MNS) y = exprMulDiv
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprShift
returns [AstNode r]
:   x = exprAddSub { $r = $x.r; }
    (
        op = (LSHIFT | RSHIFT | SIGNED_RSHIFT) y = exprAddSub
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprBitAnd
returns [AstNode r]
:   x = exprShift { $r = $x.r; }
    (
        op = BIT_AND y = exprShift
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprBitXor
returns [AstNode r]
:   x = exprBitAnd { $r = $x.r; }
    (
        op = BIT_XOR y = exprBitAnd
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprBitOr
returns [AstNode r]
:   x = exprBitXor { $r = $x.r; }
    (
        op = BIT_OR y = exprBitXor
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprComparison
returns [AstNode r]
:   x = exprBitOr { $r = $x.r; }
    (
        op = (LT | LE | GT | GE) y = exprBitOr
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprEquality
returns [AstNode r]
:   x = exprComparison { $r = $x.r; }
    (
        op = (EQ | NE | IS) y = exprComparison
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprNot
returns [AstNode r]
:   op = EXC? x = exprEquality { $r = $x.r; }
    {
        if ($op != null) {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            $r = app;
        }
    }
;

exprAnd
returns [AstNode r]
:   x = exprNot { $r = $x.r; }
    (
        op = AND y = exprNot
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

exprOr
returns [AstNode r]
:   x = exprAnd { $r = $x.r; }
    (
        op = OR y = exprAnd
        {
            AstApply app = new AstApply();
            app.fn = new AstName($op.text);
            app.add($r);
            app.add($y.r);
            $r = app;
        }
    )*
;

expression
returns [AstNode r]
:   x = exprOr { $r = $x.r; }
;

// TODO:
//  - Comma assignment x, y =  10 + 12, 10 * 12;
//  - Lambda expression
assignment
returns [AstNode r]
:   x = expression { $r = $x.r; }
    (
        op =
        (
            ASSIGN
        |   ASSIGN_MUL
        |   ASSIGN_DIV
        |   ASSIGN_MOD
        |   ASSIGN_PLS
        |   ASSIGN_MNS
        |   ASSIGN_LSHIFT
        |   ASSIGN_RSHIFT
        |   ASSIGN_SIGNED_RSHIFT
        |   ASSIGN_BIT_AND
        |   ASSIGN_BIT_XOR
        |   ASSIGN_BIT_OR
        )
        y = expression
        {
            $r = new AstAssign(Util.removeSuffix($op.text, 1), $r, $y.r);
        }
    )?
;

ifBlock
returns [AstIfBlock r]
:   c = expression b = block
    {
        $r = new AstIfBlock(new AstExpr($c.r), $b.r);
    }
;

stmtIf
returns [AstIf r = new AstIf()]
:   IF c = ifBlock { $r.add($c.r); }
    (
        ELIF c = ifBlock { $r.add($c.r); }
    )*
    (
        ELSE b = block { $r.add(new AstIfBlock(null, $b.r)); }
    )?
;

stmtVar
returns [AstVariable r]
:   VAR n = NAME ASSIGN x = expression SEMICOLON
    {
        $r = new AstVariable($n.text, $x.r);
    }
;

stmtReturn
returns [AstReturn r = new AstReturn()]
:   RETURN
    (
        e = expression { $r.expr = $e.r; }
    )?
    SEMICOLON
;

// TODO:
//  - While
//  - For
//  - Yield (add to return)
statement
returns [AstNode r]
:   e = assignment SEMICOLON { $r = new AstExpr($e.r); }
|   s = stmtIf { $r = $s.r; }
|   ret = stmtReturn { $r = $ret.r; }
|   v = stmtVar { $r = $v.r; }
;

// Type Instance
typeNotFn
returns [Type r]
:   n = absoluteName { $r = new Type($n.r); }
    (
        LPAREN
        a = typeInstance { $r.add($a.r); }
        (
            COMMA a = typeInstance { $r.add($a.r); }
        )*
        RPAREN
    |   (
            LBRACKET
            RBRACKET
            {
                $r = Type.arrayOf($r);
            }
        )+
    )?
;

typeInstance
returns [Type r = new Type(Type.FUNCTION)]
:   x = typeNotFn { $r.add($x.r); }
    (
        ARROW y = typeNotFn { $r.add($y.r); }
    )*
    {
        if ($r.args.size() == 1) {
            $r = $r.args.get(0);
        }
    }
;


// Lexer Tokens
CASE: 'case';
ELIF: 'elif';
ELSE: 'else';
FN: 'fn';
IF: 'if';
IS: 'is';
MODULE: 'module';
NEW: 'new';
RETURN: 'return';
VAR: 'var';

DOT: '.';
COMMA: ',';
COLON: ':';
SEMICOLON: ';';
ARROW: '=>';
LPAREN: '(';
RPAREN: ')';
LCURLY: '{';
RCURLY: '}';
LBRACKET: '[';
RBRACKET: ']';
QUOTE: '\'';
DBL_QUOTE: '"';
AT: '@';
TILDE: '~';
EXC: '!';
MUL: '*';
DIV: '/';
MOD: '%';
PLS: '+';
MNS: '-';
LSHIFT: '<<';
RSHIFT: '>>';
SIGNED_RSHIFT: '>>>';
BIT_AND: '&';
BIT_XOR: '^';
BIT_OR: '|';
EQ: '==';
NE: '!=';
LE: '<=';
LT: '<';
GE: '>=';
GT: '>';
AND: '&&';
OR: '||';
ASSIGN: '=';
ASSIGN_MUL: '*=';
ASSIGN_DIV: '/=';
ASSIGN_MOD: '%=';
ASSIGN_PLS: '+=';
ASSIGN_MNS: '-=';
ASSIGN_LSHIFT: '<<=';
ASSIGN_RSHIFT: '>>=';
ASSIGN_SIGNED_RSHIFT: '>>>=';
ASSIGN_BIT_AND: '&=';
ASSIGN_BIT_XOR: '^=';
ASSIGN_BIT_OR: '|=';

OCT: '0o' [0-7]+;
DEC: [0-9]+;
HEX: '0x' [0-9a-fA-F]+;
NAME: [a-zA-Z] [a-zA-Z0-9_]*;
STRING: '"' ~[\t\r\n\f"]* '"';

COMMENT: '#' ~[\n]* -> channel(HIDDEN);
WS: [ \t\r\n\f]+ -> skip;
