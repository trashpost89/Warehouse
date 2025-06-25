package com.example.warehouse.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.warehouse.R

class InventoryFragment : Fragment(), SearchView.OnQueryTextListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // Handle search submit
        filterProducts(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Handle text change
        filterProducts(newText)
        return true
    }

    private fun filterProducts(query: String?) {
        // Implement your product filtering logic here
        query?.let {
            // Filter products based on the query
            // Update your RecyclerView adapter with filtered results
        }
    }
}