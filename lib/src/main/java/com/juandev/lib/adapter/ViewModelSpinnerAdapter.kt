package com.juandev.lib.adapter

import android.content.Context
import android.widget.Adapter
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import timber.log.Timber

class ViewModelSpinnerAdapter<VM : Any, VDB : ViewDataBinding, DDVDB : ViewDataBinding>(
    context: Context,
    val variableId: Int,
    @LayoutRes layoutId: Int,
    onBindListener: (Adapter.(binding: VDB, viewModel: VM, index: Int) -> Unit)? = null,
    @LayoutRes dropDownLayoutId: Int,
    onBindDropDownListener: (Adapter.(binding: DDVDB, viewModel: VM, index: Int) -> Unit)? = null
) : ViewDataBindingSpinnerAdapter<VM, VDB, DDVDB>(
    context,
    layoutId,
    onBindListener,
    dropDownLayoutId,
    onBindDropDownListener
) {

    override fun onBind(binding: VDB, item: VM, index: Int) {
        super.onBind(binding, item, index)
        Timber.d("ViewModelArrayAdapter_TAG: onBind")
        binding.setVariable(variableId, item)
    }

    override fun onBindDropDown(binding: DDVDB, item: VM, index: Int) {
        super.onBindDropDown(binding, item, index)
        Timber.d("ViewModelArrayAdapter_TAG: onBind")
        binding.setVariable(variableId, item)
    }

}