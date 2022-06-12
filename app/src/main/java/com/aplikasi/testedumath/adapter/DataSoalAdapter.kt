package com.aplikasi.testedumath.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.testedumath.databinding.SatuSoalBinding
import com.aplikasi.testedumath.model.Data

class DataSoalAdapter(private val list: List<Data>) : RecyclerView.Adapter<DataSoalAdapter.ViewHolder>() {

    lateinit var context: Context

    var mInterface: Interface? = null

    fun setInterface(mInterface: Interface?) {
        this.mInterface = mInterface
    }

    interface Interface {
        fun onClickSources(cat: Data)
    }

    class ViewHolder(val binding: SatuSoalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SatuSoalBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = list[position]

        holder.binding.txtKategori.text = data.categoryName
        holder.binding.txtSubjek.text = data.subjectName

        holder.binding.crdSoal.setOnClickListener {
            mInterface!!.onClickSources(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}