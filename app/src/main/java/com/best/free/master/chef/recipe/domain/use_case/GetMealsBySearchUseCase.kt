package com.best.free.master.chef.recipe.domain.use_case

import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.data.dto.toMealItem
import com.best.free.master.chef.recipe.data.repository_impl.MealRepositoryImpl
import com.best.free.master.chef.recipe.domain.model.MealItem
import com.best.free.master.chef.recipe.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealsBySearchUseCase @Inject constructor(private val repository: MealRepository) {

    operator fun invoke(search: String): Flow<Resource<List<MealItem?>?>> = flow{
        try {
            emit(Resource.Loading())
            val data = repository.searchMeals(search)
            if (data.isSuccessful && data.body() != null){
                val ml = data.body()!!.meals
                val mlItem = ml?.map { it?.toMealItem() }

                emit(Resource.Success(mlItem))
            }
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "Some Error"))
        }
    }
}