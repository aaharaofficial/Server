package com.atman.aahara.Planner.Planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Repository
public interface PlannerRepository extends JpaRepository<Planner, UUID> {
    List<Planner> findAllByDate(LocalDate date);

    Planner findByFamily_IdAndDate( UUID familyId, LocalDate date);

    List<Planner> findByFamily_IdAndDateBetween( UUID familyId,LocalDate start, LocalDate end);
}
