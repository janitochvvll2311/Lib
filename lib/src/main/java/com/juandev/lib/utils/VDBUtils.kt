package com.juandev.lib.utils

import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import timber.log.Timber

fun AppCompatSpinner.setOnItemSelectedListener(l: (adapterView: AdapterView<*>, view: View?, i: Int, l: Long) -> Unit) {
    Timber.d("AppCompatSpinner_TAG: setOnItemSelectedListener")
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            adapterView: AdapterView<*>?,
            view: View?,
            i: Int,
            l: Long
        ) = if(adapterView != null)  l(adapterView, view, i, l) else {}

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }
}