package com.example.splashscreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splashscreen.model.datamodels.FavouriteRecords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteViewModel :ViewModel() {
    private var favrecordlist: MutableLiveData<List<FavouriteRecords>> = MutableLiveData()
    fun getAllFavRecords(): LiveData<List<FavouriteRecords>>{
        viewModelScope.launch(Dispatchers.IO) {

            val record = arrayListOf<FavouriteRecords>()
            for (i in 0..10){
                if(i%2 == 0)
                    record.add(FavouriteRecords(R.drawable.woman, "DSUStudent-"+i,"Hello this is discription",true))
                else
                    record.add(FavouriteRecords(R.drawable.woman, "DSUStudent-"+i,"Hello this is discription",false))
                favrecordlist.postValue(record)
            }
        }
        return  favrecordlist


    }
}



