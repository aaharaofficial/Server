package com.atman.aahara.Planner.Family;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyLogic implements FamilyService {

    private final FamilyRepository familyRepository;

    @Override
    public Family saveFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family getFamilyById(UUID id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found with id " + id));
    }

    @Override
    public List<Family> getAllFamily() {
        return familyRepository.findAll();
    }

    @Override
    public void deleteFamily(UUID id) {
        if (!familyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Family not found with id " + id);
        }
        familyRepository.deleteById(id);
    }
}
