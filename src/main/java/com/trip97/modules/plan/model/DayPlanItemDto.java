package com.trip97.modules.plan.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DayPlanItemDto {

    private int id;
    private int dayPlanId;
    private PlanItemType type;
    private String title;
    private String content;
    private Integer attractionId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int order;
}
