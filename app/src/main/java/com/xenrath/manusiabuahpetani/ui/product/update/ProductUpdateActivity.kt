package com.xenrath.manusiabuahpetani.ui.product.update

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.data.Constant
import com.xenrath.manusiabuahpetani.data.database.model.DataProduct
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductDetail
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate
import com.xenrath.manusiabuahpetani.data.database.model.rajaongkir.ResponseRajaongkirTerritory
import com.xenrath.manusiabuahpetani.ui.product.ProductMapsActivity
import com.xenrath.manusiabuahpetani.utils.ApiKey
import com.xenrath.manusiabuahpetani.utils.FileUtils
import com.xenrath.manusiabuahpetani.utils.GalleryHelper
import com.xenrath.manusiabuahpetani.utils.GlideHelper
import com.xenrath.manusiabuahpetani.utils.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_product_create.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class ProductUpdateActivity : AppCompatActivity(), ProductUpdateContract.View {

    lateinit var presenter: ProductUpdatePresenter

    lateinit var product: DataProduct

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog

    private var uri: Uri? = null
    private var pickImage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_create)

        presenter = ProductUpdatePresenter(this)
        presenter.getDetail(Constant.PRODUCT_ID)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        if (Constant.LATITUDE.isNotEmpty()) {
            et_location.text = "${Constant.LATITUDE}, ${Constant.LONGITUDE}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.LATITUDE = ""
        Constant.LONGITUDE = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uri = data!!.data
            iv_image.setImageURI(uri)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Tambah Produk"

        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
        iv_help.setOnClickListener {

        }
        et_location.setOnClickListener {
            startActivity(Intent(this, ProductMapsActivity::class.java))
        }
        iv_image.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)) {
                GalleryHelper.openGallery(this)
            }
        }
        btn_save.setOnClickListener {
            val name = et_name.text
            val price = et_price.text
            val description = et_description.text
            val address = et_address.text
            val kodePos = et_pos.text
            val location = et_location.text
            val stock = et_stock.text
            when {
                name.isEmpty() -> {
                    validationError(et_name, "Nama produk tidak boleh kosong!")
                }
                price.isEmpty() -> {
                    validationError(et_price, "Harga produk tidak boleh kosong!")
                }
                address.isEmpty() -> {
                    validationError(et_address, "Alamat produk tidak boleh kosong!")
                }
                Constant.PROVINCE_ID == "0" -> {
                    showAlertError("Pilih provinsi terlebih dahulu!")
                }
                Constant.CITY_ID == "0" -> {
                    showAlertError("Pilih kota/kabupaten terlebih dahulu!")
                }
                kodePos.isEmpty() -> {
                    validationError(et_pos, "Kode POS tidak boleh kosong")
                }
                location.isEmpty() -> {
                    showAlertError("Titik lokasi harus ditambahkan")
                }
                stock.isEmpty() -> {
                    validationError(et_stock, "Stock tidak boleh kosong!")
                }
                uri == null -> {
                    showAlertError("Gambar harus ditambahkan!")
                }
                else -> {
                    presenter.updateProduct(
                        Constant.PRODUCT_ID,
                        name.toString(),
                        price.toString(),
                        description.toString(),
                        address.toString(),
                        Constant.PROVINCE_ID,
                        Constant.PROVINCE_NAME,
                        Constant.CITY_ID,
                        Constant.CITY_NAME,
                        kodePos.toString(),
                        Constant.LATITUDE,
                        Constant.LONGITUDE,
                        FileUtils.getFile(this, uri),
                        stock.toString()
                    )
                }
            }
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onLoadingTerritory(loading: Boolean) {

    }

    @SuppressLint("SetTextI18n")
    override fun onResultDetail(responseProductDetail: ResponseProductDetail) {
        product = responseProductDetail.product

        et_name.setText(product.name)
        et_price.setText(product.price)
        et_description.setText(product.description)
        et_address.setText(product.address)
        Constant.LATITUDE = product.latitude!!
        Constant.LONGITUDE = product.longitude!!
        layout_lat_lng.visibility = View.VISIBLE
        et_location.text = "${Constant.LATITUDE}, ${Constant.LONGITUDE}"
        GlideHelper.setImage(this, Constant.IP_IMAGE + product.image!!, iv_image)
        et_stock.setText(product.stock)
    }

    override fun onResultUpdate(responseProductUpdate: ResponseProductUpdate) {
        showMessage(responseProductUpdate.message)
        finish()
    }

    override fun onResultProvince(responseRajaongkirTerritory: ResponseRajaongkirTerritory) {
        layout_province.visibility = View.VISIBLE

        val arrayString = ArrayList<String>()
        arrayString.add("Pilih Provinsi")
        val listProvince = responseRajaongkirTerritory.rajaongkir.results
        for (prov in listProvince) {
            arrayString.add(prov.province!!)
        }
        val adapter = ArrayAdapter(this, R.layout.item_spiner, arrayString.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_province.adapter = adapter
        spin_province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val idProv = listProvince[position - 1].province_id
                    presenter.getCity(ApiKey.key, idProv!!)
                    Constant.PROVINCE_ID = idProv
                    Constant.PROVINCE_NAME = listProvince[position - 1].province!!
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun onResultCity(responseRajaongkirTerritory: ResponseRajaongkirTerritory) {
        layout_city.visibility = View.VISIBLE

        val arrayString = ArrayList<String>()
        arrayString.add("Pilih Kota / Kabupaten")
        val listDistrict = responseRajaongkirTerritory.rajaongkir.results
        for (city in listDistrict) {
            arrayString.add(city.type + " " + city.city_name)
        }
        val adapter = ArrayAdapter(this, R.layout.item_spiner, arrayString.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_city.adapter = adapter
        spin_city.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val idCity = listDistrict[position - 1].city_id!!
                    Constant.CITY_ID = idCity
                    Constant.CITY_NAME = listDistrict[position - 1].city_name!!
                    val postalCode = listDistrict[position - 1].postal_code
                    et_pos.setText(postalCode)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun showAlertError(message: String) {
        sError
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
            }
            .show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun validationError(editText: EditText, message: String) {
        editText.error = message
        editText.requestFocus()
    }

}