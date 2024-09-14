package com.best.free.master.chef.recipe.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.best.free.master.chef.recipe.databinding.MealItemGridLayoutBinding
import com.best.free.master.chef.recipe.domain.model.MealItem

class SelectedCategoryMealItemAdapter :
    ListAdapter<MealItem, SelectedCategoryMealItemAdapter.MealItemViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealItemViewHolder {
        return MealItemViewHolder(
            MealItemGridLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        holder.binding.mealItem = getItem(position)
    }

    inner class MealItemViewHolder(val binding: MealItemGridLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<MealItem>() {
            override fun areItemsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}