package com.josphat.data.mappers

import com.josphat.data.remote.RecipeDTO
import com.josphat.domain.model.Recipe

fun RecipeDTO.toDomain() : Recipe{
    return Recipe(
        title, image, id
    )
}