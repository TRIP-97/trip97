package com.trip97.modules.hotPlace.model.service;

import com.trip97.infra.util.SecurityUtils;
import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.mapper.HotPlaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceMapper hotPlaceMapper;

    @Override
    public Integer registerHotPlace(HotPlace hotPlace) {
        Integer memberId = SecurityUtils.getCurrentMemberId();
        hotPlace.setWriterId(memberId);
        hotPlaceMapper.insertHotPlace(hotPlace);
        return hotPlace.getId();
    }

    @Override
    public HotPlace getHotPlace(Integer id) {
        return hotPlaceMapper.selectHotPlace(id);
    }

    @Override
    public List<HotPlace> getHotPlaces() {
        return hotPlaceMapper.selectHotPlaces();
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
