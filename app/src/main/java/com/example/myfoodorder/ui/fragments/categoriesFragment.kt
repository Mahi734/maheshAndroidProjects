package com.example.myfoodorder.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide

import com.example.myfoodorder.R
import com.example.myfoodorder.adapters.CategoriesAdapter
import com.example.myfoodorder.data.Category
import com.example.myfoodorder.databinding.FragmentCategoriesBinding
import com.example.myfoodorder.viewModel.HomeViewModel

class categoriesFragment : Fragment() {

    private lateinit var binding : FragmentCategoriesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        categoriesAdapter = CategoriesAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategoriesLiveData()

    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner,
            { categories ->
                categoriesAdapter.setCategoryList(categories)
        }
        )
    }
    private fun prepareCategoriesRecyclerView() {
        binding.recylerViewOfCategoriesBinding.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        }

    }
}
