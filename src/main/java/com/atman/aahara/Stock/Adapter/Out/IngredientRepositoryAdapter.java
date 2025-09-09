package com.atman.aahara.Stock.Adapter.Out;

import com.atman.aahara.Stock.Application.Mappers.IngredientMapper;
import com.atman.aahara.Stock.Domain.Model.Ingredient;
import com.atman.aahara.Stock.Infra.Persistance.IngredientEntity;
import com.atman.aahara.Stock.Infra.Repository.IngredientJpaRepository;
import com.atman.aahara.Stock.Port.Out.IngredientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IngredientRepositoryAdapter implements IngredientRepositoryPort {

    private final IngredientJpaRepository ingredientJpaRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    public Ingredient save(Ingredient ingredient) {
        IngredientEntity entity = ingredientMapper.toEntity(ingredient);
        IngredientEntity savedEntity = ingredientJpaRepository.save(entity);
        return ingredientMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Ingredient> findById(UUID id) {
        return ingredientJpaRepository.findById(id)
                .map(ingredientMapper::toDomain);
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientJpaRepository.findAll()
                .stream()
                .map(ingredientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        ingredientJpaRepository.deleteById(id);
    }

    @Override
    public void delete(Ingredient ingredient) {
        IngredientEntity entity = ingredientMapper.toEntity(ingredient);
        ingredientJpaRepository.delete(entity);
    }
}
