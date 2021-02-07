package com.chethan.pagingSample.view.venuDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chethan.pagingSample.view.venueSearch.VenueListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VenuesSearchDetailFragment : Fragment() {
    private val viewModel: VenuesSearchDetailFragment by viewModels()
}