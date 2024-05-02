package com.trip97.modules.planAttraction.model.service;

import com.trip97.modules.planAttraction.model.PlanAttraction;
import com.trip97.modules.planAttraction.model.mapper.PlanAttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanAttractionServiceImpl implements PlanAttractionService {

    private final PlanAttractionMapper planAttractionMapper;

    @Override
    public Integer registerPlanAttraction(int planId, int attractionId) {
        int planAttractionVisitOrder = planAttractionMapper.getCountByPlanId(planId) + 1;
        PlanAttraction planAttraction = PlanAttraction.builder()
                .attractionId(attractionId)
                .planId(planId)
                .visitOrder(planAttractionVisitOrder)
                .build();

        return planAttractionMapper.insertPlanAttraction(planAttraction);
    }

    @Override
    public List<PlanAttraction> selectPlanAttractions(Integer planId) {
        return planAttractionMapper.selectPlanAttractionsByPlanId(planId);
    }

    @Override
    public PlanAttraction selectPlanAttraction(Integer planAttractionId) {
        return planAttractionMapper.selectPlanAttractionsByPlanAttractionId(planAttractionId);
    }

    @Override
    public Integer editPlanAttraction(PlanAttraction planAttraction) {
        return planAttractionMapper.updatePlanAttraction(planAttraction);
    }

    @Override
    public Integer removePlanAttraction(Integer planAttractionId) {
        return planAttractionMapper.deletePlanAttraction(planAttractionId);
    }
}
