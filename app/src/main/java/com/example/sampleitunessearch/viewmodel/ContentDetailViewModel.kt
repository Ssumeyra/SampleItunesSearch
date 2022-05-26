package com.example.sampleitunessearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleitunessearch.model.ItunesModel

class ContentDetailViewModel:ViewModel() {
    val contentItemLiveData=MutableLiveData<ItunesModel>()
}