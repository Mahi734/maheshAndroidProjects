package com.example.myfoodorder.ui.activites

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myfoodorder.databinding.ActivityCategoryMealsBinding
import com.example.myfoodorder.ui.fragments.HomeFragment
import com.example.myfoodorder.viewModel.CategoryViewModel

class CategoryMealsActivity : AppCompatActivity() {

     lateinit var binding: ActivityCategoryMealsBinding
     lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        categoryViewModel = ViewModelProviders.of(this)[CategoryViewModel::class.java]

        categoryViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryViewModel.observeMealLiveData().observe(
            this, Observer{
                mealsList->
                mealsList.forEach{
                    Log.d("test",it.strMeal)
                }
            }
        )



    }

}