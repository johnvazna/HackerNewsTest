package com.hackernews.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackernews.app.data.local.entity.Hit

@Dao
interface HitDao {

    @Query("SELECT * FROM hits")
    fun getHits(): List<Hit>

    @Query("SELECT * FROM hits WHERE story_id = :story_id")
    fun getHitById(story_id: Long): LiveData<Hit>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<Hit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hit: Hit)

}