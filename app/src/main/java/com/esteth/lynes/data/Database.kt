package com.esteth.lynes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Park::class], version = 1)
abstract class LynesDatabase : RoomDatabase() {
  abstract fun parkDao(): ParkDao
}