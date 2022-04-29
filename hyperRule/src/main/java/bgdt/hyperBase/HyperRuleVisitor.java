// Generated from HyperRule.g4 by ANTLR 4.7

package bgdt.hyperBase;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HyperRuleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HyperRuleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HyperRuleParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(HyperRuleParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HyperRuleParser#statements_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements_block(HyperRuleParser.Statements_blockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link HyperRuleParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(HyperRuleParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStmt}
	 * labeled alternative in {@link HyperRuleParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(HyperRuleParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link HyperRuleParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(HyperRuleParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignLocal}
	 * labeled alternative in {@link HyperRuleParser#assign_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignLocal(HyperRuleParser.AssignLocalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignRef}
	 * labeled alternative in {@link HyperRuleParser#assign_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignRef(HyperRuleParser.AssignRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HyperRuleParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(HyperRuleParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HyperRuleParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(HyperRuleParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchStmt}
	 * labeled alternative in {@link HyperRuleParser#condition_branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchStmt(HyperRuleParser.BranchStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchBlock}
	 * labeled alternative in {@link HyperRuleParser#condition_branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchBlock(HyperRuleParser.BranchBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(HyperRuleParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Date_diff}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_diff(HyperRuleParser.Date_diffContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(HyperRuleParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Max}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMax(HyperRuleParser.MaxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Uminus}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUminus(HyperRuleParser.UminusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(HyperRuleParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add_to_date}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_to_date(HyperRuleParser.Add_to_dateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Count}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCount(HyperRuleParser.CountContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Eq}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(HyperRuleParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code To_date}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTo_date(HyperRuleParser.To_dateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Gt}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGt(HyperRuleParser.GtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Is_null}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_null(HyperRuleParser.Is_nullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Substr}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstr(HyperRuleParser.SubstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ne}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNe(HyperRuleParser.NeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RefVar}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefVar(HyperRuleParser.RefVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Le}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLe(HyperRuleParser.LeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntLiteral}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteral(HyperRuleParser.IntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code To_float}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTo_float(HyperRuleParser.To_floatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(HyperRuleParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mod}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMod(HyperRuleParser.ModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Log}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLog(HyperRuleParser.LogContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Trim}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrim(HyperRuleParser.TrimContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(HyperRuleParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatLiteral}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(HyperRuleParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sqrt}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrt(HyperRuleParser.SqrtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lt}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(HyperRuleParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Is_int}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_int(HyperRuleParser.Is_intContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(HyperRuleParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code To_int}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTo_int(HyperRuleParser.To_intContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Is_number}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_number(HyperRuleParser.Is_numberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code To_str}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTo_str(HyperRuleParser.To_strContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(HyperRuleParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(HyperRuleParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Avg}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAvg(HyperRuleParser.AvgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Min}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMin(HyperRuleParser.MinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(HyperRuleParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Len}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLen(HyperRuleParser.LenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(HyperRuleParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteralRefVar}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralRefVar(HyperRuleParser.StringLiteralRefVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sysdate}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSysdate(HyperRuleParser.SysdateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Pow}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(HyperRuleParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LocalVar}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVar(HyperRuleParser.LocalVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Floor_Div}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloor_Div(HyperRuleParser.Floor_DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLiteral(HyperRuleParser.NullLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ge}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGe(HyperRuleParser.GeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link HyperRuleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(HyperRuleParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link HyperRuleParser#paralist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParalist(HyperRuleParser.ParalistContext ctx);
	/**
	 * Visit a parse tree produced by {@link HyperRuleParser#para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPara(HyperRuleParser.ParaContext ctx);
}