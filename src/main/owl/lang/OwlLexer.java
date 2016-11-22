// Generated from Owl.g4 by ANTLR 4.5.3

// This file is a prt of the Owl Programming Language.
package owl.lang;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OwlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CASE=1, ELIF=2, ELSE=3, FN=4, IF=5, IS=6, MATCH=7, NEW=8, DOT=9, COMMA=10, 
		COLON=11, SEMICOLON=12, ARROW=13, LPAREN=14, RPAREN=15, LCURLY=16, RCURLY=17, 
		LBRACKET=18, RBRACKET=19, QUOTE=20, DBL_QUOTE=21, AT=22, TILDE=23, EXC=24, 
		MUL=25, DIV=26, MOD=27, PLS=28, MNS=29, LSHIFT=30, RSHIFT=31, SIGNED_RSHIFT=32, 
		BIT_AND=33, BIT_XOR=34, BIT_OR=35, EQ=36, NE=37, LE=38, LT=39, GE=40, 
		GT=41, AND=42, OR=43, ASSIGN=44, ASSIGN_MUL=45, ASSIGN_DIV=46, ASSIGN_MOD=47, 
		ASSIGN_PLS=48, ASSIGN_MNS=49, ASSIGN_LSHIFT=50, ASSIGN_RSHIFT=51, ASSIGN_SIGNED_RSHIFT=52, 
		ASSIGN_BIT_AND=53, ASSIGN_BIT_XOR=54, ASSIGN_BIT_OR=55, OCT=56, DEC=57, 
		HEX=58, NAME=59, COMMENT=60, WS=61;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"CASE", "ELIF", "ELSE", "FN", "IF", "IS", "MATCH", "NEW", "DOT", "COMMA", 
		"COLON", "SEMICOLON", "ARROW", "LPAREN", "RPAREN", "LCURLY", "RCURLY", 
		"LBRACKET", "RBRACKET", "QUOTE", "DBL_QUOTE", "AT", "TILDE", "EXC", "MUL", 
		"DIV", "MOD", "PLS", "MNS", "LSHIFT", "RSHIFT", "SIGNED_RSHIFT", "BIT_AND", 
		"BIT_XOR", "BIT_OR", "EQ", "NE", "LE", "LT", "GE", "GT", "AND", "OR", 
		"ASSIGN", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "ASSIGN_PLS", "ASSIGN_MNS", 
		"ASSIGN_LSHIFT", "ASSIGN_RSHIFT", "ASSIGN_SIGNED_RSHIFT", "ASSIGN_BIT_AND", 
		"ASSIGN_BIT_XOR", "ASSIGN_BIT_OR", "OCT", "DEC", "HEX", "NAME", "COMMENT", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'case'", "'elif'", "'else'", "'fn'", "'if'", "'is'", "'match'", 
		"'new'", "'.'", "','", "':'", "';'", "'=>'", "'('", "')'", "'{'", "'}'", 
		"'['", "']'", "'''", "'\"'", "'@'", "'~'", "'!'", "'*'", "'/'", "'%'", 
		"'+'", "'-'", "'<<'", "'>>'", "'>>>'", "'&'", "'^'", "'|'", "'=='", "'!='", 
		"'<='", "'<'", "'>='", "'>'", "'&&'", "'||'", "'='", "'*='", "'/='", "'%='", 
		"'+='", "'-='", "'<<='", "'>>='", "'>>>='", "'&='", "'^='", "'|='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CASE", "ELIF", "ELSE", "FN", "IF", "IS", "MATCH", "NEW", "DOT", 
		"COMMA", "COLON", "SEMICOLON", "ARROW", "LPAREN", "RPAREN", "LCURLY", 
		"RCURLY", "LBRACKET", "RBRACKET", "QUOTE", "DBL_QUOTE", "AT", "TILDE", 
		"EXC", "MUL", "DIV", "MOD", "PLS", "MNS", "LSHIFT", "RSHIFT", "SIGNED_RSHIFT", 
		"BIT_AND", "BIT_XOR", "BIT_OR", "EQ", "NE", "LE", "LT", "GE", "GT", "AND", 
		"OR", "ASSIGN", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "ASSIGN_PLS", 
		"ASSIGN_MNS", "ASSIGN_LSHIFT", "ASSIGN_RSHIFT", "ASSIGN_SIGNED_RSHIFT", 
		"ASSIGN_BIT_AND", "ASSIGN_BIT_XOR", "ASSIGN_BIT_OR", "OCT", "DEC", "HEX", 
		"NAME", "COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public OwlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Owl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2?\u0143\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3"+
		"&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3"+
		".\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63"+
		"\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\67"+
		"\3\67\3\67\38\38\38\39\39\39\39\69\u011c\n9\r9\169\u011d\3:\6:\u0121\n"+
		":\r:\16:\u0122\3;\3;\3;\3;\6;\u0129\n;\r;\16;\u012a\3<\3<\7<\u012f\n<"+
		"\f<\16<\u0132\13<\3=\3=\7=\u0136\n=\f=\16=\u0139\13=\3=\3=\3>\6>\u013e"+
		"\n>\r>\16>\u013f\3>\3>\2\2?\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?\3\2\t\3\2\629\3\2\62;\5\2\62;"+
		"CHch\5\2C\\aac|\6\2\62;C\\aac|\3\2\f\f\5\2\13\f\16\17\"\"\u0148\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o"+
		"\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2"+
		"\2\2\3}\3\2\2\2\5\u0082\3\2\2\2\7\u0087\3\2\2\2\t\u008c\3\2\2\2\13\u008f"+
		"\3\2\2\2\r\u0092\3\2\2\2\17\u0095\3\2\2\2\21\u009b\3\2\2\2\23\u009f\3"+
		"\2\2\2\25\u00a1\3\2\2\2\27\u00a3\3\2\2\2\31\u00a5\3\2\2\2\33\u00a7\3\2"+
		"\2\2\35\u00aa\3\2\2\2\37\u00ac\3\2\2\2!\u00ae\3\2\2\2#\u00b0\3\2\2\2%"+
		"\u00b2\3\2\2\2\'\u00b4\3\2\2\2)\u00b6\3\2\2\2+\u00b8\3\2\2\2-\u00ba\3"+
		"\2\2\2/\u00bc\3\2\2\2\61\u00be\3\2\2\2\63\u00c0\3\2\2\2\65\u00c2\3\2\2"+
		"\2\67\u00c4\3\2\2\29\u00c6\3\2\2\2;\u00c8\3\2\2\2=\u00ca\3\2\2\2?\u00cd"+
		"\3\2\2\2A\u00d0\3\2\2\2C\u00d4\3\2\2\2E\u00d6\3\2\2\2G\u00d8\3\2\2\2I"+
		"\u00da\3\2\2\2K\u00dd\3\2\2\2M\u00e0\3\2\2\2O\u00e3\3\2\2\2Q\u00e5\3\2"+
		"\2\2S\u00e8\3\2\2\2U\u00ea\3\2\2\2W\u00ed\3\2\2\2Y\u00f0\3\2\2\2[\u00f2"+
		"\3\2\2\2]\u00f5\3\2\2\2_\u00f8\3\2\2\2a\u00fb\3\2\2\2c\u00fe\3\2\2\2e"+
		"\u0101\3\2\2\2g\u0105\3\2\2\2i\u0109\3\2\2\2k\u010e\3\2\2\2m\u0111\3\2"+
		"\2\2o\u0114\3\2\2\2q\u0117\3\2\2\2s\u0120\3\2\2\2u\u0124\3\2\2\2w\u012c"+
		"\3\2\2\2y\u0133\3\2\2\2{\u013d\3\2\2\2}~\7e\2\2~\177\7c\2\2\177\u0080"+
		"\7u\2\2\u0080\u0081\7g\2\2\u0081\4\3\2\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7n\2\2\u0084\u0085\7k\2\2\u0085\u0086\7h\2\2\u0086\6\3\2\2\2\u0087\u0088"+
		"\7g\2\2\u0088\u0089\7n\2\2\u0089\u008a\7u\2\2\u008a\u008b\7g\2\2\u008b"+
		"\b\3\2\2\2\u008c\u008d\7h\2\2\u008d\u008e\7p\2\2\u008e\n\3\2\2\2\u008f"+
		"\u0090\7k\2\2\u0090\u0091\7h\2\2\u0091\f\3\2\2\2\u0092\u0093\7k\2\2\u0093"+
		"\u0094\7u\2\2\u0094\16\3\2\2\2\u0095\u0096\7o\2\2\u0096\u0097\7c\2\2\u0097"+
		"\u0098\7v\2\2\u0098\u0099\7e\2\2\u0099\u009a\7j\2\2\u009a\20\3\2\2\2\u009b"+
		"\u009c\7p\2\2\u009c\u009d\7g\2\2\u009d\u009e\7y\2\2\u009e\22\3\2\2\2\u009f"+
		"\u00a0\7\60\2\2\u00a0\24\3\2\2\2\u00a1\u00a2\7.\2\2\u00a2\26\3\2\2\2\u00a3"+
		"\u00a4\7<\2\2\u00a4\30\3\2\2\2\u00a5\u00a6\7=\2\2\u00a6\32\3\2\2\2\u00a7"+
		"\u00a8\7?\2\2\u00a8\u00a9\7@\2\2\u00a9\34\3\2\2\2\u00aa\u00ab\7*\2\2\u00ab"+
		"\36\3\2\2\2\u00ac\u00ad\7+\2\2\u00ad \3\2\2\2\u00ae\u00af\7}\2\2\u00af"+
		"\"\3\2\2\2\u00b0\u00b1\7\177\2\2\u00b1$\3\2\2\2\u00b2\u00b3\7]\2\2\u00b3"+
		"&\3\2\2\2\u00b4\u00b5\7_\2\2\u00b5(\3\2\2\2\u00b6\u00b7\7)\2\2\u00b7*"+
		"\3\2\2\2\u00b8\u00b9\7$\2\2\u00b9,\3\2\2\2\u00ba\u00bb\7B\2\2\u00bb.\3"+
		"\2\2\2\u00bc\u00bd\7\u0080\2\2\u00bd\60\3\2\2\2\u00be\u00bf\7#\2\2\u00bf"+
		"\62\3\2\2\2\u00c0\u00c1\7,\2\2\u00c1\64\3\2\2\2\u00c2\u00c3\7\61\2\2\u00c3"+
		"\66\3\2\2\2\u00c4\u00c5\7\'\2\2\u00c58\3\2\2\2\u00c6\u00c7\7-\2\2\u00c7"+
		":\3\2\2\2\u00c8\u00c9\7/\2\2\u00c9<\3\2\2\2\u00ca\u00cb\7>\2\2\u00cb\u00cc"+
		"\7>\2\2\u00cc>\3\2\2\2\u00cd\u00ce\7@\2\2\u00ce\u00cf\7@\2\2\u00cf@\3"+
		"\2\2\2\u00d0\u00d1\7@\2\2\u00d1\u00d2\7@\2\2\u00d2\u00d3\7@\2\2\u00d3"+
		"B\3\2\2\2\u00d4\u00d5\7(\2\2\u00d5D\3\2\2\2\u00d6\u00d7\7`\2\2\u00d7F"+
		"\3\2\2\2\u00d8\u00d9\7~\2\2\u00d9H\3\2\2\2\u00da\u00db\7?\2\2\u00db\u00dc"+
		"\7?\2\2\u00dcJ\3\2\2\2\u00dd\u00de\7#\2\2\u00de\u00df\7?\2\2\u00dfL\3"+
		"\2\2\2\u00e0\u00e1\7>\2\2\u00e1\u00e2\7?\2\2\u00e2N\3\2\2\2\u00e3\u00e4"+
		"\7>\2\2\u00e4P\3\2\2\2\u00e5\u00e6\7@\2\2\u00e6\u00e7\7?\2\2\u00e7R\3"+
		"\2\2\2\u00e8\u00e9\7@\2\2\u00e9T\3\2\2\2\u00ea\u00eb\7(\2\2\u00eb\u00ec"+
		"\7(\2\2\u00ecV\3\2\2\2\u00ed\u00ee\7~\2\2\u00ee\u00ef\7~\2\2\u00efX\3"+
		"\2\2\2\u00f0\u00f1\7?\2\2\u00f1Z\3\2\2\2\u00f2\u00f3\7,\2\2\u00f3\u00f4"+
		"\7?\2\2\u00f4\\\3\2\2\2\u00f5\u00f6\7\61\2\2\u00f6\u00f7\7?\2\2\u00f7"+
		"^\3\2\2\2\u00f8\u00f9\7\'\2\2\u00f9\u00fa\7?\2\2\u00fa`\3\2\2\2\u00fb"+
		"\u00fc\7-\2\2\u00fc\u00fd\7?\2\2\u00fdb\3\2\2\2\u00fe\u00ff\7/\2\2\u00ff"+
		"\u0100\7?\2\2\u0100d\3\2\2\2\u0101\u0102\7>\2\2\u0102\u0103\7>\2\2\u0103"+
		"\u0104\7?\2\2\u0104f\3\2\2\2\u0105\u0106\7@\2\2\u0106\u0107\7@\2\2\u0107"+
		"\u0108\7?\2\2\u0108h\3\2\2\2\u0109\u010a\7@\2\2\u010a\u010b\7@\2\2\u010b"+
		"\u010c\7@\2\2\u010c\u010d\7?\2\2\u010dj\3\2\2\2\u010e\u010f\7(\2\2\u010f"+
		"\u0110\7?\2\2\u0110l\3\2\2\2\u0111\u0112\7`\2\2\u0112\u0113\7?\2\2\u0113"+
		"n\3\2\2\2\u0114\u0115\7~\2\2\u0115\u0116\7?\2\2\u0116p\3\2\2\2\u0117\u0118"+
		"\7\62\2\2\u0118\u0119\7q\2\2\u0119\u011b\3\2\2\2\u011a\u011c\t\2\2\2\u011b"+
		"\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011er\3\2\2\2\u011f\u0121\t\3\2\2\u0120\u011f\3\2\2\2\u0121\u0122"+
		"\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123t\3\2\2\2\u0124"+
		"\u0125\7\62\2\2\u0125\u0126\7z\2\2\u0126\u0128\3\2\2\2\u0127\u0129\t\4"+
		"\2\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012bv\3\2\2\2\u012c\u0130\t\5\2\2\u012d\u012f\t\6\2\2"+
		"\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131"+
		"\3\2\2\2\u0131x\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0137\7%\2\2\u0134\u0136"+
		"\n\7\2\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u013a\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u013b\b="+
		"\2\2\u013bz\3\2\2\2\u013c\u013e\t\b\2\2\u013d\u013c\3\2\2\2\u013e\u013f"+
		"\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u0142\b>\3\2\u0142|\3\2\2\2\t\2\u011d\u0122\u012a\u0130\u0137\u013f\4"+
		"\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}