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
import com.example.ryanrileymtgfinal2.model.CardData
import com.example.ryanrileymtgfinal2.model.CardResponse
import com.example.ryanrileymtgfinal2.utils.httpConvert
import com.example.ryanrileymtgfinal2.view.UIState

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
//        configureObserver()
        renderCardDetails()
        return binding.root
    }

    //Sees if the data was correctly loaded in
//    private fun configureObserver() {
//        viewModel.cardDetails.observe(viewLifecycleOwner) {
//            when(it) {
//                is UIState.Success<*> -> {
//                    renderCardDetails(it.response as CardData)
//                }
//                is UIState.Error -> {
//                    binding.apply {
//                        tvCardDetailsErrorText.visibility = View.VISIBLE
//                        tvCardDetailsErrorText.text = it.error.message
//                    }
//                }
//                is UIState.Loading -> { loadingState() }
//            }
//        }
//    }

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
//        viewModel.getCardDetails(args.input)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}