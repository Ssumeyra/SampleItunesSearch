package com.example.sampleitunessearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.model.SearchResultModel
import com.example.sampleitunessearch.model.SectionModel
import com.example.sampleitunessearch.service.ItunesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ContentListViewModel:ViewModel() {
    val contents=MutableLiveData<List<ItunesModel>>()
    val contentsSectionList=MutableLiveData<List<SectionModel>>()
    val errorMessage=MutableLiveData<Boolean>()
    val emptyMessage=MutableLiveData<Boolean>()
    val contentLoading=MutableLiveData<Boolean>()

    private val itunesApiService=ItunesApiService()
    private val disposable=CompositeDisposable()

    fun refreshData(term: String){
        getItunesData(term)
    }
    private fun getItunesData(term: String){
        contentLoading.value=true
        disposable.add(
            itunesApiService.getData(term)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<SearchResultModel>(){
                    override fun onSuccess(t: SearchResultModel) {
                        val response=t.resultModels as List<ItunesModel>
                        val result = response.groupBy { it.wrapperType }
                        val hMap: MutableMap<String, List<ItunesModel>> = HashMap()
                        for(s in result) {
                            val key= s.key
                            val value=s.value
                            hMap[key!!]=value
                        }
                        var list=mutableListOf<SectionModel>()
                        for(s in hMap) {
                            val i=0
                            list.add(SectionModel(title = s.key,itemList = s.value))
                        }
                        contentsSectionList.value=list
                        contents.value=t.resultModels as List<ItunesModel>
                        errorMessage.value=false
                        emptyMessage.value = contents.value!!.size <= 0
                        contentLoading.value=false
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value=true
                        emptyMessage.value=false
                        contentLoading.value=false
                        e.printStackTrace()
                    }

                })
        )
    }
}