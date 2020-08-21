package com.jsp.freshcartshop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var errorPass = MutableLiveData<String>()
    var errorEmail = MutableLiveData<String>()
}