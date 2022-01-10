package com.example.triviaapp.presentation.game_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.data.repository.TriviaRepository
import com.example.triviaapp.domain.model.QuestionItem
import com.example.triviaapp.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor (private val repository: TriviaRepository): ViewModel() {
   private val _state: MutableState<Resource<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(Resource(null, true, Exception("")))
    val state: State<Resource<ArrayList<QuestionItem>, Boolean, Exception>> = _state


    init {
        getQuestion()
    }

    private fun getQuestion(){
        viewModelScope.launch {
            _state.value.loading = true
            _state.value = repository.getQuestion()
            if(_state.value.data!!.isNotEmpty()){
                _state.value.loading = false
            }

        }
    }
    fun getSize():Int{
        return _state.value.data?.toMutableList()?.size!!
    }
}