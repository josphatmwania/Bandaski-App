package com.josphat.presentation.recipes.components

import com.josphat.domain.model.RecipeInfo

data class RecipeDetailsState(
    val isLoading : Boolean = false,
    val recipeInfo: RecipeInfo? = null,
    val error : String = ""
)
