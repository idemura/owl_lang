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
:   (
        f = function { $r.functions.add($f.r); }
    )*
;

absoluteName
returns [AstName r = new AstName()]
:   NAME { $r.name += $NAME.text; }
    (
        DOT NAME { $r.name += "." + $NAME.text; }
    )*
;

function
returns [AstFunction r = new AstFunction()]
:   FN (NAME { $r.name = $NAME.text; })?
    (
        LPAREN
        (
            a = variable { $r.args.add($a.r); }
            (
                COMMA a = variable { $r.args.add($a.r); }
            )*
        )?
        RPAREN
    )?
    (
        COLON type = typeInstance { $r.returnType = $type.r; }
    )?
    b = block { $r.block = $b.r; }
;

variable
returns [AstVariable r = new AstVariable()]
:   NAME { $r.name = $NAME.text; }
    (
        COLON t = typeInstance { $r.type = $t.r; }
    )?
;

block
returns [AstBlock r = new AstBlock()]
:   LCURLY
    (
        s = statement { $r.statements.add($s.r); }
    )*
    RCURLY
;

// Expression
exprPrime
returns [AstNode r]
:   NAME { $r = new AstName($NAME.text); }
|   OCT { $r = new AstConstant($OCT.text); }
|   DEC { $r = new AstConstant($DEC.text); }
|   HEX { $r = new AstConstant($HEX.text); }
|   LPAREN e = expression RPAREN { $r = $e.r; }
;

exprApply
returns [AstNode r]
:   x = exprPrime { $r = $x.r; }
    (
        DOT NAME
        {
            AstMember m = new AstMember();
            m.left = $r;
            m.name = new AstName($NAME.text);
            $r = m;
        }
    |   {
            AstApply app = new AstApply();
            app.args.add($r);
            $r = app;
        }
        LPAREN
        (
            a = expression { app.args.add($a.r); }
            (
                COMMA a = expression { app.args.add($a.r); }
            )*
        )?
        RPAREN
    |   {
            AstApply app = new AstApply();
            app.args.add(new AstName("[]"));
            app.args.add($r);
            $r = app;
        }
        LBRACKET
        a = expression { app.args.add($a.r); }
        (
            COMMA a = expression { app.args.add($a.r); }
        )*
        RBRACKET
    )*
;

exprCoerce
returns [AstNode r]
:   x = exprApply { $r = $x.r; }
    (
        COLON y = typeInstance
        {
            AstApply app = new AstApply();
            app.args.add(new AstName(":"));
            app.args.add($r);
            app.args.add($y.r);
            $r = app;
        }
    )?
;

exprUnary
returns [AstNode r]
:   op = (TILDE | PLS | MNS)? x = exprCoerce
    {
        if ($op != null) {
            AstApply app = new AstApply();
            app.args.add(new AstName($op.text));
            app.args.add($x.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
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
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
            $r = app;
        }
    )*
;

expression
returns [AstNode r]
:   x = exprOr { $r = $x.r; }
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
        y = exprOr
        {
            AstApply app = new AstApply();
            app.args.add(new AstName($op.text));
            app.args.add($r);
            app.args.add($y.r);
            $r = app;
        }
    )*
;

stmtIf
returns [AstIf r = new AstIf()]
:   IF
    cond = expression { $r.condition.add($cond.r); }
    b = block { $r.block.add($b.r); }
    (
        ELIF
        cond = expression { $r.condition.add($cond.r); }
        b = block { $r.block.add($b.r); }
    )*
    (
        ELSE
        b = block { $r.block.add($b.r); }
    )?
;

stmtMatch
returns [AstMatch r = new AstMatch()]
:   MATCH e = expression { $r.expr = $e.r; }
    LCURLY
    (
        (
            CASE t = NAME (n = NAME)?
            {
                AstMatch.Label label = new AstMatch.Label();
                label.label = $t.text;
                if ($n != null) {
                    label.variable = $n.text;
                }
                label.block = $r.block.size();
                $r.label.add(label);
            }
        )+
        b = block
        {
            $r.block.add($b.r);
        }
    )*
    RCURLY
    (
        ELSE
        b = block
        {
            $r.elseBlock = $b.r;
        }
    )?
;

statement
returns [AstNode r]
:   e = expression SEMICOLON { $r = $e.r; }
|   s = stmtIf { $r = $s.r; }
|   m = stmtMatch { $r = $m.r; }
;

// Type Instance
typeNonFn
returns [AstType r = new AstType()]
:   n = absoluteName { $r.name = $n.r; }
    (
        LPAREN
        a = typeInstance { $r.args.add($a.r); }
        (
            COMMA a = typeInstance { $r.args.add($a.r); }
        )*
        RPAREN
    |   (
            LBRACKET
            RBRACKET
            {
                AstType arrayType = new AstType("Array");
                arrayType.args.add($r);
                $r = arrayType;
            }
        )+
    )?
;

typeInstance
returns [AstType r]
:   { AstType functionType = null; }
    x = typeNonFn { $r = $x.r; }
    (
        ARROW y = typeNonFn
        {
            if (functionType == null) {
                functionType = new AstType("Fn");
                functionType.args.add($r);
                $r = functionType;
            }
            functionType.args.add($y.r);
        }
    )*
;


// Lexer Tokens
CASE: 'case';
ELIF: 'elif';
ELSE: 'else';
FN: 'fn';
IF: 'if';
IS: 'is';
MATCH: 'match';
NEW: 'new';

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
NAME: [a-zA-Z_] [a-zA-Z0-9_]*;

COMMENT: '#' ~[\n]* -> channel(HIDDEN);
WS: [ \t\r\n\f]+ -> skip;
