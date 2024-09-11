package com.best.free.master.chef.recipe.domain.model

import androidx.annotation.Keep

@Keep
data class MealItem(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = ""
)
