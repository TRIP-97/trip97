package com.trip97.modules.favorite.model.service;

import com.trip97.modules.favorite.model.Favorite;
import com.trip97.modules.favorite.model.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteMapper favoriteMapper;

    @Override
    public Integer registerFavorite(Favorite favorite) {
        return favoriteMapper.insertFavorite(favorite);
    }

    @Override
    public Favorite getFavorite(Favorite favorite) {
        return favoriteMapper.selectFavorite(favorite);
    }

    @Override
    public List<Favorite> getFavorites(Integer memberId) {
        return favoriteMapper.selectFavorites(memberId);
    }

    @Override
    public Integer removeFavorite(Favorite favorite) {
        return favoriteMapper.deleteFavorite(favorite);
    }
}
