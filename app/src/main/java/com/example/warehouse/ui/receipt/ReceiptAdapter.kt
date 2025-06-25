package com.example.warehouse.ui.receipt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouse.databinding.ItemReceiptBinding
import com.example.warehouse.db.DatabaseHelper.ReceiptItem
import java.text.SimpleDateFormat
import java.util.*

class ReceiptAdapter(private val receipts: List<ReceiptItem>) : 
    RecyclerView.Adapter<ReceiptAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    inner class ViewHolder(val binding: ItemReceiptBinding) : 
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReceiptBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receipt = receipts[position]
        holder.binding.tvReceiptName.text = "Поступление-${dateFormat.format(receipt.date)}"
        holder.binding.tvProductName.text = receipt.productName
        holder.binding.tvBox.text = receipt.box
        holder.binding.tvQuantity.text = receipt.quantity.toString()
    }

    override fun getItemCount() = receipts.size
}