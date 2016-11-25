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
		CASE=1, ELIF=2, ELSE=3, FN=4, IF=5, IS=6, MATCH=7, NEW=8, DOT=9, COMMA=10, 
		COLON=11, SEMICOLON=12, ARROW=13, LPAREN=14, RPAREN=15, LCURLY=16, RCURLY=17, 
		LBRACKET=18, RBRACKET=19, QUOTE=20, DBL_QUOTE=21, AT=22, TILDE=23, EXC=24, 
		MUL=25, DIV=26, MOD=27, PLS=28, MNS=29, LSHIFT=30, RSHIFT=31, SIGNED_RSHIFT=32, 
		BIT_AND=33, BIT_XOR=34, BIT_OR=35, EQ=36, NE=37, LE=38, LT=39, GE=40, 
		GT=41, AND=42, OR=43, ASSIGN=44, ASSIGN_MUL=45, ASSIGN_DIV=46, ASSIGN_MOD=47, 
		ASSIGN_PLS=48, ASSIGN_MNS=49, ASSIGN_LSHIFT=50, ASSIGN_RSHIFT=51, ASSIGN_SIGNED_RSHIFT=52, 
		ASSIGN_BIT_AND=53, ASSIGN_BIT_XOR=54, ASSIGN_BIT_OR=55, OCT=56, DEC=57, 
		HEX=58, NAME=59, COMMENT=60, WS=61;
	public static final int
		RULE_module = 0, RULE_absoluteName = 1, RULE_function = 2, RULE_variable = 3, 
		RULE_block = 4, RULE_exprPrime = 5, RULE_exprApply = 6, RULE_exprCoerce = 7, 
		RULE_exprUnary = 8, RULE_exprMulDiv = 9, RULE_exprAddSub = 10, RULE_exprShift = 11, 
		RULE_exprBitAnd = 12, RULE_exprBitXor = 13, RULE_exprBitOr = 14, RULE_exprComparison = 15, 
		RULE_exprEquality = 16, RULE_exprNot = 17, RULE_exprAnd = 18, RULE_exprOr = 19, 
		RULE_expression = 20, RULE_stmtIf = 21, RULE_stmtMatch = 22, RULE_statement = 23, 
		RULE_typeNonFn = 24, RULE_typeInstance = 25;
	public static final String[] ruleNames = {
		"module", "absoluteName", "function", "variable", "block", "exprPrime", 
		"exprApply", "exprCoerce", "exprUnary", "exprMulDiv", "exprAddSub", "exprShift", 
		"exprBitAnd", "exprBitXor", "exprBitOr", "exprComparison", "exprEquality", 
		"exprNot", "exprAnd", "exprOr", "expression", "stmtIf", "stmtMatch", "statement", 
		"typeNonFn", "typeInstance"
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
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
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
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FN) {
				{
				{
				setState(52);
				((ModuleContext)_localctx).f = function();
				 _localctx.r.functions.add(((ModuleContext)_localctx).f.r); 
				}
				}
				setState(59);
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
			setState(60);
			((AbsoluteNameContext)_localctx).NAME = match(NAME);
			 _localctx.r.name += (((AbsoluteNameContext)_localctx).NAME!=null?((AbsoluteNameContext)_localctx).NAME.getText():null); 
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(62);
				match(DOT);
				setState(63);
				((AbsoluteNameContext)_localctx).NAME = match(NAME);
				 _localctx.r.name += "." + (((AbsoluteNameContext)_localctx).NAME!=null?((AbsoluteNameContext)_localctx).NAME.getText():null); 
				}
				}
				setState(69);
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
		public VariableContext a;
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
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
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
			setState(70);
			match(FN);
			setState(73);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(71);
				((FunctionContext)_localctx).NAME = match(NAME);
				 _localctx.r.name = (((FunctionContext)_localctx).NAME!=null?((FunctionContext)_localctx).NAME.getText():null); 
				}
			}

			setState(90);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(75);
				match(LPAREN);
				setState(87);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(76);
					((FunctionContext)_localctx).a = variable();
					 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
					setState(84);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(78);
						match(COMMA);
						setState(79);
						((FunctionContext)_localctx).a = variable();
						 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
						}
						}
						setState(86);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(89);
				match(RPAREN);
				}
			}

			setState(96);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(92);
				match(COLON);
				setState(93);
				((FunctionContext)_localctx).type = typeInstance();
				 _localctx.r.returnType = ((FunctionContext)_localctx).type.r; 
				}
			}

			setState(98);
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

	public static class VariableContext extends ParserRuleContext {
		public AstVariable r =  new AstVariable();
		public Token NAME;
		public TypeInstanceContext t;
		public TerminalNode NAME() { return getToken(OwlParser.NAME, 0); }
		public TerminalNode COLON() { return getToken(OwlParser.COLON, 0); }
		public TypeInstanceContext typeInstance() {
			return getRuleContext(TypeInstanceContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			((VariableContext)_localctx).NAME = match(NAME);
			 _localctx.r.name = (((VariableContext)_localctx).NAME!=null?((VariableContext)_localctx).NAME.getText():null); 
			setState(107);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(103);
				match(COLON);
				setState(104);
				((VariableContext)_localctx).t = typeInstance();
				 _localctx.r.type = ((VariableContext)_localctx).t.r; 
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
			setState(109);
			match(LCURLY);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << MATCH) | (1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME))) != 0)) {
				{
				{
				setState(110);
				((BlockContext)_localctx).s = statement();
				 _localctx.r.statements.add(((BlockContext)_localctx).s.r); 
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(118);
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
		public ExpressionContext e;
		public TerminalNode NAME() { return getToken(OwlParser.NAME, 0); }
		public TerminalNode OCT() { return getToken(OwlParser.OCT, 0); }
		public TerminalNode DEC() { return getToken(OwlParser.DEC, 0); }
		public TerminalNode HEX() { return getToken(OwlParser.HEX, 0); }
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
			setState(133);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				((ExprPrimeContext)_localctx).NAME = match(NAME);
				 ((ExprPrimeContext)_localctx).r =  new AstName((((ExprPrimeContext)_localctx).NAME!=null?((ExprPrimeContext)_localctx).NAME.getText():null)); 
				}
				break;
			case OCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				((ExprPrimeContext)_localctx).OCT = match(OCT);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).OCT!=null?((ExprPrimeContext)_localctx).OCT.getText():null)); 
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				((ExprPrimeContext)_localctx).DEC = match(DEC);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).DEC!=null?((ExprPrimeContext)_localctx).DEC.getText():null)); 
				}
				break;
			case HEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				((ExprPrimeContext)_localctx).HEX = match(HEX);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).HEX!=null?((ExprPrimeContext)_localctx).HEX.getText():null)); 
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
				match(LPAREN);
				setState(129);
				((ExprPrimeContext)_localctx).e = expression();
				setState(130);
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
			setState(135);
			((ExprApplyContext)_localctx).x = exprPrime();
			 ((ExprApplyContext)_localctx).r =  ((ExprApplyContext)_localctx).x.r; 
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOT) | (1L << LPAREN) | (1L << LBRACKET))) != 0)) {
				{
				setState(171);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(137);
					match(DOT);
					setState(138);
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
					        
					setState(141);
					match(LPAREN);
					setState(153);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME))) != 0)) {
						{
						setState(142);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						setState(150);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(144);
							match(COMMA);
							setState(145);
							((ExprApplyContext)_localctx).a = expression();
							 app.args.add(((ExprApplyContext)_localctx).a.r); 
							}
							}
							setState(152);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(155);
					match(RPAREN);
					}
					break;
				case LBRACKET:
					{

					            AstApply app = new AstApply();
					            app.args.add(new AstName("[]"));
					            app.args.add(_localctx.r);
					            ((ExprApplyContext)_localctx).r =  app;
					        
					setState(157);
					match(LBRACKET);
					setState(158);
					((ExprApplyContext)_localctx).a = expression();
					 app.args.add(((ExprApplyContext)_localctx).a.r); 
					setState(166);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(160);
						match(COMMA);
						setState(161);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						}
						}
						setState(168);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(169);
					match(RBRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(175);
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
			setState(176);
			((ExprCoerceContext)_localctx).x = exprApply();
			 ((ExprCoerceContext)_localctx).r =  ((ExprCoerceContext)_localctx).x.r; 
			setState(182);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(178);
				match(COLON);
				setState(179);
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
			setState(185);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) {
				{
				setState(184);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(187);
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
			setState(190);
			((ExprMulDivContext)_localctx).x = exprUnary();
			 ((ExprMulDivContext)_localctx).r =  ((ExprMulDivContext)_localctx).x.r; 
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(192);
				((ExprMulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					((ExprMulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(193);
				((ExprMulDivContext)_localctx).y = exprUnary();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprMulDivContext)_localctx).op!=null?((ExprMulDivContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprMulDivContext)_localctx).y.r);
				            ((ExprMulDivContext)_localctx).r =  app;
				        
				}
				}
				setState(200);
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
			setState(201);
			((ExprAddSubContext)_localctx).x = exprMulDiv();
			 ((ExprAddSubContext)_localctx).r =  ((ExprAddSubContext)_localctx).x.r; 
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLS || _la==MNS) {
				{
				{
				setState(203);
				((ExprAddSubContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLS || _la==MNS) ) {
					((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(204);
				((ExprAddSubContext)_localctx).y = exprMulDiv();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAddSubContext)_localctx).op!=null?((ExprAddSubContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAddSubContext)_localctx).y.r);
				            ((ExprAddSubContext)_localctx).r =  app;
				        
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
			setState(212);
			((ExprShiftContext)_localctx).x = exprAddSub();
			 ((ExprShiftContext)_localctx).r =  ((ExprShiftContext)_localctx).x.r; 
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) {
				{
				{
				setState(214);
				((ExprShiftContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) ) {
					((ExprShiftContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(215);
				((ExprShiftContext)_localctx).y = exprAddSub();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprShiftContext)_localctx).op!=null?((ExprShiftContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprShiftContext)_localctx).y.r);
				            ((ExprShiftContext)_localctx).r =  app;
				        
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
			setState(223);
			((ExprBitAndContext)_localctx).x = exprShift();
			 ((ExprBitAndContext)_localctx).r =  ((ExprBitAndContext)_localctx).x.r; 
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_AND) {
				{
				{
				setState(225);
				((ExprBitAndContext)_localctx).op = match(BIT_AND);
				setState(226);
				((ExprBitAndContext)_localctx).y = exprShift();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitAndContext)_localctx).op!=null?((ExprBitAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitAndContext)_localctx).y.r);
				            ((ExprBitAndContext)_localctx).r =  app;
				        
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
			setState(234);
			((ExprBitXorContext)_localctx).x = exprBitAnd();
			 ((ExprBitXorContext)_localctx).r =  ((ExprBitXorContext)_localctx).x.r; 
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_XOR) {
				{
				{
				setState(236);
				((ExprBitXorContext)_localctx).op = match(BIT_XOR);
				setState(237);
				((ExprBitXorContext)_localctx).y = exprBitAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitXorContext)_localctx).op!=null?((ExprBitXorContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitXorContext)_localctx).y.r);
				            ((ExprBitXorContext)_localctx).r =  app;
				        
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
			setState(245);
			((ExprBitOrContext)_localctx).x = exprBitXor();
			 ((ExprBitOrContext)_localctx).r =  ((ExprBitOrContext)_localctx).x.r; 
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_OR) {
				{
				{
				setState(247);
				((ExprBitOrContext)_localctx).op = match(BIT_OR);
				setState(248);
				((ExprBitOrContext)_localctx).y = exprBitXor();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitOrContext)_localctx).op!=null?((ExprBitOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitOrContext)_localctx).y.r);
				            ((ExprBitOrContext)_localctx).r =  app;
				        
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
			setState(256);
			((ExprComparisonContext)_localctx).x = exprBitOr();
			 ((ExprComparisonContext)_localctx).r =  ((ExprComparisonContext)_localctx).x.r; 
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) {
				{
				{
				setState(258);
				((ExprComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) ) {
					((ExprComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(259);
				((ExprComparisonContext)_localctx).y = exprBitOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprComparisonContext)_localctx).op!=null?((ExprComparisonContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprComparisonContext)_localctx).y.r);
				            ((ExprComparisonContext)_localctx).r =  app;
				        
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
			setState(267);
			((ExprEqualityContext)_localctx).x = exprComparison();
			 ((ExprEqualityContext)_localctx).r =  ((ExprEqualityContext)_localctx).x.r; 
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) {
				{
				{
				setState(269);
				((ExprEqualityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) ) {
					((ExprEqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(270);
				((ExprEqualityContext)_localctx).y = exprComparison();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprEqualityContext)_localctx).op!=null?((ExprEqualityContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprEqualityContext)_localctx).y.r);
				            ((ExprEqualityContext)_localctx).r =  app;
				        
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
			setState(279);
			_la = _input.LA(1);
			if (_la==EXC) {
				{
				setState(278);
				((ExprNotContext)_localctx).op = match(EXC);
				}
			}

			setState(281);
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
			setState(285);
			((ExprAndContext)_localctx).x = exprNot();
			 ((ExprAndContext)_localctx).r =  ((ExprAndContext)_localctx).x.r; 
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(287);
				((ExprAndContext)_localctx).op = match(AND);
				setState(288);
				((ExprAndContext)_localctx).y = exprNot();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAndContext)_localctx).op!=null?((ExprAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAndContext)_localctx).y.r);
				            ((ExprAndContext)_localctx).r =  app;
				        
				}
				}
				setState(295);
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
			setState(296);
			((ExprOrContext)_localctx).x = exprAnd();
			 ((ExprOrContext)_localctx).r =  ((ExprOrContext)_localctx).x.r; 
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(298);
				((ExprOrContext)_localctx).op = match(OR);
				setState(299);
				((ExprOrContext)_localctx).y = exprAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprOrContext)_localctx).op!=null?((ExprOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprOrContext)_localctx).y.r);
				            ((ExprOrContext)_localctx).r =  app;
				        
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
			setState(307);
			((ExpressionContext)_localctx).x = exprOr();
			 ((ExpressionContext)_localctx).r =  ((ExpressionContext)_localctx).x.r; 
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) {
				{
				{
				setState(309);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(310);
				((ExpressionContext)_localctx).y = exprOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExpressionContext)_localctx).y.r);
				            ((ExpressionContext)_localctx).r =  app;
				        
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
			setState(318);
			match(IF);
			setState(319);
			((StmtIfContext)_localctx).cond = expression();
			 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
			setState(321);
			((StmtIfContext)_localctx).b = block();
			 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(323);
				match(ELIF);
				setState(324);
				((StmtIfContext)_localctx).cond = expression();
				 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
				setState(326);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
				}
				}
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(338);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(334);
				match(ELSE);
				setState(335);
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
			setState(340);
			match(MATCH);
			setState(341);
			((StmtMatchContext)_localctx).e = expression();
			 _localctx.r.expr = ((StmtMatchContext)_localctx).e.r; 
			setState(343);
			match(LCURLY);
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(350); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(344);
					match(CASE);
					setState(345);
					((StmtMatchContext)_localctx).t = match(NAME);
					setState(347);
					_la = _input.LA(1);
					if (_la==NAME) {
						{
						setState(346);
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
					setState(352); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE );
				setState(354);
				((StmtMatchContext)_localctx).b = block();

				            _localctx.r.block.add(((StmtMatchContext)_localctx).b.r);
				        
				}
				}
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(362);
			match(RCURLY);
			setState(367);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(363);
				match(ELSE);
				setState(364);
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

	public static class StatementContext extends ParserRuleContext {
		public AstNode r;
		public ExpressionContext e;
		public StmtIfContext s;
		public StmtMatchContext m;
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
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_statement);
		try {
			setState(379);
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
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				((StatementContext)_localctx).e = expression();
				setState(370);
				match(SEMICOLON);
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).e.r; 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				((StatementContext)_localctx).s = stmtIf();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).s.r; 
				}
				break;
			case MATCH:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				((StatementContext)_localctx).m = stmtMatch();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).m.r; 
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
		enterRule(_localctx, 48, RULE_typeNonFn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			((TypeNonFnContext)_localctx).n = absoluteName();
			 _localctx.r.name = ((TypeNonFnContext)_localctx).n.r; 
			setState(404);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(383);
				match(LPAREN);
				setState(384);
				((TypeNonFnContext)_localctx).a = typeInstance();
				 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
				setState(392);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(386);
					match(COMMA);
					setState(387);
					((TypeNonFnContext)_localctx).a = typeInstance();
					 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
					}
					}
					setState(394);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(395);
				match(RPAREN);
				}
				break;
			case LBRACKET:
				{
				setState(400); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(397);
					match(LBRACKET);
					setState(398);
					match(RBRACKET);

					                AstType arrayType = new AstType("Array");
					                arrayType.args.add(_localctx.r);
					                ((TypeNonFnContext)_localctx).r =  arrayType;
					            
					}
					}
					setState(402); 
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
		enterRule(_localctx, 50, RULE_typeInstance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 AstType functionType = null; 
			setState(407);
			((TypeInstanceContext)_localctx).x = typeNonFn();
			 ((TypeInstanceContext)_localctx).r =  ((TypeInstanceContext)_localctx).x.r; 
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARROW) {
				{
				{
				setState(409);
				match(ARROW);
				setState(410);
				((TypeInstanceContext)_localctx).y = typeNonFn();

				            if (functionType == null) {
				                functionType = new AstType("Fn");
				                functionType.args.add(_localctx.r);
				                ((TypeInstanceContext)_localctx).r =  functionType;
				            }
				            functionType.args.add(((TypeInstanceContext)_localctx).y.r);
				        
				}
				}
				setState(417);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3?\u01a5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\2\7\2:\n\2\f\2\16\2=\13\2\3\3\3\3\3\3\3"+
		"\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3\4\3\4\3\4\5\4L\n\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4U\n\4\f\4\16\4X\13\4\5\4Z\n\4\3\4\5\4]\n\4\3\4\3\4\3\4\3"+
		"\4\5\4c\n\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5n\n\5\3\6\3\6\3\6\3"+
		"\6\7\6t\n\6\f\6\16\6w\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7\u0088\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\b\u0097\n\b\f\b\16\b\u009a\13\b\5\b\u009c\n\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a7\n\b\f\b\16\b\u00aa\13\b\3\b\3\b\7"+
		"\b\u00ae\n\b\f\b\16\b\u00b1\13\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00b9\n\t"+
		"\3\n\5\n\u00bc\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c7"+
		"\n\13\f\13\16\13\u00ca\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00d2\n\f\f\f"+
		"\16\f\u00d5\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00dd\n\r\f\r\16\r\u00e0"+
		"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00e8\n\16\f\16\16\16\u00eb\13"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00f3\n\17\f\17\16\17\u00f6\13"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00fe\n\20\f\20\16\20\u0101\13"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0109\n\21\f\21\16\21\u010c\13"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0114\n\22\f\22\16\22\u0117\13"+
		"\22\3\23\5\23\u011a\n\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\7\24\u0126\n\24\f\24\16\24\u0129\13\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\7\25\u0131\n\25\f\25\16\25\u0134\13\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\7\26\u013c\n\26\f\26\16\26\u013f\13\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u014c\n\27\f\27\16\27\u014f\13\27"+
		"\3\27\3\27\3\27\3\27\5\27\u0155\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\5\30\u015e\n\30\3\30\6\30\u0161\n\30\r\30\16\30\u0162\3\30\3\30\3\30"+
		"\7\30\u0168\n\30\f\30\16\30\u016b\13\30\3\30\3\30\3\30\3\30\3\30\5\30"+
		"\u0172\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u017e"+
		"\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u0189\n\32\f\32"+
		"\16\32\u018c\13\32\3\32\3\32\3\32\3\32\3\32\6\32\u0193\n\32\r\32\16\32"+
		"\u0194\5\32\u0197\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u01a0\n"+
		"\33\f\33\16\33\u01a3\13\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\2\t\4\2\31\31\36\37\3\2\33\35\3\2\36\37\3\2 "+
		"\"\3\2(+\4\2\b\b&\'\3\2.9\u01b8\2;\3\2\2\2\4>\3\2\2\2\6H\3\2\2\2\bg\3"+
		"\2\2\2\no\3\2\2\2\f\u0087\3\2\2\2\16\u0089\3\2\2\2\20\u00b2\3\2\2\2\22"+
		"\u00bb\3\2\2\2\24\u00c0\3\2\2\2\26\u00cb\3\2\2\2\30\u00d6\3\2\2\2\32\u00e1"+
		"\3\2\2\2\34\u00ec\3\2\2\2\36\u00f7\3\2\2\2 \u0102\3\2\2\2\"\u010d\3\2"+
		"\2\2$\u0119\3\2\2\2&\u011f\3\2\2\2(\u012a\3\2\2\2*\u0135\3\2\2\2,\u0140"+
		"\3\2\2\2.\u0156\3\2\2\2\60\u017d\3\2\2\2\62\u017f\3\2\2\2\64\u0198\3\2"+
		"\2\2\66\67\5\6\4\2\678\b\2\1\28:\3\2\2\29\66\3\2\2\2:=\3\2\2\2;9\3\2\2"+
		"\2;<\3\2\2\2<\3\3\2\2\2=;\3\2\2\2>?\7=\2\2?E\b\3\1\2@A\7\13\2\2AB\7=\2"+
		"\2BD\b\3\1\2C@\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\5\3\2\2\2GE\3\2"+
		"\2\2HK\7\6\2\2IJ\7=\2\2JL\b\4\1\2KI\3\2\2\2KL\3\2\2\2L\\\3\2\2\2MY\7\20"+
		"\2\2NO\5\b\5\2OV\b\4\1\2PQ\7\f\2\2QR\5\b\5\2RS\b\4\1\2SU\3\2\2\2TP\3\2"+
		"\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WZ\3\2\2\2XV\3\2\2\2YN\3\2\2\2YZ\3\2"+
		"\2\2Z[\3\2\2\2[]\7\21\2\2\\M\3\2\2\2\\]\3\2\2\2]b\3\2\2\2^_\7\r\2\2_`"+
		"\5\64\33\2`a\b\4\1\2ac\3\2\2\2b^\3\2\2\2bc\3\2\2\2cd\3\2\2\2de\5\n\6\2"+
		"ef\b\4\1\2f\7\3\2\2\2gh\7=\2\2hm\b\5\1\2ij\7\r\2\2jk\5\64\33\2kl\b\5\1"+
		"\2ln\3\2\2\2mi\3\2\2\2mn\3\2\2\2n\t\3\2\2\2ou\7\22\2\2pq\5\60\31\2qr\b"+
		"\6\1\2rt\3\2\2\2sp\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3"+
		"\2\2\2xy\7\23\2\2y\13\3\2\2\2z{\7=\2\2{\u0088\b\7\1\2|}\7:\2\2}\u0088"+
		"\b\7\1\2~\177\7;\2\2\177\u0088\b\7\1\2\u0080\u0081\7<\2\2\u0081\u0088"+
		"\b\7\1\2\u0082\u0083\7\20\2\2\u0083\u0084\5*\26\2\u0084\u0085\7\21\2\2"+
		"\u0085\u0086\b\7\1\2\u0086\u0088\3\2\2\2\u0087z\3\2\2\2\u0087|\3\2\2\2"+
		"\u0087~\3\2\2\2\u0087\u0080\3\2\2\2\u0087\u0082\3\2\2\2\u0088\r\3\2\2"+
		"\2\u0089\u008a\5\f\7\2\u008a\u00af\b\b\1\2\u008b\u008c\7\13\2\2\u008c"+
		"\u008d\7=\2\2\u008d\u00ae\b\b\1\2\u008e\u008f\b\b\1\2\u008f\u009b\7\20"+
		"\2\2\u0090\u0091\5*\26\2\u0091\u0098\b\b\1\2\u0092\u0093\7\f\2\2\u0093"+
		"\u0094\5*\26\2\u0094\u0095\b\b\1\2\u0095\u0097\3\2\2\2\u0096\u0092\3\2"+
		"\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u0090\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\u00ae\7\21\2\2\u009e\u009f\b\b\1\2\u009f"+
		"\u00a0\7\24\2\2\u00a0\u00a1\5*\26\2\u00a1\u00a8\b\b\1\2\u00a2\u00a3\7"+
		"\f\2\2\u00a3\u00a4\5*\26\2\u00a4\u00a5\b\b\1\2\u00a5\u00a7\3\2\2\2\u00a6"+
		"\u00a2\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7\25\2\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u008b\3\2\2\2\u00ad\u008e\3\2\2\2\u00ad\u009e\3\2"+
		"\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\17\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\5\16\b\2\u00b3\u00b8\b\t\1"+
		"\2\u00b4\u00b5\7\r\2\2\u00b5\u00b6\5\64\33\2\u00b6\u00b7\b\t\1\2\u00b7"+
		"\u00b9\3\2\2\2\u00b8\u00b4\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\21\3\2\2"+
		"\2\u00ba\u00bc\t\2\2\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bd\u00be\5\20\t\2\u00be\u00bf\b\n\1\2\u00bf\23\3\2\2\2\u00c0"+
		"\u00c1\5\22\n\2\u00c1\u00c8\b\13\1\2\u00c2\u00c3\t\3\2\2\u00c3\u00c4\5"+
		"\22\n\2\u00c4\u00c5\b\13\1\2\u00c5\u00c7\3\2\2\2\u00c6\u00c2\3\2\2\2\u00c7"+
		"\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\25\3\2\2"+
		"\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc\5\24\13\2\u00cc\u00d3\b\f\1\2\u00cd"+
		"\u00ce\t\4\2\2\u00ce\u00cf\5\24\13\2\u00cf\u00d0\b\f\1\2\u00d0\u00d2\3"+
		"\2\2\2\u00d1\u00cd\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\27\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\5\26\f"+
		"\2\u00d7\u00de\b\r\1\2\u00d8\u00d9\t\5\2\2\u00d9\u00da\5\26\f\2\u00da"+
		"\u00db\b\r\1\2\u00db\u00dd\3\2\2\2\u00dc\u00d8\3\2\2\2\u00dd\u00e0\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\31\3\2\2\2\u00e0\u00de"+
		"\3\2\2\2\u00e1\u00e2\5\30\r\2\u00e2\u00e9\b\16\1\2\u00e3\u00e4\7#\2\2"+
		"\u00e4\u00e5\5\30\r\2\u00e5\u00e6\b\16\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3"+
		"\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\33\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\5\32\16\2\u00ed\u00f4\b\17"+
		"\1\2\u00ee\u00ef\7$\2\2\u00ef\u00f0\5\32\16\2\u00f0\u00f1\b\17\1\2\u00f1"+
		"\u00f3\3\2\2\2\u00f2\u00ee\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2"+
		"\2\2\u00f4\u00f5\3\2\2\2\u00f5\35\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8"+
		"\5\34\17\2\u00f8\u00ff\b\20\1\2\u00f9\u00fa\7%\2\2\u00fa\u00fb\5\34\17"+
		"\2\u00fb\u00fc\b\20\1\2\u00fc\u00fe\3\2\2\2\u00fd\u00f9\3\2\2\2\u00fe"+
		"\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\37\3\2\2"+
		"\2\u0101\u00ff\3\2\2\2\u0102\u0103\5\36\20\2\u0103\u010a\b\21\1\2\u0104"+
		"\u0105\t\6\2\2\u0105\u0106\5\36\20\2\u0106\u0107\b\21\1\2\u0107\u0109"+
		"\3\2\2\2\u0108\u0104\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b!\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010e\5 \21\2"+
		"\u010e\u0115\b\22\1\2\u010f\u0110\t\7\2\2\u0110\u0111\5 \21\2\u0111\u0112"+
		"\b\22\1\2\u0112\u0114\3\2\2\2\u0113\u010f\3\2\2\2\u0114\u0117\3\2\2\2"+
		"\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116#\3\2\2\2\u0117\u0115\3"+
		"\2\2\2\u0118\u011a\7\32\2\2\u0119\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u011c\5\"\22\2\u011c\u011d\b\23\1\2\u011d\u011e\b"+
		"\23\1\2\u011e%\3\2\2\2\u011f\u0120\5$\23\2\u0120\u0127\b\24\1\2\u0121"+
		"\u0122\7,\2\2\u0122\u0123\5$\23\2\u0123\u0124\b\24\1\2\u0124\u0126\3\2"+
		"\2\2\u0125\u0121\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\'\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012b\5&\24\2"+
		"\u012b\u0132\b\25\1\2\u012c\u012d\7-\2\2\u012d\u012e\5&\24\2\u012e\u012f"+
		"\b\25\1\2\u012f\u0131\3\2\2\2\u0130\u012c\3\2\2\2\u0131\u0134\3\2\2\2"+
		"\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133)\3\2\2\2\u0134\u0132\3"+
		"\2\2\2\u0135\u0136\5(\25\2\u0136\u013d\b\26\1\2\u0137\u0138\t\b\2\2\u0138"+
		"\u0139\5(\25\2\u0139\u013a\b\26\1\2\u013a\u013c\3\2\2\2\u013b\u0137\3"+
		"\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"+\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0141\7\7\2\2\u0141\u0142\5*\26\2"+
		"\u0142\u0143\b\27\1\2\u0143\u0144\5\n\6\2\u0144\u014d\b\27\1\2\u0145\u0146"+
		"\7\4\2\2\u0146\u0147\5*\26\2\u0147\u0148\b\27\1\2\u0148\u0149\5\n\6\2"+
		"\u0149\u014a\b\27\1\2\u014a\u014c\3\2\2\2\u014b\u0145\3\2\2\2\u014c\u014f"+
		"\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0154\3\2\2\2\u014f"+
		"\u014d\3\2\2\2\u0150\u0151\7\5\2\2\u0151\u0152\5\n\6\2\u0152\u0153\b\27"+
		"\1\2\u0153\u0155\3\2\2\2\u0154\u0150\3\2\2\2\u0154\u0155\3\2\2\2\u0155"+
		"-\3\2\2\2\u0156\u0157\7\t\2\2\u0157\u0158\5*\26\2\u0158\u0159\b\30\1\2"+
		"\u0159\u0169\7\22\2\2\u015a\u015b\7\3\2\2\u015b\u015d\7=\2\2\u015c\u015e"+
		"\7=\2\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f"+
		"\u0161\b\30\1\2\u0160\u015a\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0160\3"+
		"\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\5\n\6\2\u0165"+
		"\u0166\b\30\1\2\u0166\u0168\3\2\2\2\u0167\u0160\3\2\2\2\u0168\u016b\3"+
		"\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016c\3\2\2\2\u016b"+
		"\u0169\3\2\2\2\u016c\u0171\7\23\2\2\u016d\u016e\7\5\2\2\u016e\u016f\5"+
		"\n\6\2\u016f\u0170\b\30\1\2\u0170\u0172\3\2\2\2\u0171\u016d\3\2\2\2\u0171"+
		"\u0172\3\2\2\2\u0172/\3\2\2\2\u0173\u0174\5*\26\2\u0174\u0175\7\16\2\2"+
		"\u0175\u0176\b\31\1\2\u0176\u017e\3\2\2\2\u0177\u0178\5,\27\2\u0178\u0179"+
		"\b\31\1\2\u0179\u017e\3\2\2\2\u017a\u017b\5.\30\2\u017b\u017c\b\31\1\2"+
		"\u017c\u017e\3\2\2\2\u017d\u0173\3\2\2\2\u017d\u0177\3\2\2\2\u017d\u017a"+
		"\3\2\2\2\u017e\61\3\2\2\2\u017f\u0180\5\4\3\2\u0180\u0196\b\32\1\2\u0181"+
		"\u0182\7\20\2\2\u0182\u0183\5\64\33\2\u0183\u018a\b\32\1\2\u0184\u0185"+
		"\7\f\2\2\u0185\u0186\5\64\33\2\u0186\u0187\b\32\1\2\u0187\u0189\3\2\2"+
		"\2\u0188\u0184\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b"+
		"\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018e\7\21\2\2"+
		"\u018e\u0197\3\2\2\2\u018f\u0190\7\24\2\2\u0190\u0191\7\25\2\2\u0191\u0193"+
		"\b\32\1\2\u0192\u018f\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0192\3\2\2\2"+
		"\u0194\u0195\3\2\2\2\u0195\u0197\3\2\2\2\u0196\u0181\3\2\2\2\u0196\u0192"+
		"\3\2\2\2\u0196\u0197\3\2\2\2\u0197\63\3\2\2\2\u0198\u0199\b\33\1\2\u0199"+
		"\u019a\5\62\32\2\u019a\u01a1\b\33\1\2\u019b\u019c\7\17\2\2\u019c\u019d"+
		"\5\62\32\2\u019d\u019e\b\33\1\2\u019e\u01a0\3\2\2\2\u019f\u019b\3\2\2"+
		"\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\65"+
		"\3\2\2\2\u01a3\u01a1\3\2\2\2*;EKVY\\bmu\u0087\u0098\u009b\u00a8\u00ad"+
		"\u00af\u00b8\u00bb\u00c8\u00d3\u00de\u00e9\u00f4\u00ff\u010a\u0115\u0119"+
		"\u0127\u0132\u013d\u014d\u0154\u015d\u0162\u0169\u0171\u017d\u018a\u0194"+
		"\u0196\u01a1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}