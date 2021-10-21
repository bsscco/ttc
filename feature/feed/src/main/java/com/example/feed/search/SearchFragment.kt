package com.example.feed.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.findNavController
import com.example.util.EventObserver
import com.example.util.observeLiveData
import com.example.util.observeLiveEvent
import com.example.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SearchFragment : Fragment() {

    private val searchBoxViewModel: SearchBoxViewModel by viewModels()
    private val stationListViewModel: StationListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        // Dispose the Composition when the view's LifecycleOwner is destroyed
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                SearchScreenViewModelProvider()
            }
        }
    }

    @Composable
    private fun SearchScreenViewModelProvider() {
        val searchBoxViewModel: SearchBoxViewModel = hiltViewModel()
        val stationListViewModel: StationListViewModel = hiltViewModel()

        SearchScreen(
            searchBoxUiState = searchBoxViewModel.uiStateFlow.collectAsState(initial = null).value,
            searchBoxEventListener = searchBoxViewModel,
            stationListUiState = stationListViewModel.uiStateFlow.collectAsState(initial = null).value,
            stationEventListener = stationListViewModel,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        syncUiState()
        handleViewModelEvent()
    }

    private fun syncUiState() {
        observeLiveData(searchBoxViewModel.searchText) { stationListViewModel.onSearchTextChange(it) }
    }

    private fun handleViewModelEvent() {
        observeLiveEvent(searchBoxViewModel.showToast, EventObserver { message -> showToast(message) })

        observeLiveEvent(stationListViewModel.closeScreen, EventObserver { findNavController().popBackStack() })

        observeLiveEvent(stationListViewModel.showToast, EventObserver { message -> showToast(message) })
    }
}