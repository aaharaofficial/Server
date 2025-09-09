package com.atman.aahara.Stock.Adapter.Out;

import com.atman.aahara.Stock.Application.Mappers.RecipeMapper;
import com.atman.aahara.Stock.Domain.Model.Recipe;
import com.atman.aahara.Stock.Infra.Persistance.RecipeEntity;
import com.atman.aahara.Stock.Infra.Repository.RecipeJpaRepository;
import com.atman.aahara.Stock.Port.Out.RecipeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecipeRepositoryAdapter implements RecipeRepositoryPort {

    private final RecipeJpaRepository recipeJPARepository;
    private final RecipeMapper recipeMapper;

    @Override
    public Recipe save(Recipe recipe) {
        RecipeEntity recipeEntity = recipeMapper.toEntity(recipe);
        RecipeEntity savedEntity = recipeJPARepository.save(recipeEntity);
        return recipeMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Recipe> findById(UUID id) {
        return recipeJPARepository.findById(id)
                .map(recipeMapper::toDomain);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeJPARepository.findAll()
                .stream()
                .map(recipeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        recipeJPARepository.deleteById(id);
    }
}
