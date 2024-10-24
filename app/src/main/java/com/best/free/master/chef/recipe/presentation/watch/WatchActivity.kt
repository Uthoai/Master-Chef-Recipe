package com.best.free.master.chef.recipe.presentation.watch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.best.free.master.chef.recipe.databinding.ActivityWatchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatchBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")

        binding.webView.settings.javaScriptEnabled = true

        val videoId = getYouTubeVideoId(url.toString())  // Replace with the actual YouTube video ID
        val iframeHtml = """
            <html>
                <body style="margin:0px;padding:0px;">
                    <iframe width="100%" height="100%" 
                        src="https://www.youtube.com/embed/$videoId" 
                        frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
                    </iframe>
                </body>
            </html>
        """
        // Load the HTML content into the WebView
        binding.webView.loadData(iframeHtml, "text/html", "utf-8")

    }

    private fun getYouTubeVideoId(url: String): String? {
        val regex = "(?<=watch\\?v=|/videos/|embed/|youtu.be/|/v/|/e/|watch\\?v%3D|/shorts/|/watch\\?v=|&v=|/v=|%2Fvideos%2F|%2Fv%2F|%2Fe%2F|embed\\?video_id=|%3Fv%3D|\\?v=|\\?video_id=)([\\w-]{11})"
        val matchResult = Regex(regex).find(url)
        return matchResult?.value
    }

}