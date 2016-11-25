// Generated from Owl.g4 by ANTLR 4.5.3

// This file is a prt of the Owl Programming Language.
package owl.lang;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OwlParser extends Parser {
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
		OCT=57, DEC=58, HEX=59, NAME=60, STR=61, COMMENT=62, WS=63;
	public static final int
		RULE_module = 0, RULE_absoluteName = 1, RULE_function = 2, RULE_argument = 3, 
		RULE_block = 4, RULE_exprPrime = 5, RULE_exprApply = 6, RULE_exprCoerce = 7, 
		RULE_exprUnary = 8, RULE_exprMulDiv = 9, RULE_exprAddSub = 10, RULE_exprShift = 11, 
		RULE_exprBitAnd = 12, RULE_exprBitXor = 13, RULE_exprBitOr = 14, RULE_exprComparison = 15, 
		RULE_exprEquality = 16, RULE_exprNot = 17, RULE_exprAnd = 18, RULE_exprOr = 19, 
		RULE_expression = 20, RULE_stmtIf = 21, RULE_stmtMatch = 22, RULE_stmtReturn = 23, 
		RULE_statement = 24, RULE_typeNonFn = 25, RULE_typeInstance = 26;
	public static final String[] ruleNames = {
		"module", "absoluteName", "function", "argument", "block", "exprPrime", 
		"exprApply", "exprCoerce", "exprUnary", "exprMulDiv", "exprAddSub", "exprShift", 
		"exprBitAnd", "exprBitXor", "exprBitOr", "exprComparison", "exprEquality", 
		"exprNot", "exprAnd", "exprOr", "expression", "stmtIf", "stmtMatch", "stmtReturn", 
		"statement", "typeNonFn", "typeInstance"
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
		"NAME", "STR", "COMMENT", "WS"
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

	@Override
	public String getGrammarFileName() { return "Owl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public OwlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ModuleContext extends ParserRuleContext {
		public AstModule r =  new AstModule();
		public FunctionContext f;
		public Token NAME;
		public ExpressionContext e;
		public List<TerminalNode> NAME() { return getTokens(OwlParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(OwlParser.NAME, i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(OwlParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(OwlParser.ASSIGN, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(OwlParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(OwlParser.SEMICOLON, i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FN || _la==NAME) {
				{
				setState(63);
				switch (_input.LA(1)) {
				case FN:
					{
					setState(54);
					((ModuleContext)_localctx).f = function();
					 _localctx.r.members.add(((ModuleContext)_localctx).f.r); 
					}
					break;
				case NAME:
					{
					setState(57);
					((ModuleContext)_localctx).NAME = match(NAME);
					setState(58);
					match(ASSIGN);
					setState(59);
					((ModuleContext)_localctx).e = expression();
					setState(60);
					match(SEMICOLON);

					            _localctx.r.members.add(new AstVariable((((ModuleContext)_localctx).NAME!=null?((ModuleContext)_localctx).NAME.getText():null), ((ModuleContext)_localctx).e.r));
					        
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsoluteNameContext extends ParserRuleContext {
		public AstName r =  new AstName();
		public Token NAME;
		public List<TerminalNode> NAME() { return getTokens(OwlParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(OwlParser.NAME, i);
		}
		public List<TerminalNode> DOT() { return getTokens(OwlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(OwlParser.DOT, i);
		}
		public AbsoluteNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absoluteName; }
	}

	public final AbsoluteNameContext absoluteName() throws RecognitionException {
		AbsoluteNameContext _localctx = new AbsoluteNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_absoluteName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((AbsoluteNameContext)_localctx).NAME = match(NAME);
			 _localctx.r.name += (((AbsoluteNameContext)_localctx).NAME!=null?((AbsoluteNameContext)_localctx).NAME.getText():null); 
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(70);
				match(DOT);
				setState(71);
				((AbsoluteNameContext)_localctx).NAME = match(NAME);
				 _localctx.r.name += "." + (((AbsoluteNameContext)_localctx).NAME!=null?((AbsoluteNameContext)_localctx).NAME.getText():null); 
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public AstFunction r =  new AstFunction();
		public Token NAME;
		public ArgumentContext a;
		public TypeInstanceContext type;
		public BlockContext b;
		public TerminalNode FN() { return getToken(OwlParser.FN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OwlParser.NAME, 0); }
		public TerminalNode LPAREN() { return getToken(OwlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(OwlParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(OwlParser.COLON, 0); }
		public TypeInstanceContext typeInstance() {
			return getRuleContext(TypeInstanceContext.class,0);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(OwlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OwlParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(FN);
			setState(81);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(79);
				((FunctionContext)_localctx).NAME = match(NAME);
				 _localctx.r.name = (((FunctionContext)_localctx).NAME!=null?((FunctionContext)_localctx).NAME.getText():null); 
				}
			}

			setState(98);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(83);
				match(LPAREN);
				setState(95);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(84);
					((FunctionContext)_localctx).a = argument();
					 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(86);
						match(COMMA);
						setState(87);
						((FunctionContext)_localctx).a = argument();
						 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
						}
						}
						setState(94);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(97);
				match(RPAREN);
				}
			}

			setState(104);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(100);
				match(COLON);
				setState(101);
				((FunctionContext)_localctx).type = typeInstance();
				 _localctx.r.returnType = ((FunctionContext)_localctx).type.r; 
				}
			}

			setState(106);
			((FunctionContext)_localctx).b = block();
			 _localctx.r.block = ((FunctionContext)_localctx).b.r; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public AstArgument r =  new AstArgument();
		public Token NAME;
		public TypeInstanceContext t;
		public TerminalNode NAME() { return getToken(OwlParser.NAME, 0); }
		public TerminalNode COLON() { return getToken(OwlParser.COLON, 0); }
		public TypeInstanceContext typeInstance() {
			return getRuleContext(TypeInstanceContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			((ArgumentContext)_localctx).NAME = match(NAME);
			 _localctx.r.name = (((ArgumentContext)_localctx).NAME!=null?((ArgumentContext)_localctx).NAME.getText():null); 
			setState(115);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(111);
				match(COLON);
				setState(112);
				((ArgumentContext)_localctx).t = typeInstance();
				 _localctx.r.type = ((ArgumentContext)_localctx).t.r; 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public AstBlock r =  new AstBlock();
		public StatementContext s;
		public TerminalNode LCURLY() { return getToken(OwlParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(OwlParser.RCURLY, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(LCURLY);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << MATCH) | (1L << RETURN) | (1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STR))) != 0)) {
				{
				{
				setState(118);
				((BlockContext)_localctx).s = statement();
				 _localctx.r.statements.add(((BlockContext)_localctx).s.r); 
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprPrimeContext extends ParserRuleContext {
		public AstNode r;
		public Token NAME;
		public Token OCT;
		public Token DEC;
		public Token HEX;
		public Token STR;
		public ExpressionContext e;
		public TerminalNode NAME() { return getToken(OwlParser.NAME, 0); }
		public TerminalNode OCT() { return getToken(OwlParser.OCT, 0); }
		public TerminalNode DEC() { return getToken(OwlParser.DEC, 0); }
		public TerminalNode HEX() { return getToken(OwlParser.HEX, 0); }
		public TerminalNode STR() { return getToken(OwlParser.STR, 0); }
		public TerminalNode LPAREN() { return getToken(OwlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(OwlParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprPrimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprPrime; }
	}

	public final ExprPrimeContext exprPrime() throws RecognitionException {
		ExprPrimeContext _localctx = new ExprPrimeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprPrime);
		try {
			setState(143);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				((ExprPrimeContext)_localctx).NAME = match(NAME);
				 ((ExprPrimeContext)_localctx).r =  new AstName((((ExprPrimeContext)_localctx).NAME!=null?((ExprPrimeContext)_localctx).NAME.getText():null)); 
				}
				break;
			case OCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				((ExprPrimeContext)_localctx).OCT = match(OCT);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).OCT!=null?((ExprPrimeContext)_localctx).OCT.getText():null), AstConstant.OCT); 
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				((ExprPrimeContext)_localctx).DEC = match(DEC);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).DEC!=null?((ExprPrimeContext)_localctx).DEC.getText():null), AstConstant.DEC); 
				}
				break;
			case HEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(134);
				((ExprPrimeContext)_localctx).HEX = match(HEX);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).HEX!=null?((ExprPrimeContext)_localctx).HEX.getText():null), AstConstant.HEX); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 5);
				{
				setState(136);
				((ExprPrimeContext)_localctx).STR = match(STR);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).STR!=null?((ExprPrimeContext)_localctx).STR.getText():null), AstConstant.STR); 
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				setState(138);
				match(LPAREN);
				setState(139);
				((ExprPrimeContext)_localctx).e = expression();
				setState(140);
				match(RPAREN);
				 ((ExprPrimeContext)_localctx).r =  ((ExprPrimeContext)_localctx).e.r; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprApplyContext extends ParserRuleContext {
		public AstNode r;
		public ExprPrimeContext x;
		public Token NAME;
		public ExpressionContext a;
		public ExprPrimeContext exprPrime() {
			return getRuleContext(ExprPrimeContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(OwlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(OwlParser.DOT, i);
		}
		public List<TerminalNode> NAME() { return getTokens(OwlParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(OwlParser.NAME, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(OwlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(OwlParser.LPAREN, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(OwlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(OwlParser.RPAREN, i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(OwlParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(OwlParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(OwlParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(OwlParser.RBRACKET, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(OwlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OwlParser.COMMA, i);
		}
		public ExprApplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprApply; }
	}

	public final ExprApplyContext exprApply() throws RecognitionException {
		ExprApplyContext _localctx = new ExprApplyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exprApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			((ExprApplyContext)_localctx).x = exprPrime();
			 ((ExprApplyContext)_localctx).r =  ((ExprApplyContext)_localctx).x.r; 
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOT) | (1L << LPAREN) | (1L << LBRACKET))) != 0)) {
				{
				setState(181);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(147);
					match(DOT);
					setState(148);
					((ExprApplyContext)_localctx).NAME = match(NAME);

					            AstMember m = new AstMember();
					            m.left = _localctx.r;
					            m.name = new AstName((((ExprApplyContext)_localctx).NAME!=null?((ExprApplyContext)_localctx).NAME.getText():null));
					            ((ExprApplyContext)_localctx).r =  m;
					        
					}
					break;
				case LPAREN:
					{

					            AstApply app = new AstApply();
					            app.args.add(_localctx.r);
					            ((ExprApplyContext)_localctx).r =  app;
					        
					setState(151);
					match(LPAREN);
					setState(163);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STR))) != 0)) {
						{
						setState(152);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						setState(160);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(154);
							match(COMMA);
							setState(155);
							((ExprApplyContext)_localctx).a = expression();
							 app.args.add(((ExprApplyContext)_localctx).a.r); 
							}
							}
							setState(162);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(165);
					match(RPAREN);
					}
					break;
				case LBRACKET:
					{

					            AstApply app = new AstApply();
					            app.args.add(new AstName("[]"));
					            app.args.add(_localctx.r);
					            ((ExprApplyContext)_localctx).r =  app;
					        
					setState(167);
					match(LBRACKET);
					setState(168);
					((ExprApplyContext)_localctx).a = expression();
					 app.args.add(((ExprApplyContext)_localctx).a.r); 
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(170);
						match(COMMA);
						setState(171);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						}
						}
						setState(178);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(179);
					match(RBRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprCoerceContext extends ParserRuleContext {
		public AstNode r;
		public ExprApplyContext x;
		public TypeInstanceContext y;
		public ExprApplyContext exprApply() {
			return getRuleContext(ExprApplyContext.class,0);
		}
		public TerminalNode COLON() { return getToken(OwlParser.COLON, 0); }
		public TypeInstanceContext typeInstance() {
			return getRuleContext(TypeInstanceContext.class,0);
		}
		public ExprCoerceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprCoerce; }
	}

	public final ExprCoerceContext exprCoerce() throws RecognitionException {
		ExprCoerceContext _localctx = new ExprCoerceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_exprCoerce);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((ExprCoerceContext)_localctx).x = exprApply();
			 ((ExprCoerceContext)_localctx).r =  ((ExprCoerceContext)_localctx).x.r; 
			setState(192);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(188);
				match(COLON);
				setState(189);
				((ExprCoerceContext)_localctx).y = typeInstance();

				            AstApply app = new AstApply();
				            app.args.add(new AstName(":"));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprCoerceContext)_localctx).y.r);
				            ((ExprCoerceContext)_localctx).r =  app;
				        
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprUnaryContext extends ParserRuleContext {
		public AstNode r;
		public Token op;
		public ExprCoerceContext x;
		public ExprCoerceContext exprCoerce() {
			return getRuleContext(ExprCoerceContext.class,0);
		}
		public TerminalNode TILDE() { return getToken(OwlParser.TILDE, 0); }
		public TerminalNode PLS() { return getToken(OwlParser.PLS, 0); }
		public TerminalNode MNS() { return getToken(OwlParser.MNS, 0); }
		public ExprUnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprUnary; }
	}

	public final ExprUnaryContext exprUnary() throws RecognitionException {
		ExprUnaryContext _localctx = new ExprUnaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exprUnary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) {
				{
				setState(194);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(197);
			((ExprUnaryContext)_localctx).x = exprCoerce();

			        if (((ExprUnaryContext)_localctx).op != null) {
			            AstApply app = new AstApply();
			            app.args.add(new AstName((((ExprUnaryContext)_localctx).op!=null?((ExprUnaryContext)_localctx).op.getText():null)));
			            app.args.add(((ExprUnaryContext)_localctx).x.r);
			            ((ExprUnaryContext)_localctx).r =  app;
			        } else {
			            ((ExprUnaryContext)_localctx).r =  ((ExprUnaryContext)_localctx).x.r;
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprMulDivContext extends ParserRuleContext {
		public AstNode r;
		public ExprUnaryContext x;
		public Token op;
		public ExprUnaryContext y;
		public List<ExprUnaryContext> exprUnary() {
			return getRuleContexts(ExprUnaryContext.class);
		}
		public ExprUnaryContext exprUnary(int i) {
			return getRuleContext(ExprUnaryContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(OwlParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(OwlParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(OwlParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(OwlParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(OwlParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(OwlParser.MOD, i);
		}
		public ExprMulDivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprMulDiv; }
	}

	public final ExprMulDivContext exprMulDiv() throws RecognitionException {
		ExprMulDivContext _localctx = new ExprMulDivContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprMulDiv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			((ExprMulDivContext)_localctx).x = exprUnary();
			 ((ExprMulDivContext)_localctx).r =  ((ExprMulDivContext)_localctx).x.r; 
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(202);
				((ExprMulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					((ExprMulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(203);
				((ExprMulDivContext)_localctx).y = exprUnary();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprMulDivContext)_localctx).op!=null?((ExprMulDivContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprMulDivContext)_localctx).y.r);
				            ((ExprMulDivContext)_localctx).r =  app;
				        
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprAddSubContext extends ParserRuleContext {
		public AstNode r;
		public ExprMulDivContext x;
		public Token op;
		public ExprMulDivContext y;
		public List<ExprMulDivContext> exprMulDiv() {
			return getRuleContexts(ExprMulDivContext.class);
		}
		public ExprMulDivContext exprMulDiv(int i) {
			return getRuleContext(ExprMulDivContext.class,i);
		}
		public List<TerminalNode> PLS() { return getTokens(OwlParser.PLS); }
		public TerminalNode PLS(int i) {
			return getToken(OwlParser.PLS, i);
		}
		public List<TerminalNode> MNS() { return getTokens(OwlParser.MNS); }
		public TerminalNode MNS(int i) {
			return getToken(OwlParser.MNS, i);
		}
		public ExprAddSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprAddSub; }
	}

	public final ExprAddSubContext exprAddSub() throws RecognitionException {
		ExprAddSubContext _localctx = new ExprAddSubContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprAddSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			((ExprAddSubContext)_localctx).x = exprMulDiv();
			 ((ExprAddSubContext)_localctx).r =  ((ExprAddSubContext)_localctx).x.r; 
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLS || _la==MNS) {
				{
				{
				setState(213);
				((ExprAddSubContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLS || _la==MNS) ) {
					((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(214);
				((ExprAddSubContext)_localctx).y = exprMulDiv();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAddSubContext)_localctx).op!=null?((ExprAddSubContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAddSubContext)_localctx).y.r);
				            ((ExprAddSubContext)_localctx).r =  app;
				        
				}
				}
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprShiftContext extends ParserRuleContext {
		public AstNode r;
		public ExprAddSubContext x;
		public Token op;
		public ExprAddSubContext y;
		public List<ExprAddSubContext> exprAddSub() {
			return getRuleContexts(ExprAddSubContext.class);
		}
		public ExprAddSubContext exprAddSub(int i) {
			return getRuleContext(ExprAddSubContext.class,i);
		}
		public List<TerminalNode> LSHIFT() { return getTokens(OwlParser.LSHIFT); }
		public TerminalNode LSHIFT(int i) {
			return getToken(OwlParser.LSHIFT, i);
		}
		public List<TerminalNode> RSHIFT() { return getTokens(OwlParser.RSHIFT); }
		public TerminalNode RSHIFT(int i) {
			return getToken(OwlParser.RSHIFT, i);
		}
		public List<TerminalNode> SIGNED_RSHIFT() { return getTokens(OwlParser.SIGNED_RSHIFT); }
		public TerminalNode SIGNED_RSHIFT(int i) {
			return getToken(OwlParser.SIGNED_RSHIFT, i);
		}
		public ExprShiftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprShift; }
	}

	public final ExprShiftContext exprShift() throws RecognitionException {
		ExprShiftContext _localctx = new ExprShiftContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exprShift);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			((ExprShiftContext)_localctx).x = exprAddSub();
			 ((ExprShiftContext)_localctx).r =  ((ExprShiftContext)_localctx).x.r; 
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) {
				{
				{
				setState(224);
				((ExprShiftContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) ) {
					((ExprShiftContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(225);
				((ExprShiftContext)_localctx).y = exprAddSub();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprShiftContext)_localctx).op!=null?((ExprShiftContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprShiftContext)_localctx).y.r);
				            ((ExprShiftContext)_localctx).r =  app;
				        
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprBitAndContext extends ParserRuleContext {
		public AstNode r;
		public ExprShiftContext x;
		public Token op;
		public ExprShiftContext y;
		public List<ExprShiftContext> exprShift() {
			return getRuleContexts(ExprShiftContext.class);
		}
		public ExprShiftContext exprShift(int i) {
			return getRuleContext(ExprShiftContext.class,i);
		}
		public List<TerminalNode> BIT_AND() { return getTokens(OwlParser.BIT_AND); }
		public TerminalNode BIT_AND(int i) {
			return getToken(OwlParser.BIT_AND, i);
		}
		public ExprBitAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprBitAnd; }
	}

	public final ExprBitAndContext exprBitAnd() throws RecognitionException {
		ExprBitAndContext _localctx = new ExprBitAndContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprBitAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			((ExprBitAndContext)_localctx).x = exprShift();
			 ((ExprBitAndContext)_localctx).r =  ((ExprBitAndContext)_localctx).x.r; 
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_AND) {
				{
				{
				setState(235);
				((ExprBitAndContext)_localctx).op = match(BIT_AND);
				setState(236);
				((ExprBitAndContext)_localctx).y = exprShift();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitAndContext)_localctx).op!=null?((ExprBitAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitAndContext)_localctx).y.r);
				            ((ExprBitAndContext)_localctx).r =  app;
				        
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprBitXorContext extends ParserRuleContext {
		public AstNode r;
		public ExprBitAndContext x;
		public Token op;
		public ExprBitAndContext y;
		public List<ExprBitAndContext> exprBitAnd() {
			return getRuleContexts(ExprBitAndContext.class);
		}
		public ExprBitAndContext exprBitAnd(int i) {
			return getRuleContext(ExprBitAndContext.class,i);
		}
		public List<TerminalNode> BIT_XOR() { return getTokens(OwlParser.BIT_XOR); }
		public TerminalNode BIT_XOR(int i) {
			return getToken(OwlParser.BIT_XOR, i);
		}
		public ExprBitXorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprBitXor; }
	}

	public final ExprBitXorContext exprBitXor() throws RecognitionException {
		ExprBitXorContext _localctx = new ExprBitXorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprBitXor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			((ExprBitXorContext)_localctx).x = exprBitAnd();
			 ((ExprBitXorContext)_localctx).r =  ((ExprBitXorContext)_localctx).x.r; 
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_XOR) {
				{
				{
				setState(246);
				((ExprBitXorContext)_localctx).op = match(BIT_XOR);
				setState(247);
				((ExprBitXorContext)_localctx).y = exprBitAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitXorContext)_localctx).op!=null?((ExprBitXorContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitXorContext)_localctx).y.r);
				            ((ExprBitXorContext)_localctx).r =  app;
				        
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprBitOrContext extends ParserRuleContext {
		public AstNode r;
		public ExprBitXorContext x;
		public Token op;
		public ExprBitXorContext y;
		public List<ExprBitXorContext> exprBitXor() {
			return getRuleContexts(ExprBitXorContext.class);
		}
		public ExprBitXorContext exprBitXor(int i) {
			return getRuleContext(ExprBitXorContext.class,i);
		}
		public List<TerminalNode> BIT_OR() { return getTokens(OwlParser.BIT_OR); }
		public TerminalNode BIT_OR(int i) {
			return getToken(OwlParser.BIT_OR, i);
		}
		public ExprBitOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprBitOr; }
	}

	public final ExprBitOrContext exprBitOr() throws RecognitionException {
		ExprBitOrContext _localctx = new ExprBitOrContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprBitOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			((ExprBitOrContext)_localctx).x = exprBitXor();
			 ((ExprBitOrContext)_localctx).r =  ((ExprBitOrContext)_localctx).x.r; 
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_OR) {
				{
				{
				setState(257);
				((ExprBitOrContext)_localctx).op = match(BIT_OR);
				setState(258);
				((ExprBitOrContext)_localctx).y = exprBitXor();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitOrContext)_localctx).op!=null?((ExprBitOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitOrContext)_localctx).y.r);
				            ((ExprBitOrContext)_localctx).r =  app;
				        
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprComparisonContext extends ParserRuleContext {
		public AstNode r;
		public ExprBitOrContext x;
		public Token op;
		public ExprBitOrContext y;
		public List<ExprBitOrContext> exprBitOr() {
			return getRuleContexts(ExprBitOrContext.class);
		}
		public ExprBitOrContext exprBitOr(int i) {
			return getRuleContext(ExprBitOrContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(OwlParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(OwlParser.LT, i);
		}
		public List<TerminalNode> LE() { return getTokens(OwlParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(OwlParser.LE, i);
		}
		public List<TerminalNode> GT() { return getTokens(OwlParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(OwlParser.GT, i);
		}
		public List<TerminalNode> GE() { return getTokens(OwlParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(OwlParser.GE, i);
		}
		public ExprComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprComparison; }
	}

	public final ExprComparisonContext exprComparison() throws RecognitionException {
		ExprComparisonContext _localctx = new ExprComparisonContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exprComparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			((ExprComparisonContext)_localctx).x = exprBitOr();
			 ((ExprComparisonContext)_localctx).r =  ((ExprComparisonContext)_localctx).x.r; 
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) {
				{
				{
				setState(268);
				((ExprComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) ) {
					((ExprComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(269);
				((ExprComparisonContext)_localctx).y = exprBitOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprComparisonContext)_localctx).op!=null?((ExprComparisonContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprComparisonContext)_localctx).y.r);
				            ((ExprComparisonContext)_localctx).r =  app;
				        
				}
				}
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprEqualityContext extends ParserRuleContext {
		public AstNode r;
		public ExprComparisonContext x;
		public Token op;
		public ExprComparisonContext y;
		public List<ExprComparisonContext> exprComparison() {
			return getRuleContexts(ExprComparisonContext.class);
		}
		public ExprComparisonContext exprComparison(int i) {
			return getRuleContext(ExprComparisonContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(OwlParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(OwlParser.EQ, i);
		}
		public List<TerminalNode> NE() { return getTokens(OwlParser.NE); }
		public TerminalNode NE(int i) {
			return getToken(OwlParser.NE, i);
		}
		public List<TerminalNode> IS() { return getTokens(OwlParser.IS); }
		public TerminalNode IS(int i) {
			return getToken(OwlParser.IS, i);
		}
		public ExprEqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprEquality; }
	}

	public final ExprEqualityContext exprEquality() throws RecognitionException {
		ExprEqualityContext _localctx = new ExprEqualityContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_exprEquality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			((ExprEqualityContext)_localctx).x = exprComparison();
			 ((ExprEqualityContext)_localctx).r =  ((ExprEqualityContext)_localctx).x.r; 
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) {
				{
				{
				setState(279);
				((ExprEqualityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) ) {
					((ExprEqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(280);
				((ExprEqualityContext)_localctx).y = exprComparison();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprEqualityContext)_localctx).op!=null?((ExprEqualityContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprEqualityContext)_localctx).y.r);
				            ((ExprEqualityContext)_localctx).r =  app;
				        
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprNotContext extends ParserRuleContext {
		public AstNode r;
		public Token op;
		public ExprEqualityContext x;
		public ExprEqualityContext exprEquality() {
			return getRuleContext(ExprEqualityContext.class,0);
		}
		public TerminalNode EXC() { return getToken(OwlParser.EXC, 0); }
		public ExprNotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprNot; }
	}

	public final ExprNotContext exprNot() throws RecognitionException {
		ExprNotContext _localctx = new ExprNotContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exprNot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if (_la==EXC) {
				{
				setState(288);
				((ExprNotContext)_localctx).op = match(EXC);
				}
			}

			setState(291);
			((ExprNotContext)_localctx).x = exprEquality();
			 ((ExprNotContext)_localctx).r =  ((ExprNotContext)_localctx).x.r; 

			        if (((ExprNotContext)_localctx).op != null) {
			            AstApply app = new AstApply();
			            app.args.add(new AstName((((ExprNotContext)_localctx).op!=null?((ExprNotContext)_localctx).op.getText():null)));
			            app.args.add(_localctx.r);
			            ((ExprNotContext)_localctx).r =  app;
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprAndContext extends ParserRuleContext {
		public AstNode r;
		public ExprNotContext x;
		public Token op;
		public ExprNotContext y;
		public List<ExprNotContext> exprNot() {
			return getRuleContexts(ExprNotContext.class);
		}
		public ExprNotContext exprNot(int i) {
			return getRuleContext(ExprNotContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(OwlParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(OwlParser.AND, i);
		}
		public ExprAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprAnd; }
	}

	public final ExprAndContext exprAnd() throws RecognitionException {
		ExprAndContext _localctx = new ExprAndContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			((ExprAndContext)_localctx).x = exprNot();
			 ((ExprAndContext)_localctx).r =  ((ExprAndContext)_localctx).x.r; 
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(297);
				((ExprAndContext)_localctx).op = match(AND);
				setState(298);
				((ExprAndContext)_localctx).y = exprNot();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAndContext)_localctx).op!=null?((ExprAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAndContext)_localctx).y.r);
				            ((ExprAndContext)_localctx).r =  app;
				        
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprOrContext extends ParserRuleContext {
		public AstNode r;
		public ExprAndContext x;
		public Token op;
		public ExprAndContext y;
		public List<ExprAndContext> exprAnd() {
			return getRuleContexts(ExprAndContext.class);
		}
		public ExprAndContext exprAnd(int i) {
			return getRuleContext(ExprAndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(OwlParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(OwlParser.OR, i);
		}
		public ExprOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprOr; }
	}

	public final ExprOrContext exprOr() throws RecognitionException {
		ExprOrContext _localctx = new ExprOrContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exprOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			((ExprOrContext)_localctx).x = exprAnd();
			 ((ExprOrContext)_localctx).r =  ((ExprOrContext)_localctx).x.r; 
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(308);
				((ExprOrContext)_localctx).op = match(OR);
				setState(309);
				((ExprOrContext)_localctx).y = exprAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprOrContext)_localctx).op!=null?((ExprOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprOrContext)_localctx).y.r);
				            ((ExprOrContext)_localctx).r =  app;
				        
				}
				}
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AstNode r;
		public ExprOrContext x;
		public Token op;
		public ExprOrContext y;
		public List<ExprOrContext> exprOr() {
			return getRuleContexts(ExprOrContext.class);
		}
		public ExprOrContext exprOr(int i) {
			return getRuleContext(ExprOrContext.class,i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(OwlParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(OwlParser.ASSIGN, i);
		}
		public List<TerminalNode> ASSIGN_MUL() { return getTokens(OwlParser.ASSIGN_MUL); }
		public TerminalNode ASSIGN_MUL(int i) {
			return getToken(OwlParser.ASSIGN_MUL, i);
		}
		public List<TerminalNode> ASSIGN_DIV() { return getTokens(OwlParser.ASSIGN_DIV); }
		public TerminalNode ASSIGN_DIV(int i) {
			return getToken(OwlParser.ASSIGN_DIV, i);
		}
		public List<TerminalNode> ASSIGN_MOD() { return getTokens(OwlParser.ASSIGN_MOD); }
		public TerminalNode ASSIGN_MOD(int i) {
			return getToken(OwlParser.ASSIGN_MOD, i);
		}
		public List<TerminalNode> ASSIGN_PLS() { return getTokens(OwlParser.ASSIGN_PLS); }
		public TerminalNode ASSIGN_PLS(int i) {
			return getToken(OwlParser.ASSIGN_PLS, i);
		}
		public List<TerminalNode> ASSIGN_MNS() { return getTokens(OwlParser.ASSIGN_MNS); }
		public TerminalNode ASSIGN_MNS(int i) {
			return getToken(OwlParser.ASSIGN_MNS, i);
		}
		public List<TerminalNode> ASSIGN_LSHIFT() { return getTokens(OwlParser.ASSIGN_LSHIFT); }
		public TerminalNode ASSIGN_LSHIFT(int i) {
			return getToken(OwlParser.ASSIGN_LSHIFT, i);
		}
		public List<TerminalNode> ASSIGN_RSHIFT() { return getTokens(OwlParser.ASSIGN_RSHIFT); }
		public TerminalNode ASSIGN_RSHIFT(int i) {
			return getToken(OwlParser.ASSIGN_RSHIFT, i);
		}
		public List<TerminalNode> ASSIGN_SIGNED_RSHIFT() { return getTokens(OwlParser.ASSIGN_SIGNED_RSHIFT); }
		public TerminalNode ASSIGN_SIGNED_RSHIFT(int i) {
			return getToken(OwlParser.ASSIGN_SIGNED_RSHIFT, i);
		}
		public List<TerminalNode> ASSIGN_BIT_AND() { return getTokens(OwlParser.ASSIGN_BIT_AND); }
		public TerminalNode ASSIGN_BIT_AND(int i) {
			return getToken(OwlParser.ASSIGN_BIT_AND, i);
		}
		public List<TerminalNode> ASSIGN_BIT_XOR() { return getTokens(OwlParser.ASSIGN_BIT_XOR); }
		public TerminalNode ASSIGN_BIT_XOR(int i) {
			return getToken(OwlParser.ASSIGN_BIT_XOR, i);
		}
		public List<TerminalNode> ASSIGN_BIT_OR() { return getTokens(OwlParser.ASSIGN_BIT_OR); }
		public TerminalNode ASSIGN_BIT_OR(int i) {
			return getToken(OwlParser.ASSIGN_BIT_OR, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			((ExpressionContext)_localctx).x = exprOr();
			 ((ExpressionContext)_localctx).r =  ((ExpressionContext)_localctx).x.r; 
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) {
				{
				{
				setState(319);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(320);
				((ExpressionContext)_localctx).y = exprOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExpressionContext)_localctx).y.r);
				            ((ExpressionContext)_localctx).r =  app;
				        
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtIfContext extends ParserRuleContext {
		public AstIf r =  new AstIf();
		public ExpressionContext cond;
		public BlockContext b;
		public TerminalNode IF() { return getToken(OwlParser.IF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> ELIF() { return getTokens(OwlParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(OwlParser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(OwlParser.ELSE, 0); }
		public StmtIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtIf; }
	}

	public final StmtIfContext stmtIf() throws RecognitionException {
		StmtIfContext _localctx = new StmtIfContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_stmtIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(IF);
			setState(329);
			((StmtIfContext)_localctx).cond = expression();
			 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
			setState(331);
			((StmtIfContext)_localctx).b = block();
			 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(333);
				match(ELIF);
				setState(334);
				((StmtIfContext)_localctx).cond = expression();
				 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
				setState(336);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
				}
				}
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(348);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(344);
				match(ELSE);
				setState(345);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtMatchContext extends ParserRuleContext {
		public AstMatch r =  new AstMatch();
		public ExpressionContext e;
		public Token t;
		public Token n;
		public BlockContext b;
		public TerminalNode MATCH() { return getToken(OwlParser.MATCH, 0); }
		public TerminalNode LCURLY() { return getToken(OwlParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(OwlParser.RCURLY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(OwlParser.ELSE, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> CASE() { return getTokens(OwlParser.CASE); }
		public TerminalNode CASE(int i) {
			return getToken(OwlParser.CASE, i);
		}
		public List<TerminalNode> NAME() { return getTokens(OwlParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(OwlParser.NAME, i);
		}
		public StmtMatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtMatch; }
	}

	public final StmtMatchContext stmtMatch() throws RecognitionException {
		StmtMatchContext _localctx = new StmtMatchContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stmtMatch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(MATCH);
			setState(351);
			((StmtMatchContext)_localctx).e = expression();
			 _localctx.r.expr = ((StmtMatchContext)_localctx).e.r; 
			setState(353);
			match(LCURLY);
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(360); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(354);
					match(CASE);
					setState(355);
					((StmtMatchContext)_localctx).t = match(NAME);
					setState(357);
					_la = _input.LA(1);
					if (_la==NAME) {
						{
						setState(356);
						((StmtMatchContext)_localctx).n = match(NAME);
						}
					}


					                AstMatch.Label label = new AstMatch.Label();
					                label.label = (((StmtMatchContext)_localctx).t!=null?((StmtMatchContext)_localctx).t.getText():null);
					                if (((StmtMatchContext)_localctx).n != null) {
					                    label.variable = (((StmtMatchContext)_localctx).n!=null?((StmtMatchContext)_localctx).n.getText():null);
					                }
					                label.block = _localctx.r.block.size();
					                _localctx.r.label.add(label);
					            
					}
					}
					setState(362); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE );
				setState(364);
				((StmtMatchContext)_localctx).b = block();

				            _localctx.r.block.add(((StmtMatchContext)_localctx).b.r);
				        
				}
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(372);
			match(RCURLY);
			setState(377);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(373);
				match(ELSE);
				setState(374);
				((StmtMatchContext)_localctx).b = block();

				            _localctx.r.elseBlock = ((StmtMatchContext)_localctx).b.r;
				        
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtReturnContext extends ParserRuleContext {
		public AstReturn r =  new AstReturn();
		public ExpressionContext e;
		public TerminalNode RETURN() { return getToken(OwlParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(OwlParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StmtReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtReturn; }
	}

	public final StmtReturnContext stmtReturn() throws RecognitionException {
		StmtReturnContext _localctx = new StmtReturnContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_stmtReturn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(RETURN);
			setState(383);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STR))) != 0)) {
				{
				setState(380);
				((StmtReturnContext)_localctx).e = expression();
				 _localctx.r.expr = ((StmtReturnContext)_localctx).e.r; 
				}
			}

			setState(385);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public AstNode r;
		public ExpressionContext e;
		public StmtIfContext s;
		public StmtMatchContext m;
		public StmtReturnContext ret;
		public TerminalNode SEMICOLON() { return getToken(OwlParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StmtIfContext stmtIf() {
			return getRuleContext(StmtIfContext.class,0);
		}
		public StmtMatchContext stmtMatch() {
			return getRuleContext(StmtMatchContext.class,0);
		}
		public StmtReturnContext stmtReturn() {
			return getRuleContext(StmtReturnContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_statement);
		try {
			setState(400);
			switch (_input.LA(1)) {
			case LPAREN:
			case TILDE:
			case EXC:
			case PLS:
			case MNS:
			case OCT:
			case DEC:
			case HEX:
			case NAME:
			case STR:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				((StatementContext)_localctx).e = expression();
				setState(388);
				match(SEMICOLON);
				 ((StatementContext)_localctx).r =  new AstExpr(((StatementContext)_localctx).e.r); 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				((StatementContext)_localctx).s = stmtIf();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).s.r; 
				}
				break;
			case MATCH:
				enterOuterAlt(_localctx, 3);
				{
				setState(394);
				((StatementContext)_localctx).m = stmtMatch();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).m.r; 
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(397);
				((StatementContext)_localctx).ret = stmtReturn();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).ret.r; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNonFnContext extends ParserRuleContext {
		public AstType r =  new AstType();
		public AbsoluteNameContext n;
		public TypeInstanceContext a;
		public AbsoluteNameContext absoluteName() {
			return getRuleContext(AbsoluteNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(OwlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(OwlParser.RPAREN, 0); }
		public List<TypeInstanceContext> typeInstance() {
			return getRuleContexts(TypeInstanceContext.class);
		}
		public TypeInstanceContext typeInstance(int i) {
			return getRuleContext(TypeInstanceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(OwlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OwlParser.COMMA, i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(OwlParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(OwlParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(OwlParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(OwlParser.RBRACKET, i);
		}
		public TypeNonFnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNonFn; }
	}

	public final TypeNonFnContext typeNonFn() throws RecognitionException {
		TypeNonFnContext _localctx = new TypeNonFnContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_typeNonFn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			((TypeNonFnContext)_localctx).n = absoluteName();
			 _localctx.r.name = ((TypeNonFnContext)_localctx).n.r; 
			setState(425);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(404);
				match(LPAREN);
				setState(405);
				((TypeNonFnContext)_localctx).a = typeInstance();
				 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(407);
					match(COMMA);
					setState(408);
					((TypeNonFnContext)_localctx).a = typeInstance();
					 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
					}
					}
					setState(415);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(416);
				match(RPAREN);
				}
				break;
			case LBRACKET:
				{
				setState(421); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(418);
					match(LBRACKET);
					setState(419);
					match(RBRACKET);

					                AstType arrayType = new AstType("Array");
					                arrayType.args.add(_localctx.r);
					                ((TypeNonFnContext)_localctx).r =  arrayType;
					            
					}
					}
					setState(423); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACKET );
				}
				break;
			case IS:
			case COMMA:
			case SEMICOLON:
			case ARROW:
			case RPAREN:
			case LCURLY:
			case RBRACKET:
			case MUL:
			case DIV:
			case MOD:
			case PLS:
			case MNS:
			case LSHIFT:
			case RSHIFT:
			case SIGNED_RSHIFT:
			case BIT_AND:
			case BIT_XOR:
			case BIT_OR:
			case EQ:
			case NE:
			case LE:
			case LT:
			case GE:
			case GT:
			case AND:
			case OR:
			case ASSIGN:
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
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeInstanceContext extends ParserRuleContext {
		public AstType r;
		public TypeNonFnContext x;
		public TypeNonFnContext y;
		public List<TypeNonFnContext> typeNonFn() {
			return getRuleContexts(TypeNonFnContext.class);
		}
		public TypeNonFnContext typeNonFn(int i) {
			return getRuleContext(TypeNonFnContext.class,i);
		}
		public List<TerminalNode> ARROW() { return getTokens(OwlParser.ARROW); }
		public TerminalNode ARROW(int i) {
			return getToken(OwlParser.ARROW, i);
		}
		public TypeInstanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeInstance; }
	}

	public final TypeInstanceContext typeInstance() throws RecognitionException {
		TypeInstanceContext _localctx = new TypeInstanceContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_typeInstance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 AstType functionType = null; 
			setState(428);
			((TypeInstanceContext)_localctx).x = typeNonFn();
			 ((TypeInstanceContext)_localctx).r =  ((TypeInstanceContext)_localctx).x.r; 
			setState(436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARROW) {
				{
				{
				setState(430);
				match(ARROW);
				setState(431);
				((TypeInstanceContext)_localctx).y = typeNonFn();

				            if (functionType == null) {
				                functionType = new AstType("Fn");
				                functionType.args.add(_localctx.r);
				                ((TypeInstanceContext)_localctx).r =  functionType;
				            }
				            functionType.args.add(((TypeInstanceContext)_localctx).y.r);
				        
				}
				}
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3A\u01ba\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2"+
		"B\n\2\f\2\16\2E\13\2\3\3\3\3\3\3\3\3\3\3\7\3L\n\3\f\3\16\3O\13\3\3\4\3"+
		"\4\3\4\5\4T\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4]\n\4\f\4\16\4`\13\4\5"+
		"\4b\n\4\3\4\5\4e\n\4\3\4\3\4\3\4\3\4\5\4k\n\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5v\n\5\3\6\3\6\3\6\3\6\7\6|\n\6\f\6\16\6\177\13\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0092"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a1\n\b"+
		"\f\b\16\b\u00a4\13\b\5\b\u00a6\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\7\b\u00b1\n\b\f\b\16\b\u00b4\13\b\3\b\3\b\7\b\u00b8\n\b\f\b\16\b\u00bb"+
		"\13\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c3\n\t\3\n\5\n\u00c6\n\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00d1\n\13\f\13\16\13\u00d4\13"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00dc\n\f\f\f\16\f\u00df\13\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\7\r\u00e7\n\r\f\r\16\r\u00ea\13\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u00f2\n\16\f\16\16\16\u00f5\13\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\7\17\u00fd\n\17\f\17\16\17\u0100\13\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u0108\n\20\f\20\16\20\u010b\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u0113\n\21\f\21\16\21\u0116\13\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u011e\n\22\f\22\16\22\u0121\13\22\3\23\5\23\u0124\n\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0130\n\24\f\24"+
		"\16\24\u0133\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u013b\n\25\f\25"+
		"\16\25\u013e\13\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0146\n\26\f\26"+
		"\16\26\u0149\13\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\7\27\u0156\n\27\f\27\16\27\u0159\13\27\3\27\3\27\3\27\3\27\5\27\u015f"+
		"\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0168\n\30\3\30\6\30\u016b"+
		"\n\30\r\30\16\30\u016c\3\30\3\30\3\30\7\30\u0172\n\30\f\30\16\30\u0175"+
		"\13\30\3\30\3\30\3\30\3\30\3\30\5\30\u017c\n\30\3\31\3\31\3\31\3\31\5"+
		"\31\u0182\n\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\5\32\u0193\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\7\33\u019e\n\33\f\33\16\33\u01a1\13\33\3\33\3\33\3\33\3\33"+
		"\3\33\6\33\u01a8\n\33\r\33\16\33\u01a9\5\33\u01ac\n\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\7\34\u01b5\n\34\f\34\16\34\u01b8\13\34\3\34\2\2\35"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\t\4\2\32"+
		"\32\37 \3\2\34\36\3\2\37 \3\2!#\3\2),\4\2\b\b\'(\3\2/:\u01d0\2C\3\2\2"+
		"\2\4F\3\2\2\2\6P\3\2\2\2\bo\3\2\2\2\nw\3\2\2\2\f\u0091\3\2\2\2\16\u0093"+
		"\3\2\2\2\20\u00bc\3\2\2\2\22\u00c5\3\2\2\2\24\u00ca\3\2\2\2\26\u00d5\3"+
		"\2\2\2\30\u00e0\3\2\2\2\32\u00eb\3\2\2\2\34\u00f6\3\2\2\2\36\u0101\3\2"+
		"\2\2 \u010c\3\2\2\2\"\u0117\3\2\2\2$\u0123\3\2\2\2&\u0129\3\2\2\2(\u0134"+
		"\3\2\2\2*\u013f\3\2\2\2,\u014a\3\2\2\2.\u0160\3\2\2\2\60\u017d\3\2\2\2"+
		"\62\u0192\3\2\2\2\64\u0194\3\2\2\2\66\u01ad\3\2\2\289\5\6\4\29:\b\2\1"+
		"\2:B\3\2\2\2;<\7>\2\2<=\7/\2\2=>\5*\26\2>?\7\17\2\2?@\b\2\1\2@B\3\2\2"+
		"\2A8\3\2\2\2A;\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\3\3\2\2\2EC\3\2"+
		"\2\2FG\7>\2\2GM\b\3\1\2HI\7\f\2\2IJ\7>\2\2JL\b\3\1\2KH\3\2\2\2LO\3\2\2"+
		"\2MK\3\2\2\2MN\3\2\2\2N\5\3\2\2\2OM\3\2\2\2PS\7\6\2\2QR\7>\2\2RT\b\4\1"+
		"\2SQ\3\2\2\2ST\3\2\2\2Td\3\2\2\2Ua\7\21\2\2VW\5\b\5\2W^\b\4\1\2XY\7\r"+
		"\2\2YZ\5\b\5\2Z[\b\4\1\2[]\3\2\2\2\\X\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3"+
		"\2\2\2_b\3\2\2\2`^\3\2\2\2aV\3\2\2\2ab\3\2\2\2bc\3\2\2\2ce\7\22\2\2dU"+
		"\3\2\2\2de\3\2\2\2ej\3\2\2\2fg\7\16\2\2gh\5\66\34\2hi\b\4\1\2ik\3\2\2"+
		"\2jf\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\5\n\6\2mn\b\4\1\2n\7\3\2\2\2op\7>\2"+
		"\2pu\b\5\1\2qr\7\16\2\2rs\5\66\34\2st\b\5\1\2tv\3\2\2\2uq\3\2\2\2uv\3"+
		"\2\2\2v\t\3\2\2\2w}\7\23\2\2xy\5\62\32\2yz\b\6\1\2z|\3\2\2\2{x\3\2\2\2"+
		"|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081"+
		"\7\24\2\2\u0081\13\3\2\2\2\u0082\u0083\7>\2\2\u0083\u0092\b\7\1\2\u0084"+
		"\u0085\7;\2\2\u0085\u0092\b\7\1\2\u0086\u0087\7<\2\2\u0087\u0092\b\7\1"+
		"\2\u0088\u0089\7=\2\2\u0089\u0092\b\7\1\2\u008a\u008b\7?\2\2\u008b\u0092"+
		"\b\7\1\2\u008c\u008d\7\21\2\2\u008d\u008e\5*\26\2\u008e\u008f\7\22\2\2"+
		"\u008f\u0090\b\7\1\2\u0090\u0092\3\2\2\2\u0091\u0082\3\2\2\2\u0091\u0084"+
		"\3\2\2\2\u0091\u0086\3\2\2\2\u0091\u0088\3\2\2\2\u0091\u008a\3\2\2\2\u0091"+
		"\u008c\3\2\2\2\u0092\r\3\2\2\2\u0093\u0094\5\f\7\2\u0094\u00b9\b\b\1\2"+
		"\u0095\u0096\7\f\2\2\u0096\u0097\7>\2\2\u0097\u00b8\b\b\1\2\u0098\u0099"+
		"\b\b\1\2\u0099\u00a5\7\21\2\2\u009a\u009b\5*\26\2\u009b\u00a2\b\b\1\2"+
		"\u009c\u009d\7\r\2\2\u009d\u009e\5*\26\2\u009e\u009f\b\b\1\2\u009f\u00a1"+
		"\3\2\2\2\u00a0\u009c\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u009a\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00b8\7\22\2\2\u00a8"+
		"\u00a9\b\b\1\2\u00a9\u00aa\7\25\2\2\u00aa\u00ab\5*\26\2\u00ab\u00b2\b"+
		"\b\1\2\u00ac\u00ad\7\r\2\2\u00ad\u00ae\5*\26\2\u00ae\u00af\b\b\1\2\u00af"+
		"\u00b1\3\2\2\2\u00b0\u00ac\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5"+
		"\u00b6\7\26\2\2\u00b6\u00b8\3\2\2\2\u00b7\u0095\3\2\2\2\u00b7\u0098\3"+
		"\2\2\2\u00b7\u00a8\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\17\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\5\16\b"+
		"\2\u00bd\u00c2\b\t\1\2\u00be\u00bf\7\16\2\2\u00bf\u00c0\5\66\34\2\u00c0"+
		"\u00c1\b\t\1\2\u00c1\u00c3\3\2\2\2\u00c2\u00be\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\21\3\2\2\2\u00c4\u00c6\t\2\2\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\5\20\t\2\u00c8\u00c9\b\n\1\2"+
		"\u00c9\23\3\2\2\2\u00ca\u00cb\5\22\n\2\u00cb\u00d2\b\13\1\2\u00cc\u00cd"+
		"\t\3\2\2\u00cd\u00ce\5\22\n\2\u00ce\u00cf\b\13\1\2\u00cf\u00d1\3\2\2\2"+
		"\u00d0\u00cc\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3"+
		"\3\2\2\2\u00d3\25\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6\5\24\13\2\u00d6"+
		"\u00dd\b\f\1\2\u00d7\u00d8\t\4\2\2\u00d8\u00d9\5\24\13\2\u00d9\u00da\b"+
		"\f\1\2\u00da\u00dc\3\2\2\2\u00db\u00d7\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\27\3\2\2\2\u00df\u00dd\3\2\2"+
		"\2\u00e0\u00e1\5\26\f\2\u00e1\u00e8\b\r\1\2\u00e2\u00e3\t\5\2\2\u00e3"+
		"\u00e4\5\26\f\2\u00e4\u00e5\b\r\1\2\u00e5\u00e7\3\2\2\2\u00e6\u00e2\3"+
		"\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\31\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\5\30\r\2\u00ec\u00f3\b\16"+
		"\1\2\u00ed\u00ee\7$\2\2\u00ee\u00ef\5\30\r\2\u00ef\u00f0\b\16\1\2\u00f0"+
		"\u00f2\3\2\2\2\u00f1\u00ed\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f3\u00f4\3\2\2\2\u00f4\33\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7"+
		"\5\32\16\2\u00f7\u00fe\b\17\1\2\u00f8\u00f9\7%\2\2\u00f9\u00fa\5\32\16"+
		"\2\u00fa\u00fb\b\17\1\2\u00fb\u00fd\3\2\2\2\u00fc\u00f8\3\2\2\2\u00fd"+
		"\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\35\3\2\2"+
		"\2\u0100\u00fe\3\2\2\2\u0101\u0102\5\34\17\2\u0102\u0109\b\20\1\2\u0103"+
		"\u0104\7&\2\2\u0104\u0105\5\34\17\2\u0105\u0106\b\20\1\2\u0106\u0108\3"+
		"\2\2\2\u0107\u0103\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\37\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\5\36\20"+
		"\2\u010d\u0114\b\21\1\2\u010e\u010f\t\6\2\2\u010f\u0110\5\36\20\2\u0110"+
		"\u0111\b\21\1\2\u0111\u0113\3\2\2\2\u0112\u010e\3\2\2\2\u0113\u0116\3"+
		"\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115!\3\2\2\2\u0116\u0114"+
		"\3\2\2\2\u0117\u0118\5 \21\2\u0118\u011f\b\22\1\2\u0119\u011a\t\7\2\2"+
		"\u011a\u011b\5 \21\2\u011b\u011c\b\22\1\2\u011c\u011e\3\2\2\2\u011d\u0119"+
		"\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120"+
		"#\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0124\7\33\2\2\u0123\u0122\3\2\2\2"+
		"\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\5\"\22\2\u0126\u0127"+
		"\b\23\1\2\u0127\u0128\b\23\1\2\u0128%\3\2\2\2\u0129\u012a\5$\23\2\u012a"+
		"\u0131\b\24\1\2\u012b\u012c\7-\2\2\u012c\u012d\5$\23\2\u012d\u012e\b\24"+
		"\1\2\u012e\u0130\3\2\2\2\u012f\u012b\3\2\2\2\u0130\u0133\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\'\3\2\2\2\u0133\u0131\3\2\2\2"+
		"\u0134\u0135\5&\24\2\u0135\u013c\b\25\1\2\u0136\u0137\7.\2\2\u0137\u0138"+
		"\5&\24\2\u0138\u0139\b\25\1\2\u0139\u013b\3\2\2\2\u013a\u0136\3\2\2\2"+
		"\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d)\3"+
		"\2\2\2\u013e\u013c\3\2\2\2\u013f\u0140\5(\25\2\u0140\u0147\b\26\1\2\u0141"+
		"\u0142\t\b\2\2\u0142\u0143\5(\25\2\u0143\u0144\b\26\1\2\u0144\u0146\3"+
		"\2\2\2\u0145\u0141\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148+\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014b\7\7\2\2"+
		"\u014b\u014c\5*\26\2\u014c\u014d\b\27\1\2\u014d\u014e\5\n\6\2\u014e\u0157"+
		"\b\27\1\2\u014f\u0150\7\4\2\2\u0150\u0151\5*\26\2\u0151\u0152\b\27\1\2"+
		"\u0152\u0153\5\n\6\2\u0153\u0154\b\27\1\2\u0154\u0156\3\2\2\2\u0155\u014f"+
		"\3\2\2\2\u0156\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158"+
		"\u015e\3\2\2\2\u0159\u0157\3\2\2\2\u015a\u015b\7\5\2\2\u015b\u015c\5\n"+
		"\6\2\u015c\u015d\b\27\1\2\u015d\u015f\3\2\2\2\u015e\u015a\3\2\2\2\u015e"+
		"\u015f\3\2\2\2\u015f-\3\2\2\2\u0160\u0161\7\t\2\2\u0161\u0162\5*\26\2"+
		"\u0162\u0163\b\30\1\2\u0163\u0173\7\23\2\2\u0164\u0165\7\3\2\2\u0165\u0167"+
		"\7>\2\2\u0166\u0168\7>\2\2\u0167\u0166\3\2\2\2\u0167\u0168\3\2\2\2\u0168"+
		"\u0169\3\2\2\2\u0169\u016b\b\30\1\2\u016a\u0164\3\2\2\2\u016b\u016c\3"+
		"\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e"+
		"\u016f\5\n\6\2\u016f\u0170\b\30\1\2\u0170\u0172\3\2\2\2\u0171\u016a\3"+
		"\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\u0176\3\2\2\2\u0175\u0173\3\2\2\2\u0176\u017b\7\24\2\2\u0177\u0178\7"+
		"\5\2\2\u0178\u0179\5\n\6\2\u0179\u017a\b\30\1\2\u017a\u017c\3\2\2\2\u017b"+
		"\u0177\3\2\2\2\u017b\u017c\3\2\2\2\u017c/\3\2\2\2\u017d\u0181\7\13\2\2"+
		"\u017e\u017f\5*\26\2\u017f\u0180\b\31\1\2\u0180\u0182\3\2\2\2\u0181\u017e"+
		"\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0184\7\17\2\2"+
		"\u0184\61\3\2\2\2\u0185\u0186\5*\26\2\u0186\u0187\7\17\2\2\u0187\u0188"+
		"\b\32\1\2\u0188\u0193\3\2\2\2\u0189\u018a\5,\27\2\u018a\u018b\b\32\1\2"+
		"\u018b\u0193\3\2\2\2\u018c\u018d\5.\30\2\u018d\u018e\b\32\1\2\u018e\u0193"+
		"\3\2\2\2\u018f\u0190\5\60\31\2\u0190\u0191\b\32\1\2\u0191\u0193\3\2\2"+
		"\2\u0192\u0185\3\2\2\2\u0192\u0189\3\2\2\2\u0192\u018c\3\2\2\2\u0192\u018f"+
		"\3\2\2\2\u0193\63\3\2\2\2\u0194\u0195\5\4\3\2\u0195\u01ab\b\33\1\2\u0196"+
		"\u0197\7\21\2\2\u0197\u0198\5\66\34\2\u0198\u019f\b\33\1\2\u0199\u019a"+
		"\7\r\2\2\u019a\u019b\5\66\34\2\u019b\u019c\b\33\1\2\u019c\u019e\3\2\2"+
		"\2\u019d\u0199\3\2\2\2\u019e\u01a1\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0"+
		"\3\2\2\2\u01a0\u01a2\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2\u01a3\7\22\2\2"+
		"\u01a3\u01ac\3\2\2\2\u01a4\u01a5\7\25\2\2\u01a5\u01a6\7\26\2\2\u01a6\u01a8"+
		"\b\33\1\2\u01a7\u01a4\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01a7\3\2\2\2"+
		"\u01a9\u01aa\3\2\2\2\u01aa\u01ac\3\2\2\2\u01ab\u0196\3\2\2\2\u01ab\u01a7"+
		"\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\65\3\2\2\2\u01ad\u01ae\b\34\1\2\u01ae"+
		"\u01af\5\64\33\2\u01af\u01b6\b\34\1\2\u01b0\u01b1\7\20\2\2\u01b1\u01b2"+
		"\5\64\33\2\u01b2\u01b3\b\34\1\2\u01b3\u01b5\3\2\2\2\u01b4\u01b0\3\2\2"+
		"\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\67"+
		"\3\2\2\2\u01b8\u01b6\3\2\2\2,ACMS^adju}\u0091\u00a2\u00a5\u00b2\u00b7"+
		"\u00b9\u00c2\u00c5\u00d2\u00dd\u00e8\u00f3\u00fe\u0109\u0114\u011f\u0123"+
		"\u0131\u013c\u0147\u0157\u015e\u0167\u016c\u0173\u017b\u0181\u0192\u019f"+
		"\u01a9\u01ab\u01b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}