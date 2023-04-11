package com.josphat.data.di

import com.josphat.data.network.BandaskiService
import com.josphat.data.repo.RecipeRepository
import com.josphat.domain.repo.RecipeRepo
import com.josphat.domain.use_case.GetRandomRecipesUseCase
import com.josphat.domain.use_case.GetRecipeInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(
        service: BandaskiService
    ) : RecipeRepo {
        return RecipeRepository(service)
    }

    @Provides
    @Singleton
    fun providesGetRecipeUseCase( repo: RecipeRepo) : GetRandomRecipesUseCase{
        return GetRandomRecipesUseCase(repo)
    }

    @Provides
    @Singleton
    fun providesGetRecipeInfoUseCase(
        repo: RecipeRepo
    ) : GetRecipeInfoUseCase{
        return GetRecipeInfoUseCase(repo)
    }
}