package com.example.test23.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.test23.adapters.LoadMoreAdapter
import com.example.test23.data.Article
import com.example.test23.databinding.FragmentHomeBinding
import com.example.test23.paging.NewsAdapter
import com.example.test23.paging.NewsInteractionListener
import com.example.test23.viewmodels.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val
            viewModel: ViewModel
            by viewModels()
    private lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter(object : NewsInteractionListener {
            override fun onClick(item: Article) {
                val action = NewsFragmentDirections.actionNewsFragmentToHomeNewsFragment(item)
                findNavController().navigate(action)
            }

        })



        lifecycleScope.launch {
            viewModel.newsList2.collect {
                adapter.submitData(it)
            }
        }


        lifecycleScope.launch {
            adapter.loadStateFlow.collect {

                val state = it.refresh
                binding.progressBar.isVisible = state is LoadState.Loading
            }
        }
        binding.homeNews.adapter = adapter.withLoadStateFooter(
            LoadMoreAdapter { adapter.retry() }
        )




        binding.homeNews.adapter = adapter


    }

}