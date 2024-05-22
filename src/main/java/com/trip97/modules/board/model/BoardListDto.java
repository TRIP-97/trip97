package com.trip97.modules.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardListDto {

    List<Board> list;
    int currentPage;
    int totalPageCount;

}
