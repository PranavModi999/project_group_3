package com.example.project_group_3

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val backBtn: ImageView = findViewById(R.id.back_arrow)
        backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        var customerName: EditText = findViewById(R.id.customerName);
        var customerEmail: EditText = findViewById(R.id.customerEmail)
        var customerPhone: EditText = findViewById(R.id.customerPhone)
        var customerAddress: EditText = findViewById(R.id.customerAddress)
        var cardNumber: EditText = findViewById(R.id.cardNumber)
        var cardExpiry: EditText = findViewById(R.id.cardExpiry)
        var cardCVV: EditText = findViewById(R.id.cardCVV)
        var orderPlaceBtn: Button = findViewById(R.id.orderPlaceBtn)
        var cardDetails: LinearLayout = findViewById(R.id.cardDetails)
        var radioPayment: RadioGroup = findViewById(R.id.radioPayment)

        var paymentOption: String? = null
        var isValidEmail = true
        var isValidPhone = true
        var isValidCard = true
        var isValidExpiryDate = true
        var isValidCVVValue = true
        var phoneNumber: String

        radioPayment.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonCard) {
                cardDetails.visibility = View.VISIBLE
                paymentOption = "Card"
            } else if (checkedId == R.id.radioButtonCOD) {
                cardDetails.visibility = View.GONE
                paymentOption = "COD"
            }
        }

        customerEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val input = charSequence.toString()
                if (!input.isEmpty()) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                        customerEmail.error = null
                        isValidEmail = true
                    } else {
                        customerEmail.error = "Enter a valid email address"
                        isValidEmail = false
                    }
                } else {
                    customerEmail.error = null
                    isValidEmail = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        customerPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val input = charSequence.toString().replace("-", "")
                if (!input.isEmpty()) {
                    if (isValidPhoneNumber(input)) {
                        customerPhone.error = null
                        isValidPhone = true
                    } else {
                        customerPhone.error = "Enter a valid phone number"
                        isValidPhone = false
                    }
                } else {
                    customerPhone.error = null
                    isValidPhone = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        cardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val input = charSequence.toString()
                if (!input.isEmpty()) {
                    if (isValidCardNumber(input)) {
                        cardNumber.error = null
                        isValidCard = true
                    } else {
                        cardNumber.error = "Enter a valid Card"
                        isValidCard = false
                    }
                } else {
                    cardNumber.error = null
                    isValidCard = false
                }
            }

            override fun afterTextChanged(editable: Editable) {
                // Reset the flag to true when the input is corrected
                if (isValidCard) {
                    isValidCard = true
                }
            }
        })


        cardExpiry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val input = charSequence.toString()
                if (!input.isEmpty()) {
                    if (isValidExpiry(input)) {
                        cardExpiry.error = null
                        isValidExpiryDate = true
                    } else {
                        cardExpiry.error = "Enter a valid expiry date (MM/YY)"
                        isValidExpiryDate = false
                    }
                } else {
                    cardExpiry.error = null
                    isValidExpiryDate = false
                }
            }

            override fun afterTextChanged(editable: Editable) {
                // Reset the flag to true when the input is corrected
                if (isValidExpiryDate) {
                    isValidExpiryDate = true
                }
            }
        })


        cardCVV.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val input = charSequence.toString()
                if (!input.isEmpty()) {
                    if (isValidCVV(input)) {
                        cardCVV.error = null
                        isValidCVVValue = true
                    } else {
                        cardCVV.error = "Enter a valid CVV (3 digits)"
                        isValidCVVValue = false
                    }
                } else {
                    cardCVV.error = null
                    isValidCVVValue = false
                }
            }

            override fun afterTextChanged(editable: Editable) {
                // Reset the flag to true when the input is corrected
                if (isValidCVVValue) {
                    isValidCVVValue = true
                }
            }
        })


        orderPlaceBtn.setOnClickListener {
            val name: String = customerName.text.toString()
            val email: String = customerEmail.text.toString()
            val address: String = customerAddress.text.toString()
            val phoneNumber: String = customerPhone.text.toString()
            val inputCardNumber: String = cardNumber.text.toString()
            val inputCardExpiry: String = cardExpiry.text.toString()
            val inputCardCVV: String = cardCVV.text.toString()

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this@CheckoutActivity, "Please enter a Name", Toast.LENGTH_SHORT)
                    .show()
            } else if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@CheckoutActivity, "Please enter an Email", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isValidEmail) {
                Toast.makeText(
                    this@CheckoutActivity,
                    "Please enter a valid email address",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(
                    this@CheckoutActivity,
                    "Please enter a phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!isValidPhoneNumber(phoneNumber)) {
                Toast.makeText(
                    this@CheckoutActivity,
                    "Please enter a valid phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (TextUtils.isEmpty(address)) {
                Toast.makeText(this@CheckoutActivity, "Please enter an Address", Toast.LENGTH_SHORT)
                    .show()
            } else if (paymentOption == null) {
                Toast.makeText(this@CheckoutActivity, "Please select a payment option", Toast.LENGTH_SHORT).show()
            }
            else if (paymentOption == "Card") {
                if (TextUtils.isEmpty(inputCardNumber)) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Please enter a Card number",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!isValidCard) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Please enter a valid Card Number",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (TextUtils.isEmpty(inputCardExpiry)) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Please enter a card expiry (MM/YY)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!isValidExpiryDate) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Please enter a valid Card Expiry Date (MM/YY)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (TextUtils.isEmpty(inputCardCVV)) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Please enter a CVV (3 digits)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!isValidCVVValue) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Please enter a valid CVV (3 digits)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    // Successfully Ordered
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Successfully Ordered",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, AfterCheckoutActivity::class.java))
                }
            }
            else {
                // Successfully Ordered
                Toast.makeText(
                    this@CheckoutActivity,
                    "Successfully Ordered",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, AfterCheckoutActivity::class.java))
            }
        }
    }
        // Phone Validations
    fun isValidPhoneNumber(number: String): Boolean {
        val regex = "\\d{10}|\\d{3}-\\d{3}-\\d{4}";
        return number.matches(Regex(regex)) && number.length <= 12
    }

    // Card Number Validations
    fun isValidCardNumber(cardNumber: String): Boolean {
        val regex = "\\d{4}-\\d{4}-\\d{4}-\\d{4}"
        return cardNumber.matches(Regex(regex)) && cardNumber.length <= 19
    }

    fun isValidExpiry(cardNumber: String): Boolean {
        val regex = "^(0[1-9]|1[0-2])/(\\d{2})$"
        return cardNumber.matches(Regex(regex)) && cardNumber.length <= 5
    }

    fun isValidCVV(cvv: String): Boolean {
        val regex = "\\d{3}"
        return cvv.matches(Regex(regex)) && cvv.length == 3
    }

}