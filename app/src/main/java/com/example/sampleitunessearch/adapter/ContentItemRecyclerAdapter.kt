package com.example.sampleitunessearch.adapter

import android.app.Activity
import android.content.Context
import com.example.sampleitunessearch.view.decoration.CircleTransform
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleitunessearch.R
import com.example.sampleitunessearch.databinding.ContentItemRecyclerRowBinding
import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.view.SearchFragmentDirections
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializationContext
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_item_recycler_row.view.*

class ContentItemRecyclerAdapter(val contentList:ArrayList<ItunesModel>):RecyclerView.Adapter<ContentItemRecyclerAdapter.ContentItemViewHolder>() {
    class ContentItemViewHolder(var view:ContentItemRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<ContentItemRecyclerRowBinding>(inflater,R.layout.content_item_recycler_row,parent,false)
        return ContentItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
        holder.view.itunesItem=contentList.get(position)
        val gson = Gson()
        holder.itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToContentDetail(
                gson.toJson(contentList.get(position))
            )
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return contentList.size
    }
    fun contentListUpdate(newList:List<ItunesModel>){
        contentList.clear()
        contentList.addAll(newList)
        notifyDataSetChanged()
    }


}
