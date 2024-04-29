package com.trip97.modules.group.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Group {

    private Integer id;
    private Integer creatorId;
    private String name;
    private String overview;
    private Integer maxMemberCount;
}
