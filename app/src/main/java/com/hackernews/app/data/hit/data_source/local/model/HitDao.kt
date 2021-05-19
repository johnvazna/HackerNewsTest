package com.hackernews.app.data.hit.data_source.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HitDao {

    @Query("SELECT * FROM hits")
    fun getHits(): List<HitEntity>

    @Query("SELECT * FROM hits WHERE story_id = :story_id")
    fun getHitById(story_id: Long): LiveData<HitEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<HitEntity>)

}