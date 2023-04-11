package com.josphat.domain.use_case

import com.josphat.domain.model.Recipe
import com.josphat.domain.repo.RecipeRepo
import kotlinx.coroutines.flow.Flow
import com.joel.common.utils.Result

class GetRandomRecipesUseCase(
    private val repo: RecipeRepo
) {


    suspend operator fun invoke() : Flow<Result<List<Recipe>>>{
        return repo.getRandomRecipes()
    }
}