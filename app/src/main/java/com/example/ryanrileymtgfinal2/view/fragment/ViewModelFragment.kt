package com.example.ryanrileymtgfinal2.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ryanrileymtgfinal2.viewModel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment: Fragment() {

    protected val viewModel: CardViewModel by activityViewModels()
}