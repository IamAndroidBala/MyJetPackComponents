package com.android.myjetpackcomponent.databinding.viewmodel

import androidx.lifecycle.ViewModel
import com.android.myjetpackcomponent.databinding.model.NamesModel

class NamesViewModel: ViewModel() {

    var groceryListItems: ArrayList<NamesModel> = ArrayList()

    fun removeItem(position: Int) {
        groceryListItems.removeAt(position)
    }

    fun updateItem(position: Int, updatedItem: NamesModel) {
        groceryListItems[position] = updatedItem
    }

}