package com.example.kreaz

import android.app.Application
import com.example.kreaz.data.KreazRoomDatabase

class kreazApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database: KreazRoomDatabase by lazy { KreazRoomDatabase.getDatabase(this) }
}
