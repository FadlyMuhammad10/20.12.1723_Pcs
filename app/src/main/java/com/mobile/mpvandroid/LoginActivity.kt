package com.mobile.mpvandroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.mobile.mpvandroid.api.BaseRetrofit
import com.mobile.mpvandroid.response.login.LoginResponse
import com.mobile.mpvandroid.utils.SessionManager
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {
    companion object{
        lateinit var sessionManager: SessionManager
        private lateinit var context: Context
    }

    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        val loginStatus = sessionManager.getBoolean("LOGIN_STATUS")
        if (loginStatus){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //deklar local layout
        val login = findViewById(R.id.btnLogin) as Button
        val email = findViewById(R.id.txtEmail) as TextInputEditText
        val password = findViewById(R.id.txtPassword) as TextInputEditText


        login.setOnClickListener{
            api.login(email.text.toString(),password.text.toString()).enqueue(object  : retrofit2.Callback<LoginResponse>
            {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.e("Login", response.toString())
                    val correct = response.body()!!.success
                    Log.d("a", "onResponse: "+response.body().toString())
                    if (correct) {
                        val token = response.body()!!.data.token
                        sessionManager.saveString("TOKEN", "Bearer"+token)
                        sessionManager.saveBoolean("LOGIN_STATUS", true)
                        sessionManager.saveString("ADMIN_ID",response.body()!!.data.admin.id.toString())

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "User dan password salah", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("LoginError", t.toString())
                }

            })
        }

    }
}