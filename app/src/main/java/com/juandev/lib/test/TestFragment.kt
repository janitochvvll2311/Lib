package com.juandev.lib.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.juandev.lib.adapter.VDBArrayAdapter
import com.juandev.lib.adapter.VDBRecyclerViewAdapter
import com.juandev.lib.adapter.VDBSpinnerAdapter
import com.juandev.lib.fragment.VDBFragment
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding
import com.juandev.lib.test.databinding.TestItemLayoutBinding
import com.juandev.lib.utils.setOnItemSelectedListener
import timber.log.Timber

class TestFragment : VDBFragment<TestViewModel, TestFragmentLayoutBinding>(
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

    private val itemsLVAdapter by lazy {
        VDBArrayAdapter<TestItem, TestItemLayoutBinding>(
            requireContext(),
            BR.viewModel,
            R.layout.test_item_layout
        ) { binding, item, index ->
            binding.root.setOnClickListener {
                (it.parent as AdapterView<*>).performItemClick(it, index, 0)
            }
        }
    }

    private val itemsATVAdapter by lazy {
        VDBArrayAdapter<TestItem, TestItemLayoutBinding>(
            requireContext(),
            BR.viewModel,
            R.layout.test_item_layout
        ) { binding, item, index ->
            binding.root.setOnClickListener {
                (it.parent as AdapterView<*>).performItemClick(it, index, 0)
            }
        }
    }

    private val itemsSpinnerAdapter by lazy {
        VDBSpinnerAdapter<TestItem, TestItemLayoutBinding, TestItemLayoutBinding>(
            requireContext(),
            BR.viewModel,
            R.layout.test_item_layout,
            null,
            R.layout.test_item_layout
        ) { binding, item, index ->
            binding.root.setOnClickListener {
                (it.parent as AdapterView<*>).performItemClick(it, index, 0)
            }
        }
    }

    private val itemsRVAdapter by lazy {
        VDBRecyclerViewAdapter<TestItem, TestItemLayoutBinding>(
            BR.viewModel,
            R.layout.test_item_layout
        ) { binding, item, index ->
            binding.root.setOnClickListener {
                item.value = "CHANGED"
                notifyItemChanged(index)
            }
        }
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
                val item = adapterView.getItemAtPosition(i) as TestItem
                item.value = "CHANGED"
                itemsLVAdapter.notifyDataSetChanged()
            }
            atv.setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.getItemAtPosition(i) as TestItem
                viewModel.value.postValue(item.value)
            }
            spn.setOnItemSelectedListener { adapterView, view, i, l ->
                val item = adapterView.getItemAtPosition(i) as TestItem
                viewModel.value.postValue(item.value)
            }
        }
        viewModel.apply {
            items.observe(lifecycleOwner) { items ->
                itemsLVAdapter.clear()
                itemsLVAdapter.addAll(items)
                itemsATVAdapter.clear()
                itemsATVAdapter.addAll(items)
                itemsSpinnerAdapter.clear()
                itemsSpinnerAdapter.addAll(items)
                itemsRVAdapter.items = items
            }
        }
    }

    override fun onBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = super.onBinding(inflater, container).apply {
        Timber.d("TestFragment_TAG: onBinding")
        lv.adapter = itemsLVAdapter
        atv.setAdapter(itemsATVAdapter)
        spn.adapter = itemsSpinnerAdapter
        rv.adapter = itemsRVAdapter
    }

}