package com.example.util

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Fragment.observeLiveEvent(liveData: LiveData<Event<T>>, eventObserver: EventObserver<T>) {
    liveData.observe(viewLifecycleOwner, eventObserver)
}

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.observe(viewLifecycleOwner, observer)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.safeNavigate(directions: NavDirections) {
    try {
        findNavController().navigate(directions)
    } catch (e: Exception) {
        if (e.isAlreadyNavigated()) {
            e.printStackTrace()
        } else {
            throw e
        }
    }
}

private fun Exception.isAlreadyNavigated() = this is IllegalArgumentException && this.message?.contains("cannot be found from the current destination") == true

fun <T> Fragment.observeReturnedData(
    key: String,
    @SuppressLint("UnknownNullness") initialValue: T,
    onGet: (T) -> Unit,
) = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key, initialValue)
    ?.asFlow()
    ?.distinctUntilChanged()
    ?.onEach { findNavController().currentBackStackEntry?.savedStateHandle?.set<T>(key, initialValue) }
    ?.onEach { data -> onGet.invoke(data) }
    ?.launchIn(viewLifecycleOwner.lifecycleScope)

fun <T> Fragment.setResultData(key: String, value: T) = findNavController().previousBackStackEntry
    ?.savedStateHandle
    ?.set(key, value)