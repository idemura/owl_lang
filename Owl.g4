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
:   'module' qualifiedName ';'
    (
        function
    |   variable
    )*
;

qualifiedName
:   NAME ('.' NAME)*
;

variable
:   'var' NAME '=' expression ';'
;

function
:   ('fn' | 'function') NAME? ('(' (argument (',' argument)*)? ')')? (':' type)? block
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
|   STRING
|   '(' expression ')'
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

expression
:   t = exprEq
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

// TODO:
//  - While
//  - For
//  - Yield (add to return)
statement
:   assignment ';'
|   stmtIf
|   stmtReturn
|   variable
;

arrayTypeSuffix
:   '[' ']'
;

typeSimple
:   '(' type ')'
|   qualifiedName ('(' type (',' type)* ')')? arrayTypeSuffix*
;

type
:   typeSimple ('=>' typeSimple)*
;


BOOL: 'true' | 'false';
NAME: [a-zA-Z] [a-zA-Z0-9_]*;
INT: '0o' [0-7]+ | [0-9]+ | '0x' [0-9a-fA-F]+;
STRING: '"' ~[\t\r\n\f"]* '"';

COMMENT: '#' ~[\n]* -> channel(HIDDEN);
WS: [ \t\r\n\f]+ -> skip;
