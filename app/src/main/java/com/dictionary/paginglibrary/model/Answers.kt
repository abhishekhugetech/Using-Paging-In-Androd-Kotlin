package com.dictionary.paginglibrary.model

data class Answers (
    var is_accepted : Boolean,
    var score:Int,
    var owner: Owner,
    var creation_date:Int,
    var answer_id:Int
)