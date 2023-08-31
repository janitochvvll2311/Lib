package com.juandev.lib

import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber

interface DialogDisplayComponent {
    val dialogFragmentManager: FragmentManager
}

fun DialogDisplayComponent.showDialog(fragment: AppCompatDialogFragment) {
    Timber.d("DialogDisplayComponent_TAG: showDialog")
    fragment.show(dialogFragmentManager, "__dialog_fragment")
}