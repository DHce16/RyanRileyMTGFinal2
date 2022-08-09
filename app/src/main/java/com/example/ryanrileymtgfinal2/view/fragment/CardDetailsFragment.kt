package com.example.ryanrileymtgfinal2.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ryanrileymtgfinal2.R
import com.example.ryanrileymtgfinal2.databinding.FragmentCardDetailsBinding

// for Card Details page
class CardDetailsFragment:  ViewModelFragment() {
    private var _binding: FragmentCardDetailsBinding? = null
    private val binding: FragmentCardDetailsBinding get() = _binding!!

    private val args: DrawnCardsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardDetailsBinding.inflate(layoutInflater)
        renderCardDetails()
        return binding.root
    }

    //displays the image and gets rid of the error text
    private fun renderCardDetails() {
        Glide.with(binding.ivCardDetailImage)
            .load(args.input)
            .placeholder(R.drawable.magic_icon)
            .into(binding.ivCardDetailImage)

        Log.d("Tag", "${args.input}")
        binding.apply {
            tvCardDetailsErrorText.visibility = View.GONE
            ivCardDetailImage.visibility = View.VISIBLE
        }
    }

    private fun loadingState() {
        binding.apply {
            tvCardDetailsErrorText.visibility = View.GONE
            ivCardDetailImage.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}