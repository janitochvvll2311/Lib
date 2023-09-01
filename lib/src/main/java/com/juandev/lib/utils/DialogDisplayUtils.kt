package com.juandev.lib.utils

import androidx.appcompat.app.AppCompatDialogFragment
import com.juandev.lib.DialogDisplayComponent
import timber.log.Timber

fun DialogDisplayComponent.showDialog(fragment: AppCompatDialogFragment) {
    Timber.d("DialogDisplayComponent_TAG: showDialog")
    fragment.show(dialogFragmentManager, "__dialog_fragment")
}