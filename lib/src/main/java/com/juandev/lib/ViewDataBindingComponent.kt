package com.juandev.lib

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

interface ViewDataBindingComponent<VDB : ViewDataBinding> {
    val layoutId: Int
    val lifecycleOwner: LifecycleOwner
    val binding: VDB
}