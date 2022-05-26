package com.example.sampleitunessearch.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleitunessearch.R
import com.example.sampleitunessearch.model.ItunesModel
import com.example.sampleitunessearch.viewmodel.ContentDetailViewModel
import com.example.sampleitunessearch.viewmodel.ContentListViewModel
import kotlinx.android.synthetic.main.fragment_content_detail.*
import kotlinx.android.synthetic.main.fragment_search.*


class ContentDetailFragment : Fragment() {

    private lateinit var contentItemParametresi : ItunesModel
    private lateinit var viewModel: ContentDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        observeLiveData()
        /*arguments?.let {
            contentItemParametresi= com.example.sampleitunessearch.view.ContentDetailFragmentArgs.fromBundle(
                it
            ).contentItem
            println(contentItemParametresi)
        }
        navigatebutton2.setOnClickListener{
            val action=ContentDetailFragmentDirections.actionContentDetailToSearchFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun observeLiveData() {
        viewModel.contentItemLiveData.observe(viewLifecycleOwner, Observer { item ->
            item?.let {
                artistNameDetailText.text=it.artistName
                descriptionDetailText.text=it.description
            }
        })
    }
}