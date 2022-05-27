package com.example.sampleitunessearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResultModel (
    @SerializedName("resultCount")
    @Expose
    var resultCount :Int,

    @SerializedName("results")
    @Expose
    var resultModels: List<ItunesModel>? = null
)