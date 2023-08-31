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

open class VDBArrayAdapter<VM : Any, VDB : ViewDataBinding>(
    context: Context,
    val variableId: Int,
    @LayoutRes val layoutId: Int,
    var onBindListener: (Adapter.(binding: VDB, viewModel: VM, index: Int) -> Unit)? = null
) : ArrayAdapter<VM>(context, 0) {

    private val bindings = mutableMapOf<Int, VDB>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Timber.d("ViewDataBindingArrayAdapter_TAG: getView")
        val viewModel = getItem(position) as VM
        var binding = bindings[position]
        if (binding == null) {
            binding = onBinding(parent)
            bindings[position] = binding
        }
        onBindListener?.invoke(this, binding, viewModel, position)
        binding.setVariable(variableId, viewModel)
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

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        Timber.d("VDBArrayAdapter_TAG: notifyDataSetChanged")
        while (bindings.size > count) {
            bindings.remove(bindings.size - 1)
        }
    }

}