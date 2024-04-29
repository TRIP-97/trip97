package com.trip97.modules.attraction.model.service;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.AttractionContent;
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
    public List<Attraction> getAttractionContentSidoGugun(int content, int sido, int gugun) {
        return mapper.selectContentSidoGugun(content, sido, gugun);
    }

    @Override
    public List<Attraction> getAttractionContentSido(int content, int sido) {
        return mapper.selectContentSido(content, sido);
    }

    @Override
    public List<Attraction> getAttractionContent(int content) {
        return mapper.selectContent(content);
    }

    @Override
    public List<Attraction> getAttractionSidoGugun(int sido, int gugun) {
        return mapper.selectSidoGugun(sido, gugun);
    }

    @Override
    public List<Attraction> getAttractionSido(int sido) {
        return mapper.selectSido(sido);
    }

    @Override
    public List<Attraction> getAttractions() {
    	return mapper.selectAttractions();
    }
    
    @Override
    public Attraction getAttractionById(int attractionId) {
        return mapper.selectAttractionById(attractionId);
    }

}
