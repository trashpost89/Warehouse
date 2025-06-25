package com.example.ui.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.adapters.ReceiptAdapter

class ReceiptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_receipt, container, false)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.receiptsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ReceiptAdapter(emptyList())

        view.findViewById<View>(R.id.addReceiptButton).setOnClickListener {
            AddReceiptItemDialog().show(parentFragmentManager, "AddReceiptItemDialog")
        }

        return view
    }
}