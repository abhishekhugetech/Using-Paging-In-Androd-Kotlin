package com.dictionary.paginglibrary.data

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.dictionary.paginglibrary.api.ApiService
import com.dictionary.paginglibrary.api.StackOverFlowApi
import com.dictionary.paginglibrary.model.Answers
import com.dictionary.paginglibrary.model.response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnswerDataSource : PageKeyedDataSource<Int, Answers>(){

    companion object {
        val PAGE_SIZE = 50
        var FIRST_PAGE = 1
        val SITE_NAME = "stackoverflow"
        val TAG = this::class.java.simpleName
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Answers>) {
        val call = ApiService.build(StackOverFlowApi::class.java).getAnswers(pagesize = PAGE_SIZE)
        call.enqueue(object : Callback<response>{
            override fun onFailure(call: Call<response>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<response>, response: Response<response>) {
                if (response.isSuccessful){
                    callback.onResult(response.body()!!.items , null , FIRST_PAGE + 1)
                }else{
                    Log.i(TAG,response.errorBody().toString())
                }
            }
        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Answers>) {
        val call = ApiService.build(StackOverFlowApi::class.java).getAnswers(pagesize = PAGE_SIZE,page = params.key)
        call.enqueue(object : Callback<response>{
            override fun onFailure(call: Call<response>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<response>, response: Response<response>) {
                if (response.isSuccessful){
                    val key = if (response.body()!!.has_more) params.key + 1 else null
                    callback.onResult(response.body()!!.items , key)
                }else{
                    Log.i(TAG,response.errorBody().toString())
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Answers>) {
        val call = ApiService.build(StackOverFlowApi::class.java).getAnswers(pagesize = PAGE_SIZE,page = params.key)
        call.enqueue(object : Callback<response>{
            override fun onFailure(call: Call<response>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<response>, response: Response<response>) {
                if (response.isSuccessful){
                    val key = if (params.key > 1) params.key - 1 else null
                    callback.onResult(response.body()!!.items , key)
                }else{
                    Log.i(TAG,response.errorBody().toString())
                }
            }
        })
    }

}