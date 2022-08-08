package com.example.ryanrileymtgfinal2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ryanrileymtgfinal2.databinding.FragmentBoosterListBinding
import com.example.ryanrileymtgfinal2.view.controller.BoosterListPageAdapter

class BoosterListFragment : ViewModelFragment() {
    private var _binding: FragmentBoosterListBinding? = null
    private val binding: FragmentBoosterListBinding get() = _binding!!

    private lateinit var BoosterListPageAdapter: BoosterListPageAdapter

    private var currentOffset = 0
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

    private fun configureObserver() {

    }
}