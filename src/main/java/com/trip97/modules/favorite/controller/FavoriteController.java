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
    public ResponseEntity<?> selectFavorites(@RequestParam Integer memberId) {
        List<Favorite> list = favoriteService.getFavorites(memberId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> selectFavorite(@PathVariable Integer id) {
        Favorite favorite = favoriteService.getFavorite(id);
        if (favorite != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(favorite);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> registerFavorite(@RequestBody Favorite favorite) {
        favoriteService.registerFavorite(favorite);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavorite(@PathVariable Integer id) {
        favoriteService.removeFavorite(id);
        return ResponseEntity.noContent().build();
    }
}
