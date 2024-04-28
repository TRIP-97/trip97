package com.trip97.modules.plan.model.service;

import com.trip97.modules.plan.model.Plan;

import java.util.List;

public interface PlanService {

    Integer createPlan(Plan plan);
    Plan selectPlan(Integer id);
    List<Plan> selectPlans(Integer groupId);
    Integer editPlan(Plan plan);
    Integer removePlan(Integer id);
}
