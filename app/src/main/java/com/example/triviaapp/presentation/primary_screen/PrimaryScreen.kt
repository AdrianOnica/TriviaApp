package com.example.triviaapp.presentation.primary_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.triviaapp.domain.utils.AppColors
import com.example.triviaapp.presentation.TriviaScreens

@Composable
fun PrimaryScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.DarkPurple
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Trivia App",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxHeight(0.3f),
                fontFamily = FontFamily.Monospace
            )
            Button(
                modifier = Modifier.padding(15.dp).size(125.dp,80.dp),
                shape = RoundedCornerShape(15.dp),

                onClick = { navController.navigate(TriviaScreens.GameScreen.name) }) {
                Text(text = "Play now")
            }

        }

    }
}