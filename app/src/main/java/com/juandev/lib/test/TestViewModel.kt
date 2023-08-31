package com.juandev.lib.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset

class TestViewModel : ViewModel() {
    val value = MutableLiveData("Default Value")
    val items = MutableLiveData(emptyList<TestItem>())
    val local = MutableLiveData(LocalDateTime.now())
    val utc = MutableLiveData(LocalDateTime.now(ZoneOffset.UTC))
}