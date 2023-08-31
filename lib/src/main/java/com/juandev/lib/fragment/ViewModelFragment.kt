package com.juandev.lib.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.juandev.lib.ViewModelComponent
import timber.log.Timber

abstract class ViewModelFragment<VM : Any, VDB : ViewDataBinding>(
    final override val variableId: Int,
    @LayoutRes layoutId: Int
) : ViewDataBindingFragment<VDB>(layoutId), ViewModelComponent<VM> {

    override fun onBinding(inflater: LayoutInflater, container: ViewGroup?) =
        super.onBinding(inflater, container).apply {
            Timber.d("ViewModelFragment_TAG: onBinding")
            setVariable(variableId, viewModel)
        }

}