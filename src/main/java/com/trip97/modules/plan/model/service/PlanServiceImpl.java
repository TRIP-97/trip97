package com.trip97.modules.plan.model.service;

import com.trip97.modules.plan.model.Plan;
import com.trip97.modules.plan.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;

    @Override
    public Integer createPlan(Plan plan) {
        return planMapper.insertPlan(plan);
    }

    @Override
    public Plan selectPlan(Integer id) {
        return planMapper.selectPlanById(id);
    }

    @Override
    public List<Plan> selectPlans(Integer groupId) {
        return planMapper.selectPlansByGroupId(groupId);
    }

    @Override
    public Integer editPlan(Plan plan) {
        return planMapper.updatePlan(plan);
    }

    @Override
    public Integer removePlan(Integer id) {
        return planMapper.deletePlan(id);
    }
}
