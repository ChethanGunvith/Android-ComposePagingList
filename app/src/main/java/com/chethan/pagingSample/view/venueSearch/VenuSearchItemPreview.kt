package com.chethan.pagingSample.view.venueSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chethan.pagingSample.api.VenueSearchResponse
import com.chethan.pagingSample.ui.theme.pastelGray

@Composable
fun VenueItemPreview(
    item: VenueSearchResponse.Response.Groups.Items,
    listener: ClickListenerForSearchList
) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth().wrapContentHeight()
        ) {
            Text(
                text = item.venue.name.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start
                )
            )
        }
    }
}
