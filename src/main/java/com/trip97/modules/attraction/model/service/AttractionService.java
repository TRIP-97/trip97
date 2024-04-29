package com.trip97.modules.attraction.model.service;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.Gugun;

import java.util.List;
import java.util.Map;

public interface AttractionService {

    public Map<String, List<?>> getTypeContentSido();
    public List<Gugun> getTypeGugun(int sido);

    public List<Attraction> getAttractionContentSidoGugun(int content,int sido, int gugun);
    public List<Attraction> getAttractionContentSido(int content, int sido);
    public List<Attraction> getAttractionContent(int content);

    public List<Attraction> getAttractionSidoGugun(int sido, int gugun);
    public List<Attraction> getAttractionSido(int sido);
    public List<Attraction> getAttractions();

    public Attraction getAttractionById(int attractionId);

}
