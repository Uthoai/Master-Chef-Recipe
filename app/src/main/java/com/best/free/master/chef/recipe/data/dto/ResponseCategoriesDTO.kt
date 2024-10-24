package com.best.free.master.chef.recipe.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.best.free.master.chef.recipe.domain.model.MealCategories
import com.best.free.master.chef.recipe.domain.model.MealItem

@Keep
data class ResponseCategoriesDTO(
    @SerializedName("categories")
    val categories: List<Category?>? = null
){
    @Keep
    data class Category(
        @SerializedName("idCategory")
        val idCategory: String? = null,
        @SerializedName("strCategory")
        val strCategory: String? = null,
        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String? = null,
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String? = null
    )
}

fun ResponseCategoriesDTO.Category.toMealCategory(): MealCategories {
    return MealCategories(
        id = this.idCategory.toString(),
        name = this.strCategory.toString(),
        imageUrl = this.strCategoryThumb
            ?: "https://images.immediate.co.uk/production/volatile/sites/30/2013/05/Aubergine-and-sesame-noodles-6138de6.jpg?quality=90&resize=556,505"
    )
}