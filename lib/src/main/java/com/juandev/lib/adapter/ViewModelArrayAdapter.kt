package com.juandev.lib.adapter

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

class ViewModelArrayAdapter<VM : Any, VDB : ViewDataBinding>(
    context: Context,
    val variableId: Int,
    @LayoutRes layoutId: Int,
    private val onBind: ViewModelArrayAdapter<VM, VDB>.(binding: VDB, viewModel: VM, index: Int) -> Unit = { _, _, _ -> }
) : ViewDataBindingArrayAdapter<VM, VDB>(context, layoutId, { binding, item, index ->
    val adapter = (this as ViewModelArrayAdapter<VM, VDB>)
    binding.setVariable(adapter.variableId, item)
    adapter.onBind(binding, item, index)
})