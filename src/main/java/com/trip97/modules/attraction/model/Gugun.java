package com.trip97.modules.attraction.model;


import lombok.Getter;

@Getter
public class Gugun {
    private int code;
    private String name;

    public Gugun(int code, String name){
        this.code = code;
        this.name = name;
    }
}
