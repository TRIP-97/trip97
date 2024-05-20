package com.trip97.modules.attraction.controller;

import com.trip97.modules.attraction.model.Attraction;
import com.trip97.modules.attraction.model.Bounds;
import com.trip97.modules.attraction.model.Gugun;
import com.trip97.modules.attraction.model.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    public AttractionService service;

    // 관광지 유형, 시도 데이터 불러오기
    @GetMapping("/type")
    public ResponseEntity<Map<String,List<?>>> getDropdownContentSido(){
        return new ResponseEntity<Map<String, List<?>>>(service.getTypeContentSido(),HttpStatus.OK);
    }

    // 구군 데이터 불러오기
    @GetMapping("/type/{sidoCode}")
    public ResponseEntity<List<Gugun>> getDropdownGugun
            (@PathVariable("sidoCode") int sidoCode){
        return new ResponseEntity<List<Gugun>>(service.getTypeGugun(sidoCode),HttpStatus.OK);
    }

    // 관광지 유형 + 시도 별 + 구군 별 조회
    @GetMapping()
    public ResponseEntity<List<Attraction>> getAttraction
            (@RequestParam(value = "contentType", defaultValue = "0") int contentTypeId,
            @RequestParam(value = "sidoCode", defaultValue = "0") int sidoCode,
            @RequestParam(value = "gugunCode", defaultValue = "0") int gugunCode,
            @RequestParam(value = "ha") BigDecimal ha,
            @RequestParam(value = "qa") BigDecimal qa,
            @RequestParam(value = "oa") BigDecimal oa,
            @RequestParam(value = "pa") BigDecimal pa,
            @RequestParam(value = "title", defaultValue = "") String title){

    	Bounds bound = new Bounds(ha,qa,oa,pa);

        bound.setTitle(title);

        bound.setContent(contentTypeId);
        bound.setSidoCode(sidoCode);
        bound.setGugunCode(gugunCode);

        List<Attraction> attractions=service.getAttractions(bound);

        return new ResponseEntity<List<Attraction>>(attractions,HttpStatus.OK);
    }

    // 관광지 상세 정보 조회
    @GetMapping("/{attractionId}")
    public ResponseEntity<Attraction> getAttractionById
    (@PathVariable("attractionId") int attractionId){
        return new ResponseEntity<Attraction>(service.getAttractionById(attractionId),HttpStatus.OK);
    }


}
