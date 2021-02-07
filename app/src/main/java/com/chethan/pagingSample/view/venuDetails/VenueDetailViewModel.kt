package com.chethan.pagingSample.view.venuDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.chethan.pagingSample.model.VenueDetailsWithPhotos
import com.chethan.pagingSample.repository.Resource
import com.chethan.pagingSample.repository.VenueDetailsRepository
import javax.inject.Inject

class VenueDetailViewModel @ViewModelInject
internal constructor(venueDetailsRepository: VenueDetailsRepository) : ViewModel() {

//    private val _venueId = MutableLiveData<String>()
//
//    val venueDetails: LiveData<Resource<VenueDetailsWithPhotos>> = Transformations
//            .switchMap(_venueId) { input ->
//                venueDetailsRepository.loadDetailVenue(input)
//            }
//
//    fun setVenueId(venueId: String) {
//        if (_venueId.value == venueId) {
//            return
//        }
//        _venueId.value = venueId
//    }
}
