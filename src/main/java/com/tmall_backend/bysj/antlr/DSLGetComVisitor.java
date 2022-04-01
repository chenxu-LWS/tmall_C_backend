package com.tmall_backend.bysj.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tmall_backend.bysj.antlr.DSLParser.ConditionContext;
import com.tmall_backend.bysj.antlr.DSLParser.CouponstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.DiscountstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.EndclauseContext;
import com.tmall_backend.bysj.antlr.DSLParser.FullminusstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.InitContext;
import com.tmall_backend.bysj.antlr.DSLParser.StartclauseContext;
import com.tmall_backend.bysj.antlr.DSLParser.VariableContext;
import com.tmall_backend.bysj.entity.Commodity;
import com.tmall_backend.bysj.mapper.CommodityMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Service
public class DSLGetComVisitor extends DSLBaseVisitor {
    Boolean hasDone = false;
    List<Commodity> commodities = new ArrayList<>();
    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public Object visitInit(InitContext ctx) {
        return super.visitInit(ctx);
    }

    @Override
    public Object visitCouponstatement(CouponstatementContext ctx) {
        return super.visitCouponstatement(ctx);
    }

    @Override
    public Object visitFullminusstatement(FullminusstatementContext ctx) {
        return super.visitFullminusstatement(ctx);
    }

    @Override
    public Object visitDiscountstatement(DiscountstatementContext ctx) {
        return super.visitDiscountstatement(ctx);
    }

    @Override
    public Object visitCondition(ConditionContext ctx) {
        if (!hasDone) {
            final List<Commodity> commodities = visitConditionRecurse(ctx);
            this.commodities = commodities.stream().distinct()
                    .collect(Collectors.toList());
            hasDone = true;
        }
        return super.visitCondition(ctx);
    }

    private List<Commodity> visitConditionRecurse(ConditionContext ctx) {
        if (ctx == null || ctx.getChildCount() != 3) {
            return null;
        }
        final String key = ctx.getChild(0).getText();
        final String symbol = ctx.getChild(1).getText();
        final String val = ctx.getChild(2).getText();
        if ("==".equals(symbol)) {
            switch (key) {
                case "[品牌ID]":
                    final Integer total = commodityMapper.
                            queryCommodityByBrandIdTotalNum(Integer.parseInt(val));
                    return commodityMapper.
                            queryCommodityByBrandIdByPage(Integer.valueOf(val), 0, total + 1);
                case "[品类ID]":
                    final ArrayList<Integer> categoryIds = new ArrayList<>();
                    categoryIds.add(Integer.valueOf(val));
                    return commodityMapper.queryCommodityByCategoryId(categoryIds);
                case "[商品ID]":
                    final Commodity commodity = commodityMapper.queryCommodityById(Integer.valueOf(val));
                    final ArrayList<Commodity> commodities = new ArrayList<>();
                    commodities.add(commodity);
                    return commodities;
                default:
                    return null;
            }
        } else if ("||".equals(symbol)) {
            final List<Commodity> leftCommodities = visitConditionRecurse((ConditionContext) ctx.getChild(0));
            final List<Commodity> rightCommodities = visitConditionRecurse((ConditionContext) ctx.getChild(2));
            return mergeTwoLists(leftCommodities, rightCommodities);
        } else if ("&&".equals(symbol)) {
            return acrossTwoLists(visitConditionRecurse((ConditionContext) ctx.getChild(0)),
                    visitConditionRecurse((ConditionContext) ctx.getChild(2)));
        }
        return null;
    }

    private <T> List<T> acrossTwoLists(List<T> list1, List<T> list2) {
        list1.retainAll(list2);
        return list1;
    }

    private <T> List<T> mergeTwoLists(List<T> list1, List<T> list2) {
        List<T> newList = new ArrayList<>();
        newList.addAll(list1);
        newList.addAll(list2);
        return newList;
    }

    @Override
    public Object visitStartclause(StartclauseContext ctx) {
        return super.visitStartclause(ctx);
    }

    @Override
    public Object visitEndclause(EndclauseContext ctx) {
        return super.visitEndclause(ctx);
    }

    @Override
    public Object visitVariable(VariableContext ctx) {
        return super.visitVariable(ctx);
    }
}
