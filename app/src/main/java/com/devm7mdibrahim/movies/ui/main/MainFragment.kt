package com.devm7mdibrahim.movies.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.devm7mdibrahim.movies.R
import com.devm7mdibrahim.movies.base.BaseFragment
import com.devm7mdibrahim.movies.databinding.FragmentMainBinding
import com.devm7mdibrahim.movies.utils.DataState
import com.devm7mdibrahim.movies.utils.toGone
import com.devm7mdibrahim.movies.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val navController by lazy {
        activity?.let {
            Navigation.findNavController(it, R.id.fragment_container)
        }
    }

    private val viewModel by viewModels<MainViewModel>()

    private val movieAdapter by lazy {
        MovieAdapter {
            navController?.navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(movieId = it.id))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getMovies()
    }

    private fun initRecyclerView() {
        displayLog("from init")
        binding?.rvMovies?.run {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun getMovies() {
        displayLog("from get movies")
        viewModel.getMovies()

        lifecycleScope.launchWhenCreated {
            viewModel.movies.collect {
                when(it){
                    is DataState.Loading -> {
                        binding?.progressBar?.toVisible()
                        displayLog("from loading")
                    }

                    is DataState.Success -> {
                        binding?.progressBar?.toGone()
                        movieAdapter.submitList(it.data)
                        displayLog("from success ${it.data}")
                    }

                    is DataState.Error -> {
                        binding?.progressBar?.toGone()
                        showToast(it.exception)
                        displayLog("from error ${it.exception}")
                    }
                    else -> binding?.progressBar?.toGone()
                }
            }
        }
    }

    override fun getFragmentView(): Int = R.layout.fragment_main
}