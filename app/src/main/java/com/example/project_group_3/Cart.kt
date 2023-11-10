package com.example.project_group_3


class Cart private constructor() {
    private val cartBookList: ArrayList<Produce> = ArrayList<Produce>()

    fun addBookToCart(produce: Produce) {
        cartSingleton!!.cartBookList.add(produce)
    }

    fun deleteBookFromCart(produce: Produce) {
        cartSingleton!!.cartBookList.remove(produce)
    }

    fun findItemById(idToCheck: Int): Produce? {
        return cartBookList.find { it.id == idToCheck }
    }

    val cartList: ArrayList<Produce>
        get() = cartBookList

    companion object {
        private var cartSingleton: Cart? = null
        val instance: Cart?
            get() {
                if (cartSingleton == null) {
                    cartSingleton = Cart()
                }
                return cartSingleton
            }
    }
}
