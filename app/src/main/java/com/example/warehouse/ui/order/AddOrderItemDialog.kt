package com.example.ui.order

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.R

class AddOrderItemDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_order_item)
            .setView(R.layout.dialog_add_order_item)
            .setPositiveButton(R.string.add) { _, _ -> }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
    }
}