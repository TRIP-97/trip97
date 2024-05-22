package com.trip97.modules.favorite.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteListDto {

    private List<Favorite> favorites;
    private int currentPage;
    private int totalPageCount;

}
