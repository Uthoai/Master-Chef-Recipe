package com.best.free.master.chef.recipe.data.remote

import com.best.free.master.chef.recipe.data.dto.ResponseListOfMealByCategoryDTO
import com.best.free.master.chef.recipe.data.dto.ResponseMealDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("filter.php")
    suspend fun getMealList(@Query("c") mealCategoryName: String): Response<ResponseListOfMealByCategoryDTO>

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealId: String): Response<ResponseMealDetailDTO>

}