package com.best.free.master.chef.recipe.data.repository_impl

import com.best.free.master.chef.recipe.data.dto.ResponseMealDTO
import com.best.free.master.chef.recipe.data.remote.MealApiService
import com.best.free.master.chef.recipe.domain.repository.MealRepository
import retrofit2.Response
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val apiService: MealApiService
): MealRepository {

    override suspend fun getMealList(mealCategoryName: String): Response<ResponseMealDTO> {
        val response = apiService.getMealList(mealCategoryName)
        return response
    }

}