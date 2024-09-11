package com.best.free.master.chef.recipe.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.best.free.master.chef.recipe.domain.model.MealItem

@Keep
data class ResponseMealDTO(
    @SerializedName("meals")
    val meals: List<Meal?>? = null
){
    @Keep
    data class Meal(
        @SerializedName("idMeal")
        val idMeal: String? = null,
        @SerializedName("strMeal")
        val strMeal: String? = null,
        @SerializedName("strMealThumb")
        val strMealThumb: String? = null
    )
}

fun ResponseMealDTO.Meal.toMealItem(): MealItem {
    return MealItem(
        id = this.idMeal.toString(),
        name = this.strMeal.toString(),
        imageUrl = this.strMealThumb
            ?: "https://images.immediate.co.uk/production/volatile/sites/30/2013/05/Aubergine-and-sesame-noodles-6138de6.jpg?quality=90&resize=556,505"
    )
}