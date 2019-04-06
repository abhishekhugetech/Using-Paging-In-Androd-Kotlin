package com.dictionary.paginglibrary.api

import com.dictionary.paginglibrary.model.response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowApi {

    @GET("answers")
    fun getAnswers(
        @Query("page") page:Int? = 1,
        @Query("pagesize") pagesize:Int = 50,
        @Query("site") site:String = "stackoverflow"
    ):Call<response>

}