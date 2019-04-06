package com.dictionary.paginglibrary.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dictionary.paginglibrary.R
import com.dictionary.paginglibrary.model.Answers
import kotlinx.android.synthetic.main.single_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class AnswersAdapter(context:Context) : PagedListAdapter<Answers, AnswersAdapter.AnswersViewHolder>(
    DIFF_CALLBACK
) {

    val ctx = context

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Answers>(){
            override fun areContentsTheSame(p0: Answers, p1: Answers): Boolean {
                return p0.answer_id == p1.answer_id
            }

            override fun areItemsTheSame(p0: Answers, p1: Answers): Boolean {
                return p1 == p0
            }
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AnswersViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.single_item,p0,false)
        return AnswersViewHolder(view)
    }

    override fun onBindViewHolder(p0: AnswersViewHolder, p1: Int) {
        val answers = getItem(p1)
        if (answers != null) {
            p0.itemView.position.text = "${p1+1}"
            p0.itemView.userName.text = answers.owner.display_name
            p0.itemView.lastSeen.text = "Last Seen : ${intTimeToDate(answers.creation_date)}"
            Glide.with(ctx)
                .load(answers.owner.profileImage)
                .into(p0.itemView.userImage)
            p0.itemView.reputation.text = "Reputation : ${answers.owner.reputation}"
            p0.itemView.userType.text = answers.owner.user_type
        }
    }
    fun intTimeToDate(intDate:Int):String{
        val originalFormat = SimpleDateFormat("yyyyMMdd", Locale.CHINA)
        val date = originalFormat.parse(intDate.toString())
        return date.toString()
    }

    class AnswersViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)


}