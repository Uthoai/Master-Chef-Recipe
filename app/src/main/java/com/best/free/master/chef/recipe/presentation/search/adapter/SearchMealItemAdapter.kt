package com.best.free.master.chef.recipe.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.best.free.master.chef.recipe.databinding.MealItemGridLayout2Binding
import com.best.free.master.chef.recipe.databinding.MealItemGridLayoutBinding
import com.best.free.master.chef.recipe.domain.model.MealItem

class SearchMealItemAdapter (private val listener: MealClickListener):
    ListAdapter<MealItem, SearchMealItemAdapter.MealItemViewHolder>(COMPARATOR) {

    fun interface MealClickListener {
        fun onMealItemClick(mealId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealItemViewHolder {
        return MealItemViewHolder(
            MealItemGridLayout2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        val mealItem = getItem(position)

        holder.binding.mealItem = mealItem

        holder.itemView.setOnClickListener {
            listener.onMealItemClick(mealItem.id)
        }
    }

    inner class MealItemViewHolder(val binding: MealItemGridLayout2Binding) :
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