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
		CASE=1, ELIF=2, ELSE=3, FN=4, IF=5, IS=6, MATCH=7, NEW=8, RETURN=9, DOT=10, 
		COMMA=11, COLON=12, SEMICOLON=13, ARROW=14, LPAREN=15, RPAREN=16, LCURLY=17, 
		RCURLY=18, LBRACKET=19, RBRACKET=20, QUOTE=21, DBL_QUOTE=22, AT=23, TILDE=24, 
		EXC=25, MUL=26, DIV=27, MOD=28, PLS=29, MNS=30, LSHIFT=31, RSHIFT=32, 
		SIGNED_RSHIFT=33, BIT_AND=34, BIT_XOR=35, BIT_OR=36, EQ=37, NE=38, LE=39, 
		LT=40, GE=41, GT=42, AND=43, OR=44, ASSIGN=45, ASSIGN_MUL=46, ASSIGN_DIV=47, 
		ASSIGN_MOD=48, ASSIGN_PLS=49, ASSIGN_MNS=50, ASSIGN_LSHIFT=51, ASSIGN_RSHIFT=52, 
		ASSIGN_SIGNED_RSHIFT=53, ASSIGN_BIT_AND=54, ASSIGN_BIT_XOR=55, ASSIGN_BIT_OR=56, 
		OCT=57, DEC=58, HEX=59, NAME=60, STRING=61, COMMENT=62, WS=63;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"CASE", "ELIF", "ELSE", "FN", "IF", "IS", "MATCH", "NEW", "RETURN", "DOT", 
		"COMMA", "COLON", "SEMICOLON", "ARROW", "LPAREN", "RPAREN", "LCURLY", 
		"RCURLY", "LBRACKET", "RBRACKET", "QUOTE", "DBL_QUOTE", "AT", "TILDE", 
		"EXC", "MUL", "DIV", "MOD", "PLS", "MNS", "LSHIFT", "RSHIFT", "SIGNED_RSHIFT", 
		"BIT_AND", "BIT_XOR", "BIT_OR", "EQ", "NE", "LE", "LT", "GE", "GT", "AND", 
		"OR", "ASSIGN", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "ASSIGN_PLS", 
		"ASSIGN_MNS", "ASSIGN_LSHIFT", "ASSIGN_RSHIFT", "ASSIGN_SIGNED_RSHIFT", 
		"ASSIGN_BIT_AND", "ASSIGN_BIT_XOR", "ASSIGN_BIT_OR", "OCT", "DEC", "HEX", 
		"NAME", "STRING", "COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'case'", "'elif'", "'else'", "'fn'", "'if'", "'is'", "'match'", 
		"'new'", "'return'", "'.'", "','", "':'", "';'", "'=>'", "'('", "')'", 
		"'{'", "'}'", "'['", "']'", "'''", "'\"'", "'@'", "'~'", "'!'", "'*'", 
		"'/'", "'%'", "'+'", "'-'", "'<<'", "'>>'", "'>>>'", "'&'", "'^'", "'|'", 
		"'=='", "'!='", "'<='", "'<'", "'>='", "'>'", "'&&'", "'||'", "'='", "'*='", 
		"'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", "'>>>='", "'&='", "'^='", 
		"'|='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CASE", "ELIF", "ELSE", "FN", "IF", "IS", "MATCH", "NEW", "RETURN", 
		"DOT", "COMMA", "COLON", "SEMICOLON", "ARROW", "LPAREN", "RPAREN", "LCURLY", 
		"RCURLY", "LBRACKET", "RBRACKET", "QUOTE", "DBL_QUOTE", "AT", "TILDE", 
		"EXC", "MUL", "DIV", "MOD", "PLS", "MNS", "LSHIFT", "RSHIFT", "SIGNED_RSHIFT", 
		"BIT_AND", "BIT_XOR", "BIT_OR", "EQ", "NE", "LE", "LT", "GE", "GT", "AND", 
		"OR", "ASSIGN", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "ASSIGN_PLS", 
		"ASSIGN_MNS", "ASSIGN_LSHIFT", "ASSIGN_RSHIFT", "ASSIGN_SIGNED_RSHIFT", 
		"ASSIGN_BIT_AND", "ASSIGN_BIT_XOR", "ASSIGN_BIT_OR", "OCT", "DEC", "HEX", 
		"NAME", "STRING", "COMMENT", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2A\u0157\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3"+
		"*\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61"+
		"\3\62\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65"+
		"\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\38\38\38\39\39\39\3:\3:\3:\3"+
		":\6:\u0127\n:\r:\16:\u0128\3;\6;\u012c\n;\r;\16;\u012d\3<\3<\3<\3<\6<"+
		"\u0134\n<\r<\16<\u0135\3=\3=\7=\u013a\n=\f=\16=\u013d\13=\3>\3>\7>\u0141"+
		"\n>\f>\16>\u0144\13>\3>\3>\3?\3?\7?\u014a\n?\f?\16?\u014d\13?\3?\3?\3"+
		"@\6@\u0152\n@\r@\16@\u0153\3@\3@\2\2A\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\3\2\n\3\2\62"+
		"9\3\2\62;\5\2\62;CHch\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\16\17$$\3\2\f"+
		"\f\5\2\13\f\16\17\"\"\u015d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
		"\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C"+
		"\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2"+
		"\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2"+
		"\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i"+
		"\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2"+
		"\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\3\u0081"+
		"\3\2\2\2\5\u0086\3\2\2\2\7\u008b\3\2\2\2\t\u0090\3\2\2\2\13\u0093\3\2"+
		"\2\2\r\u0096\3\2\2\2\17\u0099\3\2\2\2\21\u009f\3\2\2\2\23\u00a3\3\2\2"+
		"\2\25\u00aa\3\2\2\2\27\u00ac\3\2\2\2\31\u00ae\3\2\2\2\33\u00b0\3\2\2\2"+
		"\35\u00b2\3\2\2\2\37\u00b5\3\2\2\2!\u00b7\3\2\2\2#\u00b9\3\2\2\2%\u00bb"+
		"\3\2\2\2\'\u00bd\3\2\2\2)\u00bf\3\2\2\2+\u00c1\3\2\2\2-\u00c3\3\2\2\2"+
		"/\u00c5\3\2\2\2\61\u00c7\3\2\2\2\63\u00c9\3\2\2\2\65\u00cb\3\2\2\2\67"+
		"\u00cd\3\2\2\29\u00cf\3\2\2\2;\u00d1\3\2\2\2=\u00d3\3\2\2\2?\u00d5\3\2"+
		"\2\2A\u00d8\3\2\2\2C\u00db\3\2\2\2E\u00df\3\2\2\2G\u00e1\3\2\2\2I\u00e3"+
		"\3\2\2\2K\u00e5\3\2\2\2M\u00e8\3\2\2\2O\u00eb\3\2\2\2Q\u00ee\3\2\2\2S"+
		"\u00f0\3\2\2\2U\u00f3\3\2\2\2W\u00f5\3\2\2\2Y\u00f8\3\2\2\2[\u00fb\3\2"+
		"\2\2]\u00fd\3\2\2\2_\u0100\3\2\2\2a\u0103\3\2\2\2c\u0106\3\2\2\2e\u0109"+
		"\3\2\2\2g\u010c\3\2\2\2i\u0110\3\2\2\2k\u0114\3\2\2\2m\u0119\3\2\2\2o"+
		"\u011c\3\2\2\2q\u011f\3\2\2\2s\u0122\3\2\2\2u\u012b\3\2\2\2w\u012f\3\2"+
		"\2\2y\u0137\3\2\2\2{\u013e\3\2\2\2}\u0147\3\2\2\2\177\u0151\3\2\2\2\u0081"+
		"\u0082\7e\2\2\u0082\u0083\7c\2\2\u0083\u0084\7u\2\2\u0084\u0085\7g\2\2"+
		"\u0085\4\3\2\2\2\u0086\u0087\7g\2\2\u0087\u0088\7n\2\2\u0088\u0089\7k"+
		"\2\2\u0089\u008a\7h\2\2\u008a\6\3\2\2\2\u008b\u008c\7g\2\2\u008c\u008d"+
		"\7n\2\2\u008d\u008e\7u\2\2\u008e\u008f\7g\2\2\u008f\b\3\2\2\2\u0090\u0091"+
		"\7h\2\2\u0091\u0092\7p\2\2\u0092\n\3\2\2\2\u0093\u0094\7k\2\2\u0094\u0095"+
		"\7h\2\2\u0095\f\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7u\2\2\u0098\16"+
		"\3\2\2\2\u0099\u009a\7o\2\2\u009a\u009b\7c\2\2\u009b\u009c\7v\2\2\u009c"+
		"\u009d\7e\2\2\u009d\u009e\7j\2\2\u009e\20\3\2\2\2\u009f\u00a0\7p\2\2\u00a0"+
		"\u00a1\7g\2\2\u00a1\u00a2\7y\2\2\u00a2\22\3\2\2\2\u00a3\u00a4\7t\2\2\u00a4"+
		"\u00a5\7g\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7w\2\2\u00a7\u00a8\7t\2\2"+
		"\u00a8\u00a9\7p\2\2\u00a9\24\3\2\2\2\u00aa\u00ab\7\60\2\2\u00ab\26\3\2"+
		"\2\2\u00ac\u00ad\7.\2\2\u00ad\30\3\2\2\2\u00ae\u00af\7<\2\2\u00af\32\3"+
		"\2\2\2\u00b0\u00b1\7=\2\2\u00b1\34\3\2\2\2\u00b2\u00b3\7?\2\2\u00b3\u00b4"+
		"\7@\2\2\u00b4\36\3\2\2\2\u00b5\u00b6\7*\2\2\u00b6 \3\2\2\2\u00b7\u00b8"+
		"\7+\2\2\u00b8\"\3\2\2\2\u00b9\u00ba\7}\2\2\u00ba$\3\2\2\2\u00bb\u00bc"+
		"\7\177\2\2\u00bc&\3\2\2\2\u00bd\u00be\7]\2\2\u00be(\3\2\2\2\u00bf\u00c0"+
		"\7_\2\2\u00c0*\3\2\2\2\u00c1\u00c2\7)\2\2\u00c2,\3\2\2\2\u00c3\u00c4\7"+
		"$\2\2\u00c4.\3\2\2\2\u00c5\u00c6\7B\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7"+
		"\u0080\2\2\u00c8\62\3\2\2\2\u00c9\u00ca\7#\2\2\u00ca\64\3\2\2\2\u00cb"+
		"\u00cc\7,\2\2\u00cc\66\3\2\2\2\u00cd\u00ce\7\61\2\2\u00ce8\3\2\2\2\u00cf"+
		"\u00d0\7\'\2\2\u00d0:\3\2\2\2\u00d1\u00d2\7-\2\2\u00d2<\3\2\2\2\u00d3"+
		"\u00d4\7/\2\2\u00d4>\3\2\2\2\u00d5\u00d6\7>\2\2\u00d6\u00d7\7>\2\2\u00d7"+
		"@\3\2\2\2\u00d8\u00d9\7@\2\2\u00d9\u00da\7@\2\2\u00daB\3\2\2\2\u00db\u00dc"+
		"\7@\2\2\u00dc\u00dd\7@\2\2\u00dd\u00de\7@\2\2\u00deD\3\2\2\2\u00df\u00e0"+
		"\7(\2\2\u00e0F\3\2\2\2\u00e1\u00e2\7`\2\2\u00e2H\3\2\2\2\u00e3\u00e4\7"+
		"~\2\2\u00e4J\3\2\2\2\u00e5\u00e6\7?\2\2\u00e6\u00e7\7?\2\2\u00e7L\3\2"+
		"\2\2\u00e8\u00e9\7#\2\2\u00e9\u00ea\7?\2\2\u00eaN\3\2\2\2\u00eb\u00ec"+
		"\7>\2\2\u00ec\u00ed\7?\2\2\u00edP\3\2\2\2\u00ee\u00ef\7>\2\2\u00efR\3"+
		"\2\2\2\u00f0\u00f1\7@\2\2\u00f1\u00f2\7?\2\2\u00f2T\3\2\2\2\u00f3\u00f4"+
		"\7@\2\2\u00f4V\3\2\2\2\u00f5\u00f6\7(\2\2\u00f6\u00f7\7(\2\2\u00f7X\3"+
		"\2\2\2\u00f8\u00f9\7~\2\2\u00f9\u00fa\7~\2\2\u00faZ\3\2\2\2\u00fb\u00fc"+
		"\7?\2\2\u00fc\\\3\2\2\2\u00fd\u00fe\7,\2\2\u00fe\u00ff\7?\2\2\u00ff^\3"+
		"\2\2\2\u0100\u0101\7\61\2\2\u0101\u0102\7?\2\2\u0102`\3\2\2\2\u0103\u0104"+
		"\7\'\2\2\u0104\u0105\7?\2\2\u0105b\3\2\2\2\u0106\u0107\7-\2\2\u0107\u0108"+
		"\7?\2\2\u0108d\3\2\2\2\u0109\u010a\7/\2\2\u010a\u010b\7?\2\2\u010bf\3"+
		"\2\2\2\u010c\u010d\7>\2\2\u010d\u010e\7>\2\2\u010e\u010f\7?\2\2\u010f"+
		"h\3\2\2\2\u0110\u0111\7@\2\2\u0111\u0112\7@\2\2\u0112\u0113\7?\2\2\u0113"+
		"j\3\2\2\2\u0114\u0115\7@\2\2\u0115\u0116\7@\2\2\u0116\u0117\7@\2\2\u0117"+
		"\u0118\7?\2\2\u0118l\3\2\2\2\u0119\u011a\7(\2\2\u011a\u011b\7?\2\2\u011b"+
		"n\3\2\2\2\u011c\u011d\7`\2\2\u011d\u011e\7?\2\2\u011ep\3\2\2\2\u011f\u0120"+
		"\7~\2\2\u0120\u0121\7?\2\2\u0121r\3\2\2\2\u0122\u0123\7\62\2\2\u0123\u0124"+
		"\7q\2\2\u0124\u0126\3\2\2\2\u0125\u0127\t\2\2\2\u0126\u0125\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129t\3\2\2\2"+
		"\u012a\u012c\t\3\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012ev\3\2\2\2\u012f\u0130\7\62\2\2\u0130"+
		"\u0131\7z\2\2\u0131\u0133\3\2\2\2\u0132\u0134\t\4\2\2\u0133\u0132\3\2"+
		"\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"x\3\2\2\2\u0137\u013b\t\5\2\2\u0138\u013a\t\6\2\2\u0139\u0138\3\2\2\2"+
		"\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013cz\3"+
		"\2\2\2\u013d\u013b\3\2\2\2\u013e\u0142\7$\2\2\u013f\u0141\n\7\2\2\u0140"+
		"\u013f\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2"+
		"\2\2\u0143\u0145\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u0146\7$\2\2\u0146"+
		"|\3\2\2\2\u0147\u014b\7%\2\2\u0148\u014a\n\b\2\2\u0149\u0148\3\2\2\2\u014a"+
		"\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014e\3\2"+
		"\2\2\u014d\u014b\3\2\2\2\u014e\u014f\b?\2\2\u014f~\3\2\2\2\u0150\u0152"+
		"\t\t\2\2\u0151\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\b@\3\2\u0156\u0080\3\2"+
		"\2\2\n\2\u0128\u012d\u0135\u013b\u0142\u014b\u0153\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}