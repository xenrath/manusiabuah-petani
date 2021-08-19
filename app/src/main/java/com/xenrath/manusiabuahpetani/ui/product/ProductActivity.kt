package com.xenrath.manusiabuahpetani.ui.product

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.data.Constant
import com.xenrath.manusiabuahpetani.data.database.PrefManager
import com.xenrath.manusiabuahpetani.data.database.model.DataProduct
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductList
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate
import com.xenrath.manusiabuahpetani.ui.product.create.ProductCreateActivity
import com.xenrath.manusiabuahpetani.ui.product.update.ProductUpdateActivity
import com.xenrath.manusiabuahpetani.utils.GlideHelper
import com.xenrath.manusiabuahpetani.utils.MapsHelper
import com.xenrath.manusiabuahpetani.utils.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_my_product.*
import kotlinx.android.synthetic.main.dialog_product.view.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class ProductActivity : AppCompatActivity(), ProductContract.View, OnMapReadyCallback {

    lateinit var prefManager: PrefManager
    lateinit var presenter: ProductPresenter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var sLoading: SweetAlertDialog
    lateinit var product: DataProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_product)

        prefManager = PrefManager(this)
        presenter = ProductPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getProduct(prefManager.prefId.toString())
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Produk Saya"
        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        MapsHelper.permissionMap(this, this)

        productAdapter = ProductAdapter(this, arrayListOf())
        { dataProduct: DataProduct, position: Int, type: String ->
            product = dataProduct
            when (type) {
                "update" -> startActivity(Intent(this, ProductUpdateActivity::class.java))
                "delete" -> showDialogDelete(dataProduct, position)
                "detail" -> showDialogDetail(dataProduct, position)
            }
        }
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
        iv_help.setOnClickListener {

        }

        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_product.adapter = productAdapter
        rv_product.layoutManager = layoutManager

        fab.setOnClickListener {
            startActivity(Intent(this, ProductCreateActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseProductList: ResponseProductList) {
        val products: List<DataProduct> = responseProductList.products!!
        productAdapter.setData(products)
    }

    override fun onResultDelete(responseProductUpdate: ResponseProductUpdate) {
        showMessage(responseProductUpdate.message)
    }

    override fun showDialogDelete(dataProduct: DataProduct, position: Int) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi")
        dialog.setMessage("Hapus ${product.name}?")
        dialog.setPositiveButton("Hapus") { dialog, _ ->
            presenter.deleteProduct(Constant.PRODUCT_ID)
            productAdapter.removeProduct(position)
            dialog.dismiss()
        }
        dialog.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    @SuppressLint("InflateParams")
    override fun showDialogDetail(dataProduct: DataProduct, position: Int) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_product, null)

        GlideHelper.setImage(applicationContext, dataProduct.image!!, view.iv_product)

        view.tv_name.text = dataProduct.name
        view.tv_category.text = dataProduct.name
        view.tv_address.text = dataProduct.name

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        view.btn_close.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(mapFragment).commit()
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(product.latitude!!.toDouble(), product.longitude!!.toDouble())
        googleMap.addMarker(MarkerOptions().position(latLng).title(product.name))
    }
}