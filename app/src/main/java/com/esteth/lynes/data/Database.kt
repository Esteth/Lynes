package com.esteth.lynes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.experimental.launch

@Database(entities = [Park::class], version = 3)
abstract class LynesDatabase : RoomDatabase() {
  abstract fun parkDao(): ParkDao
  abstract fun rideDao(): RideDao

  companion object {

    @Volatile
    private var INSTANCE: LynesDatabase? = null

    fun getInstance(context: Context): LynesDatabase =
        INSTANCE ?: synchronized(this) {
          INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext, LynesDatabase::class.java, "lynes_database.db")
            // prepopulate the database after onCreate was called
            .addCallback(object : Callback() {
              override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // insert the data on the IO Thread
                launch {
                  val parkDao = getInstance(context).parkDao()
                  parkDao.insert(Park("Magic kingdom"))
                  parkDao.insert(Park("Epcot"))
                  parkDao.insert(Park("Hollywood Studios"))
                  parkDao.insert(Park("Animal Kingdom"))
                }
              }
            })
            .fallbackToDestructiveMigration()
            .build()
  }
}