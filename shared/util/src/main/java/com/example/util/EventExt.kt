package com.example.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<Event<T>>.emit(item: T) {
    value = Event(item)
}

fun MutableLiveData<Event<Unit>>.emit() {
    value = Event(Unit)
}

fun <T> MutableLiveData<Event<T>>.postEmit(item: T) {
    postValue(Event(item))
}

fun MutableLiveData<Event<Unit>>.postEmit() {
    postValue(Event(Unit))
}