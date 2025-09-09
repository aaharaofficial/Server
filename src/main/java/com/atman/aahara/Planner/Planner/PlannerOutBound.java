package com.atman.aahara.Planner.Planner;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlannerOutBound implements PlannerService {
    private final PlannerRepository plannerRepository;


    @Override
    public List<Planner> saveAllPlanners(List<Planner> planners){
        return plannerRepository.saveAll(planners);
    }

    @Override
    public Planner savePlanner(Planner planner){
        return plannerRepository.save(planner);
    }

    @Override
    public void deletePlanner(UUID id) {
        if (!plannerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Planner not found with id " + id);
        }
        plannerRepository.deleteById(id);
    }

    @Override
    public Planner getPlannerById(UUID plannerId){
        return plannerRepository.findById(plannerId).orElseThrow(() -> new ResourceNotFoundException("Planner not found with id " + plannerId));
    }


    @Override
    public Planner getPlannerForToday(UUID familyId) {
        LocalDate today = LocalDate.now();
        return plannerRepository.findByFamily_IdAndDate(familyId,today);
    }

    @Override
    public Planner getPlannerByDateAndFamily(UUID familyId, LocalDate date) {
        return plannerRepository.findByFamily_IdAndDate(familyId , date);
    }

    @Override
    public List<Planner> getAllPlannerByDate(LocalDate date) {
        return plannerRepository.findAllByDate(date);
    }

    @Override
    public List<Planner> getPlannerForWeek(UUID familyId) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(6);
        return plannerRepository.findByFamily_IdAndDateBetween(familyId,today, endDate);
    }

}
