package com.josphat.presentation.recipes.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.common.utils.HttpRoutes
import com.josphat.domain.use_case.GetRecipeInfoUseCase
import com.josphat.presentation.recipes.components.RecipeDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInfoViewModel @Inject constructor(
    private val getRecipeInfoUseCase: GetRecipeInfoUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(RecipeDetailsState())
    val state : State<RecipeDetailsState> = _state

    init {
        savedStateHandle.get<Int>(HttpRoutes.PARAM_GAME_ID.toString())?.let { id ->
            getRecipeInfo(id)
        }
    }

    private fun getRecipeInfo(id :Int) {
        viewModelScope.launch {
            getRecipeInfoUseCase(id).onEach { result ->
                when(result){
                    is com.joel.common.utils.Result.Error -> {
                        _state.value = RecipeDetailsState(
                            error = result.errorMessage ?: "An unexpected error occurred"
                        )
                    }
                    is com.joel.common.utils.Result.Loading -> {
                        _state.value = RecipeDetailsState(
                            isLoading = true
                        )
                    }
                    is com.joel.common.utils.Result.Success -> {
                        _state.value = RecipeDetailsState(
                            recipeInfo = result.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}