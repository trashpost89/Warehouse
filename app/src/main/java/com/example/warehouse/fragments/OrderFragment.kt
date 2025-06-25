package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.adapters.OrderAdapter
import com.example.db.DatabaseHelper

class OrderFragment : Fragment() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var addOrderButton: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        
        ordersRecyclerView = view.findViewById(R.id.ordersRecyclerView)
        addOrderButton = view.findViewById(R.id.addOrderButton)

        val dbHelper = DatabaseHelper(requireContext())
        val orders = dbHelper.getOrders()

        ordersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ordersRecyclerView.adapter = OrderAdapter(orders)
        ordersRecyclerView.setHasFixedSize(true)

        addOrderButton.setOnClickListener {
            // Open add order dialog
        }

        return view
    }
}