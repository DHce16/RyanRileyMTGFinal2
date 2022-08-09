package com.example.ryanrileymtgfinal2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ryanrileymtgfinal2.databinding.FragmentSearchBinding

// starting page
// for the search page
class SearchFragment : ViewModelFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.btnStartSearch.setOnClickListener {
            goToBoosterList()
        }
        return binding.root
    }

    private fun goToBoosterList() {
        viewModel.setLoadingState()
        findNavController().navigate(
            SearchFragmentDirections.actionNavSearchToBoosterList()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}