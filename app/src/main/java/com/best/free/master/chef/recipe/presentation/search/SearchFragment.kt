package com.best.free.master.chef.recipe.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.best.free.master.chef.recipe.R
import com.best.free.master.chef.recipe.core.common.gone
import com.best.free.master.chef.recipe.core.common.visible
import com.best.free.master.chef.recipe.databinding.FragmentSearchBinding
import com.best.free.master.chef.recipe.presentation.home.HomeFragmentDirections
import com.best.free.master.chef.recipe.presentation.home.adapter.SelectedCategoryMealItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), SelectedCategoryMealItemAdapter.MealClickListener {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    private val selectedCategoryMealItemAdapter by lazy {
        SelectedCategoryMealItemAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        observer()

        return binding.root
    }

    private fun observer() {

        viewModel.searchString.observe(viewLifecycleOwner){
            viewModel.searchMeals(it)
        }

        lifecycleScope.launch {
            viewModel.searchDataState.collect{state->
                if (state.loading){
                    binding.progressTrendRec.visible()
                }
                if (state.error != null){
                    binding.progressTrendRec.gone()
                }
                if (state.searchData != null){
                    binding.progressTrendRec.gone()
                    selectedCategoryMealItemAdapter.submitList(state.searchData)
                    binding.searchItemRecyclerView.adapter = selectedCategoryMealItemAdapter
                }
            }
        }
    }

    override fun onMealItemClick(mealId: String) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(mealId))
    }
}