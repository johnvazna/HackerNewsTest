package com.hackernews.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hackernews.app.data.local.dao.HintDao
import com.hackernews.app.domain.entity.Hint

@Database(entities = [Hint::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun hintDao(): HintDao

    companion object {

        private const val DATABASE_NAME = "hacker.db"

        @Volatile
        private var instance: ApplicationDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance ?: build(context).also {
                    instance = it
                }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}