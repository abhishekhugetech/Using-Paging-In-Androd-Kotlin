package com.dictionary.paginglibrary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.dictionary.paginglibrary.data.AnswerDataSource
import com.dictionary.paginglibrary.data.AnswerDataSourceFactory
import com.dictionary.paginglibrary.model.Answers

class AnswerViewModel() : ViewModel(){

    var answerPagedList: LiveData<PagedList<Answers>>
//    var answerDataSource: LiveData<PageKeyedDataSource<Int, Answers>>

    init {
        val answerDataSourceFactory = AnswerDataSourceFactory()
//        answerDataSource = answerDataSourceFactory.answerLiveDataSource

        val config = (PagedList.Config.Builder())
            .setEnablePlaceholders(false)
            .setPageSize(AnswerDataSource.PAGE_SIZE)
            .build()

        answerPagedList = (LivePagedListBuilder(answerDataSourceFactory,config)).build()
    }


}