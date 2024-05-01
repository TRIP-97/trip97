package com.trip97.modules.hotPlace.model.mapper;

import com.trip97.modules.hotPlace.model.HotPlace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotPlaceMapper {

    Integer insertHotPlace(HotPlace hotPlace);
    HotPlace selectHotPlace(Integer id);
    List<HotPlace> selectHotPlaces();
    Integer updateHotPlace(HotPlace hotPlace);
    Integer deleteHotPlace(Integer id);
}
