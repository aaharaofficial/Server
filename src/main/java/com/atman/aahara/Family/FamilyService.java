package com.atman.aahara.Family;

import java.util.List;
import java.util.UUID;

public interface FamilyService {
    Family createFamily(Family family);

    Family getFamilyByID(UUID id);

    List<Family> getListOfFamilies();

    Family updateFamily(UUID id, Family family);

    Family addFamilyMember(UUID familyId, UUID customerID);

    Family removeFamilyMember(UUID familyId, UUID customerID);

    void deleteFamily(UUID id);
}
