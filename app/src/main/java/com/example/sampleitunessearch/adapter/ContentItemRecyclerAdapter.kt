package com.example.sampleitunessearch.adapter

import CircleTransform
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleitunessearch.R
import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.view.SearchFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_item_recycler_row.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class ContentItemRecyclerAdapter(val contentList:ArrayList<ItunesModel>):RecyclerView.Adapter<ContentItemRecyclerAdapter.ContentItemViewHolder>() {
    class ContentItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.content_item_recycler_row,parent,false)
        return ContentItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
        holder.itemView.artistName.text=contentList.get(position).artistName
        holder.itemView.description.text=contentList.get(position).description
        Picasso.get().load(contentList.get(position).artworkUrl100).transform( CircleTransform()).into(holder.itemView.itemImage)
        holder.itemView.setOnClickListener{
            val action=SearchFragmentDirections.actionSearchFragmentToContentDetail(contentList.get(position)
                .toString())
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