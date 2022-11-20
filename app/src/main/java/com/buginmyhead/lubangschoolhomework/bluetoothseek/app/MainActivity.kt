package com.buginmyhead.lubangschoolhomework.bluetoothseek.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}