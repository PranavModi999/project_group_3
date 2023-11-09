package com.example.project_group_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val currentItem: Produce? = intent.getSerializableExtra("SELECTED_ITEM") as Produce?

        val productName: TextView = findViewById(R.id.productName)
        val productPrice: TextView = findViewById(R.id.productPrice)
        val productDesc: TextView = findViewById(R.id.productDescription)
        val productImage: ImageView = findViewById(R.id.productImage)

        productName.text = currentItem?.name
        productPrice.text = "$ " + currentItem?.pricePerPound
        productDesc.text = currentItem?.description
        Log.i("test2", "image" + currentItem?.image)

        val storageRef: StorageReference =
            FirebaseStorage.getInstance().getReferenceFromUrl(currentItem!!.image)
        Glide.with(this).load(storageRef).into(productImage)


        val backBtn: ImageView = findViewById(R.id.back_arrow)
        backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val quantityTextView: TextView = findViewById(R.id.quantityValue)
        val decrementButton: ImageView = findViewById(R.id.decreaseQuantity)
        val incrementButton: ImageView = findViewById(R.id.increaseQuantity)

        decrementButton.setOnClickListener(View.OnClickListener {
            val currentQuantity: Int = quantityTextView.text.toString().split(" ")[0].toInt()
            currentItem.quantityPerPound = currentQuantity.toDouble()

            if (currentQuantity > 1) {
                quantityTextView.text = (currentQuantity - 1).toString() + " lb"
            } else {
                Toast.makeText(
                    this@DetailActivity,
                    "Quantity cannot be less than 1",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        incrementButton.setOnClickListener(View.OnClickListener {
            val currentQuantity: Int = quantityTextView.text.toString().split(" ")[0].toInt()
            currentItem.quantityPerPound = currentQuantity.toDouble()

            if (currentQuantity < 50) {
                quantityTextView.text = (currentQuantity + 1).toString() + " lb"
            } else {
                Toast.makeText(
                    this@DetailActivity,
                    "Quantity cannot be greater than 50",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val addCartBtn: Button = findViewById(R.id.addToCart)

        addCartBtn.setOnClickListener {
            Toast.makeText(this@DetailActivity, "Product added to cart", Toast.LENGTH_SHORT)
            val cart: Cart = Cart.instance!!
            cart.cartList.add(currentItem)
        }
        val checkoutBtn: Button = findViewById(R.id.checkoutBtn)
        checkoutBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CheckoutActivity::class.java))
        }
    }
}