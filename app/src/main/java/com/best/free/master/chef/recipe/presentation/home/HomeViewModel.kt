package com.best.free.master.chef.recipe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.domain.use_case.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetMealListUseCase): ViewModel() {

    private val _homeMealDataState = MutableStateFlow(HomeDataState())
    val homeMealDataState: StateFlow<HomeDataState> get() = _homeMealDataState

    fun getMealData(mealCategoryName: String) {
        viewModelScope.launch {

            useCase.invoke(mealCategoryName).collect{response->
                when(response){
                    is Resource.Error -> {
                        _homeMealDataState.value = HomeDataState(error = response.message ?: "Some Error")
                    }
                    is Resource.Loading -> {
                        _homeMealDataState.value = HomeDataState(loading = true)
                    }
                    is Resource.Success -> {
                        _homeMealDataState.value = HomeDataState(homeData = response.data)

                    }
                }
            }
        }
    }
}