package com.josphat.data.mappers

import com.josphat.data.remote.RecipeDTO
import com.josphat.domain.model.RecipeInfo

fun RecipeDTO.toDomainInfo() : RecipeInfo {
    return RecipeInfo(
        title, image, id, summary
    )
}