package com.best.free.master.chef.recipe.domain.use_case

import android.util.Log
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.data.dto.toDomain
import com.best.free.master.chef.recipe.domain.model.MealDetails
import com.best.free.master.chef.recipe.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(private val repository: MealRepository) {

    operator fun invoke(mealId: String): Flow<Resource<List<MealDetails?>?>> = flow {

        try {
            emit(Resource.Loading())
            val data = repository.getMealDetail(mealId)

            if (data.isSuccessful && data.body() != null) {
                val ml = data.body()!!.meals
                val mlItem = ml.map { it.toDomain() }
                emit(Resource.Success(mlItem))
            }

        } catch (e: Exception) {
            Log.d("Exception", "invoke: ${e.message}")
            emit(Resource.Error(e.message ?: "Some Error"))
        }

    }
}