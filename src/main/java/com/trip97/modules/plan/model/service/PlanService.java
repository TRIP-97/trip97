package com.trip97.modules.plan.model.service;

import com.trip97.modules.plan.model.DayPlanDto;
import com.trip97.modules.plan.model.DayPlanItemDto;
import com.trip97.modules.plan.model.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> selectPlansByGroupId(Integer groupId);
    Plan getPlanById(Integer id);
    void createPlan(Plan plan);
    Integer insertDayPlanItem(DayPlanItemDto dayPlanItemDto);
    Integer updatePlan(Plan plan);
    Integer updateDayPlanItemOrder(int itemId, int order);
    Integer deletePlanById(Integer id);
    Integer deleteDayPlanItemById(Integer id);
}
