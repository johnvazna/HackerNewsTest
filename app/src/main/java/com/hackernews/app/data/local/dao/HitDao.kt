package com.hackernews.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackernews.app.domain.entity.Hit

@Dao
interface HitDao {

    @Query("SELECT * FROM hints")
    fun getAllHints(): LiveData<List<Hit>>

    @Query("SELECT * FROM hints WHERE story_id = :story_id")
    fun getHit(story_id: String): LiveData<Hit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<Hit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hit: Hit)

}