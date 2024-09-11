package com.best.free.master.chef.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.best.free.master.chef.recipe.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroBinding.inflate(inflater, container, false)

        binding.btnStart.setOnClickListener {
            val action = IntroFragmentDirections.actionIntroFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}