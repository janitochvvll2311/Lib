package com.juandev.lib.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.juandev.lib.adapter.ViewModelArrayAdapter
import com.juandev.lib.adapter.ViewModelSpinnerAdapter
import com.juandev.lib.fragment.ViewModelFragment
import com.juandev.lib.showDialog
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding
import com.juandev.lib.test.databinding.TestItemLayoutBinding
import timber.log.Timber

class TestFragment : ViewModelFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

    private val itemsAdapter by lazy {
        ViewModelArrayAdapter<TestItem, TestItemLayoutBinding>(
            requireContext(),
            BR.viewModel,
            R.layout.test_item_layout
        )
    }

    private val itemsSpinnerAdapter by lazy {
        ViewModelSpinnerAdapter<TestItem, TestItemLayoutBinding, TestItemLayoutBinding>(
            requireContext(),
            BR.viewModel,
            R.layout.test_item_layout,
            null,
            R.layout.test_item_layout,
            null
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = super.onCreateView(inflater, container, savedInstanceState).apply {
        Timber.d("TestFragment_TAG: onCreateView")
        binding.apply {
            val viewModel = viewModel!!
            lv.setOnItemClickListener { adapterView, view, i, l ->
                val item = itemsAdapter.getItem(i) as TestItem
                item.value = "CHANGED"
                itemsAdapter.notifyDataSetChanged()
            }
            atv.setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.getItemAtPosition(i) as TestItem
                viewModel.value.postValue(item.value)
            }
            spn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    i: Int,
                    l: Long
                ) {
                    adapterView?.run {
                        val item = getItemAtPosition(i) as TestItem
                        viewModel.value.postValue(item.value)
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            }
        }
        viewModel.apply {
            items.observe(lifecycleOwner) { items ->
                itemsAdapter.clear()
                itemsAdapter.addAll(items)
                itemsSpinnerAdapter.clear()
                itemsSpinnerAdapter.addAll(items)
            }
        }
    }

    override fun onBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = super.onBinding(inflater, container).apply {
        Timber.d("TestFragment_TAG: onBinding")
        lv.adapter = itemsAdapter
        atv.setAdapter(itemsAdapter)
        spn.adapter = itemsSpinnerAdapter
    }

}