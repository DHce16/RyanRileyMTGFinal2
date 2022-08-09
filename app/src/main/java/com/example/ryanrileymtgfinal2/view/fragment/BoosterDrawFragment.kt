package com.example.ryanrileymtgfinal2.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ryanrileymtgfinal2.R
import com.example.ryanrileymtgfinal2.databinding.FragmentDetailsBinding
import com.example.ryanrileymtgfinal2.model.BoosterDetailsResponse
import com.example.ryanrileymtgfinal2.model.BoosterNode
import com.example.ryanrileymtgfinal2.view.UIState

// for Booster draw page
class BoosterDrawFragment: ViewModelFragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private lateinit var codeSaved: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        configureObserver()
        binding.btnOpenBooster.setOnClickListener {
            goToCardList()
        }
        return binding.root
    }

    // gets the observed state of the API response
    private fun configureObserver() {
        viewModel.boosterDetails.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success<*> -> {
                    renderDetails((it.response as BoosterDetailsResponse).set)
                }
                is UIState.Error -> {
                    binding.apply {
                        tvDetailsError.text = it.error.message
                        tvDetailsError.visibility = View.VISIBLE
                    }
                }
                is UIState.Loading -> {loadingState()}
            }
        }
    }

    private fun renderDetails(node: BoosterNode) {
        Glide.with(binding.ivDetailImage)
            .load(R.drawable.magic_icon)
            .into(binding.ivDetailImage)

        binding.apply {
            tvDetailsError.visibility = View.GONE

            tvDetailTitleText.apply {
                text = node.name
                visibility = View.VISIBLE
            }

            tvRealCodes.apply {
                text = node.code
                codeSaved = node.code
                visibility = View.VISIBLE
            }
            ivDetailImage.apply {
                visibility = View.VISIBLE
            }
            tvCodesName.apply {
                visibility = View.VISIBLE
            }
            btnOpenBooster.apply {
                visibility = View.VISIBLE
            }
        }
    }

    private fun loadingState() {
        binding.apply {
            ivDetailImage.visibility = View.GONE
            tvDetailTitleText.visibility = View.GONE
            tvRealCodes.visibility = View.GONE
            tvCodesName.visibility = View.GONE
            tvDetailsError.visibility = View.GONE
            btnOpenBooster.visibility = View.GONE
            Log.e("tag", "this here")
        }
        viewModel.getBoosterDetails(viewModel.currentBooster.code)
    }

    private fun goToCardList() {
        viewModel.setDrawnLoadingState()
        findNavController().navigate(
            BoosterDrawFragmentDirections.navClosedPackToOpen(codeSaved)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}