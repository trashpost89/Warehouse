package com.example.warehouse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouse.R
import com.example.warehouse.db.entity.Order

class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.orderNameTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.orderDateTextView)
        val countTextView: TextView = itemView.findViewById(R.id.orderItemsCountTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.nameTextView.text = order.name
        holder.dateTextView.text = order.date
        holder.countTextView.text = order.items.size.toString()
    }

    override fun getItemCount(): Int = orders.size
}