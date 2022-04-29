// Generated from HyperRule.g4 by ANTLR 4.7

package bgdt.hyperBase;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HyperRuleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT_LITERAL=1, FLOAT_LITERAL=2, STRING_LITERAL=3, BOOLEAN_LITERAL=4, NULL_LITERAL=5, 
		SUM=6, AVG=7, SQRT=8, LOG=9, COUNT=10, LEN=11, MAX=12, MIN=13, SUBSTR=14, 
		TRIM=15, IS_NUMBER=16, IS_INT=17, IS_NULL=18, TO_STR=19, TO_INT=20, TO_FLOAT=21, 
		TO_DATE=22, ADD_TO_DATE=23, DATE_DIFF=24, SYSDATE=25, IF=26, ELIF=27, 
		ELSE=28, LOCAL_VAR=29, REF_VAR=30, ADD=31, SUB=32, MUL=33, DIV=34, FLOOR_DIV=35, 
		MOD=36, POW=37, ASSIGN=38, NOT=39, AND=40, OR=41, EQ=42, NE=43, GT=44, 
		LT=45, GE=46, LE=47, LPAREN=48, RPAREN=49, LBRACE=50, RBRACE=51, SEMI=52, 
		COMMA=53, BLOCK_COMMENT=54, WS=55, LINE_COMMENT=56;
	public static final int
		RULE_statements = 0, RULE_statements_block = 1, RULE_statement = 2, RULE_assign_statement = 3, 
		RULE_if_statement = 4, RULE_condition = 5, RULE_condition_branch = 6, 
		RULE_expression = 7, RULE_paralist = 8, RULE_para = 9;
	public static final String[] ruleNames = {
		"statements", "statements_block", "statement", "assign_statement", "if_statement", 
		"condition", "condition_branch", "expression", "paralist", "para"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'null'", "'sum'", "'avg'", "'sqrt'", "'log'", 
		"'count'", "'len'", "'max'", "'min'", "'substr'", "'trim'", "'is_number'", 
		"'is_int'", "'is_null'", "'to_str'", "'to_int'", "'to_float'", "'to_date'", 
		"'add_to_date'", "'date_diff'", "'sysdate'", "'if'", "'elif'", "'else'", 
		null, null, "'+'", "'-'", "'*'", "'/'", "'//'", "'%'", "'**'", "'='", 
		"'!'", "'&&'", "'||'", "'=='", "'!='", "'>'", "'<'", "'>='", "'<='", "'('", 
		"')'", "'{'", "'}'", "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "INT_LITERAL", "FLOAT_LITERAL", "STRING_LITERAL", "BOOLEAN_LITERAL", 
		"NULL_LITERAL", "SUM", "AVG", "SQRT", "LOG", "COUNT", "LEN", "MAX", "MIN", 
		"SUBSTR", "TRIM", "IS_NUMBER", "IS_INT", "IS_NULL", "TO_STR", "TO_INT", 
		"TO_FLOAT", "TO_DATE", "ADD_TO_DATE", "DATE_DIFF", "SYSDATE", "IF", "ELIF", 
		"ELSE", "LOCAL_VAR", "REF_VAR", "ADD", "SUB", "MUL", "DIV", "FLOOR_DIV", 
		"MOD", "POW", "ASSIGN", "NOT", "AND", "OR", "EQ", "NE", "GT", "LT", "GE", 
		"LE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", "BLOCK_COMMENT", 
		"WS", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "HyperRule.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HyperRuleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(HyperRuleParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(HyperRuleParser.SEMI, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			statement();
			setState(25);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(21);
					match(SEMI);
					setState(22);
					statement();
					}
					} 
				}
				setState(27);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(28);
			match(SEMI);
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

	public static class Statements_blockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(HyperRuleParser.LBRACE, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(HyperRuleParser.RBRACE, 0); }
		public Statements_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitStatements_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Statements_blockContext statements_block() throws RecognitionException {
		Statements_blockContext _localctx = new Statements_blockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statements_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(LBRACE);
			setState(31);
			statements();
			setState(32);
			match(RBRACE);
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
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStmtContext extends StatementContext {
		public Assign_statementContext assign_statement() {
			return getRuleContext(Assign_statementContext.class,0);
		}
		public AssignStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAssignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(37);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				expression(0);
				}
				break;
			case 2:
				_localctx = new AssignStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				assign_statement();
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				if_statement();
				}
				break;
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

	public static class Assign_statementContext extends ParserRuleContext {
		public Assign_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_statement; }
	 
		public Assign_statementContext() { }
		public void copyFrom(Assign_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignLocalContext extends Assign_statementContext {
		public TerminalNode LOCAL_VAR() { return getToken(HyperRuleParser.LOCAL_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(HyperRuleParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignLocalContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAssignLocal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignRefContext extends Assign_statementContext {
		public TerminalNode REF_VAR() { return getToken(HyperRuleParser.REF_VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(HyperRuleParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignRefContext(Assign_statementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAssignRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_statementContext assign_statement() throws RecognitionException {
		Assign_statementContext _localctx = new Assign_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assign_statement);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOCAL_VAR:
				_localctx = new AssignLocalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				match(LOCAL_VAR);
				setState(40);
				match(ASSIGN);
				setState(41);
				expression(0);
				}
				break;
			case REF_VAR:
				_localctx = new AssignRefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				match(REF_VAR);
				setState(43);
				match(ASSIGN);
				setState(44);
				expression(0);
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

	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(HyperRuleParser.IF, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(HyperRuleParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(HyperRuleParser.LPAREN, i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(HyperRuleParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(HyperRuleParser.RPAREN, i);
		}
		public List<Condition_branchContext> condition_branch() {
			return getRuleContexts(Condition_branchContext.class);
		}
		public Condition_branchContext condition_branch(int i) {
			return getRuleContext(Condition_branchContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(HyperRuleParser.ELSE, 0); }
		public List<TerminalNode> ELIF() { return getTokens(HyperRuleParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(HyperRuleParser.ELIF, i);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(IF);
			setState(48);
			match(LPAREN);
			setState(49);
			condition();
			setState(50);
			match(RPAREN);
			setState(51);
			condition_branch();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(52);
				match(ELIF);
				setState(53);
				match(LPAREN);
				setState(54);
				condition();
				setState(55);
				match(RPAREN);
				setState(56);
				condition_branch();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(ELSE);
			setState(64);
			condition_branch();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			expression(0);
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

	public static class Condition_branchContext extends ParserRuleContext {
		public Condition_branchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition_branch; }
	 
		public Condition_branchContext() { }
		public void copyFrom(Condition_branchContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BranchStmtContext extends Condition_branchContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BranchStmtContext(Condition_branchContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitBranchStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BranchBlockContext extends Condition_branchContext {
		public Statements_blockContext statements_block() {
			return getRuleContext(Statements_blockContext.class,0);
		}
		public BranchBlockContext(Condition_branchContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitBranchBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Condition_branchContext condition_branch() throws RecognitionException {
		Condition_branchContext _localctx = new Condition_branchContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_condition_branch);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
			case FLOAT_LITERAL:
			case STRING_LITERAL:
			case BOOLEAN_LITERAL:
			case NULL_LITERAL:
			case SUM:
			case AVG:
			case SQRT:
			case LOG:
			case COUNT:
			case LEN:
			case MAX:
			case MIN:
			case SUBSTR:
			case TRIM:
			case IS_NUMBER:
			case IS_INT:
			case IS_NULL:
			case TO_STR:
			case TO_INT:
			case TO_FLOAT:
			case TO_DATE:
			case ADD_TO_DATE:
			case DATE_DIFF:
			case SYSDATE:
			case IF:
			case LOCAL_VAR:
			case REF_VAR:
			case SUB:
			case NOT:
			case LPAREN:
				_localctx = new BranchStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				statement();
				}
				break;
			case LBRACE:
				_localctx = new BranchBlockContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				statements_block();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(HyperRuleParser.ADD, 0); }
		public AddContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Date_diffContext extends ExpressionContext {
		public TerminalNode DATE_DIFF() { return getToken(HyperRuleParser.DATE_DIFF, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public Date_diffContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitDate_diff(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(HyperRuleParser.OR, 0); }
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MaxContext extends ExpressionContext {
		public TerminalNode MAX() { return getToken(HyperRuleParser.MAX, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public MaxContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitMax(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UminusContext extends ExpressionContext {
		public TerminalNode SUB() { return getToken(HyperRuleParser.SUB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UminusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitUminus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends ExpressionContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(HyperRuleParser.BOOLEAN_LITERAL, 0); }
		public BooleanLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Add_to_dateContext extends ExpressionContext {
		public TerminalNode ADD_TO_DATE() { return getToken(HyperRuleParser.ADD_TO_DATE, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public Add_to_dateContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAdd_to_date(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CountContext extends ExpressionContext {
		public TerminalNode COUNT() { return getToken(HyperRuleParser.COUNT, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public CountContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitCount(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(HyperRuleParser.EQ, 0); }
		public EqContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitEq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class To_dateContext extends ExpressionContext {
		public TerminalNode TO_DATE() { return getToken(HyperRuleParser.TO_DATE, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public To_dateContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitTo_date(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GtContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GT() { return getToken(HyperRuleParser.GT, 0); }
		public GtContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitGt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Is_nullContext extends ExpressionContext {
		public TerminalNode IS_NULL() { return getToken(HyperRuleParser.IS_NULL, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public Is_nullContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitIs_null(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubstrContext extends ExpressionContext {
		public TerminalNode SUBSTR() { return getToken(HyperRuleParser.SUBSTR, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public SubstrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitSubstr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NeContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NE() { return getToken(HyperRuleParser.NE, 0); }
		public NeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitNe(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefVarContext extends ExpressionContext {
		public TerminalNode REF_VAR() { return getToken(HyperRuleParser.REF_VAR, 0); }
		public RefVarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitRefVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LeContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LE() { return getToken(HyperRuleParser.LE, 0); }
		public LeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitLe(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLiteralContext extends ExpressionContext {
		public TerminalNode INT_LITERAL() { return getToken(HyperRuleParser.INT_LITERAL, 0); }
		public IntLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class To_floatContext extends ExpressionContext {
		public TerminalNode TO_FLOAT() { return getToken(HyperRuleParser.TO_FLOAT, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public To_floatContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitTo_float(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SUB() { return getToken(HyperRuleParser.SUB, 0); }
		public SubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MOD() { return getToken(HyperRuleParser.MOD, 0); }
		public ModContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogContext extends ExpressionContext {
		public TerminalNode LOG() { return getToken(HyperRuleParser.LOG, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public LogContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitLog(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrimContext extends ExpressionContext {
		public TerminalNode TRIM() { return getToken(HyperRuleParser.TRIM, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public TrimContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitTrim(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(HyperRuleParser.MUL, 0); }
		public MulContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatLiteralContext extends ExpressionContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(HyperRuleParser.FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SqrtContext extends ExpressionContext {
		public TerminalNode SQRT() { return getToken(HyperRuleParser.SQRT, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public SqrtContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitSqrt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LtContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LT() { return getToken(HyperRuleParser.LT, 0); }
		public LtContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitLt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Is_intContext extends ExpressionContext {
		public TerminalNode IS_INT() { return getToken(HyperRuleParser.IS_INT, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public Is_intContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitIs_int(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SumContext extends ExpressionContext {
		public TerminalNode SUM() { return getToken(HyperRuleParser.SUM, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public SumContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitSum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class To_intContext extends ExpressionContext {
		public TerminalNode TO_INT() { return getToken(HyperRuleParser.TO_INT, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public To_intContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitTo_int(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Is_numberContext extends ExpressionContext {
		public TerminalNode IS_NUMBER() { return getToken(HyperRuleParser.IS_NUMBER, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public Is_numberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitIs_number(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class To_strContext extends ExpressionContext {
		public TerminalNode TO_STR() { return getToken(HyperRuleParser.TO_STR, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public To_strContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitTo_str(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DIV() { return getToken(HyperRuleParser.DIV, 0); }
		public DivContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(HyperRuleParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AvgContext extends ExpressionContext {
		public TerminalNode AVG() { return getToken(HyperRuleParser.AVG, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public AvgContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAvg(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinContext extends ExpressionContext {
		public TerminalNode MIN() { return getToken(HyperRuleParser.MIN, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public MinContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitMin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExpressionContext {
		public TerminalNode STRING_LITERAL() { return getToken(HyperRuleParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LenContext extends ExpressionContext {
		public TerminalNode LEN() { return getToken(HyperRuleParser.LEN, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ParalistContext paralist() {
			return getRuleContext(ParalistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public LenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitLen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(HyperRuleParser.AND, 0); }
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralRefVarContext extends ExpressionContext {
		public List<TerminalNode> STRING_LITERAL() { return getTokens(HyperRuleParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(HyperRuleParser.STRING_LITERAL, i);
		}
		public TerminalNode REF_VAR() { return getToken(HyperRuleParser.REF_VAR, 0); }
		public StringLiteralRefVarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitStringLiteralRefVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SysdateContext extends ExpressionContext {
		public TerminalNode SYSDATE() { return getToken(HyperRuleParser.SYSDATE, 0); }
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public SysdateContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitSysdate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PowContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode POW() { return getToken(HyperRuleParser.POW, 0); }
		public PowContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitPow(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LocalVarContext extends ExpressionContext {
		public TerminalNode LOCAL_VAR() { return getToken(HyperRuleParser.LOCAL_VAR, 0); }
		public LocalVarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitLocalVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Floor_DivContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode FLOOR_DIV() { return getToken(HyperRuleParser.FLOOR_DIV, 0); }
		public Floor_DivContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitFloor_Div(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullLiteralContext extends ExpressionContext {
		public TerminalNode NULL_LITERAL() { return getToken(HyperRuleParser.NULL_LITERAL, 0); }
		public NullLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitNullLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GeContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GE() { return getToken(HyperRuleParser.GE, 0); }
		public GeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitGe(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(HyperRuleParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(HyperRuleParser.RPAREN, 0); }
		public ParenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new UminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(73);
				match(SUB);
				setState(74);
				expression(45);
				}
				break;
			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(75);
				match(NOT);
				setState(76);
				expression(32);
				}
				break;
			case 3:
				{
				_localctx = new ParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				match(LPAREN);
				setState(78);
				expression(0);
				setState(79);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new RefVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(REF_VAR);
				}
				break;
			case 5:
				{
				_localctx = new LocalVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(LOCAL_VAR);
				}
				break;
			case 6:
				{
				_localctx = new IntLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(INT_LITERAL);
				}
				break;
			case 7:
				{
				_localctx = new FloatLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				match(FLOAT_LITERAL);
				}
				break;
			case 8:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				match(STRING_LITERAL);
				}
				break;
			case 9:
				{
				_localctx = new StringLiteralRefVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				match(STRING_LITERAL);
				setState(87);
				match(REF_VAR);
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(88);
						match(STRING_LITERAL);
						}
						} 
					}
					setState(93);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
				break;
			case 10:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				match(BOOLEAN_LITERAL);
				}
				break;
			case 11:
				{
				_localctx = new NullLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(95);
				match(NULL_LITERAL);
				}
				break;
			case 12:
				{
				_localctx = new SumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(SUM);
				setState(97);
				match(LPAREN);
				setState(98);
				paralist();
				setState(99);
				match(RPAREN);
				}
				break;
			case 13:
				{
				_localctx = new AvgContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(101);
				match(AVG);
				setState(102);
				match(LPAREN);
				setState(103);
				paralist();
				setState(104);
				match(RPAREN);
				}
				break;
			case 14:
				{
				_localctx = new SqrtContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(SQRT);
				setState(107);
				match(LPAREN);
				setState(108);
				paralist();
				setState(109);
				match(RPAREN);
				}
				break;
			case 15:
				{
				_localctx = new LogContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				match(LOG);
				setState(112);
				match(LPAREN);
				setState(113);
				paralist();
				setState(114);
				match(RPAREN);
				}
				break;
			case 16:
				{
				_localctx = new CountContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(COUNT);
				setState(117);
				match(LPAREN);
				setState(118);
				paralist();
				setState(119);
				match(RPAREN);
				}
				break;
			case 17:
				{
				_localctx = new LenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(LEN);
				setState(122);
				match(LPAREN);
				setState(123);
				paralist();
				setState(124);
				match(RPAREN);
				}
				break;
			case 18:
				{
				_localctx = new MaxContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				match(MAX);
				setState(127);
				match(LPAREN);
				setState(128);
				paralist();
				setState(129);
				match(RPAREN);
				}
				break;
			case 19:
				{
				_localctx = new MinContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(MIN);
				setState(132);
				match(LPAREN);
				setState(133);
				paralist();
				setState(134);
				match(RPAREN);
				}
				break;
			case 20:
				{
				_localctx = new SubstrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(SUBSTR);
				setState(137);
				match(LPAREN);
				setState(138);
				paralist();
				setState(139);
				match(RPAREN);
				}
				break;
			case 21:
				{
				_localctx = new TrimContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(TRIM);
				setState(142);
				match(LPAREN);
				setState(143);
				paralist();
				setState(144);
				match(RPAREN);
				}
				break;
			case 22:
				{
				_localctx = new Is_numberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				match(IS_NUMBER);
				setState(147);
				match(LPAREN);
				setState(148);
				paralist();
				setState(149);
				match(RPAREN);
				}
				break;
			case 23:
				{
				_localctx = new Is_intContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151);
				match(IS_INT);
				setState(152);
				match(LPAREN);
				setState(153);
				paralist();
				setState(154);
				match(RPAREN);
				}
				break;
			case 24:
				{
				_localctx = new Is_nullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				match(IS_NULL);
				setState(157);
				match(LPAREN);
				setState(158);
				paralist();
				setState(159);
				match(RPAREN);
				}
				break;
			case 25:
				{
				_localctx = new To_strContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(TO_STR);
				setState(162);
				match(LPAREN);
				setState(163);
				paralist();
				setState(164);
				match(RPAREN);
				}
				break;
			case 26:
				{
				_localctx = new To_intContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				match(TO_INT);
				setState(167);
				match(LPAREN);
				setState(168);
				paralist();
				setState(169);
				match(RPAREN);
				}
				break;
			case 27:
				{
				_localctx = new To_floatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(TO_FLOAT);
				setState(172);
				match(LPAREN);
				setState(173);
				paralist();
				setState(174);
				match(RPAREN);
				}
				break;
			case 28:
				{
				_localctx = new To_dateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				match(TO_DATE);
				setState(177);
				match(LPAREN);
				setState(178);
				paralist();
				setState(179);
				match(RPAREN);
				}
				break;
			case 29:
				{
				_localctx = new Add_to_dateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181);
				match(ADD_TO_DATE);
				setState(182);
				match(LPAREN);
				setState(183);
				paralist();
				setState(184);
				match(RPAREN);
				}
				break;
			case 30:
				{
				_localctx = new Date_diffContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				match(DATE_DIFF);
				setState(187);
				match(LPAREN);
				setState(188);
				paralist();
				setState(189);
				match(RPAREN);
				}
				break;
			case 31:
				{
				_localctx = new SysdateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(191);
				match(SYSDATE);
				setState(192);
				match(LPAREN);
				setState(193);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(241);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new PowContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 46))) throw new FailedPredicateException(this, "precpred(_ctx, 46)");
						setState(197);
						match(POW);
						setState(198);
						expression(47);
						}
						break;
					case 2:
						{
						_localctx = new MulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 44))) throw new FailedPredicateException(this, "precpred(_ctx, 44)");
						setState(200);
						match(MUL);
						setState(201);
						expression(45);
						}
						break;
					case 3:
						{
						_localctx = new DivContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 43))) throw new FailedPredicateException(this, "precpred(_ctx, 43)");
						setState(203);
						match(DIV);
						setState(204);
						expression(44);
						}
						break;
					case 4:
						{
						_localctx = new ModContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 42))) throw new FailedPredicateException(this, "precpred(_ctx, 42)");
						setState(206);
						match(MOD);
						setState(207);
						expression(43);
						}
						break;
					case 5:
						{
						_localctx = new Floor_DivContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 41))) throw new FailedPredicateException(this, "precpred(_ctx, 41)");
						setState(209);
						match(FLOOR_DIV);
						setState(210);
						expression(42);
						}
						break;
					case 6:
						{
						_localctx = new AddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 40))) throw new FailedPredicateException(this, "precpred(_ctx, 40)");
						setState(212);
						match(ADD);
						setState(213);
						expression(41);
						}
						break;
					case 7:
						{
						_localctx = new SubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 39))) throw new FailedPredicateException(this, "precpred(_ctx, 39)");
						setState(215);
						match(SUB);
						setState(216);
						expression(40);
						}
						break;
					case 8:
						{
						_localctx = new EqContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 38))) throw new FailedPredicateException(this, "precpred(_ctx, 38)");
						setState(218);
						match(EQ);
						setState(219);
						expression(39);
						}
						break;
					case 9:
						{
						_localctx = new NeContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 37))) throw new FailedPredicateException(this, "precpred(_ctx, 37)");
						setState(221);
						match(NE);
						setState(222);
						expression(38);
						}
						break;
					case 10:
						{
						_localctx = new GtContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 36))) throw new FailedPredicateException(this, "precpred(_ctx, 36)");
						setState(224);
						match(GT);
						setState(225);
						expression(37);
						}
						break;
					case 11:
						{
						_localctx = new LtContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 35))) throw new FailedPredicateException(this, "precpred(_ctx, 35)");
						setState(227);
						match(LT);
						setState(228);
						expression(36);
						}
						break;
					case 12:
						{
						_localctx = new GeContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 34))) throw new FailedPredicateException(this, "precpred(_ctx, 34)");
						setState(230);
						match(GE);
						setState(231);
						expression(35);
						}
						break;
					case 13:
						{
						_localctx = new LeContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(232);
						if (!(precpred(_ctx, 33))) throw new FailedPredicateException(this, "precpred(_ctx, 33)");
						setState(233);
						match(LE);
						setState(234);
						expression(34);
						}
						break;
					case 14:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(235);
						if (!(precpred(_ctx, 31))) throw new FailedPredicateException(this, "precpred(_ctx, 31)");
						setState(236);
						match(AND);
						setState(237);
						expression(32);
						}
						break;
					case 15:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(238);
						if (!(precpred(_ctx, 30))) throw new FailedPredicateException(this, "precpred(_ctx, 30)");
						setState(239);
						match(OR);
						setState(240);
						expression(31);
						}
						break;
					}
					} 
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class ParalistContext extends ParserRuleContext {
		public List<ParaContext> para() {
			return getRuleContexts(ParaContext.class);
		}
		public ParaContext para(int i) {
			return getRuleContext(ParaContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HyperRuleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HyperRuleParser.COMMA, i);
		}
		public ParalistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paralist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitParalist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParalistContext paralist() throws RecognitionException {
		ParalistContext _localctx = new ParalistContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paralist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			para();
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(247);
				match(COMMA);
				setState(248);
				para();
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

	public static class ParaContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_para; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HyperRuleVisitor ) return ((HyperRuleVisitor<? extends T>)visitor).visitPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaContext para() throws RecognitionException {
		ParaContext _localctx = new ParaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_para);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			expression(0);
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
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 46);
		case 1:
			return precpred(_ctx, 44);
		case 2:
			return precpred(_ctx, 43);
		case 3:
			return precpred(_ctx, 42);
		case 4:
			return precpred(_ctx, 41);
		case 5:
			return precpred(_ctx, 40);
		case 6:
			return precpred(_ctx, 39);
		case 7:
			return precpred(_ctx, 38);
		case 8:
			return precpred(_ctx, 37);
		case 9:
			return precpred(_ctx, 36);
		case 10:
			return precpred(_ctx, 35);
		case 11:
			return precpred(_ctx, 34);
		case 12:
			return precpred(_ctx, 33);
		case 13:
			return precpred(_ctx, 31);
		case 14:
			return precpred(_ctx, 30);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0103\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\5\4(\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5\60\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6=\n\6\f\6\16\6@\13\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\5\bI\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\7\t\\\n\t\f\t\16\t_\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c5\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\t\u00f4\n\t\f\t\16\t\u00f7\13\t\3\n\3\n\3\n\7\n\u00fc\n\n\f"+
		"\n\16\n\u00ff\13\n\3\13\3\13\3\13\2\3\20\f\2\4\6\b\n\f\16\20\22\24\2\2"+
		"\2\u012d\2\26\3\2\2\2\4 \3\2\2\2\6\'\3\2\2\2\b/\3\2\2\2\n\61\3\2\2\2\f"+
		"D\3\2\2\2\16H\3\2\2\2\20\u00c4\3\2\2\2\22\u00f8\3\2\2\2\24\u0100\3\2\2"+
		"\2\26\33\5\6\4\2\27\30\7\66\2\2\30\32\5\6\4\2\31\27\3\2\2\2\32\35\3\2"+
		"\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\33\3\2\2\2\36\37\7\66"+
		"\2\2\37\3\3\2\2\2 !\7\64\2\2!\"\5\2\2\2\"#\7\65\2\2#\5\3\2\2\2$(\5\20"+
		"\t\2%(\5\b\5\2&(\5\n\6\2\'$\3\2\2\2\'%\3\2\2\2\'&\3\2\2\2(\7\3\2\2\2)"+
		"*\7\37\2\2*+\7(\2\2+\60\5\20\t\2,-\7 \2\2-.\7(\2\2.\60\5\20\t\2/)\3\2"+
		"\2\2/,\3\2\2\2\60\t\3\2\2\2\61\62\7\34\2\2\62\63\7\62\2\2\63\64\5\f\7"+
		"\2\64\65\7\63\2\2\65>\5\16\b\2\66\67\7\35\2\2\678\7\62\2\289\5\f\7\29"+
		":\7\63\2\2:;\5\16\b\2;=\3\2\2\2<\66\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2"+
		"\2\2?A\3\2\2\2@>\3\2\2\2AB\7\36\2\2BC\5\16\b\2C\13\3\2\2\2DE\5\20\t\2"+
		"E\r\3\2\2\2FI\5\6\4\2GI\5\4\3\2HF\3\2\2\2HG\3\2\2\2I\17\3\2\2\2JK\b\t"+
		"\1\2KL\7\"\2\2L\u00c5\5\20\t/MN\7)\2\2N\u00c5\5\20\t\"OP\7\62\2\2PQ\5"+
		"\20\t\2QR\7\63\2\2R\u00c5\3\2\2\2S\u00c5\7 \2\2T\u00c5\7\37\2\2U\u00c5"+
		"\7\3\2\2V\u00c5\7\4\2\2W\u00c5\7\5\2\2XY\7\5\2\2Y]\7 \2\2Z\\\7\5\2\2["+
		"Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\u00c5\3\2\2\2_]\3\2\2\2`\u00c5"+
		"\7\6\2\2a\u00c5\7\7\2\2bc\7\b\2\2cd\7\62\2\2de\5\22\n\2ef\7\63\2\2f\u00c5"+
		"\3\2\2\2gh\7\t\2\2hi\7\62\2\2ij\5\22\n\2jk\7\63\2\2k\u00c5\3\2\2\2lm\7"+
		"\n\2\2mn\7\62\2\2no\5\22\n\2op\7\63\2\2p\u00c5\3\2\2\2qr\7\13\2\2rs\7"+
		"\62\2\2st\5\22\n\2tu\7\63\2\2u\u00c5\3\2\2\2vw\7\f\2\2wx\7\62\2\2xy\5"+
		"\22\n\2yz\7\63\2\2z\u00c5\3\2\2\2{|\7\r\2\2|}\7\62\2\2}~\5\22\n\2~\177"+
		"\7\63\2\2\177\u00c5\3\2\2\2\u0080\u0081\7\16\2\2\u0081\u0082\7\62\2\2"+
		"\u0082\u0083\5\22\n\2\u0083\u0084\7\63\2\2\u0084\u00c5\3\2\2\2\u0085\u0086"+
		"\7\17\2\2\u0086\u0087\7\62\2\2\u0087\u0088\5\22\n\2\u0088\u0089\7\63\2"+
		"\2\u0089\u00c5\3\2\2\2\u008a\u008b\7\20\2\2\u008b\u008c\7\62\2\2\u008c"+
		"\u008d\5\22\n\2\u008d\u008e\7\63\2\2\u008e\u00c5\3\2\2\2\u008f\u0090\7"+
		"\21\2\2\u0090\u0091\7\62\2\2\u0091\u0092\5\22\n\2\u0092\u0093\7\63\2\2"+
		"\u0093\u00c5\3\2\2\2\u0094\u0095\7\22\2\2\u0095\u0096\7\62\2\2\u0096\u0097"+
		"\5\22\n\2\u0097\u0098\7\63\2\2\u0098\u00c5\3\2\2\2\u0099\u009a\7\23\2"+
		"\2\u009a\u009b\7\62\2\2\u009b\u009c\5\22\n\2\u009c\u009d\7\63\2\2\u009d"+
		"\u00c5\3\2\2\2\u009e\u009f\7\24\2\2\u009f\u00a0\7\62\2\2\u00a0\u00a1\5"+
		"\22\n\2\u00a1\u00a2\7\63\2\2\u00a2\u00c5\3\2\2\2\u00a3\u00a4\7\25\2\2"+
		"\u00a4\u00a5\7\62\2\2\u00a5\u00a6\5\22\n\2\u00a6\u00a7\7\63\2\2\u00a7"+
		"\u00c5\3\2\2\2\u00a8\u00a9\7\26\2\2\u00a9\u00aa\7\62\2\2\u00aa\u00ab\5"+
		"\22\n\2\u00ab\u00ac\7\63\2\2\u00ac\u00c5\3\2\2\2\u00ad\u00ae\7\27\2\2"+
		"\u00ae\u00af\7\62\2\2\u00af\u00b0\5\22\n\2\u00b0\u00b1\7\63\2\2\u00b1"+
		"\u00c5\3\2\2\2\u00b2\u00b3\7\30\2\2\u00b3\u00b4\7\62\2\2\u00b4\u00b5\5"+
		"\22\n\2\u00b5\u00b6\7\63\2\2\u00b6\u00c5\3\2\2\2\u00b7\u00b8\7\31\2\2"+
		"\u00b8\u00b9\7\62\2\2\u00b9\u00ba\5\22\n\2\u00ba\u00bb\7\63\2\2\u00bb"+
		"\u00c5\3\2\2\2\u00bc\u00bd\7\32\2\2\u00bd\u00be\7\62\2\2\u00be\u00bf\5"+
		"\22\n\2\u00bf\u00c0\7\63\2\2\u00c0\u00c5\3\2\2\2\u00c1\u00c2\7\33\2\2"+
		"\u00c2\u00c3\7\62\2\2\u00c3\u00c5\7\63\2\2\u00c4J\3\2\2\2\u00c4M\3\2\2"+
		"\2\u00c4O\3\2\2\2\u00c4S\3\2\2\2\u00c4T\3\2\2\2\u00c4U\3\2\2\2\u00c4V"+
		"\3\2\2\2\u00c4W\3\2\2\2\u00c4X\3\2\2\2\u00c4`\3\2\2\2\u00c4a\3\2\2\2\u00c4"+
		"b\3\2\2\2\u00c4g\3\2\2\2\u00c4l\3\2\2\2\u00c4q\3\2\2\2\u00c4v\3\2\2\2"+
		"\u00c4{\3\2\2\2\u00c4\u0080\3\2\2\2\u00c4\u0085\3\2\2\2\u00c4\u008a\3"+
		"\2\2\2\u00c4\u008f\3\2\2\2\u00c4\u0094\3\2\2\2\u00c4\u0099\3\2\2\2\u00c4"+
		"\u009e\3\2\2\2\u00c4\u00a3\3\2\2\2\u00c4\u00a8\3\2\2\2\u00c4\u00ad\3\2"+
		"\2\2\u00c4\u00b2\3\2\2\2\u00c4\u00b7\3\2\2\2\u00c4\u00bc\3\2\2\2\u00c4"+
		"\u00c1\3\2\2\2\u00c5\u00f5\3\2\2\2\u00c6\u00c7\f\60\2\2\u00c7\u00c8\7"+
		"\'\2\2\u00c8\u00f4\5\20\t\61\u00c9\u00ca\f.\2\2\u00ca\u00cb\7#\2\2\u00cb"+
		"\u00f4\5\20\t/\u00cc\u00cd\f-\2\2\u00cd\u00ce\7$\2\2\u00ce\u00f4\5\20"+
		"\t.\u00cf\u00d0\f,\2\2\u00d0\u00d1\7&\2\2\u00d1\u00f4\5\20\t-\u00d2\u00d3"+
		"\f+\2\2\u00d3\u00d4\7%\2\2\u00d4\u00f4\5\20\t,\u00d5\u00d6\f*\2\2\u00d6"+
		"\u00d7\7!\2\2\u00d7\u00f4\5\20\t+\u00d8\u00d9\f)\2\2\u00d9\u00da\7\"\2"+
		"\2\u00da\u00f4\5\20\t*\u00db\u00dc\f(\2\2\u00dc\u00dd\7,\2\2\u00dd\u00f4"+
		"\5\20\t)\u00de\u00df\f\'\2\2\u00df\u00e0\7-\2\2\u00e0\u00f4\5\20\t(\u00e1"+
		"\u00e2\f&\2\2\u00e2\u00e3\7.\2\2\u00e3\u00f4\5\20\t\'\u00e4\u00e5\f%\2"+
		"\2\u00e5\u00e6\7/\2\2\u00e6\u00f4\5\20\t&\u00e7\u00e8\f$\2\2\u00e8\u00e9"+
		"\7\60\2\2\u00e9\u00f4\5\20\t%\u00ea\u00eb\f#\2\2\u00eb\u00ec\7\61\2\2"+
		"\u00ec\u00f4\5\20\t$\u00ed\u00ee\f!\2\2\u00ee\u00ef\7*\2\2\u00ef\u00f4"+
		"\5\20\t\"\u00f0\u00f1\f \2\2\u00f1\u00f2\7+\2\2\u00f2\u00f4\5\20\t!\u00f3"+
		"\u00c6\3\2\2\2\u00f3\u00c9\3\2\2\2\u00f3\u00cc\3\2\2\2\u00f3\u00cf\3\2"+
		"\2\2\u00f3\u00d2\3\2\2\2\u00f3\u00d5\3\2\2\2\u00f3\u00d8\3\2\2\2\u00f3"+
		"\u00db\3\2\2\2\u00f3\u00de\3\2\2\2\u00f3\u00e1\3\2\2\2\u00f3\u00e4\3\2"+
		"\2\2\u00f3\u00e7\3\2\2\2\u00f3\u00ea\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f3"+
		"\u00f0\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\21\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fd\5\24\13\2\u00f9"+
		"\u00fa\7\67\2\2\u00fa\u00fc\5\24\13\2\u00fb\u00f9\3\2\2\2\u00fc\u00ff"+
		"\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\23\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0101\5\20\t\2\u0101\25\3\2\2\2\f\33\'/>H]\u00c4"+
		"\u00f3\u00f5\u00fd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}