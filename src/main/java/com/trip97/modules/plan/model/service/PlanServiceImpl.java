package com.trip97.modules.plan.model.service;

import com.trip97.modules.plan.model.DayPlanDto;
import com.trip97.modules.plan.model.DayPlanItemDto;
import com.trip97.modules.plan.model.Plan;
import com.trip97.modules.plan.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;

    @Override
    public List<Plan> selectPlansByGroupId(Integer groupId) {
        return planMapper.selectPlansByGroupId(groupId);
    }

    @Override
    public Plan getPlanById(Integer id) {
        return planMapper.getPlanById(id);
    }

    @Override
    @Transactional
    public void createPlan(Plan plan) {
        planMapper.insertPlan(plan);

        // 날짜 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 시작일과 종료일 파싱
        LocalDate startDate = LocalDate.parse(plan.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(plan.getEndDate(), formatter);

        // DayPlanDto 리스트 생성
        List<DayPlanDto> dayPlans = new ArrayList<>();

        // 날짜 범위 내의 DayPlanDto 생성
        for (int i = 0; !startDate.plusDays(i).isAfter(endDate); i++) {
            DayPlanDto dayPlan = new DayPlanDto();
            dayPlan.setPlanId(plan.getId());
            dayPlan.setDay(i + 1);
            dayPlan.setItems(new ArrayList<>());
            dayPlans.add(dayPlan);
        }

        // DayPlanDto 삽입
        for (DayPlanDto dayPlan : dayPlans) {
            planMapper.insertDayPlan(dayPlan);
        }
    }

    @Override
    public Integer insertDayPlanItem(DayPlanItemDto dayPlanItemDto) {
        return planMapper.insertDayPlanItem(dayPlanItemDto);
    }

    @Override
    public Integer updatePlan(Plan plan) {
        return planMapper.updatePlan(plan);
    }

    @Override
    public Integer updateDayPlanItemOrder(int itemId, int order) {
        return planMapper.updateDayPlanItemOrder(itemId, order);
    }

    @Override
    public Integer deletePlanById(Integer id) {
        return planMapper.deletePlanById(id);
    }

    @Override
    public Integer deleteDayPlanItemById(Integer id) {
        return planMapper.deleteDayPlanItemById(id);
    }
}
