package com.trip97.modules.plan.controller;

import com.trip97.modules.plan.model.DayPlanItemDto;
import com.trip97.modules.plan.model.Plan;
import com.trip97.modules.plan.model.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group/{groupId}/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    /**
     * 여행 계획 목록 조회
     * @param groupId
     * @return 여행 계획 목록
     */
    @GetMapping
    public ResponseEntity<?> getPlans(@PathVariable int groupId) {
        List<Plan> list = planService.selectPlansByGroupId(groupId);
        if (list != null && !list.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /**
     * 여행 계획 상세 조회
     * @param planId
     * @return 여행 계획 상세 정보
     */
    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlan(@PathVariable int planId) {
        Plan plan = planService.getPlanById(planId);
        if (plan != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(plan);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /**
     * 여행 계획 생성
     * @param plan
     * @return 생성된 여행 계획
     */
    @PostMapping
    public ResponseEntity<?> createPlan(@RequestBody Plan plan) {
        planService.createPlan(plan);
        Plan createdPlan = planService.getPlanById(plan.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdPlan);
    }

    /**
     * 여행 계획 수정
     * @param planId
     * @param plan
     * @return 수정된 여행 계획 정보
     */
    @PutMapping("/{planId}")
    public ResponseEntity<?> updatePlan(@PathVariable int planId, @RequestBody Plan plan) {
        planService.updatePlan(plan);
        Plan updatedPlan = planService.getPlanById(planId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(updatedPlan);
    }

    /**
     * 여행 계획 삭제
     * @param planId
     * @return Void
     */
    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable int planId) {
        planService.deletePlanById(planId);
        return ResponseEntity.noContent().build();
    }


    /**
     * 여행 계획 장소, 메모 추가
     * @param dayPlanItemDto
     * @return
     */
    @PostMapping("/{planId}/dayPlanItem")
    public ResponseEntity<?> createDayPlanItem(@RequestBody DayPlanItemDto dayPlanItemDto) {
        planService.insertDayPlanItem(dayPlanItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 여행 계획 장소, 메모의 순서 변경
     * @param params
     * @return
     */
    @PutMapping("/{planId}/dayPlanItem/{itemId}")
    public ResponseEntity<?> updateDayPlanItemOrder(@RequestBody Map<String, Integer> params) {
        planService.updateDayPlanItemOrder(params.get("itemId"), params.get("order"));
        return ResponseEntity.ok().build();
    }

    /**
     * 여행 계획 장소, 메모 삭제
     * @param itemId
     * @return
     */
    @DeleteMapping("/{planId}/dayPlanItem/{itemId}")
    public ResponseEntity<?> deleteDayPlanItemById(@PathVariable int itemId) {
        planService.deleteDayPlanItemById(itemId);
        return ResponseEntity.noContent().build();
    }
}
