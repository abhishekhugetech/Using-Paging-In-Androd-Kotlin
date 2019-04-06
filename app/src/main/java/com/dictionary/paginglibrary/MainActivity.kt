package com.dictionary.paginglibrary

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dictionary.paginglibrary.adapter.AnswersAdapter
import com.dictionary.paginglibrary.model.Answers
import com.dictionary.paginglibrary.viewmodel.AnswerViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(AnswerViewModel::class.java)

        val adapter = AnswersAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.answerPagedList.observe(this,
            Observer<PagedList<Answers>> {
                    t -> adapter.submitList(t)
            })
    }
}
