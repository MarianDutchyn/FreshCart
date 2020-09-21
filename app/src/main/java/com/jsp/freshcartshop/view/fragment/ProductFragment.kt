package com.jsp.freshcartshop.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jsp.freshcartshop.R
import com.jsp.freshcartshop.adapters.ProductPhotosAdapter
import com.jsp.freshcartshop.databinding.FragmentProductBinding
import com.jsp.freshcartshop.view.MainActivity
import com.jsp.freshcartshop.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ProductFragment : BaseFragment() {

    val mainViewModel by sharedViewModel<MainViewModel>()
    private lateinit var binding : FragmentProductBinding
    private var isToolbarBackgroundWhite = false

    override fun setFragmentLayout(inflater: LayoutInflater,
                                   container: ViewGroup?,
                                   savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        loadProduct()
        binding.product = mainViewModel.product.value
        binding.lifecycleOwner = this
        // change toolbar background color
        switchToolbarBackgroundColor()
        return binding.root
    }

    private fun loadProduct() {
        mainViewModel.loadProduct(
            ProductFragmentArgs.fromBundle(requireArguments()).productId
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        tbProductViewPager.setupWithViewPager(vpProductViewPager, true)
        mainViewModel.product.observe(viewLifecycleOwner, Observer {
                product -> binding.vpProductViewPager.adapter = ProductPhotosAdapter(requireContext(), product.images)
        })
    }

    override fun onStop() {
        super.onStop()
        // switch back gray as toolbar background color
        switchToolbarBackgroundColor()
    }

    private fun switchToolbarBackgroundColor() {
        if (!isToolbarBackgroundWhite) {
            context?.let { ContextCompat.getColor(it, R.color.colorWhite) }?.let {
                (activity as MainActivity).toolbar_wrapper.setCardBackgroundColor(
                    it
                )
            }
        } else {
            context?.let { ContextCompat.getColor(it, R.color.colorBackground) }?.let {
                (activity as MainActivity).toolbar_wrapper.setCardBackgroundColor(
                    it
                )
            }
        }
        isToolbarBackgroundWhite = !isToolbarBackgroundWhite
    }
}