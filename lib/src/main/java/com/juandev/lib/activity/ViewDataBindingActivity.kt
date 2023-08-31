package com.juandev.lib.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.juandev.lib.DialogDisplayComponent
import com.juandev.lib.ViewDataBindingComponent
import timber.log.Timber

open class ViewDataBindingActivity<VDB : ViewDataBinding>(
    @LayoutRes final override val layoutId: Int
) : AppCompatActivity(), ViewDataBindingComponent<VDB>, DialogDisplayComponent {

    private lateinit var mbinding: VDB
    final override val binding: VDB get() = mbinding
    final override val lifecycleOwner get() = this
    final override val dialogFragmentManager get() = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("ViewBindingActivity_TAG: onCreate")
        mbinding = onBinding()
    }

    protected open fun onBinding(): VDB {
        Timber.d("ViewBindingActivity_TAG: onBinding")
        val binding = DataBindingUtil.setContentView<VDB>(this, layoutId)
        binding.lifecycleOwner = lifecycleOwner
        return binding
    }

}