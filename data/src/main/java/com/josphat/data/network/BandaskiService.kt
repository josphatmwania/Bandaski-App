package com.josphat.data.network

import com.joel.common.utils.HttpRoutes
import com.josphat.data.remote.RecipeDTO
import com.josphat.data.remote.RecipeResponse
import com.josphat.domain.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BandaskiService {


    @GET("/recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = HttpRoutes.API_KEY,
    ) : RecipeResponse

    suspend fun getRecipeInformation(
        @Query("apiKey") apiKey: String = HttpRoutes.API_KEY,
        @Path("id") id : Int
    ) : RecipeDTO


}