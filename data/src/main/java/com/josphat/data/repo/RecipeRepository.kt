package com.josphat.data.repo

import com.joel.common.utils.Result
import com.josphat.data.mappers.toDomain
import com.josphat.data.mappers.toDomainInfo
import com.josphat.data.network.BandaskiService
import com.josphat.domain.model.Recipe
import com.josphat.domain.model.RecipeInfo
import com.josphat.domain.repo.RecipeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecipeRepository(
    private val service: BandaskiService
) : RecipeRepo {

    override suspend fun getRandomRecipes(): Flow<Result<List<Recipe>>> = flow {
        emit(Result.Loading())
        try {
            val response = service.getRandomRecipes()
            emit(Result.Success(response.recipes.map { it.toDomain() }))
        } catch ( e: HttpException){
            emit(Result.Error(errorMessage = e.localizedMessage ?: "An unexpected error occurred"))
        } catch ( e: IOException){
            emit(Result.Error(errorMessage = "Check your Internet Connection"))
        }
    }

    override suspend fun getRecipeInformation(id: Int): Flow<Result<RecipeInfo>> = flow {
        emit(Result.Loading())
        try {
            val response = service.getRecipeInformation(id = id)
            emit(Result.Success(response.toDomainInfo()))
        } catch ( e: HttpException){
            emit(Result.Error(errorMessage = e.localizedMessage ?: "An unexpected error occurred"))
        } catch ( e: IOException){
            emit(Result.Error(errorMessage = "Check your Internet Connection"))
        }
    }

}