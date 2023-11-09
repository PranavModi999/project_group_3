package com.example.project_group_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val shopFragment = ShopFragment()
        val cartFragment = CartFragment()

        setFragment(shopFragment)

        val bottomMenu: BottomNavigationView = findViewById(R.id.bottom_menu)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.product_shop -> setFragment(shopFragment)
                R.id.product_cart -> setFragment(cartFragment)
            }
            true
        }


    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}