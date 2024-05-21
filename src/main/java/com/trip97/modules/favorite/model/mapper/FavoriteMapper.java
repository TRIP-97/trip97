package com.trip97.modules.favorite.model.mapper;

import com.trip97.modules.favorite.model.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    Integer insertFavorite(Favorite favorite);
    Favorite selectFavorite(Favorite favorite);
    List<Favorite> selectFavorites(Integer memberId);
    Integer deleteFavorite(Favorite favorite);

}
