package com.example.myfoodorder.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myfoodorder.adapters.CategoriesAdapter
import com.example.myfoodorder.adapters.MostPopularAdapter
import com.example.myfoodorder.data.Category
import com.example.myfoodorder.data.MealsByCategory
import com.example.myfoodorder.data.Meal
import com.example.myfoodorder.databinding.FragmentHomeBinding
import com.example.myfoodorder.ui.activites.CategoryMealsActivity
import com.example.myfoodorder.ui.activites.MealActivity
import com.example.myfoodorder.viewModel.HomeViewModel
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val MEAL_ID = "com.example.myfoodorder.ui.fragments.idMeal"
        const val MEAL_NAME = "com.example.myfoodorder.ui.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.myfoodorder.ui.fragments.thumbMeal"
        const val CATEGORY_NAME = "com.example.myfoodoreder.ui.fragments.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
        popularItemAdapter = MostPopularAdapter()
        categoriesAdapter = CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        //  return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observerRandomMeal()
        //when click on random meal
        onRandomMealClick()
        //for poular items
        preparePopularItemsRecyclerView()
        homeMvvm.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()
        //category for
        prepareCategoriesRecyclerView()
        homeMvvm.getCategories()
        observeCategoriesLiveData()
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }


    //category recyler view
    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.recyclerViewBinding.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }

    //observe category
    private fun observeCategoriesLiveData() {
        homeMvvm.observeCategoriesLiveData().observe(
            (viewLifecycleOwner),
            { categories ->
                categories?.let {
                    categoriesAdapter.setCategoryList(
                        it
                    )
                }
            }
        )
    }

    //clicking on popular on click
    private fun onPopularItemClick() {
        popularItemAdapter.onItemClick = {
            meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    //Recycler view for popular item s
    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.
        apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemAdapter
        }
    }

    //observe live data of poular Live data
    private fun observePopularItemsLiveData() {
        homeMvvm.observePopularItemsLiveData().observe(viewLifecycleOwner, { mealList ->
            mealList?.let {
                popularItemAdapter.setMeals(
                    mealsList = mealList as ArrayList<MealsByCategory>
                )
            } ?: run {
                Log.e("dd", "mealList null or emtpy")
            }
        })
    }

    //click on the main card of random meal
    private fun onRandomMealClick() {
        binding.randomMeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }
    //    private fun observerRandomMeal() {
//
//        homeMvvm.observeRandomMealLivedata().observe(
//            viewLifecycleOwner,object:Observer<Meal>{
//                override fun onChanged(value: Meal) {
//                    Glide.with(this@HomeFragment).
//                    load(value.strMealThumb).into(binding.imgRandomMeal)
//                }
//            })
//        }

    //pbserver random mela daata
    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLivedata().observe(
            viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment).load(meal.strMealThumb).into(binding.imgRandomMeal)
                this.randomMeal = meal
            })

    }


}



























































