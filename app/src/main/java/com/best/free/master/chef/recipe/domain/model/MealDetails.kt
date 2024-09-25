package com.best.free.master.chef.recipe.domain.model

data class MealDetails(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnailUrl: String,
    val tags: List<String>,
    val youtubeUrl: String,
    val ingredients: List<String>,
    val measures: List<String>
)
