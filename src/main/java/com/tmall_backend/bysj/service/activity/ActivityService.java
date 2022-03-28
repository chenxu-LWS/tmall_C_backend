package com.tmall_backend.bysj.service.activity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall_backend.bysj.antlr.DSLExplainVisitor;
import com.tmall_backend.bysj.antlr.DSLInsertActivityVisitor;
import com.tmall_backend.bysj.antlr.DSLLexer;
import com.tmall_backend.bysj.antlr.DSLParser;
import com.tmall_backend.bysj.antlr.ThrowingErrorListener;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.page.PageBean;
import com.tmall_backend.bysj.entity.Activity;
import com.tmall_backend.bysj.entity.Commodity;
import com.tmall_backend.bysj.mapper.ActivityMapper;
import com.tmall_backend.bysj.mapper.CommodityMapper;
import com.tmall_backend.bysj.service.activity.dto.ActivityDTO;


/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Service
public class ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    DSLInsertActivityVisitor insertVisitor;

    Map<String, ParseTree> parseTreeMap;

    public ActivityService() {
        this.parseTreeMap = new ConcurrentHashMap<>();
    }

    /**
     * 插入一个新的活动
     * @param activityName
     * @param DSL
     * @return
     * @throws BizException
     */
    public Integer insertActivity(String activityName, String DSL) throws BizException {
        Activity activity = new Activity();
        parseActivityDSL(activity, DSL);
        activity.setActivityName(activityName);
        activity.setOnline(0);
        activity.setDSL(DSL);
        System.out.println(activity);
        activityMapper.insertActivity(activity);
        return activity.getId();
    }
    private void parseActivityDSL(Activity activity, String DSL) throws BizException {
        try {
            ParseTree tree = parseTreeMap.get(DSL);
            if (tree == null) {
                ANTLRInputStream input = new ANTLRInputStream(DSL);
                DSLLexer lexer = new DSLLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                DSLParser parser = new DSLParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(ThrowingErrorListener.INSTANCE);
                tree = parser.init();
                parseTreeMap.put(DSL, tree);
            }
            insertVisitor.setActivity(activity);
            insertVisitor.visit(tree);
        } catch (ParseCancellationException e) {
            throw new BizException(ErrInfo.DSL_SYNTAX_ERROR);
        }
    }

    /**
     * 更新活动状态
     * @param id
     * @param status
     * @return
     */
    public Integer updateActivityStatus(Integer id, Integer status) {
        return activityMapper.updateActivityStatus(id, status);
    }

    /**
     * 分页查询所有活动
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Activity> queryAllActivityByPage(Integer pageNo, Integer pageSize) {
        final List<Activity> activities =
                activityMapper.queryAllActivityByPage(pageNo * pageSize, pageSize);
        PageBean<Activity> result = new PageBean<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setList(activities);
        result.setTotalNum(activityMapper.queryAllActivityTotalNum());
        return result;
    }

    /**
     * 分页查询所有在线活动
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Activity> queryOnlineActivityByPage(Integer pageNo, Integer pageSize) {
        final List<Activity> activities =
                activityMapper.queryOnlineActivityByPage(pageNo * pageSize, pageSize);
        PageBean<Activity> result = new PageBean<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setList(activities);
        result.setTotalNum(activityMapper.queryOnlineActivityTotalNum());
        return result;
    }

    /**
     * 根据ID查询活动
     * @param id
     * @return
     */
    public Activity queryActivityById(Integer id) {
        return activityMapper.queryActivityById(id);
    }

    /**
     * 根据商品ID查看他能参加哪些活动
     * @param commodityId
     * @return
     */
    public List<ActivityDTO> queryActivityByCommodityId(Integer commodityId) {
        final List<Activity> activities =
                activityMapper.queryOnlineActivityByPage(0, activityMapper.queryOnlineActivityTotalNum() + 1);
        final Commodity commodity = commodityMapper.queryCommodityById(commodityId);
        List<ActivityDTO> result = new ArrayList<>();
        try {
            activities.forEach(activity -> {
                final Timestamp startTime = activity.getStartTime();
                final Timestamp endTime = activity.getEndTime();
                Timestamp now = new Timestamp(System.currentTimeMillis());
                if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
                    return;
                }
                final String dsl = activity.getDSL();
                ParseTree tree = parseTreeMap.get(dsl);
                if (tree == null) {
                    ANTLRInputStream input = new ANTLRInputStream(dsl);
                    DSLLexer lexer = new DSLLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    DSLParser parser = new DSLParser(tokens);
                    parser.removeErrorListeners();
                    parser.addErrorListener(ThrowingErrorListener.INSTANCE);
                    tree = parser.init();
                    parseTreeMap.put(dsl, tree);
                }
                DSLExplainVisitor explainVisitor = new DSLExplainVisitor();
                explainVisitor.setCommodityId(commodityId);
                explainVisitor.setBrandId(commodity.getBrandID());
                explainVisitor.setCategoryId(commodity.getCategoryID());
                final Boolean treeRes = (Boolean)explainVisitor.visit(tree);
                if (treeRes) {
                    ActivityDTO dto = new ActivityDTO(activity);
                    dto.setINT01(explainVisitor.getINT01());
                    dto.setINT02(explainVisitor.getINT02());
                    result.add(dto);
                }
            });
            return result;
        }  catch (ParseCancellationException e) {
            throw new BizException(ErrInfo.DSL_SYNTAX_ERROR);
        }
    }

}
