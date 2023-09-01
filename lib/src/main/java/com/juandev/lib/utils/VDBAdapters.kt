package com.juandev.lib.utils

import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.ListView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juandev.lib.adapter.VDBItemsAdapter

@BindingAdapter("items")
fun <T> AdapterView<*>.setItems(items: List<T>?) {
    val adapter = this.adapter as? VDBItemsAdapter<T>
    adapter?.run {
        fill(items ?: emptyList())
    }
}

@BindingAdapter("items")
fun <T> ListView.setItems(items: List<T>?) {
    val adapter = this.adapter as? VDBItemsAdapter<T>
    adapter?.run {
        fill(items ?: emptyList())
    }
}

@BindingAdapter("items")
fun <T> AutoCompleteTextView.setItems(items: List<T>?) {
    val adapter = this.adapter as? VDBItemsAdapter<T>
    adapter?.run {
        fill(items ?: emptyList())
    }
}

@BindingAdapter("items")
fun <T> AppCompatSpinner.setItems(items: List<T>?) {
    val adapter = this.adapter as? VDBItemsAdapter<T>
    adapter?.run {
        fill(items ?: emptyList())
    }
}

@BindingAdapter("items")
fun <T> RecyclerView.setItems(items: List<T>?) {
    val adapter = this.adapter as? VDBItemsAdapter<T>
    adapter?.run {
        fill(items ?: emptyList())
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////

@BindingAdapter("value", "holder")
fun <T> AppCompatTextView.setValue(value: T?, holder: String?) {
    text = value?.toString() ?: holder ?: ""
}