package com.example.triviaapp.data.repository

import com.example.triviaapp.data.network.TriviaApi
import com.example.triviaapp.domain.model.Question
import com.example.triviaapp.domain.model.QuestionItem
import com.example.triviaapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TriviaRepository @Inject constructor(private val api: TriviaApi) {
    val resource = Resource<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getQuestion(): Resource<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            resource.loading = true
            resource.data = api.getQuestions()

            if (resource.data.toString().isNotEmpty()) {
                resource.loading = false
            }
        } catch (e: Exception) {
            resource.exception = e
        }
        return resource
    }
}