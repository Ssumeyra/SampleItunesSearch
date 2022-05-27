package com.example.sampleitunessearch.service

import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.model.SearchResultModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ItunesApiService {
    private val BASE_URL="https://itunes.apple.com/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ItunesApi::class.java)
    fun getData(term: String):Single<SearchResultModel>{
        return api.getContent(term,25,25)
    }
}