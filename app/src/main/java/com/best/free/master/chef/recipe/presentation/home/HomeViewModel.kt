package com.best.free.master.chef.recipe.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.domain.use_case.GetMealCategoriesUseCase
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
    private val randomMealUseCase: GetRandomMealUseCase,
    private val getMealCategoriesUseCase: GetMealCategoriesUseCase
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

    private var _randomMealState = MutableStateFlow(DetailDataState())
    val randomMealState: StateFlow<DetailDataState> = _randomMealState

    init {
        getRandomMeal()
    }

    private fun getRandomMeal() {
        viewModelScope.launch {

            randomMealUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _randomMealState.value = DetailDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _randomMealState.value = DetailDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _randomMealState.value = DetailDataState(mealDetails = response.data)
                    }
                }
            }
        }
    }

    private val _categoryDataState = MutableStateFlow(CategoryDataState())
    val categoryDataState: StateFlow<CategoryDataState> get() = _categoryDataState

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getMealCategoriesUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _categoryDataState.value =
                            CategoryDataState(error = response.message ?: "Some Error")
                    }
                    is Resource.Loading -> {
                        _categoryDataState.value = CategoryDataState(loading = true)
                    }
                    is Resource.Success -> {
                        _categoryDataState.value = CategoryDataState(categoryData = response.data)

                    }
                }
            }
        }
    }

}