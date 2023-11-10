package com.example.project_group_3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {


    var cartListView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var cartList: ArrayList<Produce>? = null
    var cartSubTotal: TextView? = null
    var cartTax: TextView? = null
    var cartTotal: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)
        cartList = ArrayList<Produce>()

        cartSubTotal = view.findViewById(R.id.cartSubTotalPrice)
        cartTax = view.findViewById(R.id.cartTaxPrice)
        cartTotal = view.findViewById(R.id.cartTotalPrice)
        cartList = Cart.instance?.cartList
        updatePurchaseSummary()
        cartListView = view.findViewById(R.id.cartBooksListView)
        layoutManager = LinearLayoutManager(context)
        cartListView?.layoutManager = layoutManager
        val cartBookAdapter = CartBookAdapter(cartList as ArrayList<Produce>)
        cartListView?.adapter = cartBookAdapter
        return view
    }

    fun updatePurchaseSummary() {
        var total = 0f
        var tax = 0f
        val cartList = Cart.instance?.cartList

        if (cartList != null) {
            for (item in cartList) {
                total += item?.pricePerPound!!.toFloat() * item.quantityPerPound.toFloat()
            }
            tax = total / 100 * 13
        }

        Log.i("test2", "onCreateView:Total:$total Tax:$tax")
        cartSubTotal?.text = "$" + String.format("%.2f", total)
        cartTax?.text = "$" + String.format("%.2f", tax)
        cartTotal?.text = "$" + String.format("%.2f", total + tax)
    }


}