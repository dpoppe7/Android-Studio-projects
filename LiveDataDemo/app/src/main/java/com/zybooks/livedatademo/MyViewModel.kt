package com.zybooks.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
    val myLiveData: LiveData<Int> = MutableLiveData()

    fun setNum (num: Int){
        (myLiveData as MutableLiveData).value = num
    }
}