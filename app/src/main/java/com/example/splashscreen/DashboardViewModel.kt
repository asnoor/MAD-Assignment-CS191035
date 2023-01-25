package com.example.splashscreen

import android.icu.text.AlphabeticIndex.Record
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splashscreen.model.datamodels.Records


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel :ViewModel() {
    private var recordlist: MutableLiveData<List<Records>> = MutableLiveData()
    fun getAllRecord(): LiveData<List<Records>> {
        viewModelScope.launch(Dispatchers.IO) {

            val record = arrayListOf<Records>()
            for (i in 0..10) {
                record.add(Records(R.drawable.ic_baseline_add_circle_24, ""+i, ""))

                recordlist.postValue(record)
            }
        }
        return recordlist
        // private val _text = MutableLiveData<String>().apply {
        //   value = "This is dashboard"
        // }
        //val text: LiveData<String> = _text

    }
}