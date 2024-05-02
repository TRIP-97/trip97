package com.trip97.modules.hotPlace.controller;

import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.service.HotPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/hotplace")
@RequiredArgsConstructor
public class HotPlaceController {

    private final HotPlaceService hotPlaceService;

    @GetMapping
    public ResponseEntity<?> getHotPlaces() {
        List<HotPlace> list = hotPlaceService.getHotPlaces();
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHotPlaceById(@PathVariable("id") Integer id) {
        HotPlace hotPlace = hotPlaceService.getHotPlace(id);
        if (hotPlace != null) {
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
}
