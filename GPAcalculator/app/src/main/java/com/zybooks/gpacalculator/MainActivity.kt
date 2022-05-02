package com.zybooks.gpacalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


//import javax.swing.text.TableView


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var grade1EditText: EditText
    private lateinit var grade2EditText: EditText
    private lateinit var grade3EditText: EditText
    private lateinit var grade4EditText: EditText
    private lateinit var grade5EditText: EditText


    private lateinit var totalGPATextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalGPATextView = findViewById(R.id.total_gpa_text_view)

        Log.d(TAG, "onCreate was called")

        grade1EditText = findViewById(R.id.grade1_edit_text)
        grade2EditText = findViewById(R.id.grade2_edit_text)
        grade3EditText = findViewById(R.id.grade3_edit_text)
        grade4EditText = findViewById(R.id.grade4_edit_text)
        grade5EditText = findViewById(R.id.grade5_edit_text)

    }

    private fun gradeToPoint(grade: String): Int {
        return when (grade) {
            "A" -> 4
            "B" -> 3
            "C" -> 2
            "D" -> 1
            "F" -> 0
            "" -> -1
            else -> -2
        }
    }

    fun calculateClick(view: View) {
        //convert grade String to point
        val point1 = gradeToPoint(grade1EditText.text.toString())
        val point2 = gradeToPoint(grade2EditText.text.toString())
        val point3 = gradeToPoint(grade3EditText.text.toString())
        val point4 = gradeToPoint(grade4EditText.text.toString())
        val point5 = gradeToPoint(grade5EditText.text.toString())

        val gradesArray = ArrayList<Int>(5)

        var valid:Boolean = true

        //Add grade points to gradesArray if input is either ("A", "B", "C", "D", "F")
        //If input is empty (gradeToPoint returns -1), ignore
        //Otherwise print "Grade (number): is an invalid input"
        if (point1 > -1) {
            gradesArray.add(point1)
        }
        else {
            if (point1 == -2) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.grade_1) + " is an invalid Input", Toast.LENGTH_SHORT
                ).show()

                valid = false
            }
        }

        if (point2 > -1) {
            gradesArray.add(point2)
        }
        else {
            if (point2 == -2) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.grade_2) + " is an invalid Input", Toast.LENGTH_SHORT
                ).show()
                valid = false
            }
        }

        if (point3 > -1) {
            gradesArray.add(point3)
        }
        else {
            if (point3 == -2) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.grade_3) + " is an invalid Input", Toast.LENGTH_SHORT
                ).show()
                valid = false
            }
        }

        if (point4 > -1) {
            gradesArray.add(point4)
        }
        else {
            if (point4 == -2) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.grade_4) + " is an invalid Input", Toast.LENGTH_SHORT
                ).show()
                valid = false
            }
        }

        if (point5 > -1) {
            gradesArray.add(point5)
        }
        else {
            if (point5 == -2) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.grade_5) + " is an invalid Input", Toast.LENGTH_SHORT
                ).show()
                valid = false
            }
        }


        if (valid) {
            //add all grade values in gradesArray
            var tempSum = 0
            for (i in gradesArray) {
                tempSum += i
            }

            //calculate gpa
            val gpa = String.format("%.2f", (tempSum) / gradesArray.size.toFloat())
            totalGPATextView.text = gpa
        }
        else{
            totalGPATextView.text = getString(R.string.invalid)
        }
    }



}