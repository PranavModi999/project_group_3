package com.example.project_group_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.booksmart.LoginFragment
import com.example.booksmart.SignUpFragment
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity() {
    private var accountTab: TabLayout? = null
    private var accountViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        accountTab = findViewById<TabLayout>(R.id.accountTab)
        accountViewPager = findViewById<ViewPager>(R.id.accountViewPager)

        accountTab?.setupWithViewPager(accountViewPager)

        val accountTabAdapter = LoginTabAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        accountTabAdapter.addFragment(LoginFragment(), "Login")
        accountTabAdapter.addFragment(SignUpFragment(), "Signup")

        accountViewPager?.setAdapter(accountTabAdapter)
    }
}