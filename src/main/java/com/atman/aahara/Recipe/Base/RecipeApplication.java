package com.atman.aahara.Recipe.Base;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Assets.Image.ImageService;
import com.atman.aahara.Assets.Video.Video;
import com.atman.aahara.Assets.Video.VideoService;
import com.atman.aahara.Recipe.Base.Dto.RecipeRequestWithInstructionsDto;
import com.atman.aahara.Recipe.Base.Dto.RecipeRequestDto;
import com.atman.aahara.Recipe.Mappers.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeApplication {

    private final RecipeMapper recipeMapper;
    private final ImageService imageService;
    private final VideoService videoService;
    private final RecipeService recipeService;

    @Transactional
    public Recipe createRecipe(RecipeRequestWithInstructionsDto recipeRequestWithInstructionsDto) throws IOException {
        Recipe recipe = recipeMapper.requestToEntityWithInstruction(recipeRequestWithInstructionsDto);
        Image image = imageService.saveImage(recipeRequestWithInstructionsDto.getThumbnail(), "");
        Video video = videoService.saveVideo(recipeRequestWithInstructionsDto.getVideo(), "");
        recipe.setImage(image);
        recipe.setVideo(video);
        return recipeService.saveRecipe(recipe);
    }

    @Transactional
    public Recipe updateRecipe(UUID recipeId, RecipeRequestDto recipeRequestDto) throws IOException {
        Recipe recipe = recipeService.getRecipe(recipeId);
        replaceAssets(recipeRequestDto, recipe);
        Recipe mapped = recipeMapper.mapExisting(recipeRequestDto, recipe);
        return recipeService.saveRecipe(mapped);
    }

    @Transactional
    public void deleteRecipe(UUID recipeId){
        Recipe recipe = recipeService.getRecipe(recipeId);
        imageService.deleteImage(recipe.getImage());
        videoService.deleteVideo(recipe.getVideo());
        recipeService.deleteRecipe(recipe);
    }

    public Recipe getRecipe(UUID recipeId){
        return recipeService.getRecipe(recipeId);
    }

    public List<Recipe> getAllRecipe(){
        return recipeService.getAllRecipe();
    }

    private void replaceAssets(RecipeRequestDto recipeRequestDto, Recipe recipe) throws IOException {
        if (recipeRequestDto.isThumbnailChanged()) {
            imageService.replaceimage(recipe.getImage(), recipeRequestDto.getThumbnail(), "");
        }
        if (recipeRequestDto.isVideoChanged()) {
            videoService.replaceVideo(recipe.getVideo(), recipeRequestDto.getVideo(), "");
        }
    }
}
