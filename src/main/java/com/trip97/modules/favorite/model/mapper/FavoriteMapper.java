package com.trip97.modules.favorite.model.mapper;

import com.trip97.modules.favorite.model.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FavoriteMapper {

    Integer insertFavorite(Favorite favorite);
    Favorite selectFavorite(Favorite favorite);
    List<Favorite> selectFavoritesNew(Map<String, Object> param);
    List<Favorite> selectFavoritesOld(Map<String, Object> param);
    Integer getTotalFavoriteCount(Map<String, Object> param);
    Integer deleteFavorite(Favorite favorite);
    List<Favorite> selectFavorites(Integer memberId);

}
