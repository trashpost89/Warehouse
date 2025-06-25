package com.example.warehouse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouse.data.models.ShipmentWithItems
import com.example.warehouse.databinding.ItemShipmentBinding

class ShipmentAdapter(
    private var shipments: List<ShipmentWithItems>,
    private val onItemClick: (ShipmentWithItems) -> Unit
) : RecyclerView.Adapter<ShipmentAdapter.ShipmentViewHolder>() {

    inner class ShipmentViewHolder(val binding: ItemShipmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        val binding = ItemShipmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShipmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        val shipment = shipments[position]
        holder.binding.apply {
            shipmentName.text = shipment.shipment.name
            shipmentDate.text = shipment.shipment.date
            // Теперь этот элемент существует в макете
            shipmentItemsCount.text = "Позиций: ${shipment.items.size}"
            root.setOnClickListener { onItemClick(shipment) }
        }
    }

    override fun getItemCount() = shipments.size

    fun updateList(newList: List<ShipmentWithItems>) {
        shipments = newList
        notifyDataSetChanged()
    }
}