package com.jsp.freshcartshop.data.db.dao

import androidx.lifecycle.MutableLiveData
import com.jsp.freshcartshop.R
import com.jsp.freshcartshop.data.repository.Login
import com.jsp.freshcartshop.data.repository.UserAccount
import com.jsp.freshcartshop.model.Product

class FreshCartDao {

    private val productList = mutableListOf<Product>()
    private val accountsList = mutableListOf<UserAccount>()

    init {
        fillProducts()
        accountsList.add(UserAccount("John Root", "root",
            Login("root@a.a", "root")
        ))
    }

    fun getAllProducts() = productList

    private fun fillProducts() {
        productList.add(Product(1, "Water Lemon", 15, 20, R.drawable.water_lemon))
        productList.add(Product(2, "Coconut", 20, 25, R.drawable.coconut))
        productList.add(Product(3, "Pear", 20, 22, R.drawable.pear))
        productList.add(Product(4, "Guava", 15, 17, R.drawable.guava))
        productList.add(Product(5, "Apple", 15, 20, R.drawable.apple))
        productList.add(Product(6, "Bitter Melon", 18, 22, R.drawable.bitter_melon))
        productList.add(Product(7, "Brinjal", 14, 18, R.drawable.brinjal))
    }

    fun getAccount(login: String): UserAccount {
        // todo getAccount data from database
        return accountsList.find { it.loginData.email == login || it.username == login }
            ?: UserAccount("","", Login("", ""))
    }

    fun insert(account: UserAccount) {
        // todo insert user into database
    }
}