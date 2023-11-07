package com.example.project_group_3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({ //This method will be executed once the timer is over
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}