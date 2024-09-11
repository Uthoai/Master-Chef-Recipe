package com.best.free.master.chef.recipe.data.remote

import com.best.free.master.chef.recipe.data.dto.ResponseMealDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("json/v1/1/filter.php")
    suspend fun getMealList(@Query("c") mealCategoryName: String): Response<ResponseMealDTO>

}