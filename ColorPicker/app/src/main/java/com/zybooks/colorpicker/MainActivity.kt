package com.zybooks.colorpicker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var redSeekBar: SeekBar
    lateinit var greenSeekBar: SeekBar
    lateinit var blueSeekBar: SeekBar
    lateinit var colorView: View
    lateinit var RGB: TextView

    var red = 0
    var green = 0
    var blue = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redSeekBar = findViewById<SeekBar>(R.id.red_seek_bar)
        greenSeekBar = findViewById<SeekBar>(R.id.green_seek_bar)
        blueSeekBar = findViewById<SeekBar>(R.id.blue_seek_bar)
        colorView = findViewById<SeekBar>(R.id.color_view)
        RGB = findViewById(R.id.RGB)



        val seekBarListener = ColorSeekBarListener()
        redSeekBar.setOnSeekBarChangeListener(seekBarListener)
        greenSeekBar.setOnSeekBarChangeListener(seekBarListener)
        blueSeekBar.setOnSeekBarChangeListener(seekBarListener)
    }

    //Nested class, can't access outer classes unless you declare it inner
    //inner class has access to outer classes
    inner class ColorSeekBarListener : SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            //TODO: Determine which seekbar is being changed
            when (seekBar){
                redSeekBar -> red = progress
                greenSeekBar -> green = progress
                else -> blue = progress
            }

            //TODO: Change the textview to display the RGB values
            val rgb = getString(R.string.rgb_values, red, green, blue)
            RGB.text = rgb

            //TODO: Change the color view background to the new RGB
            colorView.setBackgroundColor(Color.rgb(red, green, blue))
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
        }

    }
}