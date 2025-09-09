package com.atman.aahara.Planner.Planner;

import com.atman.aahara.Planner.Family.Family;
import com.atman.aahara.Planner.Family.FamilyService;
import com.atman.aahara.Planner.Planner.Dto.PlannerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannerApplication {

    private final PlannerService plannerService;
    private final FamilyService familyService;


    public Planner createPlannerForDate(UUID familyId, LocalDate requestedDate) {
        Family family = familyService.getFamilyById(familyId);
        Planner planner = Planner.builder()
                .family(family)
                .date(requestedDate)
                .totalPrice(BigDecimal.ZERO)
                .build();
        return plannerService.savePlanner(planner);
    }

}
