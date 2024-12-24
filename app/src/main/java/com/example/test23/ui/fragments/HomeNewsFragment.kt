package com.example.test23.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.test23.databinding.FragmentHomeNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNewsFragment : Fragment() {
    private lateinit var binding: FragmentHomeNewsBinding
    private val navArgs: HomeNewsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentHomeNewsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = navArgs.artical
        binding.webView.apply {
            article.url?.let { loadUrl(it) }
            webViewClient = WebViewClient()

            binding.progressBar.progress = this.progress

        }


    }


}