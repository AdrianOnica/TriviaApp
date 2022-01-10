package com.example.triviaapp.presentation.game_screen

import com.example.triviaapp.domain.model.QuestionItem

data class QuestionState(
    var succes:List<QuestionItem>? = null,
    var loading:Boolean = false,
    var error:String = ""
)
