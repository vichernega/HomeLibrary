package com.example.homelibrary.utilit

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.homelibrary.R

// file where different functions are inflated*/


fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
        .replace(R.id.activity_container, fragment)
        .commit()
}

fun AppCompatActivity.hideFragment() {
    supportFragmentManager.commit {
        replace(R.id.activity_container, Fragment())
    }
}

fun showToast(str: String){
    Toast.makeText(APP_ACTIVITY, str, Toast.LENGTH_SHORT).show()
}