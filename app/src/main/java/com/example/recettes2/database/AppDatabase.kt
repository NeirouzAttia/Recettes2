package com.example.recettes2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recettes2.model.FavoriteRecette

@Database(
    entities = [FavoriteRecette::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
