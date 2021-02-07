package com.chethan.pagingSample.view.venueSearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.chethan.pagingSample.R
import com.chethan.pagingSample.ui.theme.pastelGray
import com.chethan.pagingSample.ui.theme.rotatingProgressBarColor

interface ClickListenerForSearchList {
    fun onClickOfSearchListItem()

}

@Composable
fun VenueSearchPreview(listenerForSearchList: ClickListenerForSearchList) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopSearchBar()
        VenueSearchListPreview(listenerForSearchList)
    }
}


@Composable
fun TopSearchBar(viewModel: VenueListViewModel = viewModel()) {

    val query = remember { mutableStateOf("") }
    Surface(
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .padding(8.dp),
                label = { Text(text = "Search") },
                value = query.value, onValueChange = {
                    query.value = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                leadingIcon = {
                    Icon(Icons.Filled.Search)
                },
                onImeActionPerformed = { action, softKeyboardController ->
                    if (action == ImeAction.Search) {
                        viewModel.searchForUserQueryString(query.value)
                        softKeyboardController?.hideSoftwareKeyboard()
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                textStyle = TextStyle(color = MaterialTheme.colors.onSurface)

            )
        }
    }
}

@Composable
fun VenueSearchListPreview(
    listenerForSearchList: ClickListenerForSearchList,
    viewModel: VenueListViewModel = viewModel(),

    ) {
    val showEmptyMessage = remember { mutableStateOf(false) }


    val venueItemList = viewModel.venueList.collectAsLazyPagingItems()
    if (showEmptyMessage.value)
        EmptyMessageInReviewList("There is no result for given String")

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                pastelGray
            )
    ) {
        if (venueItemList.loadState.refresh == LoadState.Loading) {
            item {
                Column(
                    modifier = Modifier.fillParentMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = rotatingProgressBarColor)
                }
            }
        }

//        if (venueItemList.loadState.prepend is LoadState.NotLoading && venueItemList.loadState.prepend.endOfPaginationReached && venueItemList.itemCount == 0 && showEmptyMessage.value.not()) {
//            showEmptyMessage.value = true
//        }

        itemsIndexed(venueItemList) { _, value ->
            value?.let { venueItem ->
                VenueItemPreview(venueItem, listenerForSearchList)
            }
        }

        if (venueItemList.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    color = rotatingProgressBarColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun EmptyMessageInReviewList(userMessage: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(pastelGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = vectorResource(id = R.drawable.ic_no_result_found)
        )
        Text(
            text = userMessage,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
