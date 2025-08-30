package com.atman.aahara.Family;

import com.atman.aahara.Customer.Base.Customer;
import com.atman.aahara.Customer.Base.CustomerService;
import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyLogic implements FamilyService {

    private final FamilyRepository familyRepository;
    private final CustomerService customerService;

    @Override
    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family getFamilyByID(UUID id) {
        return fetchFamily(id);
    }

    @Override
    public List<Family> getListOfFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public Family updateFamily(UUID id, Family family) {
        Family existing = fetchFamily(id);
        existing.setName(family.getName());
        existing.setCusineType(family.getCusineType());
        return familyRepository.save(existing);
    }

    @Transactional
    @Override
    public Family addFamilyMember(UUID familyId, UUID customerID) {
        Family family = fetchFamily(familyId);
        Customer customer = customerService.getCustomer(customerID);
        if (!family.getCustomers().contains(customer)) {
            family.getCustomers().add(customer);
            log.info("Added customer {} to family {}", customerID, familyId);
        }
        return familyRepository.save(family);
    }

    @Transactional
    @Override
    public Family removeFamilyMember(UUID familyId, UUID customerID) {
        Family family = fetchFamily(familyId);
        Customer customer = customerService.getCustomer(customerID);
        if (family.getCustomers().remove(customer)) {
            log.info("Removed customer {} from family {}", customerID, familyId);
        }
        return familyRepository.save(family);
    }

    @Override
    public void deleteFamily(UUID id) {
        familyRepository.deleteById(id);
    }

    // Private helper methods
    private Family fetchFamily(UUID familyId) {
        return familyRepository.findById(familyId)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found"));
    }
}
