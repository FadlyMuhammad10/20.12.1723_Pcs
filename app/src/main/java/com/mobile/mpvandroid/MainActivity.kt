package com.mobile.mpvandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), MainView {

    //persegi panjang
    private lateinit var etPanjang: EditText
    private lateinit var etLebar: EditText
    private lateinit var btnTambah: Button
    private lateinit var tvHasil: TextView


    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this)

        //persegi panjang
        etPanjang = findViewById(R.id.etPjg)
        etLebar = findViewById(R.id.etLebar)
        btnTambah = findViewById(R.id.btnTambah)
        tvHasil = findViewById(R.id.tvHasil)


        //persegi panjang
        btnTambah.setOnClickListener{
            val panjang = etPanjang.text.toString().toFloat()
            val lebar = etLebar.text.toString().toFloat()
            mainPresenter.hitungTambah(panjang, lebar)
        }

    }


    //Persegi Panjang
    override fun updateTambah(tambah: Float) {
        tvHasil.text = tambah.toString()
    }
    override fun showError(errorMsg: String) {
        tvHasil.text = errorMsg
    }

}