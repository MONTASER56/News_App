package com.example.test23.ui.fragments.search

import android.os.Bundle

import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.test23.R
import com.example.test23.data.Article
import com.example.test23.databinding.FragmentSearchNewsBinding
import com.example.test23.paging.NewsAdapter
import com.example.test23.paging.NewsInteractionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {

    lateinit var binding: FragmentSearchNewsBinding
    private val searchNewsViewModel: SearchNewsViewModel by viewModels()
    private lateinit var searchNews_adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchNewsBinding.inflate(layoutInflater)
        binding.viewModel = searchNewsViewModel
        (activity as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchNews_adapter = NewsAdapter(object : NewsInteractionListener {
            override fun onClick(item: Article) {


                val action =
                    SearchNewsFragmentDirections.actionSearchNewsFragment2ToHomeNewsFragment(item)
                findNavController().navigate(action)
            }

        })
binding.progressBar.isVisible=false

        lifecycleScope.launchWhenCreated {
            searchNewsViewModel.getSearchNews()

            searchNews_adapter.loadStateFlow.collect {
                val status = it.refresh
                binding.progressBar.isVisible = status is LoadState.Loading
            }
        }
        binding.searchFragmentNews.adapter = searchNews_adapter

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tool_bar_menu, menu)
        val search = menu.findItem(R.id.search_view_toolbar)
        val searchView = search.actionView as SearchView

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    if (query != null) {
                        searchNewsViewModel.setSearchText(query)
                    }
                    if (query.isNullOrEmpty()) {
                        searchView.queryHint = "no text"
                        Toast.makeText(context, "no value", Toast.LENGTH_LONG).show()
                    }
                    searchNewsViewModel.searchList?.collect {
                        searchNews_adapter.submitData(it)
                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                return false
            }

        })



        super.onCreateOptionsMenu(menu, inflater)
    }


}


