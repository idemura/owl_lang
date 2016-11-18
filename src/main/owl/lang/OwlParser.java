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
		ELIF=1, ELSE=2, FN=3, IF=4, IS=5, NEW=6, DOT=7, COMMA=8, COLON=9, SEMICOLON=10, 
		ARROW=11, LPAREN=12, RPAREN=13, LCURLY=14, RCURLY=15, LBRACKET=16, RBRACKET=17, 
		QUOTE=18, DBL_QUOTE=19, AT=20, TILDE=21, EXC=22, MUL=23, DIV=24, MOD=25, 
		PLS=26, MNS=27, LSHIFT=28, RSHIFT=29, SIGNED_RSHIFT=30, BIT_AND=31, BIT_XOR=32, 
		BIT_OR=33, EQ=34, NE=35, LE=36, LT=37, GE=38, GT=39, AND=40, OR=41, ASSIGN=42, 
		ASSIGN_MUL=43, ASSIGN_DIV=44, ASSIGN_MOD=45, ASSIGN_PLS=46, ASSIGN_MNS=47, 
		ASSIGN_LSHIFT=48, ASSIGN_RSHIFT=49, ASSIGN_SIGNED_RSHIFT=50, ASSIGN_BIT_AND=51, 
		ASSIGN_BIT_XOR=52, ASSIGN_BIT_OR=53, OCT=54, DEC=55, HEX=56, NAME=57, 
		COMMENT=58, WS=59;
	public static final int
		RULE_module = 0, RULE_qualifiedName = 1, RULE_function = 2, RULE_variable = 3, 
		RULE_block = 4, RULE_exprPrime = 5, RULE_exprApply = 6, RULE_exprCoerce = 7, 
		RULE_exprUnary = 8, RULE_exprMultiplicative = 9, RULE_exprAdditive = 10, 
		RULE_exprShift = 11, RULE_exprBitAnd = 12, RULE_exprBitXor = 13, RULE_exprBitOr = 14, 
		RULE_exprComparison = 15, RULE_exprEquality = 16, RULE_exprLogicalNot = 17, 
		RULE_exprLogicalAnd = 18, RULE_exprLogicalOr = 19, RULE_expression = 20, 
		RULE_stmtIf = 21, RULE_statement = 22, RULE_typeNonFn = 23, RULE_typeInstance = 24;
	public static final String[] ruleNames = {
		"module", "qualifiedName", "function", "variable", "block", "exprPrime", 
		"exprApply", "exprCoerce", "exprUnary", "exprMultiplicative", "exprAdditive", 
		"exprShift", "exprBitAnd", "exprBitXor", "exprBitOr", "exprComparison", 
		"exprEquality", "exprLogicalNot", "exprLogicalAnd", "exprLogicalOr", "expression", 
		"stmtIf", "statement", "typeNonFn", "typeInstance"
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
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FN) {
				{
				{
				setState(50);
				((ModuleContext)_localctx).f = function();
				 _localctx.r.functions.add(((ModuleContext)_localctx).f.r); 
				}
				}
				setState(57);
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

	public static class QualifiedNameContext extends ParserRuleContext {
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
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((QualifiedNameContext)_localctx).NAME = match(NAME);
			 _localctx.r.name += (((QualifiedNameContext)_localctx).NAME!=null?((QualifiedNameContext)_localctx).NAME.getText():null); 
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(60);
				match(DOT);
				setState(61);
				((QualifiedNameContext)_localctx).NAME = match(NAME);
				 _localctx.r.name += "." + (((QualifiedNameContext)_localctx).NAME!=null?((QualifiedNameContext)_localctx).NAME.getText():null); 
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
			setState(68);
			match(FN);
			setState(71);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(69);
				((FunctionContext)_localctx).NAME = match(NAME);
				 _localctx.r.name = (((FunctionContext)_localctx).NAME!=null?((FunctionContext)_localctx).NAME.getText():null); 
				}
			}

			setState(88);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(73);
				match(LPAREN);
				setState(85);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(74);
					((FunctionContext)_localctx).a = variable();
					 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(76);
						match(COMMA);
						setState(77);
						((FunctionContext)_localctx).a = variable();
						 _localctx.r.args.add(((FunctionContext)_localctx).a.r); 
						}
						}
						setState(84);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(87);
				match(RPAREN);
				}
			}

			setState(94);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(90);
				match(COLON);
				setState(91);
				((FunctionContext)_localctx).type = typeInstance();
				 _localctx.r.returnType = ((FunctionContext)_localctx).type.r; 
				}
			}

			setState(96);
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
			setState(99);
			((VariableContext)_localctx).NAME = match(NAME);
			 _localctx.r.name = (((VariableContext)_localctx).NAME!=null?((VariableContext)_localctx).NAME.getText():null); 
			setState(105);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(101);
				match(COLON);
				setState(102);
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
			setState(107);
			match(LCURLY);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME))) != 0)) {
				{
				{
				setState(108);
				((BlockContext)_localctx).s = statement();
				 _localctx.r.statements.add(((BlockContext)_localctx).s.r); 
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
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
			setState(131);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				((ExprPrimeContext)_localctx).NAME = match(NAME);
				 ((ExprPrimeContext)_localctx).r =  new AstName((((ExprPrimeContext)_localctx).NAME!=null?((ExprPrimeContext)_localctx).NAME.getText():null)); 
				}
				break;
			case OCT:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				((ExprPrimeContext)_localctx).OCT = match(OCT);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).OCT!=null?((ExprPrimeContext)_localctx).OCT.getText():null)); 
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				((ExprPrimeContext)_localctx).DEC = match(DEC);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).DEC!=null?((ExprPrimeContext)_localctx).DEC.getText():null)); 
				}
				break;
			case HEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				((ExprPrimeContext)_localctx).HEX = match(HEX);
				 ((ExprPrimeContext)_localctx).r =  new AstConstant((((ExprPrimeContext)_localctx).HEX!=null?((ExprPrimeContext)_localctx).HEX.getText():null)); 
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				match(LPAREN);
				setState(127);
				((ExprPrimeContext)_localctx).e = expression();
				setState(128);
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
		public ExprPrimeContext p;
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
			setState(133);
			((ExprApplyContext)_localctx).p = exprPrime();
			 ((ExprApplyContext)_localctx).r =  ((ExprApplyContext)_localctx).p.r; 
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOT) | (1L << LPAREN) | (1L << LBRACKET))) != 0)) {
				{
				setState(169);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(135);
					match(DOT);
					setState(136);
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
					        
					setState(139);
					match(LPAREN);
					setState(151);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << TILDE) | (1L << EXC) | (1L << PLS) | (1L << MNS) | (1L << OCT) | (1L << DEC) | (1L << HEX) | (1L << NAME))) != 0)) {
						{
						setState(140);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						setState(148);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(142);
							match(COMMA);
							setState(143);
							((ExprApplyContext)_localctx).a = expression();
							 app.args.add(((ExprApplyContext)_localctx).a.r); 
							}
							}
							setState(150);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(153);
					match(RPAREN);
					}
					break;
				case LBRACKET:
					{

					            AstApply app = new AstApply();
					            app.args.add(new AstName("[]"));
					            app.args.add(_localctx.r);
					            ((ExprApplyContext)_localctx).r =  app;
					        
					setState(155);
					match(LBRACKET);
					setState(156);
					((ExprApplyContext)_localctx).a = expression();
					 app.args.add(((ExprApplyContext)_localctx).a.r); 
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(158);
						match(COMMA);
						setState(159);
						((ExprApplyContext)_localctx).a = expression();
						 app.args.add(((ExprApplyContext)_localctx).a.r); 
						}
						}
						setState(166);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(167);
					match(RBRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(173);
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
			setState(174);
			((ExprCoerceContext)_localctx).x = exprApply();
			 ((ExprCoerceContext)_localctx).r =  ((ExprCoerceContext)_localctx).x.r; 
			setState(180);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(176);
				match(COLON);
				setState(177);
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
			setState(183);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) {
				{
				setState(182);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TILDE) | (1L << PLS) | (1L << MNS))) != 0)) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(185);
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

	public static class ExprMultiplicativeContext extends ParserRuleContext {
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
		public ExprMultiplicativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprMultiplicative; }
	}

	public final ExprMultiplicativeContext exprMultiplicative() throws RecognitionException {
		ExprMultiplicativeContext _localctx = new ExprMultiplicativeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprMultiplicative);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((ExprMultiplicativeContext)_localctx).x = exprUnary();
			 ((ExprMultiplicativeContext)_localctx).r =  ((ExprMultiplicativeContext)_localctx).x.r; 
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(190);
				((ExprMultiplicativeContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					((ExprMultiplicativeContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(191);
				((ExprMultiplicativeContext)_localctx).y = exprUnary();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprMultiplicativeContext)_localctx).op!=null?((ExprMultiplicativeContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprMultiplicativeContext)_localctx).y.r);
				            ((ExprMultiplicativeContext)_localctx).r =  app;
				        
				}
				}
				setState(198);
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

	public static class ExprAdditiveContext extends ParserRuleContext {
		public AstNode r;
		public ExprMultiplicativeContext x;
		public Token op;
		public ExprMultiplicativeContext y;
		public List<ExprMultiplicativeContext> exprMultiplicative() {
			return getRuleContexts(ExprMultiplicativeContext.class);
		}
		public ExprMultiplicativeContext exprMultiplicative(int i) {
			return getRuleContext(ExprMultiplicativeContext.class,i);
		}
		public List<TerminalNode> PLS() { return getTokens(OwlParser.PLS); }
		public TerminalNode PLS(int i) {
			return getToken(OwlParser.PLS, i);
		}
		public List<TerminalNode> MNS() { return getTokens(OwlParser.MNS); }
		public TerminalNode MNS(int i) {
			return getToken(OwlParser.MNS, i);
		}
		public ExprAdditiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprAdditive; }
	}

	public final ExprAdditiveContext exprAdditive() throws RecognitionException {
		ExprAdditiveContext _localctx = new ExprAdditiveContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprAdditive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			((ExprAdditiveContext)_localctx).x = exprMultiplicative();
			 ((ExprAdditiveContext)_localctx).r =  ((ExprAdditiveContext)_localctx).x.r; 
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLS || _la==MNS) {
				{
				{
				setState(201);
				((ExprAdditiveContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLS || _la==MNS) ) {
					((ExprAdditiveContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(202);
				((ExprAdditiveContext)_localctx).y = exprMultiplicative();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprAdditiveContext)_localctx).op!=null?((ExprAdditiveContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprAdditiveContext)_localctx).y.r);
				            ((ExprAdditiveContext)_localctx).r =  app;
				        
				}
				}
				setState(209);
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
		public ExprAdditiveContext x;
		public Token op;
		public ExprAdditiveContext y;
		public List<ExprAdditiveContext> exprAdditive() {
			return getRuleContexts(ExprAdditiveContext.class);
		}
		public ExprAdditiveContext exprAdditive(int i) {
			return getRuleContext(ExprAdditiveContext.class,i);
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
			setState(210);
			((ExprShiftContext)_localctx).x = exprAdditive();
			 ((ExprShiftContext)_localctx).r =  ((ExprShiftContext)_localctx).x.r; 
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) {
				{
				{
				setState(212);
				((ExprShiftContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LSHIFT) | (1L << RSHIFT) | (1L << SIGNED_RSHIFT))) != 0)) ) {
					((ExprShiftContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(213);
				((ExprShiftContext)_localctx).y = exprAdditive();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprShiftContext)_localctx).op!=null?((ExprShiftContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprShiftContext)_localctx).y.r);
				            ((ExprShiftContext)_localctx).r =  app;
				        
				}
				}
				setState(220);
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
			setState(221);
			((ExprBitAndContext)_localctx).x = exprShift();
			 ((ExprBitAndContext)_localctx).r =  ((ExprBitAndContext)_localctx).x.r; 
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_AND) {
				{
				{
				setState(223);
				((ExprBitAndContext)_localctx).op = match(BIT_AND);
				setState(224);
				((ExprBitAndContext)_localctx).y = exprShift();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitAndContext)_localctx).op!=null?((ExprBitAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitAndContext)_localctx).y.r);
				            ((ExprBitAndContext)_localctx).r =  app;
				        
				}
				}
				setState(231);
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
			setState(232);
			((ExprBitXorContext)_localctx).x = exprBitAnd();
			 ((ExprBitXorContext)_localctx).r =  ((ExprBitXorContext)_localctx).x.r; 
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_XOR) {
				{
				{
				setState(234);
				((ExprBitXorContext)_localctx).op = match(BIT_XOR);
				setState(235);
				((ExprBitXorContext)_localctx).y = exprBitAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitXorContext)_localctx).op!=null?((ExprBitXorContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitXorContext)_localctx).y.r);
				            ((ExprBitXorContext)_localctx).r =  app;
				        
				}
				}
				setState(242);
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
			setState(243);
			((ExprBitOrContext)_localctx).x = exprBitXor();
			 ((ExprBitOrContext)_localctx).r =  ((ExprBitOrContext)_localctx).x.r; 
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BIT_OR) {
				{
				{
				setState(245);
				((ExprBitOrContext)_localctx).op = match(BIT_OR);
				setState(246);
				((ExprBitOrContext)_localctx).y = exprBitXor();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprBitOrContext)_localctx).op!=null?((ExprBitOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprBitOrContext)_localctx).y.r);
				            ((ExprBitOrContext)_localctx).r =  app;
				        
				}
				}
				setState(253);
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
			setState(254);
			((ExprComparisonContext)_localctx).x = exprBitOr();
			 ((ExprComparisonContext)_localctx).r =  ((ExprComparisonContext)_localctx).x.r; 
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) {
				{
				{
				setState(256);
				((ExprComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LE) | (1L << LT) | (1L << GE) | (1L << GT))) != 0)) ) {
					((ExprComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(257);
				((ExprComparisonContext)_localctx).y = exprBitOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprComparisonContext)_localctx).op!=null?((ExprComparisonContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprComparisonContext)_localctx).y.r);
				            ((ExprComparisonContext)_localctx).r =  app;
				        
				}
				}
				setState(264);
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
			setState(265);
			((ExprEqualityContext)_localctx).x = exprComparison();
			 ((ExprEqualityContext)_localctx).r =  ((ExprEqualityContext)_localctx).x.r; 
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) {
				{
				{
				setState(267);
				((ExprEqualityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS) | (1L << EQ) | (1L << NE))) != 0)) ) {
					((ExprEqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(268);
				((ExprEqualityContext)_localctx).y = exprComparison();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprEqualityContext)_localctx).op!=null?((ExprEqualityContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprEqualityContext)_localctx).y.r);
				            ((ExprEqualityContext)_localctx).r =  app;
				        
				}
				}
				setState(275);
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

	public static class ExprLogicalNotContext extends ParserRuleContext {
		public AstNode r;
		public Token op;
		public ExprEqualityContext x;
		public ExprEqualityContext exprEquality() {
			return getRuleContext(ExprEqualityContext.class,0);
		}
		public TerminalNode EXC() { return getToken(OwlParser.EXC, 0); }
		public ExprLogicalNotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLogicalNot; }
	}

	public final ExprLogicalNotContext exprLogicalNot() throws RecognitionException {
		ExprLogicalNotContext _localctx = new ExprLogicalNotContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exprLogicalNot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			_la = _input.LA(1);
			if (_la==EXC) {
				{
				setState(276);
				((ExprLogicalNotContext)_localctx).op = match(EXC);
				}
			}

			setState(279);
			((ExprLogicalNotContext)_localctx).x = exprEquality();
			 ((ExprLogicalNotContext)_localctx).r =  ((ExprLogicalNotContext)_localctx).x.r; 

			        if (((ExprLogicalNotContext)_localctx).op != null) {
			            AstApply app = new AstApply();
			            app.args.add(new AstName((((ExprLogicalNotContext)_localctx).op!=null?((ExprLogicalNotContext)_localctx).op.getText():null)));
			            app.args.add(_localctx.r);
			            ((ExprLogicalNotContext)_localctx).r =  app;
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

	public static class ExprLogicalAndContext extends ParserRuleContext {
		public AstNode r;
		public ExprLogicalNotContext x;
		public Token op;
		public ExprLogicalNotContext y;
		public List<ExprLogicalNotContext> exprLogicalNot() {
			return getRuleContexts(ExprLogicalNotContext.class);
		}
		public ExprLogicalNotContext exprLogicalNot(int i) {
			return getRuleContext(ExprLogicalNotContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(OwlParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(OwlParser.AND, i);
		}
		public ExprLogicalAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLogicalAnd; }
	}

	public final ExprLogicalAndContext exprLogicalAnd() throws RecognitionException {
		ExprLogicalAndContext _localctx = new ExprLogicalAndContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprLogicalAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			((ExprLogicalAndContext)_localctx).x = exprLogicalNot();
			 ((ExprLogicalAndContext)_localctx).r =  ((ExprLogicalAndContext)_localctx).x.r; 
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(285);
				((ExprLogicalAndContext)_localctx).op = match(AND);
				setState(286);
				((ExprLogicalAndContext)_localctx).y = exprLogicalNot();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprLogicalAndContext)_localctx).op!=null?((ExprLogicalAndContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprLogicalAndContext)_localctx).y.r);
				            ((ExprLogicalAndContext)_localctx).r =  app;
				        
				}
				}
				setState(293);
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

	public static class ExprLogicalOrContext extends ParserRuleContext {
		public AstNode r;
		public ExprLogicalAndContext x;
		public Token op;
		public ExprLogicalAndContext y;
		public List<ExprLogicalAndContext> exprLogicalAnd() {
			return getRuleContexts(ExprLogicalAndContext.class);
		}
		public ExprLogicalAndContext exprLogicalAnd(int i) {
			return getRuleContext(ExprLogicalAndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(OwlParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(OwlParser.OR, i);
		}
		public ExprLogicalOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLogicalOr; }
	}

	public final ExprLogicalOrContext exprLogicalOr() throws RecognitionException {
		ExprLogicalOrContext _localctx = new ExprLogicalOrContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exprLogicalOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			((ExprLogicalOrContext)_localctx).x = exprLogicalAnd();
			 ((ExprLogicalOrContext)_localctx).r =  ((ExprLogicalOrContext)_localctx).x.r; 
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(296);
				((ExprLogicalOrContext)_localctx).op = match(OR);
				setState(297);
				((ExprLogicalOrContext)_localctx).y = exprLogicalAnd();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExprLogicalOrContext)_localctx).op!=null?((ExprLogicalOrContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExprLogicalOrContext)_localctx).y.r);
				            ((ExprLogicalOrContext)_localctx).r =  app;
				        
				}
				}
				setState(304);
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
		public ExprLogicalOrContext x;
		public Token op;
		public ExprLogicalOrContext y;
		public List<ExprLogicalOrContext> exprLogicalOr() {
			return getRuleContexts(ExprLogicalOrContext.class);
		}
		public ExprLogicalOrContext exprLogicalOr(int i) {
			return getRuleContext(ExprLogicalOrContext.class,i);
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
			setState(305);
			((ExpressionContext)_localctx).x = exprLogicalOr();
			 ((ExpressionContext)_localctx).r =  ((ExpressionContext)_localctx).x.r; 
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) {
				{
				{
				setState(307);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD) | (1L << ASSIGN_PLS) | (1L << ASSIGN_MNS) | (1L << ASSIGN_LSHIFT) | (1L << ASSIGN_RSHIFT) | (1L << ASSIGN_SIGNED_RSHIFT) | (1L << ASSIGN_BIT_AND) | (1L << ASSIGN_BIT_XOR) | (1L << ASSIGN_BIT_OR))) != 0)) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(308);
				((ExpressionContext)_localctx).y = exprLogicalOr();

				            AstApply app = new AstApply();
				            app.args.add(new AstName((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)));
				            app.args.add(_localctx.r);
				            app.args.add(((ExpressionContext)_localctx).y.r);
				            ((ExpressionContext)_localctx).r =  app;
				        
				}
				}
				setState(315);
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
			setState(316);
			match(IF);
			setState(317);
			((StmtIfContext)_localctx).cond = expression();
			 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
			setState(319);
			((StmtIfContext)_localctx).b = block();
			 _localctx.r.branch.add(((StmtIfContext)_localctx).b.r); 
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(321);
				match(ELIF);
				setState(322);
				((StmtIfContext)_localctx).cond = expression();
				 _localctx.r.condition.add(((StmtIfContext)_localctx).cond.r); 
				setState(324);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.branch.add(((StmtIfContext)_localctx).b.r); 
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(336);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(332);
				match(ELSE);
				setState(333);
				((StmtIfContext)_localctx).b = block();
				 _localctx.r.branch.add(((StmtIfContext)_localctx).b.r); 
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
		public TerminalNode SEMICOLON() { return getToken(OwlParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StmtIfContext stmtIf() {
			return getRuleContext(StmtIfContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_statement);
		try {
			setState(345);
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
				setState(338);
				((StatementContext)_localctx).e = expression();
				setState(339);
				match(SEMICOLON);
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).e.r; 
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
				((StatementContext)_localctx).s = stmtIf();
				 ((StatementContext)_localctx).r =  ((StatementContext)_localctx).s.r; 
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
		public QualifiedNameContext n;
		public TypeInstanceContext a;
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
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
		enterRule(_localctx, 46, RULE_typeNonFn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			((TypeNonFnContext)_localctx).n = qualifiedName();
			 _localctx.r.name = ((TypeNonFnContext)_localctx).n.r; 
			setState(370);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(349);
				match(LPAREN);
				setState(350);
				((TypeNonFnContext)_localctx).a = typeInstance();
				 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(352);
					match(COMMA);
					setState(353);
					((TypeNonFnContext)_localctx).a = typeInstance();
					 _localctx.r.args.add(((TypeNonFnContext)_localctx).a.r); 
					}
					}
					setState(360);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(361);
				match(RPAREN);
				}
				break;
			case LBRACKET:
				{
				setState(366); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(363);
					match(LBRACKET);
					setState(364);
					match(RBRACKET);

					                AstType arrayType = new AstType("Array");
					                arrayType.args.add(_localctx.r);
					                ((TypeNonFnContext)_localctx).r =  arrayType;
					            
					}
					}
					setState(368); 
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
		enterRule(_localctx, 48, RULE_typeInstance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 AstType functionType = null; 
			setState(373);
			((TypeInstanceContext)_localctx).x = typeNonFn();
			 ((TypeInstanceContext)_localctx).r =  ((TypeInstanceContext)_localctx).x.r; 
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ARROW) {
				{
				{
				setState(375);
				match(ARROW);
				setState(376);
				((TypeInstanceContext)_localctx).y = typeNonFn();

				            if (functionType == null) {
				                functionType = new AstType("Fn");
				                functionType.args.add(_localctx.r);
				                ((TypeInstanceContext)_localctx).r =  functionType;
				            }
				            functionType.args.add(((TypeInstanceContext)_localctx).y.r);
				        
				}
				}
				setState(383);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3=\u0183\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\3\3\3\3\3\3\3\3\3\3\7\3"+
		"B\n\3\f\3\16\3E\13\3\3\4\3\4\3\4\5\4J\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\7\4S\n\4\f\4\16\4V\13\4\5\4X\n\4\3\4\5\4[\n\4\3\4\3\4\3\4\3\4\5\4a\n"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5l\n\5\3\6\3\6\3\6\3\6\7\6r\n"+
		"\6\f\6\16\6u\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7\u0086\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u0095\n\b\f\b\16\b\u0098\13\b\5\b\u009a\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u00a5\n\b\f\b\16\b\u00a8\13\b\3\b\3\b\7\b\u00ac"+
		"\n\b\f\b\16\b\u00af\13\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00b7\n\t\3\n\5\n"+
		"\u00ba\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c5\n\13\f"+
		"\13\16\13\u00c8\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00d0\n\f\f\f\16\f\u00d3"+
		"\13\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00db\n\r\f\r\16\r\u00de\13\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\7\16\u00e6\n\16\f\16\16\16\u00e9\13\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\7\17\u00f1\n\17\f\17\16\17\u00f4\13\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\7\20\u00fc\n\20\f\20\16\20\u00ff\13\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\7\21\u0107\n\21\f\21\16\21\u010a\13\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\7\22\u0112\n\22\f\22\16\22\u0115\13\22\3\23\5"+
		"\23\u0118\n\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24"+
		"\u0124\n\24\f\24\16\24\u0127\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u012f\n\25\f\25\16\25\u0132\13\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26"+
		"\u013a\n\26\f\26\16\26\u013d\13\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\7\27\u014a\n\27\f\27\16\27\u014d\13\27\3\27\3\27"+
		"\3\27\3\27\5\27\u0153\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u015c"+
		"\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0167\n\31\f\31"+
		"\16\31\u016a\13\31\3\31\3\31\3\31\3\31\3\31\6\31\u0171\n\31\r\31\16\31"+
		"\u0172\5\31\u0175\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u017e\n"+
		"\32\f\32\16\32\u0181\13\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\2\t\4\2\27\27\34\35\3\2\31\33\3\2\34\35\3\2\36 "+
		"\3\2&)\4\2\7\7$%\3\2,\67\u0192\29\3\2\2\2\4<\3\2\2\2\6F\3\2\2\2\be\3\2"+
		"\2\2\nm\3\2\2\2\f\u0085\3\2\2\2\16\u0087\3\2\2\2\20\u00b0\3\2\2\2\22\u00b9"+
		"\3\2\2\2\24\u00be\3\2\2\2\26\u00c9\3\2\2\2\30\u00d4\3\2\2\2\32\u00df\3"+
		"\2\2\2\34\u00ea\3\2\2\2\36\u00f5\3\2\2\2 \u0100\3\2\2\2\"\u010b\3\2\2"+
		"\2$\u0117\3\2\2\2&\u011d\3\2\2\2(\u0128\3\2\2\2*\u0133\3\2\2\2,\u013e"+
		"\3\2\2\2.\u015b\3\2\2\2\60\u015d\3\2\2\2\62\u0176\3\2\2\2\64\65\5\6\4"+
		"\2\65\66\b\2\1\2\668\3\2\2\2\67\64\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2"+
		"\2\2:\3\3\2\2\2;9\3\2\2\2<=\7;\2\2=C\b\3\1\2>?\7\t\2\2?@\7;\2\2@B\b\3"+
		"\1\2A>\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\5\3\2\2\2EC\3\2\2\2FI\7"+
		"\5\2\2GH\7;\2\2HJ\b\4\1\2IG\3\2\2\2IJ\3\2\2\2JZ\3\2\2\2KW\7\16\2\2LM\5"+
		"\b\5\2MT\b\4\1\2NO\7\n\2\2OP\5\b\5\2PQ\b\4\1\2QS\3\2\2\2RN\3\2\2\2SV\3"+
		"\2\2\2TR\3\2\2\2TU\3\2\2\2UX\3\2\2\2VT\3\2\2\2WL\3\2\2\2WX\3\2\2\2XY\3"+
		"\2\2\2Y[\7\17\2\2ZK\3\2\2\2Z[\3\2\2\2[`\3\2\2\2\\]\7\13\2\2]^\5\62\32"+
		"\2^_\b\4\1\2_a\3\2\2\2`\\\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\5\n\6\2cd\b\4"+
		"\1\2d\7\3\2\2\2ef\7;\2\2fk\b\5\1\2gh\7\13\2\2hi\5\62\32\2ij\b\5\1\2jl"+
		"\3\2\2\2kg\3\2\2\2kl\3\2\2\2l\t\3\2\2\2ms\7\20\2\2no\5.\30\2op\b\6\1\2"+
		"pr\3\2\2\2qn\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2\2\2"+
		"vw\7\21\2\2w\13\3\2\2\2xy\7;\2\2y\u0086\b\7\1\2z{\78\2\2{\u0086\b\7\1"+
		"\2|}\79\2\2}\u0086\b\7\1\2~\177\7:\2\2\177\u0086\b\7\1\2\u0080\u0081\7"+
		"\16\2\2\u0081\u0082\5*\26\2\u0082\u0083\7\17\2\2\u0083\u0084\b\7\1\2\u0084"+
		"\u0086\3\2\2\2\u0085x\3\2\2\2\u0085z\3\2\2\2\u0085|\3\2\2\2\u0085~\3\2"+
		"\2\2\u0085\u0080\3\2\2\2\u0086\r\3\2\2\2\u0087\u0088\5\f\7\2\u0088\u00ad"+
		"\b\b\1\2\u0089\u008a\7\t\2\2\u008a\u008b\7;\2\2\u008b\u00ac\b\b\1\2\u008c"+
		"\u008d\b\b\1\2\u008d\u0099\7\16\2\2\u008e\u008f\5*\26\2\u008f\u0096\b"+
		"\b\1\2\u0090\u0091\7\n\2\2\u0091\u0092\5*\26\2\u0092\u0093\b\b\1\2\u0093"+
		"\u0095\3\2\2\2\u0094\u0090\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"\u008e\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u00ac\7\17"+
		"\2\2\u009c\u009d\b\b\1\2\u009d\u009e\7\22\2\2\u009e\u009f\5*\26\2\u009f"+
		"\u00a6\b\b\1\2\u00a0\u00a1\7\n\2\2\u00a1\u00a2\5*\26\2\u00a2\u00a3\b\b"+
		"\1\2\u00a3\u00a5\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a9\u00aa\7\23\2\2\u00aa\u00ac\3\2\2\2\u00ab\u0089\3\2\2\2\u00ab"+
		"\u008c\3\2\2\2\u00ab\u009c\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\17\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1"+
		"\5\16\b\2\u00b1\u00b6\b\t\1\2\u00b2\u00b3\7\13\2\2\u00b3\u00b4\5\62\32"+
		"\2\u00b4\u00b5\b\t\1\2\u00b5\u00b7\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7\21\3\2\2\2\u00b8\u00ba\t\2\2\2\u00b9\u00b8\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\5\20\t\2\u00bc\u00bd\b"+
		"\n\1\2\u00bd\23\3\2\2\2\u00be\u00bf\5\22\n\2\u00bf\u00c6\b\13\1\2\u00c0"+
		"\u00c1\t\3\2\2\u00c1\u00c2\5\22\n\2\u00c2\u00c3\b\13\1\2\u00c3\u00c5\3"+
		"\2\2\2\u00c4\u00c0\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\25\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\5\24\13"+
		"\2\u00ca\u00d1\b\f\1\2\u00cb\u00cc\t\4\2\2\u00cc\u00cd\5\24\13\2\u00cd"+
		"\u00ce\b\f\1\2\u00ce\u00d0\3\2\2\2\u00cf\u00cb\3\2\2\2\u00d0\u00d3\3\2"+
		"\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\27\3\2\2\2\u00d3\u00d1"+
		"\3\2\2\2\u00d4\u00d5\5\26\f\2\u00d5\u00dc\b\r\1\2\u00d6\u00d7\t\5\2\2"+
		"\u00d7\u00d8\5\26\f\2\u00d8\u00d9\b\r\1\2\u00d9\u00db\3\2\2\2\u00da\u00d6"+
		"\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\31\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\5\30\r\2\u00e0\u00e7\b\16"+
		"\1\2\u00e1\u00e2\7!\2\2\u00e2\u00e3\5\30\r\2\u00e3\u00e4\b\16\1\2\u00e4"+
		"\u00e6\3\2\2\2\u00e5\u00e1\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2"+
		"\2\2\u00e7\u00e8\3\2\2\2\u00e8\33\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00eb"+
		"\5\32\16\2\u00eb\u00f2\b\17\1\2\u00ec\u00ed\7\"\2\2\u00ed\u00ee\5\32\16"+
		"\2\u00ee\u00ef\b\17\1\2\u00ef\u00f1\3\2\2\2\u00f0\u00ec\3\2\2\2\u00f1"+
		"\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\35\3\2\2"+
		"\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\5\34\17\2\u00f6\u00fd\b\20\1\2\u00f7"+
		"\u00f8\7#\2\2\u00f8\u00f9\5\34\17\2\u00f9\u00fa\b\20\1\2\u00fa\u00fc\3"+
		"\2\2\2\u00fb\u00f7\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd"+
		"\u00fe\3\2\2\2\u00fe\37\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\5\36\20"+
		"\2\u0101\u0108\b\21\1\2\u0102\u0103\t\6\2\2\u0103\u0104\5\36\20\2\u0104"+
		"\u0105\b\21\1\2\u0105\u0107\3\2\2\2\u0106\u0102\3\2\2\2\u0107\u010a\3"+
		"\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109!\3\2\2\2\u010a\u0108"+
		"\3\2\2\2\u010b\u010c\5 \21\2\u010c\u0113\b\22\1\2\u010d\u010e\t\7\2\2"+
		"\u010e\u010f\5 \21\2\u010f\u0110\b\22\1\2\u0110\u0112\3\2\2\2\u0111\u010d"+
		"\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"#\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0118\7\30\2\2\u0117\u0116\3\2\2\2"+
		"\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\5\"\22\2\u011a\u011b"+
		"\b\23\1\2\u011b\u011c\b\23\1\2\u011c%\3\2\2\2\u011d\u011e\5$\23\2\u011e"+
		"\u0125\b\24\1\2\u011f\u0120\7*\2\2\u0120\u0121\5$\23\2\u0121\u0122\b\24"+
		"\1\2\u0122\u0124\3\2\2\2\u0123\u011f\3\2\2\2\u0124\u0127\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\'\3\2\2\2\u0127\u0125\3\2\2\2"+
		"\u0128\u0129\5&\24\2\u0129\u0130\b\25\1\2\u012a\u012b\7+\2\2\u012b\u012c"+
		"\5&\24\2\u012c\u012d\b\25\1\2\u012d\u012f\3\2\2\2\u012e\u012a\3\2\2\2"+
		"\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131)\3"+
		"\2\2\2\u0132\u0130\3\2\2\2\u0133\u0134\5(\25\2\u0134\u013b\b\26\1\2\u0135"+
		"\u0136\t\b\2\2\u0136\u0137\5(\25\2\u0137\u0138\b\26\1\2\u0138\u013a\3"+
		"\2\2\2\u0139\u0135\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c+\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7\6\2\2"+
		"\u013f\u0140\5*\26\2\u0140\u0141\b\27\1\2\u0141\u0142\5\n\6\2\u0142\u014b"+
		"\b\27\1\2\u0143\u0144\7\3\2\2\u0144\u0145\5*\26\2\u0145\u0146\b\27\1\2"+
		"\u0146\u0147\5\n\6\2\u0147\u0148\b\27\1\2\u0148\u014a\3\2\2\2\u0149\u0143"+
		"\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u0152\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7\4\2\2\u014f\u0150\5\n"+
		"\6\2\u0150\u0151\b\27\1\2\u0151\u0153\3\2\2\2\u0152\u014e\3\2\2\2\u0152"+
		"\u0153\3\2\2\2\u0153-\3\2\2\2\u0154\u0155\5*\26\2\u0155\u0156\7\f\2\2"+
		"\u0156\u0157\b\30\1\2\u0157\u015c\3\2\2\2\u0158\u0159\5,\27\2\u0159\u015a"+
		"\b\30\1\2\u015a\u015c\3\2\2\2\u015b\u0154\3\2\2\2\u015b\u0158\3\2\2\2"+
		"\u015c/\3\2\2\2\u015d\u015e\5\4\3\2\u015e\u0174\b\31\1\2\u015f\u0160\7"+
		"\16\2\2\u0160\u0161\5\62\32\2\u0161\u0168\b\31\1\2\u0162\u0163\7\n\2\2"+
		"\u0163\u0164\5\62\32\2\u0164\u0165\b\31\1\2\u0165\u0167\3\2\2\2\u0166"+
		"\u0162\3\2\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2"+
		"\2\2\u0169\u016b\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u016c\7\17\2\2\u016c"+
		"\u0175\3\2\2\2\u016d\u016e\7\22\2\2\u016e\u016f\7\23\2\2\u016f\u0171\b"+
		"\31\1\2\u0170\u016d\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0170\3\2\2\2\u0172"+
		"\u0173\3\2\2\2\u0173\u0175\3\2\2\2\u0174\u015f\3\2\2\2\u0174\u0170\3\2"+
		"\2\2\u0174\u0175\3\2\2\2\u0175\61\3\2\2\2\u0176\u0177\b\32\1\2\u0177\u0178"+
		"\5\60\31\2\u0178\u017f\b\32\1\2\u0179\u017a\7\r\2\2\u017a\u017b\5\60\31"+
		"\2\u017b\u017c\b\32\1\2\u017c\u017e\3\2\2\2\u017d\u0179\3\2\2\2\u017e"+
		"\u0181\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\63\3\2\2"+
		"\2\u0181\u017f\3\2\2\2&9CITWZ`ks\u0085\u0096\u0099\u00a6\u00ab\u00ad\u00b6"+
		"\u00b9\u00c6\u00d1\u00dc\u00e7\u00f2\u00fd\u0108\u0113\u0117\u0125\u0130"+
		"\u013b\u014b\u0152\u015b\u0168\u0172\u0174\u017f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}