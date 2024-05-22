package com.trip97.modules.favorite.model.service;

import com.trip97.modules.favorite.model.Favorite;
import com.trip97.modules.favorite.model.FavoriteListDto;
import com.trip97.modules.favorite.model.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public FavoriteListDto getFavorites(Map<String, String> map) {
        Map<String, Object> param = new HashMap<>();

        int currentPage = Integer.parseInt(map.get("pgno")==null? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp")==null? "12" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;

        param.put("start", start);
        param.put("listsize", sizePerPage);

        int id = Integer.parseInt(map.get("id"));
        param.put("id",id);

        String key = map.get("key");
        param.put("key", key==null ? "" : key);

        String word = map.get("word");
        param.put("word", word==null ? "" : word);

        String filter = map.get("filter");

        List<Favorite> list = new ArrayList<>();

        if(filter.equals("")||filter.equals("newest")){
            list = favoriteMapper.selectFavoritesNew(param);
        }else if(filter.equals("oldest")){
            list = favoriteMapper.selectFavoritesOld(param);
        }

        int totalArticleCount = favoriteMapper.getTotalFavoriteCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        FavoriteListDto favoriteListDto = new FavoriteListDto();
        favoriteListDto.setFavorites(list);
        favoriteListDto.setCurrentPage(currentPage);
        favoriteListDto.setTotalPageCount(totalPageCount);

        return favoriteListDto;
    }

    @Override
    public List<Favorite> getFavoritesByMemberId(Integer memberId) {
        return favoriteMapper.selectFavorites(memberId);
    }

    @Override
    public Integer removeFavorite(Favorite favorite) {
        return favoriteMapper.deleteFavorite(favorite);
    }
}
