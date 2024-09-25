package com.best.free.master.chef.recipe.data.repository_impl

import com.best.free.master.chef.recipe.data.dto.ResponseListOfMealByCategoryDTO
import com.best.free.master.chef.recipe.data.dto.ResponseMealDetailDTO
import com.best.free.master.chef.recipe.data.remote.MealApiService
import com.best.free.master.chef.recipe.domain.repository.MealRepository
import retrofit2.Response
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val apiService: MealApiService
): MealRepository {

    override suspend fun getMealList(mealCategoryName: String): Response<ResponseListOfMealByCategoryDTO> {
        val response = apiService.getMealList(mealCategoryName)
        return response
    }

    override suspend fun getMealDetail(mealID: String): Response<ResponseMealDetailDTO> {
        val response = apiService.getMealDetail(mealID)
        return response
    }

}