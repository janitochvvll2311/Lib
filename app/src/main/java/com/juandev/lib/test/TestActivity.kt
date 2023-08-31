package com.juandev.lib.test

import android.os.Bundle
import com.juandev.lib.activity.ViewModelActivity
import com.juandev.lib.showDialog
import com.juandev.lib.test.databinding.TestActivityLayoutBinding
import timber.log.Timber
import kotlin.random.Random

class TestActivity : ViewModelActivity<TestViewModel, TestActivityLayoutBinding>(
    BR.viewModel, R.layout.test_activity_layout
) {

    override val viewModel get() = Companion.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TestActivity_TAG: onCreate")
        val random = Random(0);
        binding.btn.setOnClickListener {
            viewModel.run {
                val content = items.value ?: emptyList()
                items.postValue(
                    content + TestItem(
                        "${random.nextInt(0, 9)}" +
                                "${random.nextInt(0, 9)}" +
                                "${random.nextInt(0, 9)}"
                    )
                )
            }
        }
        viewModel.apply {
            value.postValue("Test Value")
        }
    }

    companion object {
        val viewModel = TestViewModel()
    }

}