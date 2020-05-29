package com.android.myjetpackcomponent.databinding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.databinding.adapter.NamesAdapter
import com.android.myjetpackcomponent.databinding.model.NamesModel
import com.android.myjetpackcomponent.databinding.viewmodel.NamesViewModel
import com.android.myjetpackcomponent.views.BaseActivity
import com.google.android.material.snackbar.Snackbar

class DataBindingActivity : BaseActivity(), NewItemDialogFragment.NewItemDialogListener  {

    lateinit var viewModel: NamesViewModel
    private lateinit var binding: ActivityDatabindingBinding

    override fun contentView() = R.layout.activity_coroutine

    override fun setScreenTitle() = getString(R.string.data_bind)

    override fun toolbarBackPressed() = onBackPressed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NamesViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding)

        binding.rvGroceryList.layoutManager = LinearLayoutManager(this)

        binding.rvGroceryList.adapter = NamesAdapter(viewModel.groceryListItems, this, ::editGroceryItem, ::deleteGroceryItem)

        binding.addItemButton.setOnClickListener {
            addGroceryItem()
        }

    }

    private fun addGroceryItem() {
        val newFragment = NewItemDialogFragment.newInstance(R.string.add_item, null)
        newFragment.show(supportFragmentManager, "newItem")
    }

    private fun editGroceryItem(position: Int) {
        val newFragment = NewItemDialogFragment.newInstance(R.string.edit_item, position)
        newFragment.show(supportFragmentManager, "newItem")
    }

    private fun deleteGroceryItem(position: Int) {
        viewModel.removeItem(position)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, item: NamesModel, isEdit: Boolean, position: Int?) {
        if (!isEdit) {
            viewModel.groceryListItems.add(item)
        } else {
            viewModel.updateItem(position!!, item)
            binding.rvGroceryList.adapter?.notifyDataSetChanged()
        }

        Snackbar.make(binding.addItemButton, "Item Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Snackbar.make(binding.addItemButton, "Nothing Added", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

}