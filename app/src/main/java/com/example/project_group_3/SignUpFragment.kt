package com.example.booksmart

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.project_group_3.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {
    var signupBtn: Button? = null
    var signupEmail: TextView? = null
    var signupPassword: TextView? = null
    var signupName: TextView? = null
    var signupRepeatPassword: TextView? = null
    var signupAddress: TextView? = null
    var signupPostal: TextView? = null
    var userTypeSpinner: Spinner? = null
    private var mDatabase: DatabaseReference? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)
        mDatabase = FirebaseDatabase.getInstance().reference
        signupBtn = view.findViewById<Button>(R.id.signupBtn)
        signupName = view.findViewById<TextView>(R.id.signupName)
        signupEmail = view.findViewById<TextView>(R.id.signupEmail)
        signupPassword = view.findViewById<TextView>(R.id.signupPassword)
        signupRepeatPassword = view.findViewById<TextView>(R.id.signupRepeatPassword)
        signupAddress = view.findViewById<TextView>(R.id.signupAddress)
        signupPostal = view.findViewById<TextView>(R.id.signupPostal)
        userTypeSpinner = view.findViewById<Spinner>(R.id.userTypeSpinner)
//        val adapter = ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.user_types, R.layout.simple_spinner_item
//        )
//        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        userTypeSpinner.setAdapter(adapter)
        return view
    }

    private val isInputValid: Boolean
        private get() {
            if (signupName!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                signupName!!.requestFocus()
                signupName!!.error = "Name is required"
                return false
            }
            if (signupEmail!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                signupEmail!!.requestFocus()
                signupEmail!!.error = "Email is required"
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(
                    signupEmail!!.text.toString().trim { it <= ' ' }).matches()
            ) {
                signupEmail!!.requestFocus()
                signupEmail!!.error = "Invalid email address"
                return false
            }
            if (signupPassword!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                signupPassword!!.requestFocus()
                signupPassword!!.error = "Password is required"
                return false
            }
            if (signupPassword!!.text.toString()
                    .trim { it <= ' ' } != signupRepeatPassword!!.text.toString().trim { it <= ' ' }
            ) {
                signupRepeatPassword!!.requestFocus()
                signupRepeatPassword!!.error = "Passwords do not match"
                return false
            }
            if (signupAddress!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                signupAddress!!.requestFocus()
                signupAddress!!.error = "Address is required"
                return false
            }
            if (signupPostal!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                signupPostal!!.requestFocus()
                signupPostal!!.error = "Postal code is required"
                return false
            } else if (signupPostal!!.text.toString().trim { it <= ' ' }.length != 6) {
                signupPostal!!.requestFocus()
                signupPostal!!.error = "Postal code must be 6 Character long"
                return false
            }
            return true
        }
}