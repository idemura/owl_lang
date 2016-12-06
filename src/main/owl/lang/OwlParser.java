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
		OCT=57, DEC=58, HEX=59, NAME=60, STRING=61, COMMENT=62, WS=63;
	public static final int
		RULE_module = 0, RULE_absoluteName = 1, RULE_function = 2, RULE_argument = 3, 
		RULE_block = 4, RULE_exprPrime = 5, RULE_exprApply = 6, RULE_exprCoerce = 7, 
		RULE_exprUnary = 8, RULE_exprMulDiv = 9, RULE_exprAddSub = 10, RULE_exprShift = 11, 
		RULE_exprBitAnd = 12, RULE_exprBitXor = 13, RULE_exprBitOr = 14, RULE_exprComparison = 15, 
		RULE_exprEquality = 16, RULE_exprNot = 17, RULE_exprAnd = 18, RULE_exprOr = 19, 
		RULE_expression = 20, RULE_stmtIf = 21, RULE_stmtMatch = 22, RULE_stmtReturn = 23, 
		RULE_statement = 24, RULE_typeNonLambda = 25, RULE_typeInstance = 26;
	public static final String[] ruleNames = {
		"module", "absoluteName", "function", "argument", "block", "exprPrime", 
		"exprApply", "exprCoerce", "exprUnary", "exprMulDiv", "exprAddSub", "exprShift", 
		"exprBitAnd", "exprBitXor", "exprBitOr", "exprComparison", "exprEquality", 
		"exprNot", "exprAnd", "exprOr", "expression", "stmtIf", "stmtMatch", "stmtReturn", 
		"statement", "typeNonLambda", "typeInstance"
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
		public Token FN;
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
			((FunctionContext)_localctx).FN = match(FN);

			        _localctx.r.line = (((FunctionContext)_localctx).FN!=null?((FunctionContext)_localctx).FN.getLine():0);
			        _localctx.r.charPositionInLine = (((FunctionContext)_localctx).FN!=null?((FunctionContext)_localctx).FN.getCharPositionInLine():0);
			    
			setState(82);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(80);
				((FunctionContext)_localctx).NAME = match(NAME);
				 _localctx.r.name = (((FunctionContext)_localctx).NAME!=null?((FunctionContext)_localctx).NAME.getText():null); 
				}
			}

			setState(99);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(84);
				match(LPAREN);
				setState(96);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(85);
					((FunctionContext)_localctx).a = argument();
					 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(87);
						match(COMMA);
						setState(88);
						((FunctionContext)_localctx).a = argument();
						 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
						}
						}
						setState(95);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(98);
				match(RPAREN);
				}
			}

			setState(105);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(101);
				match(COLON);
				setState(102);
				((FunctionContext)_localctx).type = typeInstance();
				 _localctx.r.returnType = ((FunctionContext)_localctx).type.r; 
				}
			}

			setState(107);
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
			setState(110);
			((ArgumentContext)_localctx).NAME = match(NAME);

			        _localctx.r.name = (((ArgumentContext)_localctx).NAME!=null?((ArgumentContext)_localctx).NAME.getText():null);
			        _localctx.r.line = (((ArgumentContext)_localctx).NAME!=null?((ArgumentContext)_localctx).NAME.getLine():0);
			        _localctx.r.charPositionInLine = (((ArgumentContext)_localctx).NAME!=null?((ArgumentContext)_localctx).NAME.getCharPositionInLine():0);
			    
			setState(116);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(112);
				match(COLON);
				setState(113);
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
			setState(118);
			match(LCURLY);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << MATCH) | (1L << RETURN) | (1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STRING))) != 0)) {
				{
				{
				setState(119);
				((BlockContext)_localctx).s = statement();
				 _localctx.r.statements.add(((BlockContext)_localctx).s.r); 
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
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
		public Token STRING;
		public ExpressionContext e;
		public TerminalNode NAME() { return getToken(OwlParser.NAME, 0); }
		public TerminalNode OCT() { return getToken(OwlParser.OCT, 0); }
		public TerminalNode DEC() { return getToken(OwlParser.DEC, 0); }
		public TerminalNode HEX() { return getToken(OwlParser.HEX, 0); }
		public TerminalNode STRING() { return getToken(OwlParser.STRING, 0); }
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
			setState(144);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				((ExprPrimeContext)_localctx).NAME = match(NAME);
				 ((ExprPrimeContext)_localctx).r =  new AstName((((ExprPrimeContext)_localctx).NAME!=null?((ExprPrimeContext)_localctx).NAME.getText():null)); 
				}
				break;
			case OCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				((ExprPrimeContext)_localctx).OCT = match(OCT);
				 ((ExprPrimeContext)_localctx).r =  new AstLiteral((((ExprPrimeContext)_localctx).OCT!=null?((ExprPrimeContext)_localctx).OCT.getText():null), AstLiteral.Format.OCT); 
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				((ExprPrimeContext)_localctx).DEC = match(DEC);
				 ((ExprPrimeContext)_localctx).r =  new AstLiteral((((ExprPrimeContext)_localctx).DEC!=null?((ExprPrimeContext)_localctx).DEC.getText():null), AstLiteral.Format.DEC); 
				}
				break;
			case HEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				((ExprPrimeContext)_localctx).HEX = match(HEX);
				 ((ExprPrimeContext)_localctx).r =  new AstLiteral((((ExprPrimeContext)_localctx).HEX!=null?((ExprPrimeContext)_localctx).HEX.getText():null), AstLiteral.Format.HEX); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(137);
				((ExprPrimeContext)_localctx).STRING = match(STRING);
				 ((ExprPrimeContext)_localctx).r =  new AstLiteral(Util.unquote((((ExprPrimeContext)_localctx).STRING!=null?((ExprPrimeContext)_localctx).STRING.getText():null)), AstLiteral.Format.STRING); 
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				setState(139);
				match(LPAREN);
				setState(140);
				((ExprPrimeContext)_localctx).e = expression();
				setState(141);
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
			setState(146);
			((ExprApplyContext)_localctx).x = exprPrime();
			 ((ExprApplyContext)_localctx).r =  ((ExprApplyContext)_localctx).x.r; 
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOT) | (1L << LPAREN) | (1L << LBRACKET))) != 0)) {
				{
				setState(182);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(148);
					match(DOT);
					setState(149);
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
					        
					setState(152);
					match(LPAREN);
					setState(164);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STRING))) != 0)) {
						{
						setState(153);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						setState(161);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(155);
							match(COMMA);
							setState(156);
							((ExprApplyContext)_localctx).a = expression();
							 app.args.add(((ExprApplyContext)_localctx).a.r); 
							}
							}
							setState(163);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(166);
					match(RPAREN);
					}
					break;
				case LBRACKET:
					{

					            AstApply app = new AstApply();
					            app.args.add(new AstName("[]"));
					            app.args.add(_localctx.r);
					            ((ExprApplyContext)_localctx).r =  app;
					        
					setState(168);
					match(LBRACKET);
					setState(169);
					((ExprApplyContext)_localctx).a = expression();
					 app.args.add(((ExprApplyContext)_localctx).a.r); 
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(171);
						match(COMMA);
						setState(172);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						}
						}
						setState(179);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(180);
					match(RBRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(186);
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
			setState(187);
			((ExprCoerceContext)_localctx).x = exprApply();
			 ((ExprCoerceContext)_localctx).r =  ((ExprCoerceContext)_localctx).x.r; 
			setState(193);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(189);
				match(COLON);
				setState(190);
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
			setState(196);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) {
				{
				setState(195);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(198);
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
			setState(201);
			((ExprMulDivContext)_localctx).x = exprUnary();
			 ((ExprMulDivContext)_localctx).r =  ((ExprMulDivContext)_localctx).x.r; 
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(203);
				((ExprMulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					((ExprMulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(204);
				((ExprMulDivContext)_localctx).y = exprUnary();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprMulDivContext)_localctx).op!=null?((ExprMulDivContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprMulDivContext)_localctx).y.r);
				            ((ExprMulDivContext)_localctx).r =  app;
				        
				}
				}
				setState(211);
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
			setState(212);
			((ExprAddSubContext)_localctx).x = exprMulDiv();
			 ((ExprAddSubContext)_localctx).r =  ((ExprAddSubContext)_localctx).x.r; 
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLS || _la==MNS) {
				{
				{
				setState(214);
				((ExprAddSubContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLS || _la==MNS) ) {
					((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(215);
				((ExprAddSubContext)_localctx).y = exprMulDiv();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAddSubContext)_localctx).op!=null?((ExprAddSubContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAddSubContext)_localctx).y.r);
				            ((ExprAddSubContext)_localctx).r =  app;
				        
				}
				}
				setState(222);
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
			setState(223);
			((ExprShiftContext)_localctx).x = exprAddSub();
			 ((ExprShiftContext)_localctx).r =  ((ExprShiftContext)_localctx).x.r; 
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) {
				{
				{
				setState(225);
				((ExprShiftContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) ) {
					((ExprShiftContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(226);
				((ExprShiftContext)_localctx).y = exprAddSub();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprShiftContext)_localctx).op!=null?((ExprShiftContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprShiftContext)_localctx).y.r);
				            ((ExprShiftContext)_localctx).r =  app;
				        
				}
				}
				setState(233);
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
			setState(234);
			((ExprBitAndContext)_localctx).x = exprShift();
			 ((ExprBitAndContext)_localctx).r =  ((ExprBitAndContext)_localctx).x.r; 
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_AND) {
				{
				{
				setState(236);
				((ExprBitAndContext)_localctx).op = match(BIT_AND);
				setState(237);
				((ExprBitAndContext)_localctx).y = exprShift();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitAndContext)_localctx).op!=null?((ExprBitAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitAndContext)_localctx).y.r);
				            ((ExprBitAndContext)_localctx).r =  app;
				        
				}
				}
				setState(244);
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
			setState(245);
			((ExprBitXorContext)_localctx).x = exprBitAnd();
			 ((ExprBitXorContext)_localctx).r =  ((ExprBitXorContext)_localctx).x.r; 
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_XOR) {
				{
				{
				setState(247);
				((ExprBitXorContext)_localctx).op = match(BIT_XOR);
				setState(248);
				((ExprBitXorContext)_localctx).y = exprBitAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitXorContext)_localctx).op!=null?((ExprBitXorContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitXorContext)_localctx).y.r);
				            ((ExprBitXorContext)_localctx).r =  app;
				        
				}
				}
				setState(255);
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
			setState(256);
			((ExprBitOrContext)_localctx).x = exprBitXor();
			 ((ExprBitOrContext)_localctx).r =  ((ExprBitOrContext)_localctx).x.r; 
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_OR) {
				{
				{
				setState(258);
				((ExprBitOrContext)_localctx).op = match(BIT_OR);
				setState(259);
				((ExprBitOrContext)_localctx).y = exprBitXor();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitOrContext)_localctx).op!=null?((ExprBitOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitOrContext)_localctx).y.r);
				            ((ExprBitOrContext)_localctx).r =  app;
				        
				}
				}
				setState(266);
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
			setState(267);
			((ExprComparisonContext)_localctx).x = exprBitOr();
			 ((ExprComparisonContext)_localctx).r =  ((ExprComparisonContext)_localctx).x.r; 
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) {
				{
				{
				setState(269);
				((ExprComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) ) {
					((ExprComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(270);
				((ExprComparisonContext)_localctx).y = exprBitOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprComparisonContext)_localctx).op!=null?((ExprComparisonContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprComparisonContext)_localctx).y.r);
				            ((ExprComparisonContext)_localctx).r =  app;
				        
				}
				}
				setState(277);
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
			setState(278);
			((ExprEqualityContext)_localctx).x = exprComparison();
			 ((ExprEqualityContext)_localctx).r =  ((ExprEqualityContext)_localctx).x.r; 
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) {
				{
				{
				setState(280);
				((ExprEqualityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) ) {
					((ExprEqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(281);
				((ExprEqualityContext)_localctx).y = exprComparison();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprEqualityContext)_localctx).op!=null?((ExprEqualityContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprEqualityContext)_localctx).y.r);
				            ((ExprEqualityContext)_localctx).r =  app;
				        
				}
				}
				setState(288);
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
			setState(290);
			_la = _input.LA(1);
			if (_la==EXC) {
				{
				setState(289);
				((ExprNotContext)_localctx).op = match(EXC);
				}
			}

			setState(292);
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
			setState(296);
			((ExprAndContext)_localctx).x = exprNot();
			 ((ExprAndContext)_localctx).r =  ((ExprAndContext)_localctx).x.r; 
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(298);
				((ExprAndContext)_localctx).op = match(AND);
				setState(299);
				((ExprAndContext)_localctx).y = exprNot();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAndContext)_localctx).op!=null?((ExprAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAndContext)_localctx).y.r);
				            ((ExprAndContext)_localctx).r =  app;
				        
				}
				}
				setState(306);
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
			setState(307);
			((ExprOrContext)_localctx).x = exprAnd();
			 ((ExprOrContext)_localctx).r =  ((ExprOrContext)_localctx).x.r; 
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(309);
				((ExprOrContext)_localctx).op = match(OR);
				setState(310);
				((ExprOrContext)_localctx).y = exprAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprOrContext)_localctx).op!=null?((ExprOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprOrContext)_localctx).y.r);
				            ((ExprOrContext)_localctx).r =  app;
				        
				}
				}
				setState(317);
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
			setState(318);
			((ExpressionContext)_localctx).x = exprOr();
			 ((ExpressionContext)_localctx).r =  ((ExpressionContext)_localctx).x.r; 
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) {
				{
				{
				setState(320);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(321);
				((ExpressionContext)_localctx).y = exprOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExpressionContext)_localctx).y.r);
				            ((ExpressionContext)_localctx).r =  app;
				        
				}
				}
				setState(328);
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
			setState(329);
			match(IF);
			setState(330);
			((StmtIfContext)_localctx).cond = expression();
			 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
			setState(332);
			((StmtIfContext)_localctx).b = block();
			 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(334);
				match(ELIF);
				setState(335);
				((StmtIfContext)_localctx).cond = expression();
				 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
				setState(337);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
				}
				}
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(349);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(345);
				match(ELSE);
				setState(346);
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
			setState(351);
			match(MATCH);
			setState(352);
			((StmtMatchContext)_localctx).e = expression();
			 _localctx.r.expr = ((StmtMatchContext)_localctx).e.r; 
			setState(354);
			match(LCURLY);
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(361); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(355);
					match(CASE);
					setState(356);
					((StmtMatchContext)_localctx).t = match(NAME);
					setState(358);
					_la = _input.LA(1);
					if (_la==NAME) {
						{
						setState(357);
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
					setState(363); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE );
				setState(365);
				((StmtMatchContext)_localctx).b = block();

				            _localctx.r.block.add(((StmtMatchContext)_localctx).b.r);
				        
				}
				}
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(373);
			match(RCURLY);
			setState(378);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(374);
				match(ELSE);
				setState(375);
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
			setState(380);
			match(RETURN);
			setState(384);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STRING))) != 0)) {
				{
				setState(381);
				((StmtReturnContext)_localctx).e = expression();
				 _localctx.r.expr = ((StmtReturnContext)_localctx).e.r; 
				}
			}

			setState(386);
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
			setState(401);
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
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(388);
				((StatementContext)_localctx).e = expression();
				setState(389);
				match(SEMICOLON);
				 ((StatementContext)_localctx).r =  new AstExpr(((StatementContext)_localctx).e.r); 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				((StatementContext)_localctx).s = stmtIf();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).s.r; 
				}
				break;
			case MATCH:
				enterOuterAlt(_localctx, 3);
				{
				setState(395);
				((StatementContext)_localctx).m = stmtMatch();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).m.r; 
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(398);
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

	public static class TypeNonLambdaContext extends ParserRuleContext {
		public AstType r;
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
		public TypeNonLambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNonLambda; }
	}

	public final TypeNonLambdaContext typeNonLambda() throws RecognitionException {
		TypeNonLambdaContext _localctx = new TypeNonLambdaContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_typeNonLambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			((TypeNonLambdaContext)_localctx).n = absoluteName();
			 ((TypeNonLambdaContext)_localctx).r =  new AstType(((TypeNonLambdaContext)_localctx).n.r); 
			setState(428);
			switch (_input.LA(1)) {
			case LPAREN:
				{

				            List<AstType> typeArgs = new ArrayList<>();
				        
				setState(406);
				match(LPAREN);
				setState(407);
				((TypeNonLambdaContext)_localctx).a = typeInstance();
				 typeArgs.add(((TypeNonLambdaContext)_localctx).a.r); 
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(409);
					match(COMMA);
					setState(410);
					((TypeNonLambdaContext)_localctx).a = typeInstance();
					 typeArgs.add(((TypeNonLambdaContext)_localctx).a.r); 
					}
					}
					setState(417);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(418);
				match(RPAREN);

				            ((TypeNonLambdaContext)_localctx).r =  new AstType(_localctx.r.name, typeArgs);
				        
				}
				break;
			case LBRACKET:
				{
				setState(424); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(421);
					match(LBRACKET);
					setState(422);
					match(RBRACKET);

					                ((TypeNonLambdaContext)_localctx).r =  AstType.arrayOf(_localctx.r);
					            
					}
					}
					setState(426); 
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
		public TypeNonLambdaContext x;
		public TypeNonLambdaContext y;
		public List<TypeNonLambdaContext> typeNonLambda() {
			return getRuleContexts(TypeNonLambdaContext.class);
		}
		public TypeNonLambdaContext typeNonLambda(int i) {
			return getRuleContext(TypeNonLambdaContext.class,i);
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

			        List<AstType> typeArgs = new ArrayList<>();
			    
			setState(431);
			((TypeInstanceContext)_localctx).x = typeNonLambda();
			 typeArgs.add(((TypeInstanceContext)_localctx).x.r); 
			setState(439);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARROW) {
				{
				{
				setState(433);
				match(ARROW);
				setState(434);
				((TypeInstanceContext)_localctx).y = typeNonLambda();
				 typeArgs.add(((TypeInstanceContext)_localctx).y.r); 
				}
				}
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        if (typeArgs.size() == 1) {
			            ((TypeInstanceContext)_localctx).r =  typeArgs.get(0);
			        } else {
			            ((TypeInstanceContext)_localctx).r =  AstType.functionOf(typeArgs);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3A\u01bf\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2"+
		"B\n\2\f\2\16\2E\13\2\3\3\3\3\3\3\3\3\3\3\7\3L\n\3\f\3\16\3O\13\3\3\4\3"+
		"\4\3\4\3\4\5\4U\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13"+
		"\4\5\4c\n\4\3\4\5\4f\n\4\3\4\3\4\3\4\3\4\5\4l\n\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5w\n\5\3\6\3\6\3\6\3\6\7\6}\n\6\f\6\16\6\u0080\13\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5"+
		"\7\u0093\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a2"+
		"\n\b\f\b\16\b\u00a5\13\b\5\b\u00a7\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u00b2\n\b\f\b\16\b\u00b5\13\b\3\b\3\b\7\b\u00b9\n\b\f\b\16\b"+
		"\u00bc\13\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c4\n\t\3\n\5\n\u00c7\n\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00d2\n\13\f\13\16\13\u00d5"+
		"\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00dd\n\f\f\f\16\f\u00e0\13\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\7\r\u00e8\n\r\f\r\16\r\u00eb\13\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\7\16\u00f3\n\16\f\16\16\16\u00f6\13\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\7\17\u00fe\n\17\f\17\16\17\u0101\13\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\7\20\u0109\n\20\f\20\16\20\u010c\13\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\7\21\u0114\n\21\f\21\16\21\u0117\13\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\7\22\u011f\n\22\f\22\16\22\u0122\13\22\3\23\5\23\u0125\n"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0131\n\24"+
		"\f\24\16\24\u0134\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u013c\n\25"+
		"\f\25\16\25\u013f\13\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0147\n\26"+
		"\f\26\16\26\u014a\13\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\7\27\u0157\n\27\f\27\16\27\u015a\13\27\3\27\3\27\3\27\3\27\5"+
		"\27\u0160\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0169\n\30\3\30"+
		"\6\30\u016c\n\30\r\30\16\30\u016d\3\30\3\30\3\30\7\30\u0173\n\30\f\30"+
		"\16\30\u0176\13\30\3\30\3\30\3\30\3\30\3\30\5\30\u017d\n\30\3\31\3\31"+
		"\3\31\3\31\5\31\u0183\n\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0194\n\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\7\33\u01a0\n\33\f\33\16\33\u01a3\13\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\6\33\u01ab\n\33\r\33\16\33\u01ac\5\33\u01af"+
		"\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u01b8\n\34\f\34\16\34\u01bb"+
		"\13\34\3\34\3\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\66\2\t\4\2\32\32\37 \3\2\34\36\3\2\37 \3\2!#\3\2),\4\2"+
		"\b\b\'(\3\2/:\u01d5\2C\3\2\2\2\4F\3\2\2\2\6P\3\2\2\2\bp\3\2\2\2\nx\3\2"+
		"\2\2\f\u0092\3\2\2\2\16\u0094\3\2\2\2\20\u00bd\3\2\2\2\22\u00c6\3\2\2"+
		"\2\24\u00cb\3\2\2\2\26\u00d6\3\2\2\2\30\u00e1\3\2\2\2\32\u00ec\3\2\2\2"+
		"\34\u00f7\3\2\2\2\36\u0102\3\2\2\2 \u010d\3\2\2\2\"\u0118\3\2\2\2$\u0124"+
		"\3\2\2\2&\u012a\3\2\2\2(\u0135\3\2\2\2*\u0140\3\2\2\2,\u014b\3\2\2\2."+
		"\u0161\3\2\2\2\60\u017e\3\2\2\2\62\u0193\3\2\2\2\64\u0195\3\2\2\2\66\u01b0"+
		"\3\2\2\289\5\6\4\29:\b\2\1\2:B\3\2\2\2;<\7>\2\2<=\7/\2\2=>\5*\26\2>?\7"+
		"\17\2\2?@\b\2\1\2@B\3\2\2\2A8\3\2\2\2A;\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD"+
		"\3\2\2\2D\3\3\2\2\2EC\3\2\2\2FG\7>\2\2GM\b\3\1\2HI\7\f\2\2IJ\7>\2\2JL"+
		"\b\3\1\2KH\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\5\3\2\2\2OM\3\2\2\2"+
		"PQ\7\6\2\2QT\b\4\1\2RS\7>\2\2SU\b\4\1\2TR\3\2\2\2TU\3\2\2\2Ue\3\2\2\2"+
		"Vb\7\21\2\2WX\5\b\5\2X_\b\4\1\2YZ\7\r\2\2Z[\5\b\5\2[\\\b\4\1\2\\^\3\2"+
		"\2\2]Y\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3\2\2\2a_\3\2\2\2bW\3\2"+
		"\2\2bc\3\2\2\2cd\3\2\2\2df\7\22\2\2eV\3\2\2\2ef\3\2\2\2fk\3\2\2\2gh\7"+
		"\16\2\2hi\5\66\34\2ij\b\4\1\2jl\3\2\2\2kg\3\2\2\2kl\3\2\2\2lm\3\2\2\2"+
		"mn\5\n\6\2no\b\4\1\2o\7\3\2\2\2pq\7>\2\2qv\b\5\1\2rs\7\16\2\2st\5\66\34"+
		"\2tu\b\5\1\2uw\3\2\2\2vr\3\2\2\2vw\3\2\2\2w\t\3\2\2\2x~\7\23\2\2yz\5\62"+
		"\32\2z{\b\6\1\2{}\3\2\2\2|y\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2"+
		"\2\2\177\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\24\2\2\u0082\13\3"+
		"\2\2\2\u0083\u0084\7>\2\2\u0084\u0093\b\7\1\2\u0085\u0086\7;\2\2\u0086"+
		"\u0093\b\7\1\2\u0087\u0088\7<\2\2\u0088\u0093\b\7\1\2\u0089\u008a\7=\2"+
		"\2\u008a\u0093\b\7\1\2\u008b\u008c\7?\2\2\u008c\u0093\b\7\1\2\u008d\u008e"+
		"\7\21\2\2\u008e\u008f\5*\26\2\u008f\u0090\7\22\2\2\u0090\u0091\b\7\1\2"+
		"\u0091\u0093\3\2\2\2\u0092\u0083\3\2\2\2\u0092\u0085\3\2\2\2\u0092\u0087"+
		"\3\2\2\2\u0092\u0089\3\2\2\2\u0092\u008b\3\2\2\2\u0092\u008d\3\2\2\2\u0093"+
		"\r\3\2\2\2\u0094\u0095\5\f\7\2\u0095\u00ba\b\b\1\2\u0096\u0097\7\f\2\2"+
		"\u0097\u0098\7>\2\2\u0098\u00b9\b\b\1\2\u0099\u009a\b\b\1\2\u009a\u00a6"+
		"\7\21\2\2\u009b\u009c\5*\26\2\u009c\u00a3\b\b\1\2\u009d\u009e\7\r\2\2"+
		"\u009e\u009f\5*\26\2\u009f\u00a0\b\b\1\2\u00a0\u00a2\3\2\2\2\u00a1\u009d"+
		"\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u009b\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00b9\7\22\2\2\u00a9\u00aa\b\b\1\2\u00aa"+
		"\u00ab\7\25\2\2\u00ab\u00ac\5*\26\2\u00ac\u00b3\b\b\1\2\u00ad\u00ae\7"+
		"\r\2\2\u00ae\u00af\5*\26\2\u00af\u00b0\b\b\1\2\u00b0\u00b2\3\2\2\2\u00b1"+
		"\u00ad\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2"+
		"\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\7\26\2\2\u00b7"+
		"\u00b9\3\2\2\2\u00b8\u0096\3\2\2\2\u00b8\u0099\3\2\2\2\u00b8\u00a9\3\2"+
		"\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\17\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\5\16\b\2\u00be\u00c3\b\t\1"+
		"\2\u00bf\u00c0\7\16\2\2\u00c0\u00c1\5\66\34\2\u00c1\u00c2\b\t\1\2\u00c2"+
		"\u00c4\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\21\3\2\2"+
		"\2\u00c5\u00c7\t\2\2\2\u00c6\u00c5\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00c9\5\20\t\2\u00c9\u00ca\b\n\1\2\u00ca\23\3\2\2\2\u00cb"+
		"\u00cc\5\22\n\2\u00cc\u00d3\b\13\1\2\u00cd\u00ce\t\3\2\2\u00ce\u00cf\5"+
		"\22\n\2\u00cf\u00d0\b\13\1\2\u00d0\u00d2\3\2\2\2\u00d1\u00cd\3\2\2\2\u00d2"+
		"\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\25\3\2\2"+
		"\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\5\24\13\2\u00d7\u00de\b\f\1\2\u00d8"+
		"\u00d9\t\4\2\2\u00d9\u00da\5\24\13\2\u00da\u00db\b\f\1\2\u00db\u00dd\3"+
		"\2\2\2\u00dc\u00d8\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de"+
		"\u00df\3\2\2\2\u00df\27\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\5\26\f"+
		"\2\u00e2\u00e9\b\r\1\2\u00e3\u00e4\t\5\2\2\u00e4\u00e5\5\26\f\2\u00e5"+
		"\u00e6\b\r\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e8\u00eb\3\2"+
		"\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\31\3\2\2\2\u00eb\u00e9"+
		"\3\2\2\2\u00ec\u00ed\5\30\r\2\u00ed\u00f4\b\16\1\2\u00ee\u00ef\7$\2\2"+
		"\u00ef\u00f0\5\30\r\2\u00f0\u00f1\b\16\1\2\u00f1\u00f3\3\2\2\2\u00f2\u00ee"+
		"\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\33\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\5\32\16\2\u00f8\u00ff\b\17"+
		"\1\2\u00f9\u00fa\7%\2\2\u00fa\u00fb\5\32\16\2\u00fb\u00fc\b\17\1\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00f9\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2"+
		"\2\2\u00ff\u0100\3\2\2\2\u0100\35\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103"+
		"\5\34\17\2\u0103\u010a\b\20\1\2\u0104\u0105\7&\2\2\u0105\u0106\5\34\17"+
		"\2\u0106\u0107\b\20\1\2\u0107\u0109\3\2\2\2\u0108\u0104\3\2\2\2\u0109"+
		"\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\37\3\2\2"+
		"\2\u010c\u010a\3\2\2\2\u010d\u010e\5\36\20\2\u010e\u0115\b\21\1\2\u010f"+
		"\u0110\t\6\2\2\u0110\u0111\5\36\20\2\u0111\u0112\b\21\1\2\u0112\u0114"+
		"\3\2\2\2\u0113\u010f\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116!\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0119\5 \21\2"+
		"\u0119\u0120\b\22\1\2\u011a\u011b\t\7\2\2\u011b\u011c\5 \21\2\u011c\u011d"+
		"\b\22\1\2\u011d\u011f\3\2\2\2\u011e\u011a\3\2\2\2\u011f\u0122\3\2\2\2"+
		"\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121#\3\2\2\2\u0122\u0120\3"+
		"\2\2\2\u0123\u0125\7\33\2\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\u0127\5\"\22\2\u0127\u0128\b\23\1\2\u0128\u0129\b"+
		"\23\1\2\u0129%\3\2\2\2\u012a\u012b\5$\23\2\u012b\u0132\b\24\1\2\u012c"+
		"\u012d\7-\2\2\u012d\u012e\5$\23\2\u012e\u012f\b\24\1\2\u012f\u0131\3\2"+
		"\2\2\u0130\u012c\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\'\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0136\5&\24\2"+
		"\u0136\u013d\b\25\1\2\u0137\u0138\7.\2\2\u0138\u0139\5&\24\2\u0139\u013a"+
		"\b\25\1\2\u013a\u013c\3\2\2\2\u013b\u0137\3\2\2\2\u013c\u013f\3\2\2\2"+
		"\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e)\3\2\2\2\u013f\u013d\3"+
		"\2\2\2\u0140\u0141\5(\25\2\u0141\u0148\b\26\1\2\u0142\u0143\t\b\2\2\u0143"+
		"\u0144\5(\25\2\u0144\u0145\b\26\1\2\u0145\u0147\3\2\2\2\u0146\u0142\3"+
		"\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"+\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014c\7\7\2\2\u014c\u014d\5*\26\2"+
		"\u014d\u014e\b\27\1\2\u014e\u014f\5\n\6\2\u014f\u0158\b\27\1\2\u0150\u0151"+
		"\7\4\2\2\u0151\u0152\5*\26\2\u0152\u0153\b\27\1\2\u0153\u0154\5\n\6\2"+
		"\u0154\u0155\b\27\1\2\u0155\u0157\3\2\2\2\u0156\u0150\3\2\2\2\u0157\u015a"+
		"\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015f\3\2\2\2\u015a"+
		"\u0158\3\2\2\2\u015b\u015c\7\5\2\2\u015c\u015d\5\n\6\2\u015d\u015e\b\27"+
		"\1\2\u015e\u0160\3\2\2\2\u015f\u015b\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"-\3\2\2\2\u0161\u0162\7\t\2\2\u0162\u0163\5*\26\2\u0163\u0164\b\30\1\2"+
		"\u0164\u0174\7\23\2\2\u0165\u0166\7\3\2\2\u0166\u0168\7>\2\2\u0167\u0169"+
		"\7>\2\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016c\b\30\1\2\u016b\u0165\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016b\3"+
		"\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\5\n\6\2\u0170"+
		"\u0171\b\30\1\2\u0171\u0173\3\2\2\2\u0172\u016b\3\2\2\2\u0173\u0176\3"+
		"\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176"+
		"\u0174\3\2\2\2\u0177\u017c\7\24\2\2\u0178\u0179\7\5\2\2\u0179\u017a\5"+
		"\n\6\2\u017a\u017b\b\30\1\2\u017b\u017d\3\2\2\2\u017c\u0178\3\2\2\2\u017c"+
		"\u017d\3\2\2\2\u017d/\3\2\2\2\u017e\u0182\7\13\2\2\u017f\u0180\5*\26\2"+
		"\u0180\u0181\b\31\1\2\u0181\u0183\3\2\2\2\u0182\u017f\3\2\2\2\u0182\u0183"+
		"\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\7\17\2\2\u0185\61\3\2\2\2\u0186"+
		"\u0187\5*\26\2\u0187\u0188\7\17\2\2\u0188\u0189\b\32\1\2\u0189\u0194\3"+
		"\2\2\2\u018a\u018b\5,\27\2\u018b\u018c\b\32\1\2\u018c\u0194\3\2\2\2\u018d"+
		"\u018e\5.\30\2\u018e\u018f\b\32\1\2\u018f\u0194\3\2\2\2\u0190\u0191\5"+
		"\60\31\2\u0191\u0192\b\32\1\2\u0192\u0194\3\2\2\2\u0193\u0186\3\2\2\2"+
		"\u0193\u018a\3\2\2\2\u0193\u018d\3\2\2\2\u0193\u0190\3\2\2\2\u0194\63"+
		"\3\2\2\2\u0195\u0196\5\4\3\2\u0196\u01ae\b\33\1\2\u0197\u0198\b\33\1\2"+
		"\u0198\u0199\7\21\2\2\u0199\u019a\5\66\34\2\u019a\u01a1\b\33\1\2\u019b"+
		"\u019c\7\r\2\2\u019c\u019d\5\66\34\2\u019d\u019e\b\33\1\2\u019e\u01a0"+
		"\3\2\2\2\u019f\u019b\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1"+
		"\u01a2\3\2\2\2\u01a2\u01a4\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4\u01a5\7\22"+
		"\2\2\u01a5\u01a6\b\33\1\2\u01a6\u01af\3\2\2\2\u01a7\u01a8\7\25\2\2\u01a8"+
		"\u01a9\7\26\2\2\u01a9\u01ab\b\33\1\2\u01aa\u01a7\3\2\2\2\u01ab\u01ac\3"+
		"\2\2\2\u01ac\u01aa\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01af\3\2\2\2\u01ae"+
		"\u0197\3\2\2\2\u01ae\u01aa\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\65\3\2\2"+
		"\2\u01b0\u01b1\b\34\1\2\u01b1\u01b2\5\64\33\2\u01b2\u01b9\b\34\1\2\u01b3"+
		"\u01b4\7\20\2\2\u01b4\u01b5\5\64\33\2\u01b5\u01b6\b\34\1\2\u01b6\u01b8"+
		"\3\2\2\2\u01b7\u01b3\3\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9"+
		"\u01ba\3\2\2\2\u01ba\u01bc\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc\u01bd\b\34"+
		"\1\2\u01bd\67\3\2\2\2,ACMT_bekv~\u0092\u00a3\u00a6\u00b3\u00b8\u00ba\u00c3"+
		"\u00c6\u00d3\u00de\u00e9\u00f4\u00ff\u010a\u0115\u0120\u0124\u0132\u013d"+
		"\u0148\u0158\u015f\u0168\u016d\u0174\u017c\u0182\u0193\u01a1\u01ac\u01ae"+
		"\u01b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}