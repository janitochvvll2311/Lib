package com.juandev.lib.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.juandev.lib.DialogDisplayComponent
import com.juandev.lib.ViewDataBindingComponent
import timber.log.Timber

abstract class VDBDialogFragment<VM : Any, VDB : ViewDataBinding>(
    final override val variableId: Int,
    @LayoutRes final override val layoutId: Int
) : AppCompatDialogFragment(), ViewDataBindingComponent<VM, VDB>, DialogDisplayComponent {

    final override val lifecycleOwner get() = viewLifecycleOwner

    private lateinit var mbinding: VDB
    final override val binding: VDB get() = mbinding

    final override val dialogFragmentManager get() = childFragmentManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        Timber.d("ViewDataBindingDialogFragment_TAG: onCreateView")
        mbinding = onBinding(inflater, container)
        binding.setVariable(variableId, viewModel)
        return mbinding.root
    }

    protected open fun onBinding(inflater: LayoutInflater, container: ViewGroup?): VDB {
        Timber.d("ViewDataBindingDialogFragment_TAG: onBinding")
        val binding = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false)
        binding.lifecycleOwner = lifecycleOwner
        return binding
    }

}