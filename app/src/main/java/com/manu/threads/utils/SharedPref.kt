package com.manu.threads.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPref {

    fun storeData(
        name: String,
        email: String,
        userName: String,
        imgUrl: String,
        bio: String,
        context: Context
    ) {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("bio", bio)
        editor.putString("email", email)
        editor.putString("userName", userName)
        editor.putString("imgUrl", imgUrl)
        editor.apply()

    }

    fun getEmail(context: Context): String {
        val sharedPref = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPref.getString("email", "")!!
    }

    fun getUserName(context: Context): String {
        val sharedPref = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPref.getString("userName", "")!!
    }

    fun getBio(context: Context): String {
        val sharedPref = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPref.getString("bio", "")!!
    }

    fun getImgUrl(context: Context): String {
        val sharedPref = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPref.getString("imgUrl", "")!!
    }

    fun getName(context: Context): String {
        val sharedPref = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPref.getString("name", "")!!
    }

}