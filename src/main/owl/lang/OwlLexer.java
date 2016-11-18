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
		ELIF=1, ELSE=2, FN=3, IF=4, IS=5, NEW=6, DOT=7, COMMA=8, COLON=9, SEMICOLON=10, 
		ARROW=11, LPAREN=12, RPAREN=13, LCURLY=14, RCURLY=15, LBRACKET=16, RBRACKET=17, 
		QUOTE=18, DBL_QUOTE=19, AT=20, TILDE=21, EXC=22, MUL=23, DIV=24, MOD=25, 
		PLS=26, MNS=27, LSHIFT=28, RSHIFT=29, SIGNED_RSHIFT=30, BIT_AND=31, BIT_XOR=32, 
		BIT_OR=33, EQ=34, NE=35, LE=36, LT=37, GE=38, GT=39, AND=40, OR=41, ASSIGN=42, 
		ASSIGN_MUL=43, ASSIGN_DIV=44, ASSIGN_MOD=45, ASSIGN_PLS=46, ASSIGN_MNS=47, 
		ASSIGN_LSHIFT=48, ASSIGN_RSHIFT=49, ASSIGN_SIGNED_RSHIFT=50, ASSIGN_BIT_AND=51, 
		ASSIGN_BIT_XOR=52, ASSIGN_BIT_OR=53, OCT=54, DEC=55, HEX=56, NAME=57, 
		COMMENT=58, WS=59;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ELIF", "ELSE", "FN", "IF", "IS", "NEW", "DOT", "COMMA", "COLON", "SEMICOLON", 
		"ARROW", "LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", 
		"QUOTE", "DBL_QUOTE", "AT", "TILDE", "EXC", "MUL", "DIV", "MOD", "PLS", 
		"MNS", "LSHIFT", "RSHIFT", "SIGNED_RSHIFT", "BIT_AND", "BIT_XOR", "BIT_OR", 
		"EQ", "NE", "LE", "LT", "GE", "GT", "AND", "OR", "ASSIGN", "ASSIGN_MUL", 
		"ASSIGN_DIV", "ASSIGN_MOD", "ASSIGN_PLS", "ASSIGN_MNS", "ASSIGN_LSHIFT", 
		"ASSIGN_RSHIFT", "ASSIGN_SIGNED_RSHIFT", "ASSIGN_BIT_AND", "ASSIGN_BIT_XOR", 
		"ASSIGN_BIT_OR", "OCT", "DEC", "HEX", "NAME", "COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'elif'", "'else'", "'fn'", "'if'", "'is'", "'new'", "'.'", "','", 
		"':'", "';'", "'=>'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'''", 
		"'\"'", "'@'", "'~'", "'!'", "'*'", "'/'", "'%'", "'+'", "'-'", "'<<'", 
		"'>>'", "'>>>'", "'&'", "'^'", "'|'", "'=='", "'!='", "'<='", "'<'", "'>='", 
		"'>'", "'&&'", "'||'", "'='", "'*='", "'/='", "'%='", "'+='", "'-='", 
		"'<<='", "'>>='", "'>>>='", "'&='", "'^='", "'|='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ELIF", "ELSE", "FN", "IF", "IS", "NEW", "DOT", "COMMA", "COLON", 
		"SEMICOLON", "ARROW", "LPAREN", "RPAREN", "LCURLY", "RCURLY", "LBRACKET", 
		"RBRACKET", "QUOTE", "DBL_QUOTE", "AT", "TILDE", "EXC", "MUL", "DIV", 
		"MOD", "PLS", "MNS", "LSHIFT", "RSHIFT", "SIGNED_RSHIFT", "BIT_AND", "BIT_XOR", 
		"BIT_OR", "EQ", "NE", "LE", "LT", "GE", "GT", "AND", "OR", "ASSIGN", "ASSIGN_MUL", 
		"ASSIGN_DIV", "ASSIGN_MOD", "ASSIGN_PLS", "ASSIGN_MNS", "ASSIGN_LSHIFT", 
		"ASSIGN_RSHIFT", "ASSIGN_SIGNED_RSHIFT", "ASSIGN_BIT_AND", "ASSIGN_BIT_XOR", 
		"ASSIGN_BIT_OR", "OCT", "DEC", "HEX", "NAME", "COMMENT", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2=\u0134\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3!"+
		"\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3"+
		")\3*\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3"+
		"\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3"+
		"\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\3\67\6\67\u010d"+
		"\n\67\r\67\16\67\u010e\38\68\u0112\n8\r8\168\u0113\39\39\39\39\69\u011a"+
		"\n9\r9\169\u011b\3:\3:\7:\u0120\n:\f:\16:\u0123\13:\3;\3;\7;\u0127\n;"+
		"\f;\16;\u012a\13;\3;\3;\3<\6<\u012f\n<\r<\16<\u0130\3<\3<\2\2=\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w"+
		"=\3\2\t\3\2\629\3\2\62;\5\2\62;CHch\5\2C\\aac|\6\2\62;C\\aac|\3\2\f\f"+
		"\5\2\13\f\16\17\"\"\u0139\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]"+
		"\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2"+
		"\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2"+
		"\2w\3\2\2\2\3y\3\2\2\2\5~\3\2\2\2\7\u0083\3\2\2\2\t\u0086\3\2\2\2\13\u0089"+
		"\3\2\2\2\r\u008c\3\2\2\2\17\u0090\3\2\2\2\21\u0092\3\2\2\2\23\u0094\3"+
		"\2\2\2\25\u0096\3\2\2\2\27\u0098\3\2\2\2\31\u009b\3\2\2\2\33\u009d\3\2"+
		"\2\2\35\u009f\3\2\2\2\37\u00a1\3\2\2\2!\u00a3\3\2\2\2#\u00a5\3\2\2\2%"+
		"\u00a7\3\2\2\2\'\u00a9\3\2\2\2)\u00ab\3\2\2\2+\u00ad\3\2\2\2-\u00af\3"+
		"\2\2\2/\u00b1\3\2\2\2\61\u00b3\3\2\2\2\63\u00b5\3\2\2\2\65\u00b7\3\2\2"+
		"\2\67\u00b9\3\2\2\29\u00bb\3\2\2\2;\u00be\3\2\2\2=\u00c1\3\2\2\2?\u00c5"+
		"\3\2\2\2A\u00c7\3\2\2\2C\u00c9\3\2\2\2E\u00cb\3\2\2\2G\u00ce\3\2\2\2I"+
		"\u00d1\3\2\2\2K\u00d4\3\2\2\2M\u00d6\3\2\2\2O\u00d9\3\2\2\2Q\u00db\3\2"+
		"\2\2S\u00de\3\2\2\2U\u00e1\3\2\2\2W\u00e3\3\2\2\2Y\u00e6\3\2\2\2[\u00e9"+
		"\3\2\2\2]\u00ec\3\2\2\2_\u00ef\3\2\2\2a\u00f2\3\2\2\2c\u00f6\3\2\2\2e"+
		"\u00fa\3\2\2\2g\u00ff\3\2\2\2i\u0102\3\2\2\2k\u0105\3\2\2\2m\u0108\3\2"+
		"\2\2o\u0111\3\2\2\2q\u0115\3\2\2\2s\u011d\3\2\2\2u\u0124\3\2\2\2w\u012e"+
		"\3\2\2\2yz\7g\2\2z{\7n\2\2{|\7k\2\2|}\7h\2\2}\4\3\2\2\2~\177\7g\2\2\177"+
		"\u0080\7n\2\2\u0080\u0081\7u\2\2\u0081\u0082\7g\2\2\u0082\6\3\2\2\2\u0083"+
		"\u0084\7h\2\2\u0084\u0085\7p\2\2\u0085\b\3\2\2\2\u0086\u0087\7k\2\2\u0087"+
		"\u0088\7h\2\2\u0088\n\3\2\2\2\u0089\u008a\7k\2\2\u008a\u008b\7u\2\2\u008b"+
		"\f\3\2\2\2\u008c\u008d\7p\2\2\u008d\u008e\7g\2\2\u008e\u008f\7y\2\2\u008f"+
		"\16\3\2\2\2\u0090\u0091\7\60\2\2\u0091\20\3\2\2\2\u0092\u0093\7.\2\2\u0093"+
		"\22\3\2\2\2\u0094\u0095\7<\2\2\u0095\24\3\2\2\2\u0096\u0097\7=\2\2\u0097"+
		"\26\3\2\2\2\u0098\u0099\7?\2\2\u0099\u009a\7@\2\2\u009a\30\3\2\2\2\u009b"+
		"\u009c\7*\2\2\u009c\32\3\2\2\2\u009d\u009e\7+\2\2\u009e\34\3\2\2\2\u009f"+
		"\u00a0\7}\2\2\u00a0\36\3\2\2\2\u00a1\u00a2\7\177\2\2\u00a2 \3\2\2\2\u00a3"+
		"\u00a4\7]\2\2\u00a4\"\3\2\2\2\u00a5\u00a6\7_\2\2\u00a6$\3\2\2\2\u00a7"+
		"\u00a8\7)\2\2\u00a8&\3\2\2\2\u00a9\u00aa\7$\2\2\u00aa(\3\2\2\2\u00ab\u00ac"+
		"\7B\2\2\u00ac*\3\2\2\2\u00ad\u00ae\7\u0080\2\2\u00ae,\3\2\2\2\u00af\u00b0"+
		"\7#\2\2\u00b0.\3\2\2\2\u00b1\u00b2\7,\2\2\u00b2\60\3\2\2\2\u00b3\u00b4"+
		"\7\61\2\2\u00b4\62\3\2\2\2\u00b5\u00b6\7\'\2\2\u00b6\64\3\2\2\2\u00b7"+
		"\u00b8\7-\2\2\u00b8\66\3\2\2\2\u00b9\u00ba\7/\2\2\u00ba8\3\2\2\2\u00bb"+
		"\u00bc\7>\2\2\u00bc\u00bd\7>\2\2\u00bd:\3\2\2\2\u00be\u00bf\7@\2\2\u00bf"+
		"\u00c0\7@\2\2\u00c0<\3\2\2\2\u00c1\u00c2\7@\2\2\u00c2\u00c3\7@\2\2\u00c3"+
		"\u00c4\7@\2\2\u00c4>\3\2\2\2\u00c5\u00c6\7(\2\2\u00c6@\3\2\2\2\u00c7\u00c8"+
		"\7`\2\2\u00c8B\3\2\2\2\u00c9\u00ca\7~\2\2\u00caD\3\2\2\2\u00cb\u00cc\7"+
		"?\2\2\u00cc\u00cd\7?\2\2\u00cdF\3\2\2\2\u00ce\u00cf\7#\2\2\u00cf\u00d0"+
		"\7?\2\2\u00d0H\3\2\2\2\u00d1\u00d2\7>\2\2\u00d2\u00d3\7?\2\2\u00d3J\3"+
		"\2\2\2\u00d4\u00d5\7>\2\2\u00d5L\3\2\2\2\u00d6\u00d7\7@\2\2\u00d7\u00d8"+
		"\7?\2\2\u00d8N\3\2\2\2\u00d9\u00da\7@\2\2\u00daP\3\2\2\2\u00db\u00dc\7"+
		"(\2\2\u00dc\u00dd\7(\2\2\u00ddR\3\2\2\2\u00de\u00df\7~\2\2\u00df\u00e0"+
		"\7~\2\2\u00e0T\3\2\2\2\u00e1\u00e2\7?\2\2\u00e2V\3\2\2\2\u00e3\u00e4\7"+
		",\2\2\u00e4\u00e5\7?\2\2\u00e5X\3\2\2\2\u00e6\u00e7\7\61\2\2\u00e7\u00e8"+
		"\7?\2\2\u00e8Z\3\2\2\2\u00e9\u00ea\7\'\2\2\u00ea\u00eb\7?\2\2\u00eb\\"+
		"\3\2\2\2\u00ec\u00ed\7-\2\2\u00ed\u00ee\7?\2\2\u00ee^\3\2\2\2\u00ef\u00f0"+
		"\7/\2\2\u00f0\u00f1\7?\2\2\u00f1`\3\2\2\2\u00f2\u00f3\7>\2\2\u00f3\u00f4"+
		"\7>\2\2\u00f4\u00f5\7?\2\2\u00f5b\3\2\2\2\u00f6\u00f7\7@\2\2\u00f7\u00f8"+
		"\7@\2\2\u00f8\u00f9\7?\2\2\u00f9d\3\2\2\2\u00fa\u00fb\7@\2\2\u00fb\u00fc"+
		"\7@\2\2\u00fc\u00fd\7@\2\2\u00fd\u00fe\7?\2\2\u00fef\3\2\2\2\u00ff\u0100"+
		"\7(\2\2\u0100\u0101\7?\2\2\u0101h\3\2\2\2\u0102\u0103\7`\2\2\u0103\u0104"+
		"\7?\2\2\u0104j\3\2\2\2\u0105\u0106\7~\2\2\u0106\u0107\7?\2\2\u0107l\3"+
		"\2\2\2\u0108\u0109\7\62\2\2\u0109\u010a\7q\2\2\u010a\u010c\3\2\2\2\u010b"+
		"\u010d\t\2\2\2\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c\3\2"+
		"\2\2\u010e\u010f\3\2\2\2\u010fn\3\2\2\2\u0110\u0112\t\3\2\2\u0111\u0110"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"p\3\2\2\2\u0115\u0116\7\62\2\2\u0116\u0117\7z\2\2\u0117\u0119\3\2\2\2"+
		"\u0118\u011a\t\4\2\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119"+
		"\3\2\2\2\u011b\u011c\3\2\2\2\u011cr\3\2\2\2\u011d\u0121\t\5\2\2\u011e"+
		"\u0120\t\6\2\2\u011f\u011e\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122t\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0128"+
		"\7%\2\2\u0125\u0127\n\7\2\2\u0126\u0125\3\2\2\2\u0127\u012a\3\2\2\2\u0128"+
		"\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012b\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012b\u012c\b;\2\2\u012cv\3\2\2\2\u012d\u012f\t\b\2\2\u012e\u012d"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0133\b<\3\2\u0133x\3\2\2\2\t\2\u010e\u0113\u011b"+
		"\u0121\u0128\u0130\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}