package com.example.project_group_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AfterCheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_checkout)
        var continueBtn: Button = findViewById(R.id.continueBtn);

        continueBtn.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))

        }
    }
}