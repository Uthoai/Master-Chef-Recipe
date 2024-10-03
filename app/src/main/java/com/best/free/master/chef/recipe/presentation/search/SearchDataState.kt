package com.best.free.master.chef.recipe.presentation.search

import com.best.free.master.chef.recipe.domain.model.MealItem

data class SearchDataState(
    val loading: Boolean = false,
    val searchData: List<MealItem?>? = null,
    val error: String? = null
)
