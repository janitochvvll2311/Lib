package com.juandev.lib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

open class VDBRecyclerViewAdapter<VM : Any, VDB : ViewDataBinding>(
    val variableId: Int,
    @LayoutRes val layoutId: Int,
    var onBindListener: (RecyclerView.Adapter<*>.(binding: VDB, viewModel: VM, index: Int) -> Unit)? = null
) : RecyclerView.Adapter<VDBRecyclerViewAdapter<VM, VDB>.ViewHolder>() {

    inner class ViewHolder(
        val binding: VDB
    ) : RecyclerView.ViewHolder(binding.root)

    private val holders = mutableMapOf<Int, ViewHolder>()

    var items = listOf<VM>()
        set(value) {
            field = value
            notifyDataSetChanged()
            while (holders.size > field.size) {
                holders.remove(holders.size - 1)
            }
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d("VDBRecyclerViewAdapter_TAG: onCreateViewHolder")
        val binding = onBinding(parent)
        return ViewHolder(binding)
    }

    protected open fun onBinding(parent: ViewGroup): VDB {
        Timber.d("ViewDataBindingArrayAdapter_TAG: onBinding")
        val binding = DataBindingUtil.inflate<VDB>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return binding
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("VDBRecyclerViewAdapter_TAG: onBindViewHolder")
        val viewModel = items[position]
        onBindListener?.invoke(this, holder.binding, viewModel, position)
        holder.binding.setVariable(variableId, viewModel)
    }

}