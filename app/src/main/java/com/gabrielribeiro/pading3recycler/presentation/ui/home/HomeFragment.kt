package com.gabrielribeiro.pading3recycler.presentation.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.gabrielribeiro.pading3recycler.R
import com.gabrielribeiro.pading3recycler.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }
    private lateinit var loadAdapter: HomeLoadStateAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setHasOptionsMenu(true)

        return root
    }


    private fun setupRecyclerView() {
        loadAdapter = HomeLoadStateAdapter {
            homeAdapter.retry()
        }
        binding.recyclerHome.adapter = homeAdapter.withLoadStateHeaderAndFooter(
            footer = loadAdapter,
            header = loadAdapter
        )
        lifecycleScope.launch {
            viewModel.imageList.observe(viewLifecycleOwner) {
                homeAdapter.submitData(viewLifecycleOwner.lifecycle,it)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.home_menu, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        searchList(searchItem)

    }

    private fun searchList(searchItem: MenuItem) {
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchQuery(query)
                    binding.recyclerHome.scrollToPosition(0)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}