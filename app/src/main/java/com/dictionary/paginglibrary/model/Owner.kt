package com.dictionary.paginglibrary.model

import com.google.gson.annotations.SerializedName

data class Owner (
    var reputation:Int,
    var user_type :String,
    @SerializedName("profile_image")
    var profileImage:String,
    var display_name:String,
    var link:String
)