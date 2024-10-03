package com.best.free.master.chef.recipe.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.best.free.master.chef.recipe.R
import com.best.free.master.chef.recipe.presentation.home.adapter.SelectedCategoryMealItemAdapter
import com.best.free.master.chef.recipe.core.common.gone
import com.best.free.master.chef.recipe.core.common.visible
import com.best.free.master.chef.recipe.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), SelectedCategoryMealItemAdapter.MealClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private val selectedCategoryMealItemAdapter by lazy {
        SelectedCategoryMealItemAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        observer()

        binding.discoverBe.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        return binding.root
    }


    private fun observer() {
        homeViewModel.getMealData("Dessert")

        lifecycleScope.launch {
            homeViewModel.homeMealDataState.collect{homeState->
                if (homeState.loading){
                    binding.progressTrendRec.visible()
                }
                if (homeState.error != null){
                    binding.progressTrendRec.gone()
                }
                if (homeState.homeData != null){
                    binding.progressTrendRec.gone()
                    selectedCategoryMealItemAdapter.submitList(homeState.homeData)
                    binding.trendRecyclerView.adapter = selectedCategoryMealItemAdapter
                }
            }
        }
    }

    override fun onMealItemClick(mealId: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(mealId))
    }
}