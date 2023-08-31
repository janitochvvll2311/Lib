package com.juandev.lib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import timber.log.Timber

open class ViewDataBindingSpinnerAdapter<VM : Any, VDB : ViewDataBinding, DDVDB : ViewDataBinding>(
    context: Context,
    variableId: Int,
    @LayoutRes layoutId: Int,
    onBindListener: (Adapter.(binding: VDB, item: VM, index: Int) -> Unit)? = null,
    @LayoutRes val dropDownLayoutId: Int,
    var onBindDropDownListener: (Adapter.(binding: DDVDB, viewModel: VM, index: Int) -> Unit)? = null,
) : ViewDataBindingArrayAdapter<VM, VDB>(context, variableId, layoutId, onBindListener) {

    private val bindings = mutableMapOf<Int, DDVDB>()

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        Timber.d("ViewDataBindingSpinnerAdapter_TAG: getDropDownView")
        val viewModel = getItem(position) as VM
        var binding = bindings[position]
        if (binding == null) {
            binding = onBindingDropDown(parent)
            bindings[position] = binding
        }
        onBindDropDownListener?.invoke(this, binding, viewModel, position)
        binding.setVariable(variableId, viewModel)
        return binding.root
    }

    protected open fun onBindingDropDown(parent: ViewGroup): DDVDB {
        Timber.d("ViewDataBindingSpinnerAdapter_TAG: onBindingDropDown")
        val binding = DataBindingUtil.inflate<DDVDB>(
            LayoutInflater.from(context),
            dropDownLayoutId,
            parent,
            false
        )
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return binding
    }

}