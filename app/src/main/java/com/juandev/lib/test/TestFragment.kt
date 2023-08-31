package com.juandev.lib.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juandev.lib.fragment.ViewModelFragment
import com.juandev.lib.showDialog
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding
import timber.log.Timber

class TestFragment : ViewModelFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = super.onCreateView(inflater, container, savedInstanceState).apply {
        Timber.d("TestFragment_TAG: onCreateView")
        binding.root.setOnClickListener {
            showDialog(TestDialogFragment())
        }
    }

}