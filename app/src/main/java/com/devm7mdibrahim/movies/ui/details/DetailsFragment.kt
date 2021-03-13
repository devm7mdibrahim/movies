package com.devm7mdibrahim.movies.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devm7mdibrahim.movies.R
import com.devm7mdibrahim.movies.base.BaseFragment
import com.devm7mdibrahim.movies.databinding.FragmentDetailsBinding
import com.devm7mdibrahim.movies.utils.DataState
import com.devm7mdibrahim.movies.utils.toGone
import com.devm7mdibrahim.movies.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = DetailsFragmentArgs.fromBundle(it)
            getMovieDetails(args.movieId)
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModel.getMovieDetails(movieId)

        lifecycleScope.launchWhenCreated {
            viewModel.movie.collect {
                when(it){
                    is DataState.Loading -> {
                        binding?.progressBar?.toVisible()
                    }

                    is DataState.Success -> {
                        binding?.progressBar?.toGone()
                        binding?.details = it.data
                    }

                    is DataState.Error -> {
                        binding?.progressBar?.toGone()
                        showToast(it.exception)
                        displayLog(it.exception)
                    }
                    else -> {
                        binding?.progressBar?.toGone()
                    }
                }
            }
        }
    }

    override fun getFragmentView(): Int =  R.layout.fragment_details
}