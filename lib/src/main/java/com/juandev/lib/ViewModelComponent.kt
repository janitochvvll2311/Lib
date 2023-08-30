package com.juandev.lib

import androidx.lifecycle.LifecycleOwner

interface ViewModelComponent<VM : Any> {
    val variableId: Int
    val lifecycleOwner: LifecycleOwner
    val viewModel: VM
}