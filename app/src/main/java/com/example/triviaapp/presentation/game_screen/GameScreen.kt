package com.example.triviaapp.presentation.game_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.triviaapp.domain.model.QuestionItem
import com.example.triviaapp.domain.utils.AppColors
import kotlinx.coroutines.launch


@Composable
fun GameScreen(navController: NavController,viewModel: GameScreenViewModel) {
    val questionIndex = remember {
        mutableStateOf<Int>(0)
    }
    val listOfQuestions = viewModel.state.value.succes?.toMutableList()
    if (viewModel.state.value.loading == true) {
        CircularProgressIndicator()
    } else {
        val question = listOfQuestions?.get(questionIndex.value)
        if (listOfQuestions != null) {
            QuestionDisplay(
                question = question!!,
                questionIndex = questionIndex,
                viewModel = viewModel
            ) {
                questionIndex.value++
            }
        }
    }
}


@Composable
fun QuestionDisplay(
    question: QuestionItem,
    viewModel: GameScreenViewModel,
    questionIndex: MutableState<Int>,
    onNext: (Int) -> Unit
) {
    val snackbarState = remember{
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean>(false)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = question.choices[it] == question.answer
        }

    }

    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.DarkPurple) {
     
            Column(modifier = Modifier.padding(17.dp)) {
                Text(
                    text = "Question ${questionIndex.value}/${viewModel.state.value.succes?.size}",
                    color = AppColors.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    Text(
                        text = "${question.question}",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxHeight(0.3f),
                        color = AppColors.OffWhite
                    )
                    question.choices.forEachIndexed { index, answer ->
                        Row(
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth()
                                .height(45.dp)
                                .border(
                                    4.dp,
                                    Brush.linearGradient(
                                        colors = listOf(
                                            AppColors.OffDarkPurple,
                                            AppColors.OffDarkPurple
                                        )
                                    ), shape = RoundedCornerShape(15.dp)
                                )
                                .background(Color.Transparent)
                        ) {
                            RadioButton(
                                selected = answerState.value == index,
                                onClick = { updateAnswer(index) },
                                modifier = Modifier.padding(15.dp),
                                colors = RadioButtonDefaults.colors(selectedColor = Color.Green)
                            )
                            Text(text = answer,modifier = Modifier.padding(6.dp), fontSize = 20.sp, color = AppColors.LightGray )
                        }


                    }
                    Button(
                        onClick = {
                            if (correctAnswerState.value) onNext(questionIndex.value)
                            else {

                                    scope.launch { snackbarState.showSnackbar(message =  "Oops! Wrong answer! Try again!:D", duration = SnackbarDuration.Short)
                                     }

                            }},
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Text(text = "Next", fontSize = 17.sp)
                    }
                Box(modifier = Modifier.fillMaxSize(), Alignment.BottomCenter){
                SnackbarHost(hostState = snackbarState)
                }
                
            }
        }
    }
}

