/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package owl.lang;

import java.util.ArrayList;
import java.util.List;

public class Parser implements ParserConstants {

  final public AstModule module() throws ParseException {AstModule m = new AstModule();
    AstFunction f;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FN:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      f = function();
m.functions.add(f);
    }
{if ("" != null) return m;}
    throw new Error("Missing return statement in function");
  }

  final public AstName name() throws ParseException {Token tok;
    tok = jj_consume_token(NAME);
{if ("" != null) return new AstName(tok.image);}
    throw new Error("Missing return statement in function");
  }

  final public AstName qualifiedName() throws ParseException {String name;
    Token tok;
    tok = jj_consume_token(NAME);
name = tok.image;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DOT:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(DOT);
      tok = jj_consume_token(NAME);
name += "." + tok.image;
    }
{if ("" != null) return new AstName(name);}
    throw new Error("Missing return statement in function");
  }

  final public AstFunction function() throws ParseException {AstFunction f = new AstFunction();
    AstVariable arg;
    jj_consume_token(FN);
    f.name = jj_consume_token(NAME).image;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        arg = variable();
f.args.add(arg);
        label_3:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[2] = jj_gen;
            break label_3;
          }
          jj_consume_token(COMMA);
          arg = variable();
f.args.add(arg);
        }
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        ;
      }
      jj_consume_token(RPAREN);
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COLON:{
      jj_consume_token(COLON);
      f.returnType = type();
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    f.block = block();
{if ("" != null) return f;}
    throw new Error("Missing return statement in function");
  }

  final public AstVariable variable() throws ParseException {AstVariable v = new AstVariable();
    Token tok;
    tok = jj_consume_token(NAME);
v.name = tok.image;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COLON:{
      jj_consume_token(COLON);
      v.type = type();
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
{if ("" != null) return v;}
    throw new Error("Missing return statement in function");
  }

  final public AstBlock block() throws ParseException {AstBlock b = new AstBlock();
    AstNode s;
    jj_consume_token(LCURLY);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case LPAREN:
      case TILDE:
      case EXC:
      case PLS:
      case MNS:
      case OCT:
      case DEC:
      case HEX:
      case NAME:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      s = statement();
b.statements.add(s);
    }
    jj_consume_token(RCURLY);
{if ("" != null) return b;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode prime() throws ParseException {AstNode p = null;
    Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NAME:{
      p = name();
      break;
      }
    case OCT:{
      tok = jj_consume_token(OCT);
p = new AstConstant(tok.image);
      break;
      }
    case DEC:{
      tok = jj_consume_token(DEC);
p = new AstConstant(tok.image);
      break;
      }
    case HEX:{
      tok = jj_consume_token(HEX);
p = new AstConstant(tok.image);
      break;
      }
    case LPAREN:{
      jj_consume_token(LPAREN);
      p = expression();
      jj_consume_token(RPAREN);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return p;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode call() throws ParseException {AstNode r, arg;
    AstName n;
    AstApply apply;
    r = prime();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DOT:
      case LPAREN:
      case LBRACKET:{
        ;
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DOT:{
        jj_consume_token(DOT);
        n = name();
AstMember m = new AstMember();
                m.left = r;
                m.name = n;
                r = m;
        break;
        }
      case LPAREN:{
apply = new AstApply();
                apply.args.add(r);
                r = apply;
        jj_consume_token(LPAREN);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case LPAREN:
        case TILDE:
        case EXC:
        case PLS:
        case MNS:
        case OCT:
        case DEC:
        case HEX:
        case NAME:{
          arg = expression();
apply.args.add(arg);
          label_6:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case COMMA:{
              ;
              break;
              }
            default:
              jj_la1[10] = jj_gen;
              break label_6;
            }
            jj_consume_token(COMMA);
            arg = expression();
apply.args.add(arg);
          }
          break;
          }
        default:
          jj_la1[11] = jj_gen;
          ;
        }
        jj_consume_token(RPAREN);
        break;
        }
      case LBRACKET:{
apply = new AstApply();
                apply.args.add(new AstName("[]"));
                apply.args.add(r);
                r = apply;
        jj_consume_token(LBRACKET);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case LPAREN:
        case TILDE:
        case EXC:
        case PLS:
        case MNS:
        case OCT:
        case DEC:
        case HEX:
        case NAME:{
          arg = expression();
apply.args.add(arg);
          label_7:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case COMMA:{
              ;
              break;
              }
            default:
              jj_la1[12] = jj_gen;
              break label_7;
            }
            jj_consume_token(COMMA);
            arg = expression();
apply.args.add(arg);
          }
          break;
          }
        default:
          jj_la1[13] = jj_gen;
          ;
        }
        jj_consume_token(RBRACKET);
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return r;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode cast() throws ParseException {AstNode r, t;
    r = call();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COLON:{
      jj_consume_token(COLON);
      t = type();
AstApply apply = new AstApply();
            apply.args.add(new AstName(":"));
            apply.args.add(r);
            apply.args.add(t);
            r = apply;
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      ;
    }
{if ("" != null) return r;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode unary() throws ParseException {Token op = null;
    AstNode n;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TILDE:
    case PLS:
    case MNS:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TILDE:{
        op = jj_consume_token(TILDE);
        break;
        }
      case PLS:{
        op = jj_consume_token(PLS);
        break;
        }
      case MNS:{
        op = jj_consume_token(MNS);
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      ;
    }
    n = cast();
if (op == null) {
            {if ("" != null) return n;}
        }
        AstApply apply = new AstApply();
        apply.args.add(new AstName(op.image));
        apply.args.add(n);
        {if ("" != null) return apply;}
    throw new Error("Missing return statement in function");
  }

  final public Token multiplicativeOp() throws ParseException {Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MUL:{
      tok = jj_consume_token(MUL);
{if ("" != null) return tok;}
      break;
      }
    case DIV:{
      tok = jj_consume_token(DIV);
{if ("" != null) return tok;}
      break;
      }
    case MOD:{
      tok = jj_consume_token(MOD);
{if ("" != null) return tok;}
      break;
      }
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstNode multiplicative() throws ParseException {Token op;
    AstNode l, r;
    l = unary();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MUL:
      case DIV:
      case MOD:{
        ;
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        break label_8;
      }
      op = multiplicativeOp();
      r = unary();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public Token additiveOp() throws ParseException {Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLS:{
      tok = jj_consume_token(PLS);
{if ("" != null) return tok;}
      break;
      }
    case MNS:{
      tok = jj_consume_token(MNS);
{if ("" != null) return tok;}
      break;
      }
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstNode additive() throws ParseException {Token op;
    AstNode l, r;
    l = multiplicative();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLS:
      case MNS:{
        ;
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        break label_9;
      }
      op = additiveOp();
      r = multiplicative();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public Token shiftOp() throws ParseException {Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LSHIFT:{
      tok = jj_consume_token(LSHIFT);
{if ("" != null) return tok;}
      break;
      }
    case RSHIFT:{
      tok = jj_consume_token(RSHIFT);
{if ("" != null) return tok;}
      break;
      }
    case SIGNED_RSHIFT:{
      tok = jj_consume_token(SIGNED_RSHIFT);
{if ("" != null) return tok;}
      break;
      }
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstNode shift() throws ParseException {Token op;
    AstNode l, r;
    l = additive();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LSHIFT:
      case RSHIFT:
      case SIGNED_RSHIFT:{
        ;
        break;
        }
      default:
        jj_la1[23] = jj_gen;
        break label_10;
      }
      op = shiftOp();
      r = additive();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode bitAnd() throws ParseException {Token op;
    AstNode l, r;
    l = shift();
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case BIT_AND:{
        ;
        break;
        }
      default:
        jj_la1[24] = jj_gen;
        break label_11;
      }
      op = jj_consume_token(BIT_AND);
      r = shift();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode bitXor() throws ParseException {Token op;
    AstNode l, r;
    l = bitAnd();
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case BIT_XOR:{
        ;
        break;
        }
      default:
        jj_la1[25] = jj_gen;
        break label_12;
      }
      op = jj_consume_token(BIT_XOR);
      r = bitAnd();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode bitOr() throws ParseException {Token op;
    AstNode l, r;
    l = bitXor();
    label_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case BIT_OR:{
        ;
        break;
        }
      default:
        jj_la1[26] = jj_gen;
        break label_13;
      }
      op = jj_consume_token(BIT_OR);
      r = bitXor();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public Token comparisonOp() throws ParseException {Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LT:{
      tok = jj_consume_token(LT);
{if ("" != null) return tok;}
      break;
      }
    case LE:{
      tok = jj_consume_token(LE);
{if ("" != null) return tok;}
      break;
      }
    case GT:{
      tok = jj_consume_token(GT);
{if ("" != null) return tok;}
      break;
      }
    case GE:{
      tok = jj_consume_token(GE);
{if ("" != null) return tok;}
      break;
      }
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstNode comparison() throws ParseException {Token op;
    AstNode l, r;
    l = bitOr();
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LE:
      case LT:
      case GE:
      case GT:{
        ;
        break;
        }
      default:
        jj_la1[28] = jj_gen;
        break label_14;
      }
      op = comparisonOp();
      r = bitOr();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public Token equalityOp() throws ParseException {Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:{
      tok = jj_consume_token(EQ);
{if ("" != null) return tok;}
      break;
      }
    case NE:{
      tok = jj_consume_token(NE);
{if ("" != null) return tok;}
      break;
      }
    case IS:{
      tok = jj_consume_token(IS);
{if ("" != null) return tok;}
      break;
      }
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstNode equality() throws ParseException {Token op;
    AstNode l, r;
    l = comparison();
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IS:
      case EQ:
      case NE:{
        ;
        break;
        }
      default:
        jj_la1[30] = jj_gen;
        break label_15;
      }
      op = equalityOp();
      r = comparison();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode logicalNot() throws ParseException {Token op = null;
    AstNode l;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EXC:{
      op = jj_consume_token(EXC);
      break;
      }
    default:
      jj_la1[31] = jj_gen;
      ;
    }
    l = equality();
if (op == null) {
            {if ("" != null) return l;}
        } else {
            AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            {if ("" != null) return apply;}
        }
    throw new Error("Missing return statement in function");
  }

  final public AstNode logicalAnd() throws ParseException {Token op;
    AstNode l, r;
    l = logicalNot();
    label_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[32] = jj_gen;
        break label_16;
      }
      op = jj_consume_token(AND);
      r = logicalNot();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode logicalOr() throws ParseException {Token op;
    AstNode l, r;
    l = logicalAnd();
    label_17:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[33] = jj_gen;
        break label_17;
      }
      op = jj_consume_token(OR);
      r = logicalAnd();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public Token assignmentOp() throws ParseException {Token tok;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ASSIGN:{
      tok = jj_consume_token(ASSIGN);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_MUL:{
      tok = jj_consume_token(ASSIGN_MUL);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_DIV:{
      tok = jj_consume_token(ASSIGN_DIV);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_MOD:{
      tok = jj_consume_token(ASSIGN_MOD);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_PLS:{
      tok = jj_consume_token(ASSIGN_PLS);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_MNS:{
      tok = jj_consume_token(ASSIGN_MNS);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_LSHIFT:{
      tok = jj_consume_token(ASSIGN_LSHIFT);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_RSHIFT:{
      tok = jj_consume_token(ASSIGN_RSHIFT);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_SIGNED_RSHIFT:{
      tok = jj_consume_token(ASSIGN_SIGNED_RSHIFT);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_BIT_AND:{
      tok = jj_consume_token(ASSIGN_BIT_AND);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_BIT_XOR:{
      tok = jj_consume_token(ASSIGN_BIT_XOR);
{if ("" != null) return tok;}
      break;
      }
    case ASSIGN_BIT_OR:{
      tok = jj_consume_token(ASSIGN_BIT_OR);
{if ("" != null) return tok;}
      break;
      }
    default:
      jj_la1[34] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstNode assignment() throws ParseException {Token op;
    AstNode l, r;
    l = logicalOr();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ASSIGN_MUL:
    case ASSIGN_DIV:
    case ASSIGN_MOD:
    case ASSIGN_PLS:
    case ASSIGN_MNS:
    case ASSIGN_LSHIFT:
    case ASSIGN_RSHIFT:
    case ASSIGN_SIGNED_RSHIFT:
    case ASSIGN_BIT_AND:
    case ASSIGN_BIT_XOR:
    case ASSIGN_BIT_OR:
    case ASSIGN:{
      op = assignmentOp();
      r = logicalOr();
AstApply apply = new AstApply();
            apply.args.add(new AstName(op.image));
            apply.args.add(l);
            apply.args.add(r);
            l = apply;
      break;
      }
    default:
      jj_la1[35] = jj_gen;
      ;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode expression() throws ParseException {AstNode n;
    n = assignment();
{if ("" != null) return n;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode stmtIf() throws ParseException {AstIf stmt = new AstIf();
    AstNode c, b;
    jj_consume_token(IF);
    c = expression();
stmt.cond.add(c);
    b = block();
stmt.thenBlock.add(b);
    label_18:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ELIF:{
        ;
        break;
        }
      default:
        jj_la1[36] = jj_gen;
        break label_18;
      }
      jj_consume_token(ELIF);
      c = expression();
stmt.cond.add(c);
      b = block();
stmt.thenBlock.add(b);
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      stmt.elseBlock = block();
      break;
      }
    default:
      jj_la1[37] = jj_gen;
      ;
    }
{if ("" != null) return stmt;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode statement() throws ParseException {AstNode n;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:
    case TILDE:
    case EXC:
    case PLS:
    case MNS:
    case OCT:
    case DEC:
    case HEX:
    case NAME:{
      n = expression();
      jj_consume_token(SEMICOLON);
{if ("" != null) return n;}
      break;
      }
    case IF:{
      n = stmtIf();
{if ("" != null) return n;}
      break;
      }
    default:
      jj_la1[38] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AstType baseType() throws ParseException {AstType t = new AstType(), arg;
    t.name = qualifiedName();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:
    case LBRACKET:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LPAREN:{
        jj_consume_token(LPAREN);
        arg = type();
t.args.add(arg);
        label_19:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[39] = jj_gen;
            break label_19;
          }
          jj_consume_token(COMMA);
          arg = type();
t.args.add(arg);
        }
        jj_consume_token(RPAREN);
        break;
        }
      case LBRACKET:{
        label_20:
        while (true) {
          jj_consume_token(LBRACKET);
          jj_consume_token(RBRACKET);
AstType arrayType = new AstType("Array");
                arrayType.args.add(t);
                t = arrayType;
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case LBRACKET:{
            ;
            break;
            }
          default:
            jj_la1[40] = jj_gen;
            break label_20;
          }
        }
        break;
        }
      default:
        jj_la1[41] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[42] = jj_gen;
      ;
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
  }

  final public AstType type() throws ParseException {AstType t, s, functionType = null;
    t = baseType();
    label_21:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ARROW:{
        ;
        break;
        }
      default:
        jj_la1[43] = jj_gen;
        break label_21;
      }
      jj_consume_token(ARROW);
      s = baseType();
if (functionType == null) {
                functionType = new AstType("Fn");
                functionType.args.add(t);
                t = functionType;
            }
            functionType.args.add(s);
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[44];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x100,0x1000,0x2000,0x0,0x10000,0x4000,0x4000,0x1810200,0x10000,0x111000,0x2000,0x1810000,0x2000,0x1810000,0x111000,0x4000,0x800000,0x800000,0x54000000,0x54000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x400,0x400,0x1000000,0x0,0x0,0xaa000000,0xaa000000,0x40,0x80,0x1810200,0x2000,0x100000,0x110000,0x110000,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x10000000,0x0,0x0,0x0,0x1e000005,0x1e000000,0x0,0x0,0x1e000005,0x0,0x1e000005,0x0,0x0,0x5,0x5,0x0,0x0,0x5,0x5,0xd0,0xd0,0x80000,0x200000,0x800000,0xf000,0xf000,0xc00,0xc00,0x0,0x10000,0x20000,0x154012a,0x154012a,0x0,0x0,0x1e000005,0x0,0x0,0x0,0x0,0x200,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 44; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[61];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 44; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 61; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
