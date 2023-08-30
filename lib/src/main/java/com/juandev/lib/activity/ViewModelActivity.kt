package com.juandev.lib.activity

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.juandev.lib.ViewModelComponent
import timber.log.Timber

abstract class ViewModelActivity<VM : Any, VDB : ViewDataBinding>(
    final override val variableId: Int,
    @LayoutRes layoutId: Int
) : ViewDataBindingActivity<VDB>(layoutId), ViewModelComponent<VM> {

    override fun onBinding() = super.onBinding().apply {
        Timber.d("ViewModelActivity_TAG: onBinding")
        setVariable(variableId, viewModel)
    }

}