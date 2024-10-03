package com.best.free.master.chef.recipe.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.free.master.chef.recipe.core.Resource
import com.best.free.master.chef.recipe.domain.use_case.GetMealsBySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: GetMealsBySearchUseCase) : ViewModel() {
    private val _searchDataState = MutableStateFlow(SearchDataState())
    val searchDataState: StateFlow<SearchDataState> get() = _searchDataState

    val searchString = MutableLiveData<String>()

    fun searchMeals(search: String){
        viewModelScope.launch {
            searchString.value?.let {
                useCase.invoke(it).collect{ response->
                    when(response){
                        is Resource.Error -> {
                            _searchDataState.value = SearchDataState(error = response.message ?: "Some Error")
                        }

                        is Resource.Loading -> {
                            _searchDataState.value = SearchDataState(loading = true)
                        }

                        is Resource.Success -> {
                            _searchDataState.value = SearchDataState(searchData = response.data)

                            Log.d("TAG12", "searchMeals: ${response.data} ")

                        }
                    }
                }
            }
        }
    }
}