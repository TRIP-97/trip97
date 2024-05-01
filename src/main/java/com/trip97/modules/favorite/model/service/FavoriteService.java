package com.trip97.modules.favorite.model.service;

import com.trip97.modules.favorite.model.Favorite;

import java.util.List;

public interface FavoriteService {

    Integer registerFavorite(Favorite favorite);
    Favorite getFavorite(Integer id);
    List<Favorite> getFavorites(Integer memberId);
    Integer removeFavorite(Integer id);
}
