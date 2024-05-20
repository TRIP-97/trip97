package com.trip97.modules.attraction.model.mapper;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.AttractionContent;
import com.trip97.modules.attraction.model.Bounds;
import com.trip97.modules.attraction.model.Gugun;
import com.trip97.modules.attraction.model.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionMapper {

//    // 관광지 유형 + 시도구군 조합
//    List<Attraction> selectContentSidoGugun(Bounds bound);
//    List<Attraction> selectContentSido(Bounds bound);
//    List<Attraction> selectContent(Bounds bound);
//
//    // 시도구군 조합
//    List<Attraction> selectSidoGugun(Bounds bound);
//    List<Attraction> selectSido(Bounds bound);
//    List<Attraction> selectAttractions(Bounds bound);

    // 관광지 불러오기
    List<Attraction> selectAttraction(Bounds bound);

    // 드롭다운 불러오기
    List<AttractionContent> selectTypeContent();
    List<Sido> selectTypeSido();
    List<Gugun> selectTypeGugun(int sidoCode);

    // 관광지 상세 정보
    Attraction selectAttractionById(int attractionId);

    Double getRating(int attractionId);
    Integer getReviewCount(int attractionId);
    Integer updateRating(double rating, int attractionId);
    Integer updateReviewCount(int reviewCount, int attractionId);
}
