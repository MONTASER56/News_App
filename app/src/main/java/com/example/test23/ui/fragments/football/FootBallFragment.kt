package com.example.test23.ui.fragments.football

import android.os.Bundle
import android.util.Log
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
import com.example.test23.databinding.FragmentFootBallBinding
import com.example.test23.paging.NewsAdapter
import com.example.test23.paging.NewsInteractionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootBallFragment : Fragment() {
    private lateinit var binding: FragmentFootBallBinding

    private val
            footBallViewModel: FootBallViewModel
            by viewModels()
    private lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFootBallBinding.inflate(layoutInflater)
        binding.viewModel = footBallViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        adapter = NewsAdapter(object : NewsInteractionListener {
            override fun onClick(item: Article) {
             val action=FootBallFragmentDirections.actionFootBallFragment2ToHomeNewsFragment(item)
                findNavController().navigate(action)
            }

        })
        lifecycleScope.launchWhenCreated {
            footBallViewModel.footBallList.collect {
                adapter.submitData(it)
            }
        }

        binding.apply {
            lifecycleScope.launchWhenCreated {
                adapter.loadStateFlow.collect {
                    val state = it.refresh

                    Log.d("TAG", "onViewCreated: ${it.refresh}")
                    progressBar.isVisible = state is LoadState.Loading
                }
            }
            homeNews.adapter = adapter.withLoadStateFooter(
                LoadMoreAdapter { adapter.retry() }
            )

            homeNews.adapter = adapter
        }


    }


}