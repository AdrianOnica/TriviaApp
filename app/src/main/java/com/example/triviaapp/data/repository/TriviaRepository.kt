package com.example.triviaapp.data.repository

import com.example.triviaapp.data.network.TriviaApi
import com.example.triviaapp.domain.model.QuestionItem
import com.example.triviaapp.domain.utils.Resource
import javax.inject.Inject

class TriviaRepository @Inject constructor(private val api: TriviaApi) {
    suspend fun getQuestions():Resource<List<QuestionItem>>{
           val response = try{
               api.getQuestions()
            }catch (e:Exception){
                return Resource.Error("An error occured")
            }
            return Resource.Succes(response)
    }
}