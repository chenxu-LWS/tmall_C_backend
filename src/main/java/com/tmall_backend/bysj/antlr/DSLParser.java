package com.tmall_backend.bysj.antlr;// Generated from DSL.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DSLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, INT=14, WS=15, NEWLINE=16;
	public static final int
		RULE_init = 0, RULE_couponstatement = 1, RULE_fullminusstatement = 2, 
		RULE_discountstatement = 3, RULE_condition = 4, RULE_startclause = 5, 
		RULE_endclause = 6, RULE_variable = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"init", "couponstatement", "fullminusstatement", "discountstatement", 
			"condition", "startclause", "endclause", "variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Coupon'", "'FullMinus'", "'Discount'", "'&&'", "'||'", "'=='", 
			"'Start'", "'-'", "':'", "'End'", "'[\u5546\u54C1ID]'", "'[\u54C1\u7C7BID]'", 
			"'[\u54C1\u724CID]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "INT", "WS", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "DSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DSLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class InitContext extends ParserRuleContext {
		public CouponstatementContext couponstatement() {
			return getRuleContext(CouponstatementContext.class,0);
		}
		public FullminusstatementContext fullminusstatement() {
			return getRuleContext(FullminusstatementContext.class,0);
		}
		public DiscountstatementContext discountstatement() {
			return getRuleContext(DiscountstatementContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_init);
		try {
			setState(19);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				couponstatement();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(17);
				fullminusstatement();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(18);
				discountstatement();
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

	public static class CouponstatementContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(DSLParser.INT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StartclauseContext startclause() {
			return getRuleContext(StartclauseContext.class,0);
		}
		public EndclauseContext endclause() {
			return getRuleContext(EndclauseContext.class,0);
		}
		public CouponstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_couponstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterCouponstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitCouponstatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitCouponstatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CouponstatementContext couponstatement() throws RecognitionException {
		CouponstatementContext _localctx = new CouponstatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_couponstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(T__0);
			setState(22);
			match(INT);
			setState(23);
			condition(0);
			setState(24);
			startclause();
			setState(25);
			endclause();
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

	public static class FullminusstatementContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(DSLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(DSLParser.INT, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StartclauseContext startclause() {
			return getRuleContext(StartclauseContext.class,0);
		}
		public EndclauseContext endclause() {
			return getRuleContext(EndclauseContext.class,0);
		}
		public FullminusstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullminusstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterFullminusstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitFullminusstatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitFullminusstatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullminusstatementContext fullminusstatement() throws RecognitionException {
		FullminusstatementContext _localctx = new FullminusstatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_fullminusstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(T__1);
			setState(28);
			match(INT);
			setState(29);
			match(INT);
			setState(30);
			condition(0);
			setState(31);
			startclause();
			setState(32);
			endclause();
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

	public static class DiscountstatementContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(DSLParser.INT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StartclauseContext startclause() {
			return getRuleContext(StartclauseContext.class,0);
		}
		public EndclauseContext endclause() {
			return getRuleContext(EndclauseContext.class,0);
		}
		public DiscountstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_discountstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterDiscountstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitDiscountstatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitDiscountstatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DiscountstatementContext discountstatement() throws RecognitionException {
		DiscountstatementContext _localctx = new DiscountstatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_discountstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__2);
			setState(35);
			match(INT);
			setState(36);
			condition(0);
			setState(37);
			startclause();
			setState(38);
			endclause();
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

	public static class ConditionContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode INT() { return getToken(DSLParser.INT, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_condition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(41);
			variable();
			setState(42);
			match(T__5);
			setState(43);
			match(INT);
			}
			_ctx.stop = _input.LT(-1);
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(51);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(45);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(46);
						match(T__3);
						setState(47);
						condition(4);
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(48);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(49);
						match(T__4);
						setState(50);
						condition(3);
						}
						break;
					}
					} 
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StartclauseContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(DSLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(DSLParser.INT, i);
		}
		public StartclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startclause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterStartclause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitStartclause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitStartclause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartclauseContext startclause() throws RecognitionException {
		StartclauseContext _localctx = new StartclauseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_startclause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__6);
			setState(57);
			match(INT);
			setState(58);
			match(T__7);
			setState(59);
			match(INT);
			setState(60);
			match(T__7);
			setState(61);
			match(INT);
			setState(62);
			match(T__7);
			setState(63);
			match(INT);
			setState(64);
			match(T__8);
			setState(65);
			match(INT);
			setState(66);
			match(T__8);
			setState(67);
			match(INT);
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

	public static class EndclauseContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(DSLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(DSLParser.INT, i);
		}
		public EndclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endclause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterEndclause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitEndclause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitEndclause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndclauseContext endclause() throws RecognitionException {
		EndclauseContext _localctx = new EndclauseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_endclause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__9);
			setState(70);
			match(INT);
			setState(71);
			match(T__7);
			setState(72);
			match(INT);
			setState(73);
			match(T__7);
			setState(74);
			match(INT);
			setState(75);
			match(T__7);
			setState(76);
			match(INT);
			setState(77);
			match(T__8);
			setState(78);
			match(INT);
			setState(79);
			match(T__8);
			setState(80);
			match(INT);
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
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLListener ) ((DSLListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLVisitor ) return ((DSLVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22W\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\5\2\26"+
		"\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\66\n\6\f\6"+
		"\16\69\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\2\3\n\n\2\4"+
		"\6\b\n\f\16\20\2\3\3\2\r\17\2R\2\25\3\2\2\2\4\27\3\2\2\2\6\35\3\2\2\2"+
		"\b$\3\2\2\2\n*\3\2\2\2\f:\3\2\2\2\16G\3\2\2\2\20T\3\2\2\2\22\26\5\4\3"+
		"\2\23\26\5\6\4\2\24\26\5\b\5\2\25\22\3\2\2\2\25\23\3\2\2\2\25\24\3\2\2"+
		"\2\26\3\3\2\2\2\27\30\7\3\2\2\30\31\7\20\2\2\31\32\5\n\6\2\32\33\5\f\7"+
		"\2\33\34\5\16\b\2\34\5\3\2\2\2\35\36\7\4\2\2\36\37\7\20\2\2\37 \7\20\2"+
		"\2 !\5\n\6\2!\"\5\f\7\2\"#\5\16\b\2#\7\3\2\2\2$%\7\5\2\2%&\7\20\2\2&\'"+
		"\5\n\6\2\'(\5\f\7\2()\5\16\b\2)\t\3\2\2\2*+\b\6\1\2+,\5\20\t\2,-\7\b\2"+
		"\2-.\7\20\2\2.\67\3\2\2\2/\60\f\5\2\2\60\61\7\6\2\2\61\66\5\n\6\6\62\63"+
		"\f\4\2\2\63\64\7\7\2\2\64\66\5\n\6\5\65/\3\2\2\2\65\62\3\2\2\2\669\3\2"+
		"\2\2\67\65\3\2\2\2\678\3\2\2\28\13\3\2\2\29\67\3\2\2\2:;\7\t\2\2;<\7\20"+
		"\2\2<=\7\n\2\2=>\7\20\2\2>?\7\n\2\2?@\7\20\2\2@A\7\n\2\2AB\7\20\2\2BC"+
		"\7\13\2\2CD\7\20\2\2DE\7\13\2\2EF\7\20\2\2F\r\3\2\2\2GH\7\f\2\2HI\7\20"+
		"\2\2IJ\7\n\2\2JK\7\20\2\2KL\7\n\2\2LM\7\20\2\2MN\7\n\2\2NO\7\20\2\2OP"+
		"\7\13\2\2PQ\7\20\2\2QR\7\13\2\2RS\7\20\2\2S\17\3\2\2\2TU\t\2\2\2U\21\3"+
		"\2\2\2\5\25\65\67";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}