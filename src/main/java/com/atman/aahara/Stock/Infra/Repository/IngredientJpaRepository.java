package com.atman.aahara.Stock.Infra.Repository;

import com.atman.aahara.Stock.Infra.Persistance.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngredientJpaRepository extends JpaRepository<IngredientEntity , UUID> {
}
