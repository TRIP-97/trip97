package com.trip97.modules.planAttraction.model.mapper;

import com.trip97.modules.planAttraction.model.PlanAttraction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanAttractionMapper {

    Integer insertPlanAttraction(PlanAttraction planAttraction);
    List<PlanAttraction> selectPlanAttractionsByPlanId(Integer planId);
    PlanAttraction selectPlanAttractionsByPlanAttractionId(Integer planAttractionId);
    Integer updatePlanAttraction(PlanAttraction planAttraction);
    Integer deletePlanAttraction(Integer planAttractionId);
    Integer getCountByPlanId(Integer planId);

}
