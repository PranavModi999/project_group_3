package com.example.booksmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.project_group_3.R

class LoginFragment : Fragment() {
    var loginBtn: Button? = null
    var loginEmail: TextView? = null
    var loginPassword: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        loginBtn = view.findViewById<Button>(R.id.loginBtn)
        loginEmail = view.findViewById<TextView>(R.id.loginEmail)
        loginPassword = view.findViewById<TextView>(R.id.loginPassword)
        return view
    }
}