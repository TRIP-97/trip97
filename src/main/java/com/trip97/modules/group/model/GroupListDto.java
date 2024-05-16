package com.trip97.modules.group.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupListDto {
	
	private List<Group> groups;
	private int currentPage;
    private int totalPageCount;
}
