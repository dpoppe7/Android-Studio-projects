package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

import android.util.Log             //Debug
import android.widget.*

const val TAG = "MainActivity"
const val SLICES_PER_PIZZA = 8

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView

    private var slicesPerPerson: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate was called")

        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)

        val sizes = resources.getStringArray(R.array.sizes_array)

        val spinner = findViewById<Spinner>(R.id.spinner_size)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, sizes)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + sizes[position], Toast.LENGTH_SHORT).show()
                    if (position == 0) slicesPerPerson = 2
                    if (position == 1) slicesPerPerson = 3
                    if (position == 2) slicesPerPerson = 4

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(this@MainActivity, "Nothing selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()
        Log.d(TAG, "number is $numAttendStr")

        // Convert the text into an integer
        //val numAttend = numAttendStr.toInt()
        /* Replacing with:
            toIntOrNull() returns null if the string argument cannot be converted into an integer.
            The Elvis operator ?: returns the right expression (0) when the left expression returns null. */

        val numAttend = numAttendStr.toIntOrNull() ?: 0     //No longer crashes when no number is entered.

        // Determine how many slices on average each person will eat




        // Calculate and show the number of pizzas needed
        val totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }


}