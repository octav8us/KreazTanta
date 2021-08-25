package com.example.kreaz.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton INSTANCE object.
 */
@Database(entities = [cart::class], version = 1, exportSchema = false)
abstract class KreazRoomDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: KreazRoomDatabase? = null

        fun getDatabase(context: Context): KreazRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KreazRoomDatabase::class.java,
                    "cart_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
