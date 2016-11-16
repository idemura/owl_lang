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


ELIF: 'elif';
ELSE: 'else';
FN: 'fn';
IF: 'if';
IS: 'is';
NEW: 'new';
DOT: '.';
COMMA: ',';
COLON: ':';
SEMICOLON: ';';
LPAREN: '(';
RPAREN: ')';
LCURLY: '{';
RCURLY: '}';
LBRACKET: '[';
RBRACKET: ']';
AT: '@';
TILDE: '~';
EXC: '!';
ASSIGN_MUL: '*=';
MUL: '*';
ASSIGN_DIV: '/=';
DIV: '/';
ASSIGN_MOD: '%=';
MOD: '%';
ASSIGN_PLS: '+=';
PLS: '+';
ASSIGN_MNS: '-=';
MNS: '-';
ASSIGN_LSHIFT: '<<=';
LSHIFT: '<<';
ASSIGN_RSHIFT: '>>=';
RSHIFT: '>>';
SIGNED_RSHIFT: '>>>';
ASSIGN_SIGNED_RSHIFT: '>>>=';
ARROW: '=>';
EQ: '==';
NE: '!=';
LE: '<=';
LT: '<';
GE: '>=';
GT: '>';
AND: '&&';
OR: '||';
ASSIGN_BIT_AND: '&=';
BIT_AND: '&';
ASSIGN_BIT_XOR: '^=';
BIT_XOR: '^';
ASSIGN_BIT_OR: '|=';
BIT_OR: '|';
ASSIGN: '=';
OCT: '0o' [0-7]+;
DEC: [0-9]+;
HEX: '0x' [0-9, a-f, A-F]+;
NAME: [a-z,A-Z,_] [a-z,A-Z,0-9,_]*;

COMMENT: '#' [^\n]* -> skip;
WS: [ \t\r\n\f]+ -> skip;
