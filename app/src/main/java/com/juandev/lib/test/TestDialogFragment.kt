package com.juandev.lib.test

import com.juandev.lib.fragment.ViewModelDialogFragment
import com.juandev.lib.fragment.ViewModelFragment
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding

class TestDialogFragment : ViewModelDialogFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

}