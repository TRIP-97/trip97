package com.trip97.modules.hotPlace.model.service;

import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.HotPlaceListDto;

import java.util.Map;

public interface HotPlaceService {

    Integer registerHotPlace(HotPlace hotPlace);
    HotPlace getHotPlace(Integer id);
    HotPlaceListDto getHotPlaces(Map<String, String> map);
    void updateHit(int hotPlaceId);
    Boolean isLiked(int memberId, int hotPlaceId);
    void updateLike(int memberId, int hotPlaceId);
    Integer editHotPlace(HotPlace hotPlace);
    Integer removeHotPlace(Integer id);

}
