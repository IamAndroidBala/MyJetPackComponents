package com.android.myjetpackcomponent.databinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.databinding.ItemDatabindNamesBinding
import com.android.myjetpackcomponent.databinding.model.NamesModel

class NamesAdapter(val items: ArrayList<NamesModel>, private val context: Context,
                   val itemEditListener: (position: Int) -> Unit,
                   val itemDeleteListener: (position: Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemDatabindNamesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_databind_names, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.buttonEdit.setOnClickListener { itemEditListener(position) }
        holder.binding.buttonDelete.setOnClickListener { itemDeleteListener(position) }
    }
}

class ViewHolder(val binding: ItemDatabindNamesBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NamesModel) {
        binding.apply {
            itemName = item.name
        }
    }
}