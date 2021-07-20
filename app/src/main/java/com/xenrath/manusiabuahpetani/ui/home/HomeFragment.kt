package com.xenrath.manusiabuahpetani.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.data.ResponseProduct
import com.xenrath.manusiabuahpetani.data.database.model.DataProduct

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var homeAdapter: HomeAdapter
    lateinit var presenter: HomePresenter

    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product, container, false)

        presenter = HomePresenter(this)
        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getProduct()
    }

    override fun initListener(view: View) {
        progressBar = view.findViewById(R.id.progress_bar)
        homeAdapter = HomeAdapter(requireActivity(), ArrayList())

        val rvProduct = view.findViewById<RecyclerView>(R.id.rv_product)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvProduct.adapter = homeAdapter
        rvProduct.layoutManager = layoutManager
    }

    override fun onResult(responseProduct: ResponseProduct) {
        val dataProduct: List<DataProduct> = responseProduct.product
        homeAdapter.setData(dataProduct)
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }

}