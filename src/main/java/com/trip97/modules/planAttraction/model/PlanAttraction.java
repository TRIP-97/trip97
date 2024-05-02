package com.trip97.modules.planAttraction.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PlanAttraction {

    private Integer id;
    private Integer attractionId;
    private Integer planId;
    private Integer visitOrder;
    private String memo;
    private Integer sidoCode;
    private Integer gugunCode;
    private Integer contentTypeId;
    private String title;
    private String overview;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer rating;
    private Integer reviewCount;
}
