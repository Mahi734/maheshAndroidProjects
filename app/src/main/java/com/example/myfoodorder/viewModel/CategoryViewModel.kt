package com.example.myfoodorder.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodorder.Room.RetrofitApi.RetrofitInstance
import com.example.myfoodorder.data.CategoryList
import com.example.myfoodorder.data.MealsByCategory
import com.example.myfoodorder.data.MealsByCategoryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel: ViewModel() {

    val mealLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName:String)
    {
        RetrofitInstance.api.getMealByCategory(categoryName).enqueue(
            object: Callback<MealsByCategoryList>{
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                    if(response.body()!=null)
                    {
//                        mealLiveData.value = response.body()!!.meals
                        Log.d("CategoryMealViews",response.body()!!.meals.toString())
                    }
                }
                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.e("CategoryMealViews",t.message.toString())
                }
            }
        )
    }
    fun observeMealLiveData():LiveData<List<MealsByCategory>>
    {
        return mealLiveData
    }
}