package com.example.sampleitunessearch.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.sampleitunessearch.R
import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.view.decoration.getImage
import com.example.sampleitunessearch.view.decoration.setHtmlTextValue
import com.example.sampleitunessearch.viewmodel.ContentDetailViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_content_detail.*


class ContentDetailFragment : Fragment() {

    //private lateinit var contentItemParameter : ItunesModel
    private lateinit var contentItemParameter : String
    private lateinit var viewModel: ContentDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var actionbar=(activity as AppCompatActivity?)!!.supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true);
        arguments?.let {
            contentItemParameter = ContentDetailFragmentArgs.fromBundle(
                it
            ).contentItem
            println(contentItemParameter)
            val gson = Gson()
            val item = gson.fromJson(contentItemParameter, ItunesModel::class.java)

            actionbar.title = item.artistName
            actionbar.setDisplayHomeAsUpEnabled(false)

        }
        /*var actionbar=(activity as AppCompatActivity?)!!.supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true);

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action=ContentDetailFragmentDirections.actionContentDetailToSearchFragment()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ContentDetailViewModel::class.java)
        //viewModel.contentItemLiveData
       arguments?.let {
           contentItemParameter= ContentDetailFragmentArgs.fromBundle(
                it
            ).contentItem
           println(contentItemParameter)
           val gson = Gson()
           val item = gson.fromJson(contentItemParameter, ItunesModel::class.java)
           println(item)
           artistNameDetailText.text=item.artistName
           descriptionDetailText.text=item.description
           collectionName.text=item.collectionName
           country.text=item.country
           price.text=item.collectionPrice.toString()
           getImage(itemDetailImage,item.artworkUrl100)
           setHtmlTextValue(descriptionDetailText,item.description)
        }

         /*navigatebutton2.setOnClickListener{
            val action=ContentDetailFragmentDirections.actionContentDetailToSearchFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

}