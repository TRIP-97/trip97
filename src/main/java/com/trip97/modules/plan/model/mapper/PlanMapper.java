package com.trip97.modules.plan.model.mapper;

import com.trip97.modules.plan.model.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {

    Integer insertPlan(Plan plan);
    Plan selectPlanById(Integer id);
    List<Plan> selectPlansByGroupId(Integer groupId);
    Integer updatePlan(Plan plan);
    Integer deletePlan(Integer id);
}
