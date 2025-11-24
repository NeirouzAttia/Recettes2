package com.example.recettes2.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var db: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return db ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "recettes_db"
            ).build()
            db = instance
            instance
        }
    }
}
