package com.jsp.freshcartshop.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.jsp.freshcartshop.R
import com.jsp.freshcartshop.view.BaseActivity
import com.jsp.freshcartshop.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    override val viewModel: SignUpViewModel by sharedViewModel()

    override fun setFragmentLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        tvHaveAccount.setOnClickListener {
            view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        (activity as BaseActivity).setToolBarTitle(getString(R.string.sign_up))
    }
}