package com.atman.aahara.Stock.Infra.Repository;

import com.atman.aahara.Stock.Infra.Persistance.StepInstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.UUID;

@Repository
public interface StepInstructionJpaRepository extends JpaRepository<StepInstructionEntity, UUID> {
}
