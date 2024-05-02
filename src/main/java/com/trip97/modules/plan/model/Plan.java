package com.trip97.modules.plan.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Plan {

    private Integer id;
    private Integer travelGroupId;
    private Integer creatorId;
    private String title;
    private String overview;
    private String startDate;
    private String endDate;

}
