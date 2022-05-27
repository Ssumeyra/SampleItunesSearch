package com.example.sampleitunessearch.model

import com.google.gson.annotations.SerializedName

data class SectionModel (
        @SerializedName("title"            ) var title            : String? = "",
        @SerializedName("item"               ) var itemList               : List<ItunesModel>?    = null,
)