package com.trip97.modules.attraction.model;

import lombok.Getter;

@Getter
public class AttractionContent {
    private int code;
    private String name;

    public AttractionContent(int code, String name){
        this.code = code;
        this.name = name;
    }
}
