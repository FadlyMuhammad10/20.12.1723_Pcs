package com.mobile.mpvandroid

class MainPresenter(private val mainView: MainView) {

    //persegi panjang
    fun hitungTambah(panjang: Float, lebar: Float) {

        if (panjang == 0F) {
            mainView.showError("Panjang tidak boleh 0")
            return
        }
        if (lebar == 0F) {
            mainView.showError("Lebar tidak boleh 0")
            return
        }
        val tambah = panjang + lebar
        mainView.updateTambah(tambah)
    }


}