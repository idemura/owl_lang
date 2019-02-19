grammar Owl;

@header {
package owl.compiler;
}

// Parser Rules
module
:   'module' NAME ';'
    (
        function
    |   variable
    |   object
    )*
;

qualifiedName
:   NAME ('::' NAME)*
;

object
:   'object' NAME '{' (argument (';' argument)*)? '}'
;

variable
:   'var' NAME '=' expression ';'
;

function
:   NAME? '(' (argument (',' argument)*)? ')' (':' type)? block
;

argument
:   NAME (':' type)?
;

block
:   '{' statement* '}'
;

// Expression
exprPrime
:   NAME
|   BOOL
|   INT
|   (dollar = '$')? STRING
|   '(' expression ')'
|   exprNew
;

exprNew
:   'new' type '{' expression* '}'
;

exprApply
:   exprPrime
|   exprApply op = '.' NAME
|   exprApply op = '(' (expression (',' expression)*)? ')'
|   exprApply op = '[' expression ']'
;

exprCoerce
:   exprApply (':' type)?
;

exprUnary
:   (op = ('~' | '+' | '-'))? t = exprCoerce
;

exprMulDiv
:   t = exprUnary
|   l = exprMulDiv op = '*' r = exprUnary
|   l = exprMulDiv op = '/' r = exprUnary
|   l = exprMulDiv op = '%' r = exprUnary
|   l = exprMulDiv op = '//' r = exprUnary
;

exprAddSub
:   t = exprMulDiv
|   l = exprAddSub op = '+' r = exprMulDiv
|   l = exprAddSub op = '-' r = exprMulDiv
;

exprShift
:   t = exprAddSub
|   l = exprShift op = '<<' r = exprAddSub
|   l = exprShift op = '>>' r = exprAddSub
|   l = exprShift op = '>>>' r = exprAddSub
;

exprBitAnd
:   t = exprShift
|   l = exprBitAnd op = '&' r = exprShift
;

exprBitXor
:   t = exprBitAnd
|   l = exprBitXor op = '^' r = exprBitAnd
;

exprBitOr
:   t = exprBitXor
|   l = exprBitOr op = '|' r = exprBitXor
;

exprCmp
:   t = exprBitOr
|   l = exprCmp op = '<' r = exprBitOr
|   l = exprCmp op = '<=' r = exprBitOr
|   l = exprCmp op = '>' r = exprBitOr
|   l = exprCmp op = '>=' r = exprBitOr
;

exprEq
:   t = exprCmp
|   l = exprEq op = '==' r = exprCmp
|   l = exprEq op = '!=' r = exprCmp
;

exprNot
:   t = exprEq
|   op = '!' l = exprNot
;

exprAnd
:   t = exprNot
|   l = exprAnd op = '&&' r = exprNot
;

// Compared to == has different priority
exprXor
:   t = exprAnd
|   l = exprXor op = '^^' r = exprAnd
;

exprOr
:   t = exprXor
|   l = exprOr op = '||' r = exprXor
;

expression
:   t = exprOr
;

// TODO:
//  - Comma assignment x, y =  10 + 12, 10 * 12;
//  - Lambda expression
assignment
:   l = expression
    (
        op = ('=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '>>>=' | '&=' | '^=' | '|=')
        r = expression
    )?
;

stmtIf
:   'if' expression block
    (
        'elif' expression block
    )*
    (
        'else' block
    )?
;

stmtReturn
:   'return' expression? ';'
;

stmtFor
:   'for' (c = expression | NAME 'in' l = expression ',' r = expression) block
;

// Another would be stmtForKey

// TODO:
//  - Range for
//  - Yield (add to return)
statement
:   assignment ';'
|   stmtIf
|   stmtReturn
|   variable
|   stmtFor
;

arrayTypeSuffix
:   '[' ']'
;

typeConstructor
:   '(' type ')'
|   qualifiedName ('(' type (',' type)* ')')? arrayTypeSuffix*
;

type
:   typeConstructor ('=>' typeConstructor)*
;

BOOL: 'true' | 'false';
NAME: [a-zA-Z] [a-zA-Z0-9_]*;
INT: '0o' [0-7]+ | [0-9]+ | '0x' [0-9a-fA-F]+;
STRING: '"' ~[\t\r\n\f"]* '"';

COMMENT: '#' ~[\n]* -> channel(HIDDEN);
WS: [ \t\r\n\f]+ -> skip;
