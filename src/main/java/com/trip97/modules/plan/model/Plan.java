package com.trip97.modules.plan.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Plan {

    private Integer id;
    private Integer travelGroupId;
    private Integer creatorId;
    private String title;
    private String overview;
    private String startDate;
    private String endDate;
    private List<DayPlanDto> dayPlans;
}
