package com.gabrielribeiro.pading3recycler.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.gabrielribeiro.pading3recycler.databinding.FragmentHomeBinding
import com.gabrielribeiro.pading3recycler.domain.network.PixaBayApi
import com.gabrielribeiro.pading3recycler.domain.repository.PixabayRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViewModel()
        setupRecyclerView()

        return root
    }

    private fun setupRecyclerView() {
        binding.recyclerHome.adapter = homeAdapter
        lifecycleScope.launch {
            viewModel.imageList.collect {
                homeAdapter.submitData(it)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, HomeViewModel.MainViewModelFactory(
                PixabayRepositoryImpl(PixaBayApi.providePixabayApi())
            )
        )[HomeViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}