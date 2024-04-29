package com.trip97.modules.attraction.model;


import lombok.Getter;

@Getter
public class Sido {

    private int code;
    private String name;

    public Sido(int code, String name){
        this.code = code;
        this.name = name;
    }

}
