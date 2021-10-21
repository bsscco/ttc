package com.example.feed

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
import com.example.util.EventObserver
import com.example.util.observeLiveEvent
import com.example.util.safeNavigate
import com.example.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class FeedFragment : Fragment() {

    private val searchBoxViewModel: SearchBoxViewModel by viewModels()
    private val favoriteListViewModel: FavoriteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        // Dispose the Composition when the view's LifecycleOwner is destroyed
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                FeedScreenViewModelProvider()
            }
        }
    }

    @Composable
    private fun FeedScreenViewModelProvider() {
        val searchBoxViewModel: SearchBoxViewModel = hiltViewModel()
        val favoriteListViewModel: FavoriteListViewModel = hiltViewModel()

        FeedScreen(
            searchBoxEventListener = searchBoxViewModel,
            favoriteListUiState = favoriteListViewModel.uiStateFlow.collectAsState(initial = null).value,
            stationEventListener = favoriteListViewModel,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleViewModelEvent()
    }

    private fun handleViewModelEvent() {
        observeLiveEvent(searchBoxViewModel.navigateToSearch, EventObserver {
            safeNavigate(FeedFragmentDirections.actionFeedFragmentToSearchFragment())
        })

        observeLiveEvent(searchBoxViewModel.showToast, EventObserver { message -> showToast(message) })

        observeLiveEvent(favoriteListViewModel.showToast, EventObserver { message -> showToast(message) })
    }
}