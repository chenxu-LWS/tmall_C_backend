package com.tmall_backend.bysj.antlr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall_backend.bysj.antlr.DSLParser.ConditionContext;
import com.tmall_backend.bysj.antlr.DSLParser.CouponstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.DiscountstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.EndclauseContext;
import com.tmall_backend.bysj.antlr.DSLParser.FullminusstatementContext;
import com.tmall_backend.bysj.antlr.DSLParser.InitContext;
import com.tmall_backend.bysj.antlr.DSLParser.StartclauseContext;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.entity.Activity;
import com.tmall_backend.bysj.mapper.BrandMapper;
import com.tmall_backend.bysj.mapper.CategoryMapper;
import com.tmall_backend.bysj.mapper.CommodityMapper;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Service
@Data
public class DSLInsertActivityVisitor extends DSLBaseVisitor {
    Activity activity;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;

    public DSLInsertActivityVisitor() {}
    public DSLInsertActivityVisitor(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Object visitInit(InitContext ctx) {
        return super.visitInit(ctx);
    }

    @Override
    public Object visitCouponstatement(CouponstatementContext ctx) {
        activity.setType(1);
        return super.visitCouponstatement(ctx);
    }

    @Override
    public Object visitFullminusstatement(FullminusstatementContext ctx) {
        activity.setType(2);
        return super.visitFullminusstatement(ctx);
    }

    @Override
    public Object visitDiscountstatement(DiscountstatementContext ctx) {
        activity.setType(3);
        return super.visitDiscountstatement(ctx);
    }

    @Override
    public Object visitCondition(ConditionContext ctx) {
        final ParseTree child = ctx.getChild(0);
        if (child.getClass().toString().contains("Variable")) {
            ParseTree childNum;
            switch (child.getText()) {
                case "[商品ID]":
                    childNum = ctx.getChild(2);
                    if (commodityMapper.queryCommodityById(Integer.valueOf(childNum.getText())) == null) {
                        throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
                    }
                    break;
                case "[品类ID]":
                    childNum = ctx.getChild(2);
                    if (categoryMapper.queryCategoryById(Integer.valueOf(childNum.getText())) == null) {
                        throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
                    }
                    break;
                case "[品牌ID]":
                    childNum = ctx.getChild(2);
                    if (brandMapper.queryBrandById(Integer.valueOf(childNum.getText())) == null) {
                        throw new BizException(ErrInfo.BRAND_ID_NOT_EXISTS);
                    }
                    break;
            }
        }
        return super.visitCondition(ctx);
    }

    @Override
    public Object visitStartclause(StartclauseContext ctx) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(ctx.INT(0).toString()));
            calendar.set(Calendar.MONTH, Integer.parseInt(ctx.INT(1).toString()) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ctx.INT(2).toString()));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ctx.INT(3).toString()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(ctx.INT(4).toString()));
            calendar.set(Calendar.SECOND, Integer.parseInt(ctx.INT(5).toString()));
            Date date = calendar.getTime();
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            activity.setStartTime(Timestamp.valueOf(simpleDate.format(date)));
            return super.visitStartclause(ctx);
        } catch (Exception e) {
            throw new BizException(ErrInfo.DSL_SYNTAX_ERROR);
        }
    }

    @Override
    public Object visitEndclause(EndclauseContext ctx) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(ctx.INT(0).toString()));
            calendar.set(Calendar.MONTH, Integer.parseInt(ctx.INT(1).toString()) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ctx.INT(2).toString()));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ctx.INT(3).toString()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(ctx.INT(4).toString()));
            calendar.set(Calendar.SECOND, Integer.parseInt(ctx.INT(5).toString()));
            Date date = calendar.getTime();
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            activity.setEndTime(Timestamp.valueOf(simpleDate.format(date)));
            return super.visitEndclause(ctx);
        } catch (Exception e) {
            throw new BizException(ErrInfo.DSL_SYNTAX_ERROR);
        }
    }
}
