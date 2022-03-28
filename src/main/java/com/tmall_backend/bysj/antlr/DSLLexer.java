package com.tmall_backend.bysj.antlr;// Generated from DSL.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DSLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, INT=14, WS=15, NEWLINE=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "INT", "WS", "NEWLINE"
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


	public DSLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\6\17k\n\17\r\17\16\17l\3\20\6\20p\n\20\r\20\16\20q\3\20\3\20\3\21"+
		"\6\21w\n\21\r\21\16\21x\2\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22\3\2\4\5\2\13\f\17\17\"\"\4\2\f"+
		"\f\17\17\2|\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\3#\3\2\2\2\5*\3\2\2\2\7\64\3\2\2\2\t=\3\2\2\2\13@\3\2\2\2\rC\3\2\2"+
		"\2\17F\3\2\2\2\21L\3\2\2\2\23N\3\2\2\2\25P\3\2\2\2\27T\3\2\2\2\31[\3\2"+
		"\2\2\33b\3\2\2\2\35j\3\2\2\2\37o\3\2\2\2!v\3\2\2\2#$\7E\2\2$%\7q\2\2%"+
		"&\7w\2\2&\'\7r\2\2\'(\7q\2\2()\7p\2\2)\4\3\2\2\2*+\7H\2\2+,\7w\2\2,-\7"+
		"n\2\2-.\7n\2\2./\7O\2\2/\60\7k\2\2\60\61\7p\2\2\61\62\7w\2\2\62\63\7u"+
		"\2\2\63\6\3\2\2\2\64\65\7F\2\2\65\66\7k\2\2\66\67\7u\2\2\678\7e\2\289"+
		"\7q\2\29:\7w\2\2:;\7p\2\2;<\7v\2\2<\b\3\2\2\2=>\7(\2\2>?\7(\2\2?\n\3\2"+
		"\2\2@A\7~\2\2AB\7~\2\2B\f\3\2\2\2CD\7?\2\2DE\7?\2\2E\16\3\2\2\2FG\7U\2"+
		"\2GH\7v\2\2HI\7c\2\2IJ\7t\2\2JK\7v\2\2K\20\3\2\2\2LM\7/\2\2M\22\3\2\2"+
		"\2NO\7<\2\2O\24\3\2\2\2PQ\7G\2\2QR\7p\2\2RS\7f\2\2S\26\3\2\2\2TU\7]\2"+
		"\2UV\7\u5548\2\2VW\7\u54c3\2\2WX\7K\2\2XY\7F\2\2YZ\7_\2\2Z\30\3\2\2\2"+
		"[\\\7]\2\2\\]\7\u54c3\2\2]^\7\u7c7d\2\2^_\7K\2\2_`\7F\2\2`a\7_\2\2a\32"+
		"\3\2\2\2bc\7]\2\2cd\7\u54c3\2\2de\7\u724e\2\2ef\7K\2\2fg\7F\2\2gh\7_\2"+
		"\2h\34\3\2\2\2ik\4\62;\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\36\3"+
		"\2\2\2np\t\2\2\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\b"+
		"\20\2\2t \3\2\2\2uw\t\3\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\""+
		"\3\2\2\2\6\2lqx\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}