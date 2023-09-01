package com.juandev.lib.popup

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import timber.log.Timber

class PopupLifeCycleOwner : LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    override val lifecycle get() = lifecycleRegistry

    fun onShow() {
        Timber.d("PopupLifeCycleOwner_TAG: onShow")
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    fun onDismiss() {
        Timber.d("PopupLifeCycleOwner_TAG: onDismiss")
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

}