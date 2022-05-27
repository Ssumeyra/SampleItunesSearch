package com.example.sampleitunessearch.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampleitunessearch.R
import com.example.sampleitunessearch.adapter.ContentItemRecyclerAdapter
import com.example.sampleitunessearch.view.decoration.Utility
import com.example.sampleitunessearch.viewmodel.ContentListViewModel
import kotlinx.android.synthetic.main.content_item_recycler_row.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private lateinit var viewModel:ContentListViewModel
    private val recyclerContentItemAdapter=ContentItemRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorMessageText.visibility=View.GONE
        progressbar.visibility=View.GONE
        viewModel=ViewModelProvider(this).get(ContentListViewModel::class.java)

        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val display: Display = wm.defaultDisplay

        println(display.width)
        recyclerView.layoutManager=GridLayoutManager(context,
            Utility.calculateNoOfColumns(requireContext(),180f))
        recyclerView.adapter=recyclerContentItemAdapter
        observeLiveData()

        search_button.setOnClickListener{
            progressbar.visibility=View.VISIBLE
            viewModel.refreshData(searchText.text.toString())
        }
    }
    private fun observeLiveData(){
        viewModel.contents.observe(viewLifecycleOwner, Observer {contents->
            contents?.let {
                recyclerView.visibility=View.VISIBLE
                emptyMessageText.visibility=View.GONE
                recyclerContentItemAdapter.contentListUpdate(contents)
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if(it){
                    errorMessageText.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                    emptyMessageText.visibility=View.GONE
                }
                else{
                    errorMessageText.visibility=View.GONE
                    emptyMessageText.visibility=View.GONE
                }
            }
        })
        viewModel.contentLoading.observe(viewLifecycleOwner, Observer {loading->
            loading?.let {
                if(it){
                    recyclerView.visibility=View.GONE
                    errorMessageText.visibility=View.GONE
                    progressbar.visibility=View.VISIBLE
                    emptyMessageText.visibility=View.GONE
                }
                else{
                    progressbar.visibility=View.GONE
                    emptyMessageText.visibility=View.GONE
                }
            }
        })
    }
}