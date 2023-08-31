package com.juandev.lib.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import timber.log.Timber

class ViewModelArrayAdapter<VM : Any, VDB : ViewDataBinding>(
    context: Context,
    val variableId: Int,
    @LayoutRes layoutId: Int,
    onBindListener: (Adapter.(binding: VDB, viewModel: VM, index: Int) -> Unit)? = null
) : ViewDataBindingArrayAdapter<VM, VDB>(context, layoutId, onBindListener) {

    override fun onBind(binding: VDB, item: VM, index: Int) {
        Timber.d("ViewModelArrayAdapter_TAG: onBind")
        binding.setVariable(variableId, item)
        super.onBind(binding, item, index)
    }

}