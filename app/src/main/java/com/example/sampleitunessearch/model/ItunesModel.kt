package com.example.sampleitunessearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


data class ItunesModel(
    @SerializedName("wrapperType"            ) var wrapperType            : String? = null,
    @SerializedName("artistId"               ) var artistId               : Int?    = null,
    @SerializedName("collectionId"           ) var collectionId           : Int?    = null,
    @SerializedName("artistName"             ) var artistName             : String? = null,
    @SerializedName("collectionName"         ) var collectionName         : String? = null,
    @SerializedName("collectionCensoredName" ) var collectionCensoredName : String? = null,
    @SerializedName("artistViewUrl"          ) var artistViewUrl          : String? = null,
    @SerializedName("collectionViewUrl"      ) var collectionViewUrl      : String? = null,
    @SerializedName("artworkUrl60"           ) var artworkUrl60           : String? = null,
    @SerializedName("artworkUrl100"          ) var artworkUrl100          : String? = null,
    @SerializedName("collectionPrice"        ) var collectionPrice        : Double? = null,
    @SerializedName("collectionExplicitness" ) var collectionExplicitness : String? = null,
    @SerializedName("trackCount"             ) var trackCount             : Int?    = null,
    @SerializedName("copyright"              ) var copyright              : String? = null,
    @SerializedName("country"                ) var country                : String? = null,
    @SerializedName("currency"               ) var currency               : String? = null,
    @SerializedName("releaseDate"            ) var releaseDate            : String? = null,
    @SerializedName("primaryGenreName"       ) var primaryGenreName       : String? = null,
    @SerializedName("previewUrl"             ) var previewUrl             : String? = null,
    @SerializedName("description"            ) var description            : String? = null
)