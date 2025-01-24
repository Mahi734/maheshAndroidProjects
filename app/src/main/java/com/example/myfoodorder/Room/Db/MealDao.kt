package com.example.myfoodorder.Room.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myfoodorder.data.Meal

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(meal: Meal)

    @Update
    fun updateFavorite(meal: Meal)
//
    @Query("SELECT * FROM mealinformation")
    fun getAllMeals(): LiveData<List<Meal>>

    @Query("SELECT * FROM mealinformation WHERE idMeal =:id")
    fun getMealById(id: String): Meal
//
    @Query("DELETE FROM mealinformation WHERE idMeal =:id")
    fun deleteMealById(id: String)

    @Delete
    suspend fun delete(meal: Meal)

}