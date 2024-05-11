package com.trip97.modules.hotPlace.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HotPlaceListDto {

    private List<HotPlace> hotPlaces;
    private int currentPage;
    private int totalPageCount;
}
