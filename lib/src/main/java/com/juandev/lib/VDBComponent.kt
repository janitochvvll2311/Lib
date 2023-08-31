package com.juandev.lib

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

interface VDBComponent<VM : Any, VDB : ViewDataBinding> {
    val lifecycleOwner: LifecycleOwner

    val variableId: Int
    val viewModel: VM

    val layoutId: Int
    val binding: VDB
}