package com.chethan.pagingSample.pagingSource

import androidx.paging.PagingSource
import com.chethan.pagingSample.api.NetWorkApi
import com.chethan.pagingSample.api.VenueSearchResponse
import timber.log.Timber


class VenuePagingSource(
    val givenString: String,
    val services: NetWorkApi, private val pageSize: Int = 10
) : PagingSource<Int, VenueSearchResponse.Response.Groups.Items>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, VenueSearchResponse.Response.Groups.Items> {


        try {
            // Load page 1 if undefined.
            val nextPageNumber = params.key ?: 0
            val response = services.searchVenues(
                limit = 10,
                offset = nextPageNumber,
                givenString = givenString, near = givenString
            )


            val nextOffset = response.body()?.response?.let {
                val nextCalOffset = nextPageNumber
                    .plus(if (it.groups[0].items.isNotEmpty()) it.groups[0].items.size else 0)
                if (it.totalResults > nextCalOffset && it.groups[0].items.isNotEmpty())
                    nextCalOffset else null
            }



            return LoadResult.Page(
                data = response.body()?.response?.groups?.get(0)?.items ?: emptyList(),
                prevKey = null, // Only paging forward.
                nextKey = nextOffset
            )
        } catch (e: Exception) {
            // Handle errors in this block
            Timber.i(e.localizedMessage)
            return LoadResult.Error(e)
        }
    }
}