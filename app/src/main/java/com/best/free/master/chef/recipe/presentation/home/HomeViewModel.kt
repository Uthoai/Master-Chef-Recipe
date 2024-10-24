package com.best.free.master.chef.recipe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.domain.use_case.GetMealDetailUseCase
import com.best.free.master.chef.recipe.domain.use_case.GetMealListUseCase
import com.best.free.master.chef.recipe.domain.use_case.GetRandomMealUseCase
import com.best.free.master.chef.recipe.presentation.details.DetailDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listOfMealUseCase: GetMealListUseCase,
    private val randomMealUseCase: GetRandomMealUseCase
) : ViewModel() {

    private val _homeMealDataState = MutableStateFlow(HomeDataState())
    val homeMealDataState: StateFlow<HomeDataState> get() = _homeMealDataState

    fun getMealData(mealCategoryName: String) {
        viewModelScope.launch {

            listOfMealUseCase.invoke(mealCategoryName).collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _homeMealDataState.value =
                            HomeDataState(error = response.message ?: "Some Error")
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

    private var _randomMeal = MutableStateFlow(DetailDataState())
    val randomMeal: StateFlow<DetailDataState> = _randomMeal

    init {
        getRandomMeal()
    }

    private fun getRandomMeal() {
        viewModelScope.launch {

            randomMealUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _randomMeal.value = DetailDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _randomMeal.value = DetailDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _randomMeal.value = DetailDataState(mealDetails = response.data)
                    }
                }
            }
        }
    }

}