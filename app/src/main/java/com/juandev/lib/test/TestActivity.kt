package com.juandev.lib.test

import android.os.Bundle
import com.juandev.lib.activity.ViewModelActivity
import com.juandev.lib.test.databinding.TestActivityLayoutBinding
import timber.log.Timber

class TestActivity : ViewModelActivity<TestViewModel, TestActivityLayoutBinding>(
    BR.viewModel, R.layout.test_activity_layout
) {

    override val viewModel get() = Companion.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TestActivity_TAG: onCreate")
        viewModel.value.postValue("Test Value")
    }

    companion object {
        val viewModel = TestViewModel()
    }

}