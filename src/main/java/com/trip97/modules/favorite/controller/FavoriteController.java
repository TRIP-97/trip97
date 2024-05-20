package com.trip97.modules.favorite.controller;

import com.trip97.modules.favorite.model.Favorite;
import com.trip97.modules.favorite.model.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<?> selectFavorites(@RequestParam("memberId") Integer memberId) {
        List<Favorite> list = favoriteService.getFavorites(memberId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/attraction")
    public ResponseEntity<?> selectFavorite(@RequestParam("attractionId") Integer attractionId,
                                            @RequestParam("memberId") Integer memberId) {

        Favorite favorite = Favorite.builder()
                .attractionId(attractionId)
                .memberId(memberId)
                .build();

        Favorite result = favoriteService.getFavorite(favorite);

        if (result != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(favorite);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> registerFavorite(@RequestBody Favorite favorite) {

        Favorite result = favoriteService.getFavorite(favorite);

        if (result != null) {
            return ResponseEntity.noContent().build();
        }else {
            favoriteService.registerFavorite(favorite);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> removeFavorite(@RequestParam("attractionId") Integer attractionId,
                                            @RequestParam("memberId") Integer memberId) {

        Favorite favorite = Favorite.builder()
                .attractionId(attractionId)
                .memberId(memberId)
                .build();

        favoriteService.removeFavorite(favorite);
        return ResponseEntity.noContent().build();
    }
}
