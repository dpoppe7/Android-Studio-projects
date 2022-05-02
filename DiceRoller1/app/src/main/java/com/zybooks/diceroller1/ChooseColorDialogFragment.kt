package com.zybooks.diceroller1

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ChooseColorDialogFragment : DialogFragment() {

    interface OnChooseColorSelectedListener {
        fun diceColor(which: Int)
    }

    private lateinit var listener: OnChooseColorSelectedListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.action_color)
        builder.setItems(R.array.color_array) { dialog, which ->
            // 'which' is the zero-based index position chosen
            listener.diceColor(which)
        }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnChooseColorSelectedListener
    }
}