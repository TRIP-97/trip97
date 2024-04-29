package com.trip97.modules.planAttraction.model.service;

import com.trip97.modules.planAttraction.model.PlanAttraction;

import java.util.List;

public interface PlanAttractionService {

    Integer registerPlanAttraction(int planId, int attractionId);
    List<PlanAttraction> selectPlanAttractions(Integer planId);
    PlanAttraction selectPlanAttraction(Integer planAttractionId);
    Integer editPlanAttraction(PlanAttraction planAttraction);
    Integer removePlanAttraction(Integer planAttractionId);

}
