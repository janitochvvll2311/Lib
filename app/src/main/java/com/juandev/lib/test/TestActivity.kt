package com.juandev.lib.test

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.juandev.lib.test.databinding.TestActivityLayoutBinding
import timber.log.Timber

class TestActivity : AppCompatActivity() {

    lateinit var binding: TestActivityLayoutBinding

    companion object {
        val viewModel = TestViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TestActivity_TAG: onCreate")
        setContentView(R.layout.test_activity_layout)
        binding = DataBindingUtil.setContentView(this, R.layout.test_activity_layout)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.value.postValue("Test Value")
    }

}