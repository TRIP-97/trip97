package com.trip97.modules.plan.model.mapper;

import com.trip97.modules.plan.model.DayPlanDto;
import com.trip97.modules.plan.model.DayPlanItemDto;
import com.trip97.modules.plan.model.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    // 수정 Mapper
    List<Plan> selectPlansByGroupId(Integer groupId);
    Plan getPlanById(Integer id);
    Integer insertPlan(Plan plan);
    Integer insertDayPlan(DayPlanDto dayPlanDto);
    Integer insertDayPlanItem(DayPlanItemDto dayPlanItemDto);
    Integer updatePlan(Plan plan);
    Integer updateDayPlanItemOrder(int itemId, int order);
    Integer deletePlanById(Integer id);
    Integer deleteDayPlanItemById(Integer id);

    // 추후 DayPlanItemMemo 를 수정하는 기능도 고려
}
