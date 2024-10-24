package com.best.free.master.chef.recipe.domain.use_case

import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.data.dto.toMealCategory
import com.best.free.master.chef.recipe.domain.model.MealCategories
import com.best.free.master.chef.recipe.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(private val repository: MealRepository) {

    operator fun invoke(): Flow<Resource<List<MealCategories?>?>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealCategories()

            if (data.isSuccessful && data.body() != null){
                val categories = data.body()!!.categories
                val mlItem = categories?.map { it?.toMealCategory() }

                emit(Resource.Success(mlItem))
            }

        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "Some Error"))
        }
    }


}