package com.trip97.modules.hotPlace.model.mapper;

import com.trip97.modules.hotPlace.model.HotPlace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HotPlaceMapper {

    Integer insertHotPlace(HotPlace hotPlace);
    void registerFile(HotPlace hotPlace);
    HotPlace selectHotPlace(Integer id);
    List<HotPlace> selectHotPlacesOrderByCreatedDate(Map<String, Object> param);
    List<HotPlace> selectHotPlacesOrderByViewCount(Map<String, Object> param);
    List<HotPlace> selectHotPlacesOrderByLikeCount(Map<String, Object> param);
    Integer getTotalHotPlaceCount(Map<String, Object> param);
    void updateHit(int hotPlaceId);
    Boolean isLiked(Map<String, String> param);
    Integer insertLike(Map<String, String> param);
    void incrementLikeCount(int hotPlaceId);
    Integer updateHotPlace(HotPlace hotPlace);
    Integer deleteHotPlace(Integer id);
}
