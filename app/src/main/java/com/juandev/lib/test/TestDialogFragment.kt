package com.juandev.lib.test

import com.juandev.lib.fragment.VDBDialogFragment
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding

class TestDialogFragment : VDBDialogFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

}