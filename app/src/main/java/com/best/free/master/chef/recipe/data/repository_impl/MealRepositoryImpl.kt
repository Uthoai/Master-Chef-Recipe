package com.best.free.master.chef.recipe.data.repository_impl

import com.best.free.master.chef.recipe.data.dto.ResponseCategoriesDTO
import com.best.free.master.chef.recipe.data.dto.ResponseListOfMealByCategoryDTO
import com.best.free.master.chef.recipe.data.dto.ResponseMealDetailDTO
import com.best.free.master.chef.recipe.data.dto.ResponseSearchMealsDTO
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

    override suspend fun searchMeals(mealsName: String): Response<ResponseSearchMealsDTO> {
        val response = apiService.searchMeal(mealsName)
        return response
    }

    override suspend fun getRandomMeal(): Response<ResponseMealDetailDTO> {
        val response = apiService.getRandomMeal()
        return response
    }

    override suspend fun getMealCategories(): Response<ResponseCategoriesDTO> {
        val response = apiService.getMealCategories()
        return response
    }

}