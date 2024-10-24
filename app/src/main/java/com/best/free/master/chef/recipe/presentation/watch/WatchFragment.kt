package com.best.free.master.chef.recipe.presentation.watch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.best.free.master.chef.recipe.databinding.FragmentWatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchFragment : Fragment() {
    private lateinit var binding: FragmentWatchBinding
    private val args: WatchFragmentArgs by navArgs()
    private var player: ExoPlayer? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchBinding.inflate(inflater, container, false)

        val url = args.url.toString()
        player = ExoPlayer.Builder(requireContext()).build()
        binding.mediaPlayer.player = player

        lifecycleScope.launch {
            url?.let {
                val mediaItem = MediaItem.fromUri(url)
                player?.apply {
                    setMediaItem(mediaItem)
                    prepare()
                    play()
                }
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        player?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player?.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

}