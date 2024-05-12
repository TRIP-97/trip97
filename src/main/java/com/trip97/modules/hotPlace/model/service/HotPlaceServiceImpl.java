package com.trip97.modules.hotPlace.model.service;

import com.trip97.infra.util.SecurityUtils;
import com.trip97.modules.hotPlace.model.FileInfoDto;
import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.HotPlaceListDto;
import com.trip97.modules.hotPlace.model.mapper.HotPlaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceMapper hotPlaceMapper;

    @Override
    public Integer registerHotPlace(HotPlace hotPlace) {
        hotPlaceMapper.insertHotPlace(hotPlace);
        List<FileInfoDto> fileInfos = hotPlace.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            hotPlaceMapper.registerFile(hotPlace);
        }
        return hotPlace.getId();
    }

    @Override
    public HotPlace getHotPlace(Integer id) {
        return hotPlaceMapper.selectHotPlace(id);
    }

    @Override
    public HotPlaceListDto getHotPlaces(Map<String, String> map) {
        Map<String, Object> param = new HashMap<>();

        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "12" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("member_nickname".equals(key))
            param.put("key", key == null ? "" : "m.nickname");

        List<HotPlace> list = new ArrayList<>();
        String filter = map.get("filter");
        if (filter.equals("") || filter.equals("createdDate")) {
            list = hotPlaceMapper.selectHotPlacesOrderByCreatedDate(param);
        } else if (filter.equals("viewCount")) {
            list = hotPlaceMapper.selectHotPlacesOrderByViewCount(param);
        } else if (filter.equals("likeCount")) {
            list = hotPlaceMapper.selectHotPlacesOrderByLikeCount(param);
        }

        int totalArticleCount = hotPlaceMapper.getTotalHotPlaceCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        HotPlaceListDto hotPlaceListDto = new HotPlaceListDto();
        hotPlaceListDto.setHotPlaces(list);
        hotPlaceListDto.setCurrentPage(currentPage);
        hotPlaceListDto.setTotalPageCount(totalPageCount);

        return hotPlaceListDto;
    }

    @Override
    public void updateHit(int hotPlaceId) {
        hotPlaceMapper.updateHit(hotPlaceId);
    }

    @Override
    public Boolean isLiked(int memberId, int hotPlaceId) {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", String.valueOf(memberId));
        map.put("hotPlaceId", String.valueOf(hotPlaceId));
        return hotPlaceMapper.isLiked(map);
    }

    @Transactional
    @Override
    public void updateLike(int memberId, int hotPlaceId) {
        log.info("updateLike 서비스 호출!");
        Map<String, String> map = new HashMap<>();
        map.put("memberId", String.valueOf(memberId));
        map.put("hotPlaceId", String.valueOf(hotPlaceId));
        hotPlaceMapper.insertLike(map);
        hotPlaceMapper.incrementLikeCount(hotPlaceId);
    }

    @Override
    public Integer editHotPlace(HotPlace hotPlace) {
        return hotPlaceMapper.updateHotPlace(hotPlace);
    }

    @Override
    public Integer removeHotPlace(Integer id) {
        return hotPlaceMapper.deleteHotPlace(id);
    }
}
