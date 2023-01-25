package com.example.splashscreen.model.datamodels

import androidx.room.*

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    suspend fun getAll(): List<Record>

    @Query("SELECT * FROM record WHERE id IN (:recordIds)")
    suspend fun loadAllByIds(recordIds: IntArray): List<Record>

    @Query("SELECT * FROM record WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): Record

    @Update
    fun update(record: Record)

    @Insert
    fun insertAll(vararg record: Record)

    @Delete
    fun delete(record: Record)
}
