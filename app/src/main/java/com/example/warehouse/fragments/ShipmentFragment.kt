package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.adapters.ShipmentAdapter
import com.example.db.entity.ShipmentWithItems

class ShipmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_shipment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.shipmentsRecyclerView)

        // Пример исправленного observe с явным указанием типа
        viewModel.shipments.observe(viewLifecycleOwner, Observer<List<ShipmentWithItems>> { shipments ->
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = ShipmentAdapter(shipments)
        })

        view.findViewById<View>(R.id.addShipmentButton).setOnClickListener {
            // Open add shipment dialog
        }

        return view
    }
}