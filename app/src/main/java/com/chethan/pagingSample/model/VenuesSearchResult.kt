package com.chethan.pagingSample.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.chethan.pagingSample.db.VenueTypeConverters

@Entity(primaryKeys = ["query"])
@TypeConverters(VenueTypeConverters::class)
data class VenuesSearchResult(
    val query: String,
    val repoIds: List<String>,
    val totalCount: Int
)
