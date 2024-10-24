package com.best.free.master.chef.recipe.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.best.free.master.chef.recipe.databinding.CategoryItemBinding
import com.best.free.master.chef.recipe.domain.model.MealCategories
import com.bumptech.glide.Glide

class CategoryAdapter(private val list: List<MealCategories?>?) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        list?.get(position)?.let {
            holder.binding.tvCategoryTitle.text = it.name

            Glide.with(holder.itemView.context)
                .load(it.imageUrl)
                .into(holder.binding.ivCategoryUrl)
        }
    }

    inner class CategoryViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}