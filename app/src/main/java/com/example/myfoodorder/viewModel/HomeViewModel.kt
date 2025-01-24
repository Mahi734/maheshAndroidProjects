package com.example.myfoodorder.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodorder.Room.RetrofitApi.RetrofitInstance
import com.example.myfoodorder.data.Category
import com.example.myfoodorder.data.CategoryList
import com.example.myfoodorder.data.MealsByCategoryList
import com.example.myfoodorder.data.MealsByCategory
import com.example.myfoodorder.data.Meal
import com.example.myfoodorder.data.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()

//for random meal data
    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }

    //for popular items

    fun getPopularItems() {
        RetrofitInstance.api.getPopularItems("Chicken")
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                    if (response.body() != null) {
                        popularItemLiveData.value = response.body()!!.meals
                    } else {
                        return
                    }
                }
                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {

                }
            }
            )
    }

    fun observePopularItemsLiveData(): LiveData<List<MealsByCategory>> {
        return popularItemLiveData
    }

    //get categories of all products

    //its funcation running fine
    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
                    categoriesLiveData.value = response.body()!!.categories
                }
                else
                {
                    return
                }
            }
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("HomeViewModel", t.message.toString())
            }
        })
    }

    fun observeCategoriesLiveData(): LiveData<List<Category>>
    {
        return categoriesLiveData
    }
}