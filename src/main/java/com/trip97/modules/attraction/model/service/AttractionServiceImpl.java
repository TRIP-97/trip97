package com.trip97.modules.attraction.model.service;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.AttractionContent;
import com.trip97.modules.attraction.model.Bounds;
import com.trip97.modules.attraction.model.Gugun;
import com.trip97.modules.attraction.model.mapper.AttractionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    public AttractionMapper mapper;

    @Override
    public Map<String, List<?>> getTypeContentSido() {
        Map<String, List<?>> dropdown = new HashMap<>();
        dropdown.put("content",mapper.selectTypeContent());
        dropdown.put("sido",mapper.selectTypeSido());
        return dropdown;
    }

    @Override
    public List<Gugun> getTypeGugun(int sido) {
        return mapper.selectTypeGugun(sido);
    }

    @Override
    public List<Attraction> getAttractions(Bounds bound) {
        return mapper.selectAttraction(bound);
    }

    @Override
    public List<Attraction> getHotAttractions() {
        return mapper.selectHotAttractions();
    }

//    @Override
//    public List<Attraction> getAttractionContentSidoGugun(Bounds bound) {
//        return mapper.selectContentSidoGugun(bound);
//    }
//
//    @Override
//    public List<Attraction> getAttractionContentSido(Bounds bound) {
//        return mapper.selectContentSido(bound);
//    }
//
//    @Override
//    public List<Attraction> getAttractionContent(Bounds bound) {
//        return mapper.selectContent(bound);
//    }
//
//    @Override
//    public List<Attraction> getAttractionSidoGugun(Bounds bound) {
//        return mapper.selectSidoGugun(bound);
//    }
//
//    @Override
//    public List<Attraction> getAttractionSido(Bounds bound) {
//        return mapper.selectSido(bound);
//    }
//
//    @Override
//    public List<Attraction> getAttractions(Bounds bound) {
//    	return mapper.selectAttractions(bound);
//    }

    @Override
    public Attraction getAttractionById(int attractionId) {
        return mapper.selectAttractionById(attractionId);
    }

}
