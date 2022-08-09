package com.example.ryanrileymtgfinal2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.ryanrileymtgfinal2.databinding.FragmentCardListBinding
import com.example.ryanrileymtgfinal2.model.CardData
import com.example.ryanrileymtgfinal2.model.CardResponse
import com.example.ryanrileymtgfinal2.view.UIState
import com.example.ryanrileymtgfinal2.view.controller.CardListPageAdapter

// for the list of drawn cards page
class DrawnCardsFragment: ViewModelFragment() {
    private var _binding: FragmentCardListBinding? = null
    private val binding: FragmentCardListBinding get() = _binding!!
    private val args: DrawnCardsFragmentArgs by navArgs()

    private lateinit var cardListPageAdapter: CardListPageAdapter

    private var shouldUpdateList = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    // gets the observed state of the API response
    private fun configureObserver() {
        viewModel.carList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success<*> -> {
                    renderList(it.response as CardResponse)
                }
                is UIState.Error -> {
                    binding.apply {
                        tvErrorText.text = it.error.message
                        tvErrorText.visibility = View.VISIBLE
                    }
                }
                is UIState.Loading -> {
                    viewModel.getCardList(args.input)
                }
            }
        }
    }

    private fun renderList(response: CardResponse) {
        binding.apply {
            rvCardList.apply {
                if (!shouldUpdateList) {
                    cardListPageAdapter =
                        CardListPageAdapter(openCardDetails = ::openCardDetails)
                    adapter = cardListPageAdapter
                }
                cardListPageAdapter.setCardList(
                    response.cards,
                    shouldUpdateList
                )
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!rvCardList.canScrollVertically(1)) {
                            shouldUpdateList = true
                            viewModel.getCardList(args.input)
                            Toast.makeText(
                                context,
                                "Opening Another Booster",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        }
                })
            }
        }
    }

    private fun openCardDetails(data: CardData) {
        viewModel.setCardDetails(data)
        shouldUpdateList = false
        findNavController().navigate(
            DrawnCardsFragmentDirections.navToCardDetails(args.input)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}