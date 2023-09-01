package com.juandev.lib.popup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.juandev.lib.VDBComponent
import timber.log.Timber

abstract class VDBPopup<VM : Any, VDB : ViewDataBinding>(
    val context: Context,
    final override val variableId: Int,
    @LayoutRes final override val layoutId: Int
) : PopupWindow(context), VDBComponent<VM, VDB> {

    final override val lifecycleOwner = PopupLifeCycleOwner()

    private lateinit var mbinding: VDB
    final override val binding: VDB get() = mbinding

    protected open fun onCreateContent() {
        Timber.d("VDBPopup_TAG: onCreateContent")
        lifecycleOwner.onShow()
        mbinding = onBinding()
        isFocusable = true
    }

    protected open fun onBinding(): VDB {
        Timber.d("VDBPopup_TAG: onBinding")
        val binding =
            DataBindingUtil.inflate<VDB>(LayoutInflater.from(context), layoutId, null, false)
        contentView = binding.root
        binding.lifecycleOwner = lifecycleOwner
        binding.setVariable(variableId, viewModel)
        return binding
    }

    override fun dismiss() {
        super.dismiss()
        Timber.d("VDBPopup_TAG: dismiss")
        lifecycleOwner.onDismiss()
    }

    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        Timber.d("VDBPopup_TAG: showAtLocation")
        onCreateContent()
        super.showAtLocation(parent, gravity, x, y)
    }

    override fun showAsDropDown(anchor: View?) {
        Timber.d("VDBPopup_TAG: showAsDropDown")
        onCreateContent()
        super.showAsDropDown(anchor)
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int) {
        Timber.d("VDBPopup_TAG: showAsDropDown")
        onCreateContent()
        super.showAsDropDown(anchor, xoff, yoff)
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        Timber.d("VDBPopup_TAG: showAsDropDown")
        onCreateContent()
        super.showAsDropDown(anchor, xoff, yoff, gravity)
    }

}