package com.atman.aahara.Recipe.Instruction.Dto;


import com.atman.aahara.Recipe.Enum.CookingProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstructionRequestDto {
    private Integer step;
    private String command;
    private CookingProcess cookingProcess;
    private Duration duration;
    private UUID recipeId;
}
