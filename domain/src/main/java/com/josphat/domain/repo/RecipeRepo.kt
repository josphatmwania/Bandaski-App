package com.josphat.domain.repo

import com.josphat.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import com.joel.common.utils.Result
import com.josphat.domain.model.RecipeInfo

interface RecipeRepo {

    suspend fun getRandomRecipes() : Flow<Result<List<Recipe>>>

    suspend fun getRecipeInformation(id : Int) : Flow<Result<RecipeInfo>>

}