package com.juandev.lib.test

import com.juandev.lib.fragment.ViewDataBindingDialogFragment
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding

class TestDialogFragment : ViewDataBindingDialogFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

}