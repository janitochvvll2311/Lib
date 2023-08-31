package com.juandev.lib.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.juandev.lib.ViewDataBindingComponent
import timber.log.Timber

open class ViewDataBindingDialogFragment<VDB : ViewDataBinding>(
    @LayoutRes final override val layoutId: Int
) : Fragment(), ViewDataBindingComponent<VDB> {

    private lateinit var mbinding: VDB
    final override val binding: VDB get() = mbinding
    final override val lifecycleOwner get() = viewLifecycleOwner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        Timber.d("ViewDataBindingDialogFragment_TAG: onCreateView")
        mbinding = onBinding(inflater, container)
        return mbinding.root
    }

    protected open fun onBinding(inflater: LayoutInflater, container: ViewGroup?): VDB {
        Timber.d("ViewDataBindingDialogFragment_TAG: onBinding")
        val binding = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false)
        binding.lifecycleOwner = lifecycleOwner
        return binding
    }

}