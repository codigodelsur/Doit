package com.codigodelsur.doit.data.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codigodelsur.doit.data.framework.db.converter.LocalConverters
import com.codigodelsur.doit.data.framework.db.dao.TaskDao
import com.codigodelsur.doit.data.framework.db.model.LocalGoal
import com.codigodelsur.doit.data.framework.db.model.LocalTask


@Database(
    version = 1,
    entities = [
        LocalTask::class,
        LocalGoal::class,
    ]
)
@TypeConverters(LocalConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DB_NAME = "doit.db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
