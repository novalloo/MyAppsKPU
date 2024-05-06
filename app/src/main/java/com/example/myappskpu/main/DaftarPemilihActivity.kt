package com.example.myappskpu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappskpu.R
import com.example.myappskpu.databinding.ActivityDaftarPemilihBinding
import com.example.myappskpu.helper.ViewModelFactory
import com.example.myappskpu.repository.DataPemilihAdapter
import com.google.android.material.snackbar.Snackbar

class DaftarPemilihActivity : AppCompatActivity() {
    private var _daftarDataPemilihBinding: ActivityDaftarPemilihBinding? = null
    private val binding get() = _daftarDataPemilihBinding

    private lateinit var adapter: DataPemilihAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _daftarDataPemilihBinding = ActivityDaftarPemilihBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Set up action bar dengan judul dan tombol kembali.
        supportActionBar?.title = "Daftar Data Pemilih"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mendapatkan ViewModel dari factory.
        val daftarDataPemilihViewModel = obtainViewModel(this@DaftarPemilihActivity)

        // Mengobservasi LiveData dari ViewModel untuk mendapatkan data pemilih.
        daftarDataPemilihViewModel.getAllDataPemilih().observe(this) { datapemilihList ->
            if (datapemilihList != null && datapemilihList.isNotEmpty()) {
                adapter.setListDataPemilih(datapemilihList)
            } else {
                adapter.setListDataPemilih(emptyList()) // Menetapkan daftar kosong untuk menghapus data sebelumnya.
                showNoDataSnackbar()
            }
        }

        adapter = DataPemilihAdapter()

        // Mengatur RecyclerView dengan LinearLayoutManager dan Adapter.
        binding?.rvDatapemilih?.layoutManager = LinearLayoutManager(this)
        binding?.rvDatapemilih?.setHasFixedSize(true)
        binding?.rvDatapemilih?.adapter = adapter

    }

    // Menampilkan Snackbar jika tidak ada data.
    private fun showNoDataSnackbar() {
        val snackbar = Snackbar.make(
            binding?.root!!, // Root view dari layout
            "Tidak ada data pada saat ini",
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    // Membuat ViewModel dengan factory yang disediakan.
    private fun obtainViewModel(activity: AppCompatActivity): DaftarPemilihViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DaftarPemilihViewModel::class.java)
    }

    // Membersihkan binding ketika Activity dihancurkan.
    override fun onDestroy() {
        super.onDestroy()
        _daftarDataPemilihBinding = null
    }

    // Menangani tombol kembali di action bar.
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}