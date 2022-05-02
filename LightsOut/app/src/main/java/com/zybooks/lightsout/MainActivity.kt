package com.zybooks.lightsout

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var game: LightsOutGame
    private lateinit var lightGridLayout: GridLayout
    private lateinit var cheatGridLoc: Button

    private var lightOnColor = 0
    private var lightOffColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lightGridLayout = findViewById(R.id.light_grid)

        // Add the same click handler to all grid buttons
        for (gridButton in lightGridLayout.children) {
            gridButton.setOnClickListener(this::onLightButtonClick)
        }

        //Cheat long-click callback
        cheatGridLoc = findViewById(R.id.topLeft)

        cheatGridLoc.setOnLongClickListener{ view: View ->
            lightsOff()
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show()
            true // <- set to true
        }


        lightOnColor = ContextCompat.getColor(this, R.color.yellow)
        lightOffColor = ContextCompat.getColor(this, R.color.black)

        game = LightsOutGame()
        startGame()
    }

    private fun startGame() {
        game.newGame()
        setButtonColors()
    }

    private fun onLightButtonClick(view: View) {

        // Find the button's row and col
        val buttonIndex = lightGridLayout.indexOfChild(view)
        val row = buttonIndex / GRID_SIZE
        val col = buttonIndex % GRID_SIZE

        game.selectLight(row, col)
        setButtonColors()

        // Congratulate the user if the game is over
        if (game.isGameOver) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setButtonColors() {

        // Set all buttons' background color and ON/OFF text
        for (buttonIndex in 0 until lightGridLayout.childCount) {
            val gridButton = lightGridLayout.getChildAt(buttonIndex)

            // Find the button's row and col
            val row = buttonIndex / GRID_SIZE
            val col = buttonIndex % GRID_SIZE

            if (game.isLightOn(row, col)) {
                gridButton.setBackgroundColor(lightOnColor)
                gridButton.contentDescription = getString(R.string.ON)
                //Log.d(TAG, gridButton.contentDescription.toString())
            } else {
                gridButton.setBackgroundColor(lightOffColor)
                gridButton.contentDescription = getString(R.string.OFF)
                //Log.d(TAG, gridButton.contentDescription.toString())
            }
        }
    }


    private fun lightsOff(){
        for (buttonIndex in 0 until lightGridLayout.childCount) {
            val gridButton = lightGridLayout.getChildAt(buttonIndex)

            gridButton.setBackgroundColor(lightOffColor)

            // Find the button's row and col
            val row = buttonIndex / GRID_SIZE
            val col = buttonIndex % GRID_SIZE

                gridButton.setBackgroundColor(lightOffColor)
                gridButton.contentDescription = getString(R.string.OFF)
                //Log.d(TAG, gridButton.contentDescription.toString())
        }
    }

    fun onNewGameClick(view: View) {
        startGame()
    }
}