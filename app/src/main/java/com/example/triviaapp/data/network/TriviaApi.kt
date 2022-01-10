package com.example.triviaapp.data.network

import com.example.triviaapp.domain.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TriviaApi {

    @GET("world.json")
    suspend fun getQuestions():Question
}