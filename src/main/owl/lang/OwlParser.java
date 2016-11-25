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
		RULE_module = 0, RULE_absoluteName = 1, RULE_function = 2, RULE_variable = 3, 
		RULE_block = 4, RULE_exprPrime = 5, RULE_exprApply = 6, RULE_exprCoerce = 7, 
		RULE_exprUnary = 8, RULE_exprMulDiv = 9, RULE_exprAddSub = 10, RULE_exprShift = 11, 
		RULE_exprBitAnd = 12, RULE_exprBitXor = 13, RULE_exprBitOr = 14, RULE_exprComparison = 15, 
		RULE_exprEquality = 16, RULE_exprNot = 17, RULE_exprAnd = 18, RULE_exprOr = 19, 
		RULE_expression = 20, RULE_stmtIf = 21, RULE_stmtMatch = 22, RULE_stmtReturn = 23, 
		RULE_statement = 24, RULE_typeNonFn = 25, RULE_typeInstance = 26;
	public static final String[] ruleNames = {
		"module", "absoluteName", "function", "variable", "block", "exprPrime", 
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
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FN) {
				{
				{
				setState(54);
				((ModuleContext)_localctx).f = function();
				 _localctx.r.functions.add(((ModuleContext)_localctx).f.r); 
				}
				}
				setState(61);
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
			setState(62);
			((AbsoluteNameContext)_localctx).NAME = match(NAME);
			 _localctx.r.name += (((AbsoluteNameContext)_localctx).NAME!=null?((AbsoluteNameContext)_localctx).NAME.getText():null); 
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(64);
				match(DOT);
				setState(65);
				((AbsoluteNameContext)_localctx).NAME = match(NAME);
				 _localctx.r.name += "." + (((AbsoluteNameContext)_localctx).NAME!=null?((AbsoluteNameContext)_localctx).NAME.getText():null); 
				}
				}
				setState(71);
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
			setState(72);
			match(FN);
			setState(75);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(73);
				((FunctionContext)_localctx).NAME = match(NAME);
				 _localctx.r.name = (((FunctionContext)_localctx).NAME!=null?((FunctionContext)_localctx).NAME.getText():null); 
				}
			}

			setState(92);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(77);
				match(LPAREN);
				setState(89);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(78);
					((FunctionContext)_localctx).a = variable();
					 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(80);
						match(COMMA);
						setState(81);
						((FunctionContext)_localctx).a = variable();
						 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
						}
						}
						setState(88);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(91);
				match(RPAREN);
				}
			}

			setState(98);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(94);
				match(COLON);
				setState(95);
				((FunctionContext)_localctx).type = typeInstance();
				 _localctx.r.returnType = ((FunctionContext)_localctx).type.r; 
				}
			}

			setState(100);
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
			setState(103);
			((VariableContext)_localctx).NAME = match(NAME);
			 _localctx.r.name = (((VariableContext)_localctx).NAME!=null?((VariableContext)_localctx).NAME.getText():null); 
			setState(109);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(105);
				match(COLON);
				setState(106);
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
			setState(111);
			match(LCURLY);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << MATCH) | (1L << RETURN) | (1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STR))) != 0)) {
				{
				{
				setState(112);
				((BlockContext)_localctx).s = statement();
				 _localctx.r.statements.add(((BlockContext)_localctx).s.r); 
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
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
			setState(137);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				((ExprPrimeContext)_localctx).NAME = match(NAME);
				 ((ExprPrimeContext)_localctx).r =  new AstName((((ExprPrimeContext)_localctx).NAME!=null?((ExprPrimeContext)_localctx).NAME.getText():null)); 
				}
				break;
			case OCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				((ExprPrimeContext)_localctx).OCT = match(OCT);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).OCT!=null?((ExprPrimeContext)_localctx).OCT.getText():null), AstConstant.OCT); 
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				((ExprPrimeContext)_localctx).DEC = match(DEC);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).DEC!=null?((ExprPrimeContext)_localctx).DEC.getText():null), AstConstant.DEC); 
				}
				break;
			case HEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(128);
				((ExprPrimeContext)_localctx).HEX = match(HEX);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).HEX!=null?((ExprPrimeContext)_localctx).HEX.getText():null), AstConstant.HEX); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				((ExprPrimeContext)_localctx).STR = match(STR);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).STR!=null?((ExprPrimeContext)_localctx).STR.getText():null), AstConstant.STR); 
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				setState(132);
				match(LPAREN);
				setState(133);
				((ExprPrimeContext)_localctx).e = expression();
				setState(134);
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
			setState(139);
			((ExprApplyContext)_localctx).x = exprPrime();
			 ((ExprApplyContext)_localctx).r =  ((ExprApplyContext)_localctx).x.r; 
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOT) | (1L << LPAREN) | (1L << LBRACKET))) != 0)) {
				{
				setState(175);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(141);
					match(DOT);
					setState(142);
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
					        
					setState(145);
					match(LPAREN);
					setState(157);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STR))) != 0)) {
						{
						setState(146);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						setState(154);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(148);
							match(COMMA);
							setState(149);
							((ExprApplyContext)_localctx).a = expression();
							 app.args.add(((ExprApplyContext)_localctx).a.r); 
							}
							}
							setState(156);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(159);
					match(RPAREN);
					}
					break;
				case LBRACKET:
					{

					            AstApply app = new AstApply();
					            app.args.add(new AstName("[]"));
					            app.args.add(_localctx.r);
					            ((ExprApplyContext)_localctx).r =  app;
					        
					setState(161);
					match(LBRACKET);
					setState(162);
					((ExprApplyContext)_localctx).a = expression();
					 app.args.add(((ExprApplyContext)_localctx).a.r); 
					setState(170);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(164);
						match(COMMA);
						setState(165);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						}
						}
						setState(172);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(173);
					match(RBRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(179);
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
			setState(180);
			((ExprCoerceContext)_localctx).x = exprApply();
			 ((ExprCoerceContext)_localctx).r =  ((ExprCoerceContext)_localctx).x.r; 
			setState(186);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(182);
				match(COLON);
				setState(183);
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
			setState(189);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) {
				{
				setState(188);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(191);
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
			setState(194);
			((ExprMulDivContext)_localctx).x = exprUnary();
			 ((ExprMulDivContext)_localctx).r =  ((ExprMulDivContext)_localctx).x.r; 
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(196);
				((ExprMulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					((ExprMulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(197);
				((ExprMulDivContext)_localctx).y = exprUnary();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprMulDivContext)_localctx).op!=null?((ExprMulDivContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprMulDivContext)_localctx).y.r);
				            ((ExprMulDivContext)_localctx).r =  app;
				        
				}
				}
				setState(204);
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
			setState(205);
			((ExprAddSubContext)_localctx).x = exprMulDiv();
			 ((ExprAddSubContext)_localctx).r =  ((ExprAddSubContext)_localctx).x.r; 
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLS || _la==MNS) {
				{
				{
				setState(207);
				((ExprAddSubContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLS || _la==MNS) ) {
					((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(208);
				((ExprAddSubContext)_localctx).y = exprMulDiv();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAddSubContext)_localctx).op!=null?((ExprAddSubContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAddSubContext)_localctx).y.r);
				            ((ExprAddSubContext)_localctx).r =  app;
				        
				}
				}
				setState(215);
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
			setState(216);
			((ExprShiftContext)_localctx).x = exprAddSub();
			 ((ExprShiftContext)_localctx).r =  ((ExprShiftContext)_localctx).x.r; 
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) {
				{
				{
				setState(218);
				((ExprShiftContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) ) {
					((ExprShiftContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(219);
				((ExprShiftContext)_localctx).y = exprAddSub();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprShiftContext)_localctx).op!=null?((ExprShiftContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprShiftContext)_localctx).y.r);
				            ((ExprShiftContext)_localctx).r =  app;
				        
				}
				}
				setState(226);
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
			setState(227);
			((ExprBitAndContext)_localctx).x = exprShift();
			 ((ExprBitAndContext)_localctx).r =  ((ExprBitAndContext)_localctx).x.r; 
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_AND) {
				{
				{
				setState(229);
				((ExprBitAndContext)_localctx).op = match(BIT_AND);
				setState(230);
				((ExprBitAndContext)_localctx).y = exprShift();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitAndContext)_localctx).op!=null?((ExprBitAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitAndContext)_localctx).y.r);
				            ((ExprBitAndContext)_localctx).r =  app;
				        
				}
				}
				setState(237);
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
			setState(238);
			((ExprBitXorContext)_localctx).x = exprBitAnd();
			 ((ExprBitXorContext)_localctx).r =  ((ExprBitXorContext)_localctx).x.r; 
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_XOR) {
				{
				{
				setState(240);
				((ExprBitXorContext)_localctx).op = match(BIT_XOR);
				setState(241);
				((ExprBitXorContext)_localctx).y = exprBitAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitXorContext)_localctx).op!=null?((ExprBitXorContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitXorContext)_localctx).y.r);
				            ((ExprBitXorContext)_localctx).r =  app;
				        
				}
				}
				setState(248);
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
			setState(249);
			((ExprBitOrContext)_localctx).x = exprBitXor();
			 ((ExprBitOrContext)_localctx).r =  ((ExprBitOrContext)_localctx).x.r; 
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_OR) {
				{
				{
				setState(251);
				((ExprBitOrContext)_localctx).op = match(BIT_OR);
				setState(252);
				((ExprBitOrContext)_localctx).y = exprBitXor();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitOrContext)_localctx).op!=null?((ExprBitOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitOrContext)_localctx).y.r);
				            ((ExprBitOrContext)_localctx).r =  app;
				        
				}
				}
				setState(259);
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
			setState(260);
			((ExprComparisonContext)_localctx).x = exprBitOr();
			 ((ExprComparisonContext)_localctx).r =  ((ExprComparisonContext)_localctx).x.r; 
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) {
				{
				{
				setState(262);
				((ExprComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) ) {
					((ExprComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(263);
				((ExprComparisonContext)_localctx).y = exprBitOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprComparisonContext)_localctx).op!=null?((ExprComparisonContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprComparisonContext)_localctx).y.r);
				            ((ExprComparisonContext)_localctx).r =  app;
				        
				}
				}
				setState(270);
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
			setState(271);
			((ExprEqualityContext)_localctx).x = exprComparison();
			 ((ExprEqualityContext)_localctx).r =  ((ExprEqualityContext)_localctx).x.r; 
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) {
				{
				{
				setState(273);
				((ExprEqualityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) ) {
					((ExprEqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(274);
				((ExprEqualityContext)_localctx).y = exprComparison();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprEqualityContext)_localctx).op!=null?((ExprEqualityContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprEqualityContext)_localctx).y.r);
				            ((ExprEqualityContext)_localctx).r =  app;
				        
				}
				}
				setState(281);
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
			setState(283);
			_la = _input.LA(1);
			if (_la==EXC) {
				{
				setState(282);
				((ExprNotContext)_localctx).op = match(EXC);
				}
			}

			setState(285);
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
			setState(289);
			((ExprAndContext)_localctx).x = exprNot();
			 ((ExprAndContext)_localctx).r =  ((ExprAndContext)_localctx).x.r; 
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(291);
				((ExprAndContext)_localctx).op = match(AND);
				setState(292);
				((ExprAndContext)_localctx).y = exprNot();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAndContext)_localctx).op!=null?((ExprAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAndContext)_localctx).y.r);
				            ((ExprAndContext)_localctx).r =  app;
				        
				}
				}
				setState(299);
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
			setState(300);
			((ExprOrContext)_localctx).x = exprAnd();
			 ((ExprOrContext)_localctx).r =  ((ExprOrContext)_localctx).x.r; 
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(302);
				((ExprOrContext)_localctx).op = match(OR);
				setState(303);
				((ExprOrContext)_localctx).y = exprAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprOrContext)_localctx).op!=null?((ExprOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprOrContext)_localctx).y.r);
				            ((ExprOrContext)_localctx).r =  app;
				        
				}
				}
				setState(310);
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
			setState(311);
			((ExpressionContext)_localctx).x = exprOr();
			 ((ExpressionContext)_localctx).r =  ((ExpressionContext)_localctx).x.r; 
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) {
				{
				{
				setState(313);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(314);
				((ExpressionContext)_localctx).y = exprOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExpressionContext)_localctx).y.r);
				            ((ExpressionContext)_localctx).r =  app;
				        
				}
				}
				setState(321);
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
			setState(322);
			match(IF);
			setState(323);
			((StmtIfContext)_localctx).cond = expression();
			 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
			setState(325);
			((StmtIfContext)_localctx).b = block();
			 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(327);
				match(ELIF);
				setState(328);
				((StmtIfContext)_localctx).cond = expression();
				 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
				setState(330);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.block.add(((StmtIfContext)_localctx).b.r); 
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(342);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(338);
				match(ELSE);
				setState(339);
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
			setState(344);
			match(MATCH);
			setState(345);
			((StmtMatchContext)_localctx).e = expression();
			 _localctx.r.expr = ((StmtMatchContext)_localctx).e.r; 
			setState(347);
			match(LCURLY);
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(354); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(348);
					match(CASE);
					setState(349);
					((StmtMatchContext)_localctx).t = match(NAME);
					setState(351);
					_la = _input.LA(1);
					if (_la==NAME) {
						{
						setState(350);
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
					setState(356); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE );
				setState(358);
				((StmtMatchContext)_localctx).b = block();

				            _localctx.r.block.add(((StmtMatchContext)_localctx).b.r);
				        
				}
				}
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(366);
			match(RCURLY);
			setState(371);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(367);
				match(ELSE);
				setState(368);
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
			setState(373);
			match(RETURN);
			setState(377);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME) | (1L << STR))) != 0)) {
				{
				setState(374);
				((StmtReturnContext)_localctx).e = expression();
				 _localctx.r.expr = ((StmtReturnContext)_localctx).e.r; 
				}
			}

			setState(379);
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
			setState(394);
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
				setState(381);
				((StatementContext)_localctx).e = expression();
				setState(382);
				match(SEMICOLON);
				 ((StatementContext)_localctx).r =  new AstExpr(((StatementContext)_localctx).e.r); 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(385);
				((StatementContext)_localctx).s = stmtIf();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).s.r; 
				}
				break;
			case MATCH:
				enterOuterAlt(_localctx, 3);
				{
				setState(388);
				((StatementContext)_localctx).m = stmtMatch();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).m.r; 
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(391);
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
			setState(396);
			((TypeNonFnContext)_localctx).n = absoluteName();
			 _localctx.r.name = ((TypeNonFnContext)_localctx).n.r; 
			setState(419);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(398);
				match(LPAREN);
				setState(399);
				((TypeNonFnContext)_localctx).a = typeInstance();
				 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(401);
					match(COMMA);
					setState(402);
					((TypeNonFnContext)_localctx).a = typeInstance();
					 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
					}
					}
					setState(409);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(410);
				match(RPAREN);
				}
				break;
			case LBRACKET:
				{
				setState(415); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(412);
					match(LBRACKET);
					setState(413);
					match(RBRACKET);

					                AstType arrayType = new AstType("Array");
					                arrayType.args.add(_localctx.r);
					                ((TypeNonFnContext)_localctx).r =  arrayType;
					            
					}
					}
					setState(417); 
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
			setState(422);
			((TypeInstanceContext)_localctx).x = typeNonFn();
			 ((TypeInstanceContext)_localctx).r =  ((TypeInstanceContext)_localctx).x.r; 
			setState(430);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARROW) {
				{
				{
				setState(424);
				match(ARROW);
				setState(425);
				((TypeInstanceContext)_localctx).y = typeNonFn();

				            if (functionType == null) {
				                functionType = new AstType("Fn");
				                functionType.args.add(_localctx.r);
				                ((TypeInstanceContext)_localctx).r =  functionType;
				            }
				            functionType.args.add(((TypeInstanceContext)_localctx).y.r);
				        
				}
				}
				setState(432);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3A\u01b4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\7\2<\n\2\f\2\16\2?\13\2\3\3"+
		"\3\3\3\3\3\3\3\3\7\3F\n\3\f\3\16\3I\13\3\3\4\3\4\3\4\5\4N\n\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\5\4\\\n\4\3\4\5\4_\n\4\3"+
		"\4\3\4\3\4\3\4\5\4e\n\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5p\n\5\3"+
		"\6\3\6\3\6\3\6\7\6v\n\6\f\6\16\6y\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u008c\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u009b\n\b\f\b\16\b\u009e\13\b\5\b"+
		"\u00a0\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00ab\n\b\f\b\16\b"+
		"\u00ae\13\b\3\b\3\b\7\b\u00b2\n\b\f\b\16\b\u00b5\13\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u00bd\n\t\3\n\5\n\u00c0\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\7\13\u00cb\n\13\f\13\16\13\u00ce\13\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\7\f\u00d6\n\f\f\f\16\f\u00d9\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00e1"+
		"\n\r\f\r\16\r\u00e4\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00ec\n\16"+
		"\f\16\16\16\u00ef\13\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00f7\n\17"+
		"\f\17\16\17\u00fa\13\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0102\n\20"+
		"\f\20\16\20\u0105\13\20\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u010d\n\21"+
		"\f\21\16\21\u0110\13\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0118\n\22"+
		"\f\22\16\22\u011b\13\22\3\23\5\23\u011e\n\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\7\24\u012a\n\24\f\24\16\24\u012d\13\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\7\25\u0135\n\25\f\25\16\25\u0138\13\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\7\26\u0140\n\26\f\26\16\26\u0143\13\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0150\n\27\f\27"+
		"\16\27\u0153\13\27\3\27\3\27\3\27\3\27\5\27\u0159\n\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\5\30\u0162\n\30\3\30\6\30\u0165\n\30\r\30\16\30\u0166"+
		"\3\30\3\30\3\30\7\30\u016c\n\30\f\30\16\30\u016f\13\30\3\30\3\30\3\30"+
		"\3\30\3\30\5\30\u0176\n\30\3\31\3\31\3\31\3\31\5\31\u017c\n\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5"+
		"\32\u018d\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0198"+
		"\n\33\f\33\16\33\u019b\13\33\3\33\3\33\3\33\3\33\3\33\6\33\u01a2\n\33"+
		"\r\33\16\33\u01a3\5\33\u01a6\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7"+
		"\34\u01af\n\34\f\34\16\34\u01b2\13\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\t\4\2\32\32\37 \3\2\34\36\3"+
		"\2\37 \3\2!#\3\2),\4\2\b\b\'(\3\2/:\u01c9\2=\3\2\2\2\4@\3\2\2\2\6J\3\2"+
		"\2\2\bi\3\2\2\2\nq\3\2\2\2\f\u008b\3\2\2\2\16\u008d\3\2\2\2\20\u00b6\3"+
		"\2\2\2\22\u00bf\3\2\2\2\24\u00c4\3\2\2\2\26\u00cf\3\2\2\2\30\u00da\3\2"+
		"\2\2\32\u00e5\3\2\2\2\34\u00f0\3\2\2\2\36\u00fb\3\2\2\2 \u0106\3\2\2\2"+
		"\"\u0111\3\2\2\2$\u011d\3\2\2\2&\u0123\3\2\2\2(\u012e\3\2\2\2*\u0139\3"+
		"\2\2\2,\u0144\3\2\2\2.\u015a\3\2\2\2\60\u0177\3\2\2\2\62\u018c\3\2\2\2"+
		"\64\u018e\3\2\2\2\66\u01a7\3\2\2\289\5\6\4\29:\b\2\1\2:<\3\2\2\2;8\3\2"+
		"\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\3\3\2\2\2?=\3\2\2\2@A\7>\2\2AG\b\3"+
		"\1\2BC\7\f\2\2CD\7>\2\2DF\b\3\1\2EB\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2"+
		"\2\2H\5\3\2\2\2IG\3\2\2\2JM\7\6\2\2KL\7>\2\2LN\b\4\1\2MK\3\2\2\2MN\3\2"+
		"\2\2N^\3\2\2\2O[\7\21\2\2PQ\5\b\5\2QX\b\4\1\2RS\7\r\2\2ST\5\b\5\2TU\b"+
		"\4\1\2UW\3\2\2\2VR\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\\\3\2\2\2ZX"+
		"\3\2\2\2[P\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]_\7\22\2\2^O\3\2\2\2^_\3\2\2"+
		"\2_d\3\2\2\2`a\7\16\2\2ab\5\66\34\2bc\b\4\1\2ce\3\2\2\2d`\3\2\2\2de\3"+
		"\2\2\2ef\3\2\2\2fg\5\n\6\2gh\b\4\1\2h\7\3\2\2\2ij\7>\2\2jo\b\5\1\2kl\7"+
		"\16\2\2lm\5\66\34\2mn\b\5\1\2np\3\2\2\2ok\3\2\2\2op\3\2\2\2p\t\3\2\2\2"+
		"qw\7\23\2\2rs\5\62\32\2st\b\6\1\2tv\3\2\2\2ur\3\2\2\2vy\3\2\2\2wu\3\2"+
		"\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7\24\2\2{\13\3\2\2\2|}\7>\2\2}\u008c"+
		"\b\7\1\2~\177\7;\2\2\177\u008c\b\7\1\2\u0080\u0081\7<\2\2\u0081\u008c"+
		"\b\7\1\2\u0082\u0083\7=\2\2\u0083\u008c\b\7\1\2\u0084\u0085\7?\2\2\u0085"+
		"\u008c\b\7\1\2\u0086\u0087\7\21\2\2\u0087\u0088\5*\26\2\u0088\u0089\7"+
		"\22\2\2\u0089\u008a\b\7\1\2\u008a\u008c\3\2\2\2\u008b|\3\2\2\2\u008b~"+
		"\3\2\2\2\u008b\u0080\3\2\2\2\u008b\u0082\3\2\2\2\u008b\u0084\3\2\2\2\u008b"+
		"\u0086\3\2\2\2\u008c\r\3\2\2\2\u008d\u008e\5\f\7\2\u008e\u00b3\b\b\1\2"+
		"\u008f\u0090\7\f\2\2\u0090\u0091\7>\2\2\u0091\u00b2\b\b\1\2\u0092\u0093"+
		"\b\b\1\2\u0093\u009f\7\21\2\2\u0094\u0095\5*\26\2\u0095\u009c\b\b\1\2"+
		"\u0096\u0097\7\r\2\2\u0097\u0098\5*\26\2\u0098\u0099\b\b\1\2\u0099\u009b"+
		"\3\2\2\2\u009a\u0096\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u0094\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00b2\7\22\2\2\u00a2"+
		"\u00a3\b\b\1\2\u00a3\u00a4\7\25\2\2\u00a4\u00a5\5*\26\2\u00a5\u00ac\b"+
		"\b\1\2\u00a6\u00a7\7\r\2\2\u00a7\u00a8\5*\26\2\u00a8\u00a9\b\b\1\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a6\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00b0\7\26\2\2\u00b0\u00b2\3\2\2\2\u00b1\u008f\3\2\2\2\u00b1\u0092\3"+
		"\2\2\2\u00b1\u00a2\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\17\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\5\16\b"+
		"\2\u00b7\u00bc\b\t\1\2\u00b8\u00b9\7\16\2\2\u00b9\u00ba\5\66\34\2\u00ba"+
		"\u00bb\b\t\1\2\u00bb\u00bd\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\21\3\2\2\2\u00be\u00c0\t\2\2\2\u00bf\u00be\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\5\20\t\2\u00c2\u00c3\b\n\1\2"+
		"\u00c3\23\3\2\2\2\u00c4\u00c5\5\22\n\2\u00c5\u00cc\b\13\1\2\u00c6\u00c7"+
		"\t\3\2\2\u00c7\u00c8\5\22\n\2\u00c8\u00c9\b\13\1\2\u00c9\u00cb\3\2\2\2"+
		"\u00ca\u00c6\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd"+
		"\3\2\2\2\u00cd\25\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\5\24\13\2\u00d0"+
		"\u00d7\b\f\1\2\u00d1\u00d2\t\4\2\2\u00d2\u00d3\5\24\13\2\u00d3\u00d4\b"+
		"\f\1\2\u00d4\u00d6\3\2\2\2\u00d5\u00d1\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\27\3\2\2\2\u00d9\u00d7\3\2\2"+
		"\2\u00da\u00db\5\26\f\2\u00db\u00e2\b\r\1\2\u00dc\u00dd\t\5\2\2\u00dd"+
		"\u00de\5\26\f\2\u00de\u00df\b\r\1\2\u00df\u00e1\3\2\2\2\u00e0\u00dc\3"+
		"\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\31\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\5\30\r\2\u00e6\u00ed\b\16"+
		"\1\2\u00e7\u00e8\7$\2\2\u00e8\u00e9\5\30\r\2\u00e9\u00ea\b\16\1\2\u00ea"+
		"\u00ec\3\2\2\2\u00eb\u00e7\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ed\u00ee\3\2\2\2\u00ee\33\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1"+
		"\5\32\16\2\u00f1\u00f8\b\17\1\2\u00f2\u00f3\7%\2\2\u00f3\u00f4\5\32\16"+
		"\2\u00f4\u00f5\b\17\1\2\u00f5\u00f7\3\2\2\2\u00f6\u00f2\3\2\2\2\u00f7"+
		"\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\35\3\2\2"+
		"\2\u00fa\u00f8\3\2\2\2\u00fb\u00fc\5\34\17\2\u00fc\u0103\b\20\1\2\u00fd"+
		"\u00fe\7&\2\2\u00fe\u00ff\5\34\17\2\u00ff\u0100\b\20\1\2\u0100\u0102\3"+
		"\2\2\2\u0101\u00fd\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\37\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\5\36\20"+
		"\2\u0107\u010e\b\21\1\2\u0108\u0109\t\6\2\2\u0109\u010a\5\36\20\2\u010a"+
		"\u010b\b\21\1\2\u010b\u010d\3\2\2\2\u010c\u0108\3\2\2\2\u010d\u0110\3"+
		"\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f!\3\2\2\2\u0110\u010e"+
		"\3\2\2\2\u0111\u0112\5 \21\2\u0112\u0119\b\22\1\2\u0113\u0114\t\7\2\2"+
		"\u0114\u0115\5 \21\2\u0115\u0116\b\22\1\2\u0116\u0118\3\2\2\2\u0117\u0113"+
		"\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"#\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011e\7\33\2\2\u011d\u011c\3\2\2\2"+
		"\u011d\u011e\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\5\"\22\2\u0120\u0121"+
		"\b\23\1\2\u0121\u0122\b\23\1\2\u0122%\3\2\2\2\u0123\u0124\5$\23\2\u0124"+
		"\u012b\b\24\1\2\u0125\u0126\7-\2\2\u0126\u0127\5$\23\2\u0127\u0128\b\24"+
		"\1\2\u0128\u012a\3\2\2\2\u0129\u0125\3\2\2\2\u012a\u012d\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\'\3\2\2\2\u012d\u012b\3\2\2\2"+
		"\u012e\u012f\5&\24\2\u012f\u0136\b\25\1\2\u0130\u0131\7.\2\2\u0131\u0132"+
		"\5&\24\2\u0132\u0133\b\25\1\2\u0133\u0135\3\2\2\2\u0134\u0130\3\2\2\2"+
		"\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137)\3"+
		"\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\5(\25\2\u013a\u0141\b\26\1\2\u013b"+
		"\u013c\t\b\2\2\u013c\u013d\5(\25\2\u013d\u013e\b\26\1\2\u013e\u0140\3"+
		"\2\2\2\u013f\u013b\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0142\3\2\2\2\u0142+\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\7\7\2\2"+
		"\u0145\u0146\5*\26\2\u0146\u0147\b\27\1\2\u0147\u0148\5\n\6\2\u0148\u0151"+
		"\b\27\1\2\u0149\u014a\7\4\2\2\u014a\u014b\5*\26\2\u014b\u014c\b\27\1\2"+
		"\u014c\u014d\5\n\6\2\u014d\u014e\b\27\1\2\u014e\u0150\3\2\2\2\u014f\u0149"+
		"\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152"+
		"\u0158\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0155\7\5\2\2\u0155\u0156\5\n"+
		"\6\2\u0156\u0157\b\27\1\2\u0157\u0159\3\2\2\2\u0158\u0154\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159-\3\2\2\2\u015a\u015b\7\t\2\2\u015b\u015c\5*\26\2"+
		"\u015c\u015d\b\30\1\2\u015d\u016d\7\23\2\2\u015e\u015f\7\3\2\2\u015f\u0161"+
		"\7>\2\2\u0160\u0162\7>\2\2\u0161\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0163\3\2\2\2\u0163\u0165\b\30\1\2\u0164\u015e\3\2\2\2\u0165\u0166\3"+
		"\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\3\2\2\2\u0168"+
		"\u0169\5\n\6\2\u0169\u016a\b\30\1\2\u016a\u016c\3\2\2\2\u016b\u0164\3"+
		"\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e"+
		"\u0170\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0175\7\24\2\2\u0171\u0172\7"+
		"\5\2\2\u0172\u0173\5\n\6\2\u0173\u0174\b\30\1\2\u0174\u0176\3\2\2\2\u0175"+
		"\u0171\3\2\2\2\u0175\u0176\3\2\2\2\u0176/\3\2\2\2\u0177\u017b\7\13\2\2"+
		"\u0178\u0179\5*\26\2\u0179\u017a\b\31\1\2\u017a\u017c\3\2\2\2\u017b\u0178"+
		"\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\7\17\2\2"+
		"\u017e\61\3\2\2\2\u017f\u0180\5*\26\2\u0180\u0181\7\17\2\2\u0181\u0182"+
		"\b\32\1\2\u0182\u018d\3\2\2\2\u0183\u0184\5,\27\2\u0184\u0185\b\32\1\2"+
		"\u0185\u018d\3\2\2\2\u0186\u0187\5.\30\2\u0187\u0188\b\32\1\2\u0188\u018d"+
		"\3\2\2\2\u0189\u018a\5\60\31\2\u018a\u018b\b\32\1\2\u018b\u018d\3\2\2"+
		"\2\u018c\u017f\3\2\2\2\u018c\u0183\3\2\2\2\u018c\u0186\3\2\2\2\u018c\u0189"+
		"\3\2\2\2\u018d\63\3\2\2\2\u018e\u018f\5\4\3\2\u018f\u01a5\b\33\1\2\u0190"+
		"\u0191\7\21\2\2\u0191\u0192\5\66\34\2\u0192\u0199\b\33\1\2\u0193\u0194"+
		"\7\r\2\2\u0194\u0195\5\66\34\2\u0195\u0196\b\33\1\2\u0196\u0198\3\2\2"+
		"\2\u0197\u0193\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a"+
		"\3\2\2\2\u019a\u019c\3\2\2\2\u019b\u0199\3\2\2\2\u019c\u019d\7\22\2\2"+
		"\u019d\u01a6\3\2\2\2\u019e\u019f\7\25\2\2\u019f\u01a0\7\26\2\2\u01a0\u01a2"+
		"\b\33\1\2\u01a1\u019e\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a1\3\2\2\2"+
		"\u01a3\u01a4\3\2\2\2\u01a4\u01a6\3\2\2\2\u01a5\u0190\3\2\2\2\u01a5\u01a1"+
		"\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\65\3\2\2\2\u01a7\u01a8\b\34\1\2\u01a8"+
		"\u01a9\5\64\33\2\u01a9\u01b0\b\34\1\2\u01aa\u01ab\7\20\2\2\u01ab\u01ac"+
		"\5\64\33\2\u01ac\u01ad\b\34\1\2\u01ad\u01af\3\2\2\2\u01ae\u01aa\3\2\2"+
		"\2\u01af\u01b2\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\67"+
		"\3\2\2\2\u01b2\u01b0\3\2\2\2+=GMX[^dow\u008b\u009c\u009f\u00ac\u00b1\u00b3"+
		"\u00bc\u00bf\u00cc\u00d7\u00e2\u00ed\u00f8\u0103\u010e\u0119\u011d\u012b"+
		"\u0136\u0141\u0151\u0158\u0161\u0166\u016d\u0175\u017b\u018c\u0199\u01a3"+
		"\u01a5\u01b0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}