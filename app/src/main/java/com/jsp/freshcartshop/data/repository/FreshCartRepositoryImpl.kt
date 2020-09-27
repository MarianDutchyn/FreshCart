package com.jsp.freshcartshop.data.repository

import com.jsp.freshcartshop.data.db.dao.FreshCartDao
import com.jsp.freshcartshop.data.db.dao.UserDao
import com.jsp.freshcartshop.model.Product
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FreshCartRepositoryImpl(private val applicationDao: FreshCartDao,
                              private val userDao: UserDao) : FreshCartRepository {

    override fun loginUser(login : String, password : String) : Boolean {
        val userAccount = applicationDao.getAccount(login)
        return userAccount.loginData.password == password
    }

    override suspend fun insertUser(fullName : String, username: String, login: Login) {
        val account = UserAccount(0L, fullName, username, login)
        userDao.insert(account)
    }

    override suspend fun getProductById(id: Long): Product {
        return suspendCoroutine { continuation ->
            val response = applicationDao.getProduct(id)
            if (response != null) {
                continuation.resume(response)
            } else {
                continuation.resumeWithException(Exception("Can't load product"))
            }
        }
    }

    override suspend fun getProducts(): List<Product>? {
        // todo simulation of data loading
        delay(1000)
        return suspendCoroutine { continuation ->
            val response = applicationDao.getAllProducts()
            if (response != null) {
                continuation.resume(response)
            } else {
                continuation.resumeWithException(Exception("Can't load products"))
            }
        }
    }
}