package com.example.myappskpu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataPemilih::class], version = 1)
abstract class DataPemilihRoom : RoomDatabase() {

    // Mendeklarasikan fungsi abstrak untuk mendapatkan DAO yang terkait dengan database ini.
    abstract fun datapemilihDao(): DataPemilihDao

    // Mendefinisikan sebuah companion object untuk mengimplementasikan singleton pattern.
    companion object {
        // Variabel INSTANCE bersifat volatile untuk memastikan perubahan instance terlihat dengan baik oleh semua thread.
        @Volatile
        private var INSTANCE: DataPemilihRoom? = null

        // Fungsi untuk mendapatkan instance dari database.
        @JvmStatic
        fun getDatabase(context: Context): DataPemilihRoom {
            // Memeriksa apakah instance sudah ada; jika tidak, membuat instance baru.
            if (INSTANCE == null) {
                synchronized(DataPemilihRoom::class.java) {
                    // Membuat database baru jika belum ada dengan menggunakan Room database builder.
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DataPemilihRoom::class.java, "datapemilih_database")
                        .build()
                }
            }
            // Mengembalikan instance database.
            return INSTANCE as DataPemilihRoom
        }
    }
}