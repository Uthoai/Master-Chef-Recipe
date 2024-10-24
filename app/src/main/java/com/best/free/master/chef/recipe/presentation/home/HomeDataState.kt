package com.best.free.master.chef.recipe.presentation.home

import com.best.free.master.chef.recipe.domain.model.MealCategories
import com.best.free.master.chef.recipe.domain.model.MealItem

data class HomeDataState(
    val loading: Boolean = false,
    val homeData: List<MealItem?>? = null,
    val error: String? = null
)

data class CategoryDataState(
    val loading: Boolean = false,
    val categoryData: List<MealCategories?>? = null,
    val error: String? = null
)