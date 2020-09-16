package com.jsp.freshcartshop.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jsp.freshcartshop.R
import com.jsp.freshcartshop.adapters.ProductPagerAdapter
import com.jsp.freshcartshop.adapters.ProductRecyclerAdapter
import com.jsp.freshcartshop.databinding.FragmentMainBinding
import com.jsp.freshcartshop.view.MainActivity
import com.jsp.freshcartshop.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.get

class MainFragment : BaseFragment() {

    private val mainViewModel: MainViewModel = get()
    private lateinit var binding: FragmentMainBinding

    override fun setFragmentLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolBarTitle(getString(R.string.toolbar_title))
        init()
    }

    private fun init() {
        observeData()
        setDiscount(15)
    }

    private fun observeData() {
        mainViewModel.loadProducts()

        tdMainViewPager.setupWithViewPager(vpMainProducts, true)
        mainViewModel.productList.observe(viewLifecycleOwner, Observer { products ->
            binding.vpMainProducts.adapter = ProductPagerAdapter(requireContext(), products)
        })

        mainViewModel.productList.observe(viewLifecycleOwner, Observer { products ->
            rvMainProducts.also {
                it.layoutManager = GridLayoutManager(activity, 3)
                it.adapter = ProductRecyclerAdapter().also { it.addAll(products) }
            }
        })

        mainViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                pbRecommendProducts.visibility = ProgressBar.VISIBLE
                pbAllProducts.visibility = ProgressBar.VISIBLE
            } else {
                pbRecommendProducts.visibility = ProgressBar.GONE
                pbAllProducts.visibility = ProgressBar.GONE
            }
        })
    }

    private fun setDiscount(amount: Int) {
        tvDiscount.text = resources.getString(R.string.discount, amount)
    }

}