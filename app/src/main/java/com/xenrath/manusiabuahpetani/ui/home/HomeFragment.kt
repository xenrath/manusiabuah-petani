package com.xenrath.manusiabuahpetani.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.data.ResponseProduct
import com.xenrath.manusiabuahpetani.data.database.model.DataProduct
import com.xenrath.manusiabuahpetani.utils.sweetalert.SweetAlertDialog

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

}