package com.example.triviaapp.presentation.game_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.data.repository.TriviaRepository
import com.example.triviaapp.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor (private val repository: TriviaRepository): ViewModel() {
    private val _state = mutableStateOf<QuestionState>(QuestionState())
    val state:State<QuestionState> =  _state

    init{
        getQuestions()
    }

    fun getQuestions() {
        viewModelScope.launch {
            val response = repository.getQuestions()
            when(response){
                is Resource.Succes ->{
                _state.value = QuestionState(response.data)
                }
                is Resource.Loading ->{
                    _state.value = QuestionState(loading = true)
                }
                is Resource.Error ->{
                    _state.value = QuestionState(error =  "An error occured")
                }
            }
        }
    }
}