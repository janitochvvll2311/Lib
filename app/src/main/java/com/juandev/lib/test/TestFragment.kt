package com.juandev.lib.test

import com.juandev.lib.fragment.ViewModelFragment
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding

class TestFragment : ViewModelFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

}