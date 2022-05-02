package com.zybooks.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //private val myLiveData = MutableLiveData<Int>()
    //private val myViewModel = MyViewModel()
    private val myViewModel : MyViewModel by lazy{
        ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.new_btn).setOnClickListener{
            myViewModel.setNum(Random.nextInt(1000))

            //myViewModel.myLiveData.value = 222

            //myLiveData.value = Random.nextInt(1000)
            /*findViewById<TextView>(R.id.num_text_view).text =
                Random.nextInt(1000).toString()*/
        }

        myViewModel.myLiveData.observe(this) { randNum ->
            findViewById<TextView>(R.id.num_text_view).text = randNum.toString()
        }
    }
}