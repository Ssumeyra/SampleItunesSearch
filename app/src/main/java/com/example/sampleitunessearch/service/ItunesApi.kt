package com.example.sampleitunessearch.service

import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.model.SearchResultModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItunesApi {
    @GET("search")
    fun getContent(@Query("term") term:String,@Query("limit") limit:Int,@Query("offset") offset:Int):Single<SearchResultModel>
}