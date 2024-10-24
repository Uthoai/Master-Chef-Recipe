package com.best.free.master.chef.recipe.presentation.details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.best.free.master.chef.recipe.core.common.gone
import com.best.free.master.chef.recipe.core.common.visible
import com.best.free.master.chef.recipe.databinding.FragmentDetailsBinding
import com.best.free.master.chef.recipe.domain.model.MealDetails
import com.best.free.master.chef.recipe.presentation.watch.WatchActivity
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args : DetailsFragmentArgs by navArgs()

    private var textToSpeech: TextToSpeech? = null
    private var speechText: String? = null
    private var url: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        textToSpeech = TextToSpeech(requireContext(), TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.US
            }
        })

        binding.ivBtnSpeech.setOnClickListener {
            speakOutText()
        }

        getMealDetailByID(args.mealID)

        binding.btnWatchVideo.setOnClickListener {
            url?.let {
                //findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToWatchFragment(url))
                val intent = Intent(requireContext(), WatchActivity::class.java)
                intent.putExtra("URL", url)
                startActivity(intent)
            }
        }

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
                    setSpeechText(detailState.mealDetails?.get(0))
                    url = detailState.mealDetails?.get(0)?.youtubeUrl
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
            val tagItem = Chip(requireContext())
            tagItem.text = tag
            tagItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            binding.cgTags.addView(tagItem)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setIngredients(ingredients: List<String>) {
        ingredients.forEachIndexed { index, ingredient ->
            val ingredientItem = TextView(requireContext())
            ingredientItem.text = "${index + 1}.$ingredient "
            ingredientItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )

            binding.ingredientsLayout.addView(ingredientItem)
        }
    }

    private fun setSpeechText(mealData: MealDetails?) {
        val stringBuilder = StringBuilder()

        // Collect text from views
        stringBuilder.append("Meal Name: ${mealData?.name}\n")
        stringBuilder.append("Category: ${mealData?.category}\n")
        stringBuilder.append("Area: ${mealData?.area}\n")
        stringBuilder.append("Instructions: ${mealData?.instructions}\n")

        // Collect ingredients text
        mealData?.ingredients?.forEachIndexed { index, ingredient ->
            stringBuilder.append("${index + 1}. $ingredient\n")
        }

        // Collect tags text
        mealData?.tags?.forEach { tag ->
            stringBuilder.append("Tag: $tag\n")
        }

        // Set the built string to speechText
        speechText = stringBuilder.toString()
    }

    private fun speakOutText() {
        textToSpeech?.speak(speechText, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onPause() {
        // Stop the TextToSpeech if it's speaking
        if (textToSpeech?.isSpeaking == true) {
            textToSpeech?.stop()
        }
        super.onPause()
    }

    override fun onDestroy() {
        // Stop and release TextToSpeech resources
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onDestroy()
    }

}