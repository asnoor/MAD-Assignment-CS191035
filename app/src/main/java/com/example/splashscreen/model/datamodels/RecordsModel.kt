package com.example.splashscreen.model.datamodels
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class RecordsModel : ViewModel() {
    private val recordsList: MutableLiveData<ArrayList<Record>> =
        MutableLiveData<ArrayList<Record>>()

    val records: LiveData<ArrayList<Record>>
        get() = recordsList

//    fun initializeRecords() {
//        recordsList.value = Record.createRecordList(10)
//    }

    suspend fun loadRecords(context: Context): Unit {
        val db = RecordDatabase.getInstance(context!!.applicationContext)
        val recordDao = db.recordDao()
        val records = recordDao.getAll()
        recordsList.value = ArrayList(records);
    }


    fun markFavorite(index: Int, context: Context) {
        val updatedRecord = recordsList.value!![index]
        updatedRecord.isFav = !updatedRecord.isFav;
        Log.i("updated", updatedRecord.isFav.toString())
        viewModelScope.launch {
            val db = RecordDatabase.getInstance(context.applicationContext)
            db.transactionExecutor.execute {
                db.recordDao().update(updatedRecord)
            }
        }
    }

    fun addRecord(newRecord: Record, context: Context) {
        var tempList = recordsList.value
        tempList?.add(newRecord)
        recordsList.value = tempList ?: ArrayList()
        viewModelScope.launch {
            val db = RecordDatabase.getInstance(context.applicationContext)
            db.transactionExecutor.execute {
                db.recordDao().insertAll(newRecord)
            }
        }
    }

    fun deleteRecord(index: Int, context: Context) {
        var tempList = recordsList.value
        val deletedRecord = tempList?.removeAt(index)
        recordsList.value = tempList ?: ArrayList()
        viewModelScope.launch {
            val db = RecordDatabase.getInstance(context.applicationContext)
            db.transactionExecutor.execute {
                db.recordDao().delete(deletedRecord!!)
            }
        }
    }
}