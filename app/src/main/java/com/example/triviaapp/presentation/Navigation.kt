package com.example.triviaapp.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triviaapp.presentation.game_screen.GameScreen
import com.example.triviaapp.presentation.game_screen.GameScreenViewModel
import com.example.triviaapp.presentation.primary_screen.PrimaryScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TriviaScreens.PrimaryScreen.name) {
        composable(TriviaScreens.PrimaryScreen.name) {
            PrimaryScreen(navController)
        }
        composable(TriviaScreens.GameScreen.name) {
            val viewModel = hiltViewModel<GameScreenViewModel>()
            GameScreen(navController, viewModel = viewModel)
        }
    }
}