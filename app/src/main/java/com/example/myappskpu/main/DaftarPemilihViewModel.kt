package com.example.myappskpu.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myappskpu.database.DataPemilih
import com.example.myappskpu.repository.DataPemilihRepo

class DaftarPemilihViewModel (application: Application) : ViewModel() {

    // Membuat instance dari DataPemilihRepository.
    private val mDataPemilihRepository: DataPemilihRepo = DataPemilihRepo(application)

    // Fungsi untuk mendapatkan LiveData yang berisi daftar DataPemilih dari Repository.
    // LiveData ini akan ter-update secara otomatis ketika ada perubahan data di database.
    fun getAllDataPemilih(): LiveData<List<DataPemilih>> = mDataPemilihRepository.getAllDataPemilih()
}