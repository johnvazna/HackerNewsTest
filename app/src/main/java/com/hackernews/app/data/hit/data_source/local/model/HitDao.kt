package com.hackernews.app.data.hit.data_source.local.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HitDao {

    @Update
    suspend fun update(hit: HitEntity)

    @Query("SELECT * FROM hits WHERE isDelete is 0")
    suspend fun getHits(): List<HitEntity>

    @Query("SELECT * FROM hits WHERE story_id = :story_id")
    fun getHitById(story_id: Long): LiveData<HitEntity>?

    @Query("SELECT isDelete FROM hits WHERE story_id = :story_id")
    suspend fun isHitDeleted(story_id: Long): Boolean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<HitEntity>)

}