package com.best.free.master.chef.recipe.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.domain.use_case.GetMealDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getMealDetailUseCase: GetMealDetailUseCase) : ViewModel() {

    private var _mealDetails = MutableStateFlow(DetailDataState())
    val mealDetails: StateFlow<DetailDataState> = _mealDetails

    fun getMealDetail(mealID: String){
        viewModelScope.launch {

            getMealDetailUseCase.invoke(mealID).collect{response->
                when(response){
                    is Resource.Error -> {
                        _mealDetails.value = DetailDataState(error = response.message)
                    }
                    is Resource.Loading -> {
                        _mealDetails.value = DetailDataState(loading = true)
                    }
                    is Resource.Success -> {
                        _mealDetails.value = DetailDataState(mealDetails = response.data)
                    }
                }
            }
        }
    }
}