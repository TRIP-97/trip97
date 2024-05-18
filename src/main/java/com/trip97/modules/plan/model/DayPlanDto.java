package com.trip97.modules.plan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DayPlanDto {

    private int id;
    private int planId;
    private int day;
    private List<DayPlanItemDto> items;
}
