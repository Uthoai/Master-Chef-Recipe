package com.best.free.master.chef.recipe.domain.repository

import com.best.free.master.chef.recipe.data.dto.ResponseMealDTO
import retrofit2.Response

interface MealRepository {

    suspend fun getMealList(mealCategoryName: String): Response<ResponseMealDTO>

}