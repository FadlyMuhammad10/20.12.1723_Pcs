package com.mobile.mpvandroid.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(internal var _context: Context) {
    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor
    internal  var PRIVATE_MODE = 0

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun saveInteger(key: String, valueInt: Int) {
        editor.putInt(key, valueInt)
        editor.commit()
    }
    fun getInteger(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun saveString(key: String, valueString: String?) {
        editor.putString(key, valueString)
        editor.commit()
    }
    fun getString(key: String): String? {
        return pref.getString(key, "")
    }

    fun saveBoolean(key: String, valueBoolean: Boolean) {
        editor.putBoolean(key, valueBoolean)
        editor.commit()
    }
    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    //clear session
    fun clearSession(){
        editor.clear()
        editor.commit()
    }

    //remove session
    fun removeSession(string: String) {
        editor.remove(string).commit()
    }

    companion object{
        private val PREF_NAME = "pref_pcs"
        val SESSION_TOKEN = "token"
    }

}