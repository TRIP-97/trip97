package com.trip97.modules.favorite.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Favorite {

    private Integer id;
    private Integer memberId;
    private Integer attractionId;
    private String createdAt;
    private String title;
    private String overview;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer rating;
}