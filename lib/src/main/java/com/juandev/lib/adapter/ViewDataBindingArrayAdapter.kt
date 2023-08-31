package com.juandev.lib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import timber.log.Timber

open class ViewDataBindingArrayAdapter<IT : Any, VDB : ViewDataBinding>(
    context: Context,
    @LayoutRes val layoutId: Int,
    var onBindListener: (Adapter.(binding: VDB, item: IT, index: Int) -> Unit)? = null
) : ArrayAdapter<IT>(context, 0) {

    private val bindings = mutableMapOf<Int, VDB>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Timber.d("ViewDataBindingArrayAdapter_TAG: getView")
        var binding = bindings[position]
        if (binding == null) {
            binding = onBinding(parent)
            bindings[position] = binding
        }
        val item = getItem(position) as IT
        onBind(binding, item, position)
        return binding.root
    }

    protected open fun onBinding(parent: ViewGroup): VDB {
        Timber.d("ViewDataBindingArrayAdapter_TAG: onBinding")
        val binding = DataBindingUtil.inflate<VDB>(
            LayoutInflater.from(context),
            layoutId,
            parent,
            false
        )
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return binding
    }

    protected open fun onBind(binding: VDB, item: IT, index: Int) {
        Timber.d("ViewDataBindingArrayAdapter_TAG: onBind")
        onBindListener?.invoke(this, binding, item, index)
    }

}