package com.trip97.modules.hotPlace.controller;

import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.HotPlaceListDto;
import com.trip97.modules.hotPlace.model.service.HotPlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hotplace")
@RequiredArgsConstructor
public class HotPlaceController {

    private final HotPlaceService hotPlaceService;

    @GetMapping
    public ResponseEntity<?> getHotPlaces(@RequestParam Map<String, String> map) {
        HotPlaceListDto hotPlaceList = hotPlaceService.getHotPlaces(map);
        if (hotPlaceList != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(hotPlaceList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHotPlaceById(@PathVariable("id") Integer id) {
        HotPlace hotPlace = hotPlaceService.getHotPlace(id);
        if (hotPlace != null) {
            hotPlaceService.updateHit(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(hotPlace);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> registerHotPlace(@RequestBody HotPlace hotPlace) {
        hotPlaceService.registerHotPlace(hotPlace);
        HotPlace createdHotPlace = hotPlaceService.getHotPlace(hotPlace.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdHotPlace);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateHotPlace(@PathVariable int id, @RequestBody HotPlace hotPlace) {
        hotPlaceService.editHotPlace(hotPlace);
        HotPlace updatedHotPlace = hotPlaceService.getHotPlace(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(updatedHotPlace);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeHotPlace(@PathVariable int id) {
        hotPlaceService.removeHotPlace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/like")
    public ResponseEntity<?> checkLike(@RequestParam int memberId, @RequestParam int hotPlaceId) {
        log.info("checkLike 컨트롤러 호출!");
        return ResponseEntity.ok(hotPlaceService.isLiked(memberId, hotPlaceId));
    }

    @PutMapping("/api/like")
    public ResponseEntity<?> updateLike(@RequestBody Map<String, Integer> params) {
        int memberId = params.get("memberId");
        int hotPlaceId = params.get("hotPlaceId");
        hotPlaceService.updateLike(memberId, hotPlaceId);
        return ResponseEntity.noContent().build();
    }
}
