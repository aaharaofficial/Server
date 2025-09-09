package com.atman.aahara.Planner.Family;

import java.util.List;
import java.util.UUID;

public interface FamilyService {

    Family saveFamily(Family familyCreated);

    Family getFamilyById(UUID id);

    List<Family> getAllFamily();

    void deleteFamily(UUID id);
}
