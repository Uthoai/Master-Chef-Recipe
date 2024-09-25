package com.best.free.master.chef.recipe.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.best.free.master.chef.recipe.core.common.gone
import com.best.free.master.chef.recipe.core.common.visible
import com.best.free.master.chef.recipe.databinding.FragmentDetailsBinding
import com.best.free.master.chef.recipe.domain.model.MealDetails
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args : DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        getMealDetailByID(args.mealID)

        return binding.root
    }

    private fun getMealDetailByID(mealID: String) {
        detailsViewModel.getMealDetail(mealID)

        lifecycleScope.launch {
            detailsViewModel.mealDetails.collect{detailState->
                if (detailState.loading){
                    binding.progress.visible()
                }
                if (detailState.error != null){
                    binding.progress.gone()
                }
                if ((detailState.mealDetails ?.size ?: 0) != 0){
                    setUiData(detailState.mealDetails?.get(0))
                    binding.progress.gone()
                    //Log.d("TAG5", "getMealDetailByID: ${mealDetail.thumbnailUrl}")
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUiData(mealData: MealDetails?) {
        Glide.with(this)
            .load(mealData!!.thumbnailUrl)
            .into(binding.ivMealThumbnail)
        binding.apply {
            tvMealName.text = mealData.name
            tvCategory.text = "Category: ${mealData.category}"
            tvArea.text = "Area: ${mealData.area}"
            tvInstructions.text = mealData.instructions

            setIngredients(mealData.ingredients)
            setTagsData(mealData.tags)
        }
    }

    private fun setTagsData(tags: List<String>) {
        tags.forEach { tag ->
            val ingredientItem = Chip(requireContext())
            ingredientItem.text = tag
            ingredientItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            binding.cgTags.addView(ingredientItem)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setIngredients(ingredients: List<String>) {
        ingredients.forEachIndexed { index, ingredient ->
            val ingredientItem = TextView(requireContext())
            ingredientItem.text = "${index + 1}.$ingredient"
            ingredientItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )

            binding.ingredientsLayout.addView(ingredientItem)
        }
    }

}