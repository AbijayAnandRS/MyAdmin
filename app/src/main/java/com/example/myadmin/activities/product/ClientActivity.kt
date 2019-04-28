package com.example.myadmin.activities.product

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.example.myadmin.R
import com.example.myadmin.activities.admin.AdminActivity
import com.example.myadmin.activities.base.BaseActivity
import com.example.myadmin.activities.productdetails.ProductDetailsActivity
import com.example.myadmin.adapters.ProductRecyclerAdapter
import com.example.myadmin.data.ClientAdapterData
import com.example.myadmin.data.ClientData
import com.example.myadmin.data.ProductData
import com.example.myadmin.utils.FunctionUtils.isNotEmpty
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_products.*

class ClientActivity : BaseActivity(), ClientContract.View {

    private var clientAdapterData: ClientAdapterData? = null
    private var clientData: ClientData? = null

    internal var presenter = ClientPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        presenter.attachView(this)
        if (intent != null && intent.getStringExtra(COMPANY_DATA) != null) {
            clientAdapterData = Gson()
                    .fromJson(intent.getStringExtra(COMPANY_DATA), ClientAdapterData::class.java)
            clientData = clientAdapterData!!.clientData
        }
        val ivCompanyLogo = findViewById<ImageView>(R.id.companyImage)
        if(isNotEmpty(clientData!!.companyLogoUrl)) {
            Picasso.with(this).load(clientData!!.companyLogoUrl)
                    .into(ivCompanyLogo)
        }
        val productMap = clientAdapterData?.productMap
        if (productMap != null) {
            var productList = ArrayList<ProductData>()
            productMap?.entries?.forEach { entry ->
                val productData = Gson().fromJson(Gson().toJson(entry.value), ProductData::class.java)
                productData.clientKey = clientAdapterData!!.clientKey
                productData.productKey = entry.key
                productList.add(productData)

            }
            rv_products.layoutManager = GridLayoutManager(this, 3)
            val productAdapter = ProductRecyclerAdapter(this)
            rv_products.adapter = productAdapter
            productAdapter.updateProduct(productList)
        }
        fillEditTexts()
        bt_update.setOnClickListener {
            presenter.updateClient(clientAdapterData?.clientKey, getClientData())
        }
        bt_delete.setOnClickListener {
            presenter.deleteClient(clientAdapterData?.clientKey)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this,AdminActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getClientData(): ClientData {
        val clientData = ClientData()
        clientData.companyName = edCompanyName.text.toString()
        clientData.companyEmail = edCompanyEmail.text.toString()
        clientData.companyAddress = (edCompanAddress.text.toString())
        clientData.companyContactId = (edCompanyContactId.text.toString())
        clientData.companyPhone = (edCompanyMobile.text.toString())
        clientData.companyWebsite = (edCompanyWebsite.text.toString())
        clientData.companyLogoUrl = (edCompanyUrl.text.toString())
        return clientData;
    }
    private fun fillEditTexts() {
        fillEditText(R.id.edCompanyName, clientData!!.companyName)
        fillEditText(R.id.edCompanyEmail, clientData!!.companyEmail)
        fillEditText(R.id.edCompanyMobile, clientData!!.companyPhone)
        fillEditText(R.id.edCompanyContactId, clientData!!.companyContactId)
        fillEditText(R.id.edCompanAddress, clientData!!.companyAddress)
        fillEditText(R.id.edCompanyWebsite, clientData!!.companyWebsite)
        fillEditText(R.id.edCompanyUrl, clientData!!.companyLogoUrl)
    }

    private fun fillEditText(edId: Int, value: String) {
        (findViewById<View>(edId) as EditText).setText(value)
    }

    fun launchProductDetails(productData: ProductData) {
        val intent = ProductDetailsActivity.createIntent(this, productData)
        startActivity(intent)
    }

    companion object {

        private val COMPANY_DATA = "COMPANY_DATA"

        fun createIntent(activity: Activity, clientAdapterData: ClientAdapterData): Intent {
            val intent = Intent(activity, ClientActivity::class.java)
            intent.putExtra(COMPANY_DATA, Gson().toJson(clientAdapterData))
            return intent
        }
    }
}
