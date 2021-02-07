package com.chethan.pagingSample.api

import com.chethan.pagingSample.CLIENT_ID
import com.chethan.pagingSample.CLIENT_SECRET
import com.chethan.pagingSample.VERSION
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Chethan on 5/3/2019.
 * This interface contains the definition list of all the network endpoints used by the App.
 * Ref: Retrofit
 */
interface NetWorkApi {

    @GET("v2/venues/{venueId}")
    fun getVenueDetails(
        @Path("venueId") venueId: String,
        @Query("client_id") appId: String,
        @Query("client_secret") appKey: String,
        @Query("v") v: String
    ): Response<VenueDetailsResponse>


    @GET("v2/venues/explore")
    suspend fun searchVenues(
        @Query("client_id") appId: String = CLIENT_ID,
        @Query("client_secret") appKey: String = CLIENT_SECRET,
        @Query("query") givenString: String,
        @Query("limit") limit: Int,
        @Query("near") near: String,
        @Query("offset") offset: Int,
        @Query("v") v: String = VERSION
    ): Response<VenueSearchResponse>
}