package com.dictionary.paginglibrary.model

data class response(var items:ArrayList<Answers>,
                    val quota_max : Int,
                    var has_more: Boolean)