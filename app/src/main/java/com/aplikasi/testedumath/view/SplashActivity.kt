package com.aplikasi.testedumath.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aplikasi.testedumath.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initView()
    }

    fun initView(){
        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                },
                1000
            )
    }
}