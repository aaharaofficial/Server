package com.atman.aahara.Inventory;

import com.atman.aahara.Recipe.Base.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID>  , JpaSpecificationExecutor<Inventory> {
    List<Inventory> findAllByIdIn(List<UUID> ids);
}
