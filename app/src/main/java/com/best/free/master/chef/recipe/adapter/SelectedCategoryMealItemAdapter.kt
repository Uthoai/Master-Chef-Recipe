package com.best.free.master.chef.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.best.free.master.chef.recipe.databinding.MealItemLayoutBinding
import com.best.free.master.chef.recipe.domain.model.MealItem
import com.bumptech.glide.Glide

class SelectedCategoryMealItemAdapter(private val mealList: List<MealItem?>) :
    RecyclerView.Adapter<SelectedCategoryMealItemAdapter.MealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealItemViewHolder {
        return MealItemViewHolder(
            MealItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        mealList[position].let {data->
            holder.binding.apply {
                tvMealTitle.text = data?.name
            }
            Glide.with(holder.itemView.context)
                .load(data?.imageUrl)
                .into(holder.binding.ivMealUrl)
        }
    }

    inner class MealItemViewHolder(val binding: MealItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}