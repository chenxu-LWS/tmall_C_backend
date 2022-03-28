package com.tmall_backend.bysj.antlr;// Generated from DSL.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DSLParser}.
 */
public interface DSLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DSLParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(DSLParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(DSLParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#couponstatement}.
	 * @param ctx the parse tree
	 */
	void enterCouponstatement(DSLParser.CouponstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#couponstatement}.
	 * @param ctx the parse tree
	 */
	void exitCouponstatement(DSLParser.CouponstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#fullminusstatement}.
	 * @param ctx the parse tree
	 */
	void enterFullminusstatement(DSLParser.FullminusstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#fullminusstatement}.
	 * @param ctx the parse tree
	 */
	void exitFullminusstatement(DSLParser.FullminusstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#discountstatement}.
	 * @param ctx the parse tree
	 */
	void enterDiscountstatement(DSLParser.DiscountstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#discountstatement}.
	 * @param ctx the parse tree
	 */
	void exitDiscountstatement(DSLParser.DiscountstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(DSLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(DSLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#startclause}.
	 * @param ctx the parse tree
	 */
	void enterStartclause(DSLParser.StartclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#startclause}.
	 * @param ctx the parse tree
	 */
	void exitStartclause(DSLParser.StartclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#endclause}.
	 * @param ctx the parse tree
	 */
	void enterEndclause(DSLParser.EndclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#endclause}.
	 * @param ctx the parse tree
	 */
	void exitEndclause(DSLParser.EndclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(DSLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(DSLParser.VariableContext ctx);
}