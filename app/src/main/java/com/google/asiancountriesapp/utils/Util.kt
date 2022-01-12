package com.google.asiancountriesapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun log(msg: String) {
    Log.d("zeeshan", "log: $msg")
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(
        context,
        msg,
        Toast.LENGTH_LONG
    ).show()
}