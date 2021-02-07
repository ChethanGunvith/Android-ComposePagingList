package com.chethan.pagingSample.repository

import com.chethan.pagingSample.AppExecutors
import com.chethan.pagingSample.api.NetWorkApi
import com.chethan.pagingSample.db.VenueDetailsDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class VenueDetailsRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val venueDetailsDao: VenueDetailsDao,
        private val netWorkApi: NetWorkApi
)
