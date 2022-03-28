package com.tmall_backend.bysj.antlr;// Generated from DSL.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DSLParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(DSLParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#couponstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCouponstatement(DSLParser.CouponstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#fullminusstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullminusstatement(DSLParser.FullminusstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#discountstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiscountstatement(DSLParser.DiscountstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(DSLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#startclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartclause(DSLParser.StartclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#endclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndclause(DSLParser.EndclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSLParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(DSLParser.VariableContext ctx);
}