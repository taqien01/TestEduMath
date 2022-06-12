package com.aplikasi.testedumath.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.testedumath.R
import com.aplikasi.testedumath.adapter.DataSoalAdapter
import com.aplikasi.testedumath.databinding.ActivityMainBinding
import com.aplikasi.testedumath.model.Data
import com.aplikasi.testedumath.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), DataSoalAdapter.Interface {

    lateinit var binding: ActivityMainBinding

    val vm :MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initObserver()
        initListener()
    }

    private fun initView() {
        binding.progressBar.visibility = View.VISIBLE

        vm.getData()
    }

    private fun initObserver() {
        vm.loadingEvent.observe(this, {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        vm.errorEvent.observe(this, {
            Toast.makeText(this, it+"", Toast.LENGTH_LONG).show()
        })

        vm.listEvent.observe(this, {
            setAdapter(it)
        })
    }

    private fun setAdapter(list: List<Data>){
        binding.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        if (list.isNullOrEmpty()){
            binding.rvList.visibility = View.GONE
            binding.rlTrouble.visibility = View.VISIBLE
        }else{
            binding.rvList.visibility = View.VISIBLE
            binding.rlTrouble.visibility = View.GONE

            var adapter = DataSoalAdapter(list)

            binding.rvList.adapter = adapter

            adapter.notifyDataSetChanged()
            adapter.setInterface(this)
        }
    }

    private fun initListener() {
    }

    override fun onClickSources(data: Data) {
        if (data.questionType == 1){
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("DATA", data)
            startActivity(i)
        }else{
            val i = Intent(this, DetailEsaiActivity::class.java)
            i.putExtra("DATA", data)
            startActivity(i)
        }

    }
}