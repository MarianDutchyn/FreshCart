package com.jsp.freshcartshop.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.*

object ValidationUtils {
    private const val MIN_PASSWORD_LENGTH = 6
    private const val MIN_EMAIL_LENGTH = 6
    private const val EMPTY_PASSWORD = "Password can't be empty"
    private const val EMPTY_EMAIL = "Email can't be empty"
    private const val NOT_VALID_EMAIL = "Email is not valid"
    private const val SHORT_PASS = "Password must be $MIN_PASSWORD_LENGTH digits or longer"
    private const val SHORT_EMAIL = "Email must be $MIN_EMAIL_LENGTH characters or longer"
    private const val NO_ERROR = ""

    fun isPasswordValid(password : String) : String {
        return when {
            TextUtils.isEmpty(Objects.requireNonNull(password)) -> {
                EMPTY_PASSWORD
            }
            password.length < MIN_PASSWORD_LENGTH -> {
                SHORT_PASS
            }
            else -> NO_ERROR
        }
    }

    fun isEmailValid(email : String) : String {
        return when {
            TextUtils.isEmpty(Objects.requireNonNull(email)) -> {
                EMPTY_EMAIL
            }
            email.length < MIN_EMAIL_LENGTH -> {
                SHORT_EMAIL
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                NOT_VALID_EMAIL
            }
            else -> NO_ERROR
        }
    }
}
