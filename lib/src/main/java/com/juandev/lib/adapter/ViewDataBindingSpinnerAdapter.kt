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

open class ViewDataBindingSpinnerAdapter<IT : Any, VDB : ViewDataBinding, DDVDB : ViewDataBinding>(
    context: Context,
    @LayoutRes layoutId: Int,
    onBindListener: (Adapter.(binding: VDB, item: IT, index: Int) -> Unit)? = null,
    @LayoutRes dropDownLayoutId: Int,
    var onBindDropDownListener: (Adapter.(binding: DDVDB, item: IT, index: Int) -> Unit)? = null,
) : ViewDataBindingArrayAdapter<IT, VDB>(context, layoutId, onBindListener) {

    private val bindings = mutableMapOf<Int, DDVDB>()

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        Timber.d("ViewDataBindingSpinnerAdapter_TAG: getDropDownView")
        var binding = bindings[position]
        if (binding == null) {
            binding = onBindingDropDown(parent)
            bindings[position] = binding
        }
        val item = getItem(position) as IT
        onBindDropDown(binding, item, position)
        return binding.root
    }

    protected open fun onBindingDropDown(parent: ViewGroup): DDVDB {
        Timber.d("ViewDataBindingSpinnerAdapter_TAG: onBindingDropDown")
        val binding = DataBindingUtil.inflate<DDVDB>(
            LayoutInflater.from(context),
            layoutId,
            parent,
            false
        )
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return binding
    }

    protected open fun onBindDropDown(binding: DDVDB, item: IT, index: Int) {
        Timber.d("ViewDataBindingSpinnerAdapter_TAG: onBindDropDown")
        onBindDropDownListener?.invoke(this, binding, item, index)
    }

}