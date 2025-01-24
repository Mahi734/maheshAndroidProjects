package com.example.myfoodorder.Room.RetrofitApi

import com.example.myfoodorder.data.CategoryList
import com.example.myfoodorder.data.MealsByCategoryList
import com.example.myfoodorder.data.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
        @GET("categories.php")
        fun getCategories(): Call<CategoryList>
//
        @GET("filter.php?")
        fun getPopularItems(@Query("i") category:String):Call<MealsByCategoryList>

//        @GET ("random.php")
//        fun getRandomMeal():Call<RandomMealResponse>
//
        @GET("lookup.php?")
        fun getMealById(@Query("i") id:String):Call<MealList>
//
//        @GET("search.php?")
//        fun getMealByName(@Query("s") s:String):Call<RandomMealResponse>
    @GET ("random.php")
     fun getRandomMeal():Call<MealList>


    @GET("filter.php?")
    fun getMealByCategory(@Query("c") category:String):Call<MealsByCategoryList>


}
