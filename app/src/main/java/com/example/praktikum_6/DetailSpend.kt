package com.example.praktikum_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailSpend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_spend)

        val bundle: Bundle? = intent.extras

        val tv1: TextView = findViewById(R.id.detailNama)
        val tv2: TextView = findViewById(R.id.detailValue)

        if (bundle !== null){
            tv1.text =bundle.getString("judul")
            tv2.text = bundle.getString("artis")
        }

    }
}