package com.trip97.modules.favorite.model.service;

import com.trip97.modules.favorite.model.Favorite;
import com.trip97.modules.favorite.model.FavoriteListDto;

import java.util.List;
import java.util.Map;

public interface FavoriteService {

    Integer registerFavorite(Favorite favorite);
    Favorite getFavorite(Favorite favorite);
    FavoriteListDto getFavorites(Map<String, String> map);
    Integer removeFavorite(Favorite favorite);
}
