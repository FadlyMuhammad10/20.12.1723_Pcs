package com.mobile.mpvandroid.response.login

import android.provider.ContactsContract.Data

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: com.mobile.mpvandroid.response.login.Data
)
