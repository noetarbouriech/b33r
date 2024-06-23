package com.noetarbouriech.b33r.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.noetarbouriech.b33r.ui.SearchViewModel
import com.noetarbouriech.b33r.ui.components.BeerList
import com.noetarbouriech.b33r.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()

    ) {
            SearchBar(
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                query = uiState.searchText,
                onQueryChange = viewModel::onSearchChange,
                onSearch = { viewModel.onSearchChange(uiState.searchText) },
                active = uiState.isSearching,
                onActiveChange = { viewModel.onToggleSearch() },
            ) {
                if (uiState.results.isNullOrEmpty()) {
                    Text(
                        text = "No results \uD83D\uDE1E",
                        color = Color.White
                    )
                } else {
                    BeerList(uiState.results!!)
                }
            }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    AppTheme {
        SearchScreen()
    }
}
