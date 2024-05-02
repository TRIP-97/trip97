package com.trip97.modules.hotPlace.model.service;

import com.trip97.modules.hotPlace.model.HotPlace;

import java.util.List;

public interface HotPlaceService {

    Integer registerHotPlace(HotPlace hotPlace);
    HotPlace getHotPlace(Integer id);
    List<HotPlace> getHotPlaces();
    Integer editHotPlace(HotPlace hotPlace);
    Integer removeHotPlace(Integer id);

}
