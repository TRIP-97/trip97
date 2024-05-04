package com.trip97.modules.attraction.model.mapper;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.AttractionContent;
import com.trip97.modules.attraction.model.Gugun;
import com.trip97.modules.attraction.model.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {

    // 관광지 유형 + 시도구군 조합
    List<Attraction> selectContentSidoGugun(int contentCode, int sidoCode, int gugunCode);
    List<Attraction> selectContentSido(int contentCode, int sidoCode);
    List<Attraction> selectContent(int contentCode);

    // 시도구군 조합
    List<Attraction> selectSidoGugun(int sidoCode, int gugunCode);
    List<Attraction> selectSido(int sidoCode);
    List<Attraction> selectAttractions();

    // 드롭다운 불러오기
    List<AttractionContent> selectTypeContent();
    List<Sido> selectTypeSido();
    List<Gugun> selectTypeGugun(int sidoCode);

    // 관광지 상세 정보
    Attraction selectAttractionById(int attractionId);

}