package com.xenrath.manusiabuahpetani.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.data.Constant
import com.xenrath.manusiabuahpetani.data.database.model.DataProduct
import com.xenrath.manusiabuahpetani.utils.GlideHelper

class ProductAdapter(
    val context: Context,
    var dataProduct: ArrayList<DataProduct>,
    val clickListener: (DataProduct, Int, String) -> Unit
    ): RecyclerView.Adapter<ProductAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)!!
        val tvPrice = view.findViewById<TextView>(R.id.tv_price)!!
        val tvAddress = view.findViewById<TextView>(R.id.tv_address)!!
        val imgProduct = view.findViewById<ImageView>(R.id.iv_product)!!
        val tvOption = view.findViewById<TextView>(R.id.tv_option)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_my_product, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val product = dataProduct[position]
        holder.tvName.text = product.name
        holder.tvPrice.text = product.price
        holder.tvAddress.text = product.address
        GlideHelper.setImage(context, Constant.IP_IMAGE + product.image!!, holder.imgProduct)
        holder.tvOption.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.tvOption)
            popupMenu.inflate(R.menu.menu_options)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_update -> {
                        Constant.PRODUCT_ID = product.id!!
                        clickListener(product, position, "update")
                    }

                    R.id.action_delete -> {
                        Constant.PRODUCT_ID = product.id!!
                        clickListener(product, position, "delete")
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return dataProduct.size
    }

    fun setData(newDataProduct: List<DataProduct>) {
        dataProduct.clear()
        dataProduct.addAll(newDataProduct)
        notifyDataSetChanged()
    }

    fun removeProduct(position: Int) {
        dataProduct.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataProduct.size)
    }

}