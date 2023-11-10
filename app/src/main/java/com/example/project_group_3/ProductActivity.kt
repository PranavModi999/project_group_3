package com.example.project_group_3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
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

        val uId = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseDatabase.getInstance().getReference("users").child(uId).get()
            .addOnCompleteListener { task ->
                val dataSnapshot = task.result
                val user = dataSnapshot.getValue(User::class.java)
                val userName: TextView = findViewById(R.id.username)
                userName.text = user?.userName
            }
        val backBtn: ImageView = findViewById(R.id.back_arrow)
        backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val logoutBtn: ImageView = findViewById(R.id.logout_btn)
        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val snackbar = Snackbar.make(
                it,
                "User Logged out Successfully",
                Snackbar.LENGTH_SHORT
            )
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(
                    transientBottomBar: Snackbar,
                    event: Int
                ) {
                    super.onDismissed(transientBottomBar, event)
                    startActivity(Intent(it.context, LoginActivity::class.java))
                }
            })
            snackbar.show()
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}