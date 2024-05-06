package com.example.myappskpu.repository

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myappskpu.database.DataPemilih
import com.example.myappskpu.databinding.DataItemBinding
import com.example.myappskpu.helper.DataPemilihDiffCallback
import com.example.myappskpu.ui.FormEntryActivity
import java.util.ArrayList

class DataPemilihAdapter : RecyclerView.Adapter<DataPemilihAdapter.DataPemilihViewHolder>() {
    private val listDataPemilih = ArrayList<DataPemilih>()

    // Metode untuk mengatur atau mengupdate daftar DataPemilih.
    fun setListDataPemilih(listDataPemilih: List<DataPemilih>) {
        val diffCallback = DataPemilihDiffCallback(this.listDataPemilih, listDataPemilih)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listDataPemilih.clear()
        this.listDataPemilih.addAll(listDataPemilih)
        diffResult.dispatchUpdatesTo(this) // Melakukan update efisien pada RecyclerView.
    }

    // Membuat ViewHolder baru.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPemilihViewHolder {
        val binding = DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataPemilihViewHolder(binding)
    }

    // Mengikat data ke ViewHolder.
    override fun onBindViewHolder(holder: DataPemilihViewHolder, position: Int) {
        holder.bind(listDataPemilih[position])
    }

    // Mengembalikan ukuran daftar DataPemilih.
    override fun getItemCount(): Int {
        return listDataPemilih.size
    }

    // ViewHolder yang memegang tampilan untuk setiap item dalam RecyclerView.
    inner class DataPemilihViewHolder(private val binding: DataItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(datapemilih: DataPemilih) {
            with(binding) {
                // Menetapkan nilai ke tampilan.
                tvItemNik.text = datapemilih.nik?.toString() ?: ""
                tvItemDate.text = datapemilih.date
                tvItemNama.text = datapemilih.nama

                // Menetapkan OnClickListener untuk setiap item.
                cvItemDatapemilih.setOnClickListener {
                    val intent = Intent(it.context, FormEntryActivity::class.java)
                    intent.putExtra(FormEntryActivity.EXTRA_NOTE, datapemilih)
                    it.context.startActivity(intent) // Memulai aktivitas FormEntry dengan data yang dipilih.
                }
            }
        }
    }
}
