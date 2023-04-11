package com.josphat.presentation.recipes.components

import com.josphat.domain.model.Recipe

data class RecipeState(
    val recipes : List<Recipe> = emptyList(),
    val loading : Boolean = false,
    val error : String ? = null
)
