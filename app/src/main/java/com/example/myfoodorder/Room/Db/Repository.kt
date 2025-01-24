package com.example.myfoodorder.Room.Db

import androidx.lifecycle.LiveData
import com.example.myfoodorder.data.Meal

class Repository(
    private val mealDao:MealDao
) {
    val mealList : LiveData<List<Meal>> = mealDao.getAllMeals()
    suspend fun insertFavoriteMeal(meal: Meal){
        return mealDao.upsert(meal)
    }
    suspend fun getMealById(mealId:String):Meal{
      return mealDao.getMealById(mealId)
    }
    suspend fun deleteMealById(id:String){
        return mealDao.deleteMealById(id)
    }
   // suspend fun deleteMeal(meal: Meal) = mealDao.deleteMeal(meal)
}