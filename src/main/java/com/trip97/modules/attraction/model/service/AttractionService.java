package com.trip97.modules.attraction.model.service;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.Bounds;
import com.trip97.modules.attraction.model.Gugun;

import java.util.List;
import java.util.Map;

public interface AttractionService {

    public Map<String, List<?>> getTypeContentSido();
    public List<Gugun> getTypeGugun(int sido);

    public List<Attraction> getAttractionContentSidoGugun(Bounds bound);
    public List<Attraction> getAttractionContentSido(Bounds bound);
    public List<Attraction> getAttractionContent(Bounds bound);

    public List<Attraction> getAttractionSidoGugun(Bounds bound);
    public List<Attraction> getAttractionSido(Bounds bound);
    public List<Attraction> getAttractions(Bounds bound);

    public Attraction getAttractionById(int attractionId);

}
