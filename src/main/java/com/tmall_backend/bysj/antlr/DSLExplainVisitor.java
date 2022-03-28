package com.tmall_backend.bysj.antlr;

import com.tmall_backend.bysj.antlr.DSLParser.ConditionContext;
import com.tmall_backend.bysj.antlr.DSLParser.CouponstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.DiscountstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.EndclauseContext;
import com.tmall_backend.bysj.antlr.DSLParser.FullminusstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.InitContext;
import com.tmall_backend.bysj.antlr.DSLParser.StartclauseContext;
import com.tmall_backend.bysj.antlr.DSLParser.VariableContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DSLExplainVisitor extends DSLBaseVisitor {

    Integer commodityId;
    Integer categoryId;
    Integer brandId;

    Integer INT01;
    Integer INT02;

    @Override
    public Object visitInit(InitContext ctx) {
        return super.visitInit(ctx);
    }

    @Override
    public Object visitCouponstatement(CouponstatementContext ctx) {
        this.setINT01(Integer.valueOf(ctx.INT().getText()));
        return super.visitCouponstatement(ctx);
    }

    @Override
    public Object visitFullminusstatement(FullminusstatementContext ctx) {
        this.setINT01(Integer.valueOf(ctx.INT(0).getText()));
        this.setINT02(Integer.valueOf(ctx.INT(1).getText()));
        return super.visitFullminusstatement(ctx);
    }

    @Override
    public Object visitDiscountstatement(DiscountstatementContext ctx) {
        this.setINT01(Integer.valueOf(ctx.INT().getText()));
        return super.visitDiscountstatement(ctx);
    }

    @Override
    public Object visitCondition(ConditionContext ctx) {
//        System.out.println("visiting condition");
        final String relation = ctx.getChild(1).getText();
//        System.out.println(relation);
        if (relation.equals("||")) {
            return ctx.getChild(0).accept(this).toString().equals("true")
                    || ctx.getChild(2).accept(this).toString().equals("true");
        } else if (relation.equals("&&")) {
            return ctx.getChild(0).accept(this).toString().equals("true")
                    && ctx.getChild(2).accept(this).toString().equals("true");
        } else if (relation.equals("==")) {
            final String condition = ctx.getChild(0).getText();
            final String value = ctx.getChild(2).getText();
//            System.out.println(condition);
            if (condition.equals("[商品ID]")) {
//                System.out.println("商品ID匹配：" + (Integer.parseInt(value) == commodityId));
                return Integer.parseInt(value) == commodityId;
            } else if(condition.equals("[品类ID]")) {
//                System.out.println("品类ID匹配：" + (Integer.parseInt(value) == categoryId));
                return Integer.parseInt(value) == categoryId;
            } else if (condition.equals("[品牌ID]")) {
//                System.out.println("品牌ID：" + (Integer.parseInt(value)));
//                System.out.println("品牌ID匹配：" + (Integer.parseInt(value) == brandId));
                return Integer.parseInt(value) == brandId;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Object visitStartclause(StartclauseContext ctx) {
        return true;
    }

    @Override
    public Object visitEndclause(EndclauseContext ctx) {
        return true;
    }

    @Override
    public Object visitVariable(VariableContext ctx) {
        return super.visitVariable(ctx);
    }

    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        if (aggregate instanceof Boolean && nextResult instanceof Boolean) {
            return (Boolean)aggregate && (Boolean)nextResult;
        }
        return super.aggregateResult(aggregate, nextResult);
    }
}
