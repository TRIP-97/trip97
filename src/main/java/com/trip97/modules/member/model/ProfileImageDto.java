package com.trip97.modules.member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileImageDto {

    private Integer memberId;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
    private String url;
}
