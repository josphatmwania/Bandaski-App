package com.josphat.domain.use_case

import com.josphat.domain.model.RecipeInfo
import com.josphat.domain.repo.RecipeRepo
import kotlinx.coroutines.flow.Flow

class GetRecipeInfoUseCase(
    private val repo: RecipeRepo
) {

    suspend operator fun invoke(id : Int) : Flow<com.joel.common.utils.Result<RecipeInfo>> {
        return repo.getRecipeInformation(id)
    }
}