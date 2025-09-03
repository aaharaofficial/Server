package com.atman.aahara.Recipe.Instruction;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, UUID> {
    @Modifying
    @Query("UPDATE Instruction i SET i.step = i.step + 1 " +
            "WHERE i.recipe.id = :recipeId AND i.step >= :step")
    void incrementSteps(@Param("recipeId") UUID recipeId, @Param("step") int step);

    @Modifying
    @Query("UPDATE Instruction i " +
            "SET i.step = i.step - 1 " +
            "WHERE i.recipe.id = :recipeId AND i.step > :step")
    void decrementSteps(@Param("recipeId") UUID recipeId, @Param("step") int step);


    Optional<Instruction> findByRecipeIdAndStep(UUID recipeId,Integer step);

    List<Instruction> findAllByRecipeId(UUID recipeId);

}
