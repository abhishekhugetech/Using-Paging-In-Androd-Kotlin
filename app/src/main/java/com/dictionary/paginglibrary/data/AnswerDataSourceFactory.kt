package com.dictionary.paginglibrary.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.dictionary.paginglibrary.model.Answers

class AnswerDataSourceFactory : DataSource.Factory<Int, Answers>() {

    val answerLiveDataSource : MutableLiveData<PageKeyedDataSource<Int, Answers>> = MutableLiveData()

    override fun create(): DataSource<Int, Answers>? {
        val answerDataSource = AnswerDataSource()
        answerLiveDataSource.postValue(answerDataSource)
        return answerDataSource
    }
}
