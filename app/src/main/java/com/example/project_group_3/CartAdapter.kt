package com.example.project_group_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.String


internal class CartBookAdapter(cartList: ArrayList<Produce>) :
    RecyclerView.Adapter<CartBookAdapter.CartViewHolder>() {
    var cartList: ArrayList<Produce>

    init {
        this.cartList = cartList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem: Produce = cartList[position]
        val roundedPrice = String.format("%.2f", currentItem.pricePerPound)
//        Glide.with(cart).get().load(currentBook.imgSrc).into(holder.bookCover)
        holder.bookName.setText(currentItem.name)
        holder.bookPrice.text = "$roundedPrice$"
        holder.bookQuantity.text = "X " + currentItem.quantityPerPound
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    internal inner class CartViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var closeBtn: ImageView
        var checkoutBtn: Button? = null
        var bookName: TextView
        var bookPrice: TextView
        var bookQuantity: TextView

        init {
            bookName = itemView.findViewById(R.id.cardBookName)
            bookPrice = itemView.findViewById(R.id.cardBookPrice)
            closeBtn = itemView.findViewById(R.id.cardCloseBtn)
            bookQuantity = itemView.findViewById(R.id.cardBookQuantity)
            closeBtn.setOnClickListener {
                Cart.instance?.deleteBookFromCart(cartList[adapterPosition])
                val cartFragment = CartFragment()
                cartFragment.updatePurchaseSummary()
                notifyDataSetChanged()
            }
        }
    }
}
