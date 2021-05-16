package com.hackernews.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackernews.app.domain.entity.Hint

@Dao
interface HintDao {

    @Query("SELECT * FROM hints")
    fun getAllHints(): LiveData<List<Hint>>

    @Query("SELECT * FROM hints WHERE story_id = :story_id")
    fun getHint(story_id: String): LiveData<Hint>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hints: List<Hint>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hint: Hint)

}