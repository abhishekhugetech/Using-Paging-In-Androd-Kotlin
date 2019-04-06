package com.dictionary.paginglibrary.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    val inter = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(inter)
        .build()

    val BASE_URL = "https://api.stackexchange.com/2.2/"

    val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()



    fun <T> build(service:Class<T>):T{
        return retrofit.create(service)
    }

}