package com.best.free.master.chef.recipe.presentation.details

import com.best.free.master.chef.recipe.domain.model.MealDetails

data class DetailDataState(
    val loading: Boolean = false,
    val mealDetails: List<MealDetails?>? = null,
    val error: String? = null
)
