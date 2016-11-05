/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package owl.lang;

import java.util.ArrayList;
import java.util.List;

public class Parser implements ParserConstants {

  final public AstModule module() throws ParseException {AstModule module = new AstModule();
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
module.addFunction(f);
    }
{if ("" != null) return module;}
    throw new Error("Missing return statement in function");
  }

  final public AstName qualifiedName() throws ParseException {AstName name = new AstName();
    Token tok;
    tok = jj_consume_token(NAME);
name.add(tok.image);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 8:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(8);
      tok = jj_consume_token(NAME);
name.add(tok.image);
    }
{if ("" != null) return name;}
    throw new Error("Missing return statement in function");
  }

  final public AstFunction function() throws ParseException {AstFunction function = new AstFunction();
    jj_consume_token(FN);
    function.name = jj_consume_token(NAME).image;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 13:{
      jj_consume_token(13);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        function.arguments = variableList();
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        ;
      }
      jj_consume_token(14);
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 11:{
      jj_consume_token(11);
      function.returnType = type();
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    function.block = block();
{if ("" != null) return function;}
    throw new Error("Missing return statement in function");
  }

  final public List<AstVariable> variableList() throws ParseException {List<AstVariable> argList = new ArrayList<AstVariable>();
    AstVariable a;
    a = variable();
argList.add(a);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 9:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      jj_consume_token(9);
      a = variable();
argList.add(a);
    }
{if ("" != null) return argList;}
    throw new Error("Missing return statement in function");
  }

  final public AstVariable variable() throws ParseException {AstVariable variable = new AstVariable();
    Token tok;
    tok = jj_consume_token(NAME);
variable.name = tok.image;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 11:{
      jj_consume_token(11);
      variable.type = type();
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
{if ("" != null) return variable;}
    throw new Error("Missing return statement in function");
  }

  final public AstList block() throws ParseException {AstList block = new AstList();
    AstNode s;
    jj_consume_token(15);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 12:
      case NAME:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NAME:{
        s = statement();
block.nodes.add(s);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      jj_consume_token(12);
    }
    jj_consume_token(16);
{if ("" != null) return block;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode expression() throws ParseException {AstNode first, arg;
    AstInvoke invoke = null;
    first = qualifiedName();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 13:
    case 17:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 13:{
        jj_consume_token(13);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case NAME:{
          arg = expression();
invoke = new AstInvoke();
                invoke.nodes.add(first);
                invoke.nodes.add(arg);
          label_5:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case 9:{
              ;
              break;
              }
            default:
              jj_la1[9] = jj_gen;
              break label_5;
            }
            jj_consume_token(9);
            arg = expression();
invoke.nodes.add(arg);
          }
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          ;
        }
        jj_consume_token(14);
        break;
        }
      case 17:{
        jj_consume_token(17);
        arg = expression();
invoke = new AstInvoke();
            invoke.mapping = true;
            invoke.nodes.add(first);
            invoke.nodes.add(arg);
        jj_consume_token(18);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
{if ("" != null) return invoke == null? first: invoke;}
    throw new Error("Missing return statement in function");
  }

  final public AstNode statement() throws ParseException {AstNode expr;
    expr = expression();
{if ("" != null) return expr;}
    throw new Error("Missing return statement in function");
  }

  final public AstType baseType() throws ParseException {AstType type = new AstType();
    type.name = qualifiedName();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 13:{
      jj_consume_token(13);
      jj_consume_token(14);
      break;
      }
    case 17:{
      jj_consume_token(17);
      jj_consume_token(18);
AstType arrayType = AstType.fromName("Array");
            arrayType.params.add(type);
            type = arrayType;
      break;
      }
    default:
      jj_la1[13] = jj_gen;

    }
{if ("" != null) return type;}
    throw new Error("Missing return statement in function");
  }

  final public AstType type() throws ParseException {AstType type, t, functionType = null;
    type = baseType();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 19:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_6;
      }
      jj_consume_token(19);
      t = baseType();
if (functionType == null) {
                functionType = AstType.fromName("Function");
                type.params.add(type);
            }
            functionType.params.add(t);
    }
{if ("" != null) return functionType != null? functionType: type;}
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
  final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80,0x100,0x800000,0x2000,0x800,0x200,0x800,0x801000,0x800000,0x200,0x800000,0x22000,0x22000,0x22000,0x80000,};
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
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[24];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 15; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 24; i++) {
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
