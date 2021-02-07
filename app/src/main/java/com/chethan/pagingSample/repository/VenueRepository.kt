package com.chethan.pagingSample.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chethan.pagingSample.api.NetWorkApi
import com.chethan.pagingSample.api.VenueSearchResponse
import kotlinx.coroutines.flow.Flow
import com.chethan.pagingSample.pagingSource.VenuePagingSource
import timber.log.Timber


class VenueRepository constructor(private val services: NetWorkApi) {
    fun searchVenues(
        givenString: String
    ): Flow<PagingData<VenueSearchResponse.Response.Groups.Items>> {
        return Pager(config = PagingConfig(pageSize = 10, initialLoadSize = 10)) {
            Timber.i("getPendingReviewList")
            VenuePagingSource(givenString = givenString, services = services)
        }.flow
    }
}
