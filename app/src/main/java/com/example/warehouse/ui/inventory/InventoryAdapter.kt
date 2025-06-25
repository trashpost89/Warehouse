package com.example.warehouse.ui.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouse.databinding.ItemInventoryBinding
import com.example.warehouse.db.DatabaseHelper.InventoryItem

class InventoryAdapter(private val items: List<InventoryItem>) : 
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemInventoryBinding) : 
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInventoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.name.text = item.name
        holder.binding.box.text = item.box
        holder.binding.quantity.text = item.quantity.toString()
    }

    override fun getItemCount() = items.size
}