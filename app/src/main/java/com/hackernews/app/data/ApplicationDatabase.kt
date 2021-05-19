package com.hackernews.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hackernews.app.data.hit.data_source.local.model.HitDao
import com.hackernews.app.data.hit.data_source.local.model.HitEntity

@Database(entities = [HitEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    /** */
    abstract fun hitDao(): HitDao

    /** */
    companion object {

        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        /** */
        fun getDatabase(context: Context): ApplicationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "hacker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}