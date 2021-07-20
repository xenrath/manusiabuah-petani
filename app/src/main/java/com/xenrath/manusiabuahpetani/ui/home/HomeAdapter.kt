package com.xenrath.manusiabuahpetani.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.data.Constant
import com.xenrath.manusiabuahpetani.data.database.model.DataProduct
import com.xenrath.manusiabuahpetani.ui.home.detail.DetailProductActivity
import com.xenrath.manusiabuahpetani.utils.GlideHelper

class HomeAdapter(
    var context: Context,
    var product: ArrayList<DataProduct>
    ): RecyclerView.Adapter<HomeAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)!!
        val tvPrice = view.findViewById<TextView>(R.id.tv_price)!!
        val tvAddress = view.findViewById<TextView>(R.id.tv_address)!!
        val imgProduct = view.findViewById<ImageView>(R.id.iv_product)!!
        val cvProduct = view.findViewById<CardView>(R.id.cv_product)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_home, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvName.text = product[position].name
        holder.tvPrice.text = product[position].price
        holder.tvAddress.text = product[position].address
        GlideHelper.setImage(context, Constant.IP_IMAGE + product[position].image!!, holder.imgProduct)
        holder.cvProduct.setOnClickListener {
            val intent = Intent(context, DetailProductActivity::class.java)
            val data = Gson().toJson(product[position], DataProduct::class.java)
            intent.putExtra("product", data)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return product.size
    }

    fun setData(newDataProduct: List<DataProduct>) {
        product.clear()
        product.addAll(newDataProduct)
        notifyDataSetChanged()
    }

}