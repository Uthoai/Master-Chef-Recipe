package com.best.free.master.chef.recipe.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.domain.model.MealItem
import com.best.free.master.chef.recipe.domain.use_case.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetMealListUseCase): ViewModel() {

    private val _mealList = MutableLiveData<List<MealItem?>>()
    val mealList: LiveData<List<MealItem?>> = _mealList

    fun getMealData(mealCategoryName: String) {
        viewModelScope.launch {

            useCase.invoke(mealCategoryName).collect{response->
                when(response){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let { meals ->
                            _mealList.value = meals
                            Log.d("Tag1","${mealList.value}")
                        }
                        //Log.d("Tag1","${response.data}")
                    }
                }
            }
        }
    }
}