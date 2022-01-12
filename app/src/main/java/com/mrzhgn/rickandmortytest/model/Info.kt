package com.mrzhgn.rickandmortytest.model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    var characterCount: Int,
    @SerializedName("pages")
    var pagesCount: Int,
    @SerializedName("next")
    var nextPage: String?,
    @SerializedName("prev")
    var previousPage: String?
)
