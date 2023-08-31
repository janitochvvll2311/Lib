package com.juandev.lib.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel : ViewModel() {
    val value = MutableLiveData("Default Value")
    val items = MutableLiveData(emptyList<TestItem>())
}