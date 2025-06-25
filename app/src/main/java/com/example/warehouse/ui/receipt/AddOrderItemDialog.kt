package com.example.warehouse.ui.order

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.warehouse.databinding.DialogAddOrderItemBinding
import com.example.warehouse.db.DatabaseHelper

class AddOrderItemDialog : DialogFragment() {
    private lateinit var binding: DialogAddOrderItemBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddOrderItemBinding.inflate(requireActivity().layoutInflater)
        dbHelper = DatabaseHelper(requireContext())

        setupAutoComplete()
        setupBoxSpinner()

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Добавить позицию в заказ")
            .setPositiveButton("Добавить") { _, _ -> saveOrderItem() }
            .setNegativeButton("Отмена", null)
            .create()
    }

    private fun setupAutoComplete() {
        val products = dbHelper.getAllInventory().map { it.name }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            products
        )
        binding.etProductName.setAdapter(adapter)
    }

    private fun setupBoxSpinner() {
        binding.etProductName.setOnItemClickListener { _, _, position, _ ->
            val selectedProduct = binding.etProductName.adapter.getItem(position).toString()
            val boxes = dbHelper.getBoxesForProduct(selectedProduct)
            val boxAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                boxes
            )
            binding.spinnerBox.adapter = boxAdapter
        }
    }

    private fun saveOrderItem() {
        // Логика сохранения заказа
    }
}