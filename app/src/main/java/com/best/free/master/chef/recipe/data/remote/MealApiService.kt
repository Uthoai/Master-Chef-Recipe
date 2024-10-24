package com.best.free.master.chef.recipe.data.remote

import com.best.free.master.chef.recipe.data.dto.ResponseCategoriesDTO
import com.best.free.master.chef.recipe.data.dto.ResponseListOfMealByCategoryDTO
import com.best.free.master.chef.recipe.data.dto.ResponseMealDetailDTO
import com.best.free.master.chef.recipe.data.dto.ResponseSearchMealsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("filter.php")
    suspend fun getMealList(@Query("c") mealCategoryName: String): Response<ResponseListOfMealByCategoryDTO>

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealId: String): Response<ResponseMealDetailDTO>

    @GET("search.php")
    suspend fun searchMeal(@Query("s") searchMealName: String): Response<ResponseSearchMealsDTO>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<ResponseMealDetailDTO>

    @GET("categories.php")
    suspend fun getMealCategories(): Response<ResponseCategoriesDTO>

}