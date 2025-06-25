package com.example.warehouse.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehouse.databinding.FragmentOrderBinding
import com.example.warehouse.db.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.*

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        dbHelper = DatabaseHelper(requireContext())

        setupRecyclerView()
        setupAddButton()

        return binding.root
    }

    private fun setupRecyclerView() {
        val orders = dbHelper.getAllOrders()
        val adapter = OrderAdapter(orders)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupAddButton() {
        binding.fabAddOrder.setOnClickListener {
            AddOrderItemDialog().show(
                parentFragmentManager,
                "AddOrderItemDialog"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}