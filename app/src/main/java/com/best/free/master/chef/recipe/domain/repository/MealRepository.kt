package com.best.free.master.chef.recipe.domain.repository

import com.best.free.master.chef.recipe.data.dto.ResponseListOfMealByCategoryDTO
import com.best.free.master.chef.recipe.data.dto.ResponseMealDetailDTO
import retrofit2.Response

interface MealRepository {

    suspend fun getMealList(mealCategoryName: String): Response<ResponseListOfMealByCategoryDTO>

    suspend fun getMealDetail(mealID: String): Response<ResponseMealDetailDTO>

}