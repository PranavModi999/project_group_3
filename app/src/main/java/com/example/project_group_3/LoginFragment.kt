package com.example.booksmart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.project_group_3.ProductActivity
import com.example.project_group_3.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    var loginBtn: Button? = null
    var loginEmail: TextView? = null
    var loginPassword: TextView? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()
        loginBtn = view.findViewById<Button>(R.id.loginBtn)
        loginEmail = view.findViewById<TextView>(R.id.loginEmail)
        loginPassword = view.findViewById<TextView>(R.id.loginPassword)
        loginBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                if (isLoginInputValid) {
                    mAuth!!.signInWithEmailAndPassword(
                        loginEmail?.getText().toString().trim { it <= ' ' },
                        loginPassword?.getText().toString().trim { it <= ' ' })
                        .addOnCompleteListener(
                            (context as Activity?)!!
                        ) { task ->
                            if (task.isSuccessful) {
                                Log.i("test2", "Login success User id:")
                                val snackbar = Snackbar.make(
                                    view,
                                    "User Login Successfully",
                                    Snackbar.LENGTH_SHORT
                                )
                                snackbar.addCallback(object : Snackbar.Callback() {
                                    override fun onDismissed(
                                        transientBottomBar: Snackbar,
                                        event: Int
                                    ) {
                                        super.onDismissed(transientBottomBar, event)
                                        startActivity(Intent(context, ProductActivity::class.java))
                                    }
                                })
                                snackbar.show()
                            } else {
                                Snackbar.make(
                                    requireView(),
                                    "Something went wrong...",
                                    Snackbar.LENGTH_LONG
                                ).setAction("Action", null).show()
                            }


                        }
                }
            }
        })
        // Inflate the layout for this fragment
        return view
    }

    private val isLoginInputValid: Boolean
        private get() {
            if (loginEmail!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                loginEmail!!.requestFocus()
                loginEmail!!.error = "Email is required"
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(
                    loginEmail!!.text.toString().trim { it <= ' ' }).matches()
            ) {
                loginEmail!!.requestFocus()
                loginEmail!!.error = "Invalid email address"
                return false
            }
            if (loginPassword!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                loginPassword!!.requestFocus()
                loginPassword!!.error = "Password is required"
                return false
            }
            return true
        }
}