package com.best.free.master.chef.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.best.free.master.chef.recipe.adapter.SelectedCategoryMealItemAdapter
import com.best.free.master.chef.recipe.databinding.FragmentHomeBinding
import com.best.free.master.chef.recipe.domain.model.MealItem
import com.best.free.master.chef.recipe.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var selectedCategoryMealItemAdapter: SelectedCategoryMealItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        observer()

        return binding.root
    }


    private fun observer() {
        homeViewModel.getMealData("Dessert")

        homeViewModel.mealList.observe(viewLifecycleOwner){
            binding.progressTrendRec.visibility = View.VISIBLE
            selectedCategoryMealItemAdapter = SelectedCategoryMealItemAdapter(it)
            binding.trendRecyclerView.adapter = selectedCategoryMealItemAdapter
            binding.progressTrendRec.visibility = View.GONE
        }
    }
}