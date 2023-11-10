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
import com.example.project_group_3.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {

    var mAuth: FirebaseAuth? = null

    private var mDatabase: DatabaseReference? = null

    var signupEmail: TextView? =
        null
    var signupPassword: TextView? = null
    var signupName: TextView? = null
    var signupRepeatPassword: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        val signupBtn: Button = view.findViewById<Button>(R.id.signupBtn)
        signupName = view.findViewById(R.id.signupName)
        signupEmail = view.findViewById(R.id.signupEmail)
        signupPassword = view.findViewById(R.id.signupPassword)
        signupRepeatPassword = view.findViewById(R.id.signupRepeatPassword)

        signupBtn.setOnClickListener(View.OnClickListener { view ->
            if (isInputValid()) {
                mAuth!!.createUserWithEmailAndPassword(
                    signupEmail?.getText().toString(),
                    signupPassword?.getText().toString()
                )
                    .addOnCompleteListener(
                        (context as Activity?)!!
                    ) { task ->
                        if (task.isSuccessful) {
                            val currentUser = mAuth!!.currentUser
                            Log.i("test2", "signup success User id:" + currentUser!!.uid)

                            val newUser = User(
                                currentUser!!.uid,
                                signupName?.getText().toString().trim { it <= ' ' },
                                signupEmail?.getText().toString().trim { it <= ' ' },
                                "test address",
                                "test postal",
                                "test type"
                            )
                            mDatabase!!.child("users").child(currentUser!!.uid).setValue(newUser)
                            val snackbar =
                                Snackbar.make(
                                    view,
                                    "User created Successfully",
                                    Snackbar.LENGTH_SHORT
                                )
                            snackbar.addCallback(object : Snackbar.Callback() {
                                override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                                    super.onDismissed(transientBottomBar, event)
                                    startActivity(Intent(context, ProductActivity::class.java))
                                }
                            })
                            snackbar.show()
                        } else {
                            val e = task.exception as FirebaseAuthException?
                            Log.i("test2", "onComplete: " + e!!.errorCode)
                            if (e!!.errorCode === "ERROR_EMAIL_ALREADY_IN_USE") {
                                signupEmail?.requestFocus()
                                signupEmail?.error = "Email address already in use"
                            } else if (e!!.errorCode === "ERROR_INVALID_EMAIL") {
                                signupEmail?.requestFocus()
                                signupEmail?.error = "Invalid Email address"
                            } else if (e!!.errorCode === "ERROR_WEAK_PASSWORD") {
                                signupPassword?.requestFocus()
                                signupPassword?.setError("Please enter a stronger password")
                            } else {
                                Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show()
                            }
                        }
                    }
            }
        })
        return view
    }

    fun isInputValid(): Boolean {
        if (signupName?.getText().toString().trim { it <= ' ' }.isEmpty()) {
            signupName?.requestFocus()
            signupName?.setError("Name is required")
            return false
        }

        if (signupEmail?.getText().toString().trim { it <= ' ' }.isEmpty()) {
            signupEmail?.requestFocus()
            signupEmail?.setError("Email is required")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(
                signupEmail?.getText().toString().trim { it <= ' ' }).matches()
        ) {
            signupEmail?.requestFocus()
            signupEmail?.setError("Invalid email address")
            return false
        }

        if (signupPassword?.getText().toString().trim { it <= ' ' }.isEmpty()) {
            signupPassword?.requestFocus()
            signupPassword?.setError("Password is required")
            return false
        }

        if (signupPassword?.getText().toString()
                .trim { it <= ' ' } != signupRepeatPassword?.getText()
                .toString().trim { it <= ' ' }
        ) {
            signupRepeatPassword?.requestFocus()
            signupRepeatPassword?.setError("Passwords do not match")
            return false
        }
        return true
    }
}