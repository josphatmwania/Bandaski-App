package com.josphat.presentation.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.josphat.presentation.recipes.components.RecipeItem

@Composable
fun Recipes(
    navController : NavHostController,
    viewModel: RecipeViewModel = hiltViewModel()
){

    val state = viewModel.recipeState.value
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(state.recipes.size){index ->
                RecipeItem(
                    recipe = state.recipes[index],
                    onItemClick = {  game ->
                    }
                )
            }
        }
        if(state.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            viewModel.getRandomRecipes()
                        }
                    ){
                       Text("Retry")
                    }
                }
            }
        }
        if(state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}