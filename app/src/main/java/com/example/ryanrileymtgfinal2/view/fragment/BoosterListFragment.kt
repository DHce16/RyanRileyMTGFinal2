package com.example.ryanrileymtgfinal2.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ryanrileymtgfinal2.databinding.FragmentBoosterListBinding
import com.example.ryanrileymtgfinal2.model.BoosterNode
import com.example.ryanrileymtgfinal2.model.BoosterResponse
import com.example.ryanrileymtgfinal2.view.UIState
import com.example.ryanrileymtgfinal2.view.controller.BoosterListPageAdapter

//for BoosterList page
class BoosterListFragment : ViewModelFragment() {
    private var _binding: FragmentBoosterListBinding? = null
    private val binding: FragmentBoosterListBinding get() = _binding!!

    private lateinit var boosterListPageAdapter: BoosterListPageAdapter

    private var shouldUpdateList = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoosterListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    // gets the observed state of the API response
    private fun configureObserver() {
        viewModel.boosterList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success<*> -> {
//                    Log.e("TAG", "configureObserver: sus")
                    renderList(it.response as BoosterResponse)
                }
                is UIState.Error -> {
                    binding.apply {
                        tvErrorText.text = it.error.message
                        tvErrorText.visibility = View.VISIBLE
                    }
                }
                is UIState.Loading -> {
                    viewModel.getBoosterList()
                }
            }
        }
    }

    private fun renderList(response: BoosterResponse) {
        binding.apply {
            rvBoosterList.apply {
                if (!shouldUpdateList) {
                    boosterListPageAdapter =
                        BoosterListPageAdapter(openBoosterDetails = ::openBoosterDetails)
                    adapter = boosterListPageAdapter
                }
                boosterListPageAdapter.setBoosterList(
                    response.sets,
                    shouldUpdateList
                )
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(
                        recyclerView: RecyclerView,
                        dx: Int,
                        dy: Int
                    ) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!rvBoosterList.canScrollVertically(1)) {
                            shouldUpdateList = true
                            viewModel.getBoosterList()
                            Toast.makeText(
                                context,
                                "Loading more Booster Packs",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }
        }
    }

    private fun openBoosterDetails(node: BoosterNode) {
        viewModel.setBoosterDetails(node)
        shouldUpdateList = false
        findNavController().navigate(
            BoosterListFragmentDirections.navListToNavDetails()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}