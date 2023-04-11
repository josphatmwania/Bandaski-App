package com.josphat.presentation.recipes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josphat.domain.use_case.GetRandomRecipesUseCase
import com.josphat.presentation.recipes.components.RecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.joel.common.utils.Result
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase
) : ViewModel() {


    private val _recipeState = mutableStateOf(RecipeState())
    val recipeState : State<RecipeState> = _recipeState

    init {
        getRandomRecipes()
    }

    fun getRandomRecipes(){
        viewModelScope.launch {
            getRandomRecipesUseCase().onEach { result ->
                when(result){
                    is Result.Success -> {
                        _recipeState.value = RecipeState(
                            recipes = result.data ?: emptyList()
                        )
                    }
                    is Result.Error -> {
                        _recipeState.value = RecipeState(
                            error = result.errorMessage ?: "An unexpected error occurred"
                        )
                    }
                    is Result.Loading -> {
                        _recipeState.value = RecipeState(
                            loading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}