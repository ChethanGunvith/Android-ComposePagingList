package com.chethan.pagingSample.view.venueSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VenuesSearchListFragment : Fragment(), ClickListenerForSearchList {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                venueSearchFragment()
            }
        }
    }


    @Composable
    @Preview
    fun venueSearchFragment() {
        VenueSearchPreview(listenerForSearchList = this)
    }

    override fun onClickOfSearchListItem() {
        TODO("Not yet implemented")
    }
}