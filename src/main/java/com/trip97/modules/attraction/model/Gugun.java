package com.trip97.modules.attraction.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@Getter
@JsonSerialize
public class Gugun {
    private int code;
    private String name;

    public Gugun(int code, String name){
        this.code = code;
        this.name = name;
    }
}
