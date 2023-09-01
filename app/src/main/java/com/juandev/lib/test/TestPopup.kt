package com.juandev.lib.test

import android.content.Context
import android.widget.AdapterView
import com.juandev.lib.adapter.VDBArrayAdapter
import com.juandev.lib.adapter.VDBRecyclerViewAdapter
import com.juandev.lib.adapter.VDBSpinnerAdapter
import com.juandev.lib.popup.VDBPopup
import com.juandev.lib.test.databinding.TestFragmentLayoutBinding
import com.juandev.lib.test.databinding.TestItemLayoutBinding
import com.juandev.lib.utils.setItems
import com.juandev.lib.utils.setOnItemSelectedListener
import timber.log.Timber


class TestPopup(
    context: Context
) : VDBPopup<TestViewModel, TestFragmentLayoutBinding>(
    context,
    BR.viewModel,
    R.layout.test_fragment_layout
) {

    override val viewModel get() = TestActivity.viewModel

    private val itemsLVAdapter by lazy {
        VDBArrayAdapter<TestItem, TestItemLayoutBinding>(
            context,
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
            context,
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
            context,
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
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateContent() {
        super.onCreateContent()
        Timber.d("TestPopup_TAG: onCreateContent")
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

    override fun onBinding() = super.onBinding().apply {
        Timber.d("TestPopup_TAG: onBinding")
        lv.adapter = itemsLVAdapter
        atv.setAdapter(itemsATVAdapter)
        spn.adapter = itemsSpinnerAdapter
        rv.adapter = itemsRVAdapter
    }

}