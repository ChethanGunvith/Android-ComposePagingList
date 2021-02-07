package com.chethan.pagingSample.view.venueSearch


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chethan.pagingSample.api.VenueSearchResponse
import com.chethan.pagingSample.repository.VenueRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Chethan on 5/3/2019.
 */


class VenueListViewModel @ViewModelInject
internal constructor(private val venueRepository: VenueRepository) : ViewModel() {


    private val _userQueryString = MutableLiveData("")


    val venueList: Flow<PagingData<VenueSearchResponse.Response.Groups.Items>> =
        _userQueryString.switchMap {
            venueRepository.searchVenues(givenString = it).cachedIn(viewModelScope)
                .asLiveData()
        }.asFlow()


    fun getUserQuery(): String {
        return _userQueryString.value ?: ""
    }

    fun searchForUserQueryString(givenString: String) {
        _userQueryString.value = givenString

    }
}