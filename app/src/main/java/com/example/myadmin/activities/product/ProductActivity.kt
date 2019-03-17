package com.example.myadmin.activities.product

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.example.myadmin.R
import com.example.myadmin.activities.base.BaseActivity
import com.example.myadmin.data.ClientAdapterData
import com.example.myadmin.data.ClientData
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ProductActivity : BaseActivity() {

    private var clientAdapterData: ClientAdapterData? = null
    private var clientData: ClientData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        if (intent != null && intent.getStringExtra(COMPANY_DATA) != null) {
            clientAdapterData = Gson()
                    .fromJson(intent.getStringExtra(COMPANY_DATA), ClientAdapterData::class.java)
            clientData = clientAdapterData!!.clientData
        }
        val ivCompanyLogo = findViewById<ImageView>(R.id.companyImage)
        Picasso.with(this).load(clientData!!.companyLogoUrl)
                .into(ivCompanyLogo)
        fillEditTexts()
    }

    private fun fillEditTexts() {
        fillEditText(R.id.edCompanyName, clientData!!.companyName)
        fillEditText(R.id.edCompanyEmail, clientData!!.companyEmail)
        fillEditText(R.id.edCompanyMobile, clientData!!.companyPhone)
        fillEditText(R.id.edCompanyContactId, clientData!!.companyContactId)
        fillEditText(R.id.edCompanAddress, clientData!!.companyAddress)
        fillEditText(R.id.edCompanyWebsite, clientData!!.companyWebsite)
    }

    private fun fillEditText(edId: Int, value: String) {
        (findViewById<View>(edId) as EditText).setText(value)
    }

    companion object {

        private val COMPANY_DATA = "COMPANY_DATA"

        fun createIntent(activity: Activity, clientAdapterData: ClientAdapterData): Intent {
            val intent = Intent(activity, ProductActivity::class.java)
            intent.putExtra(COMPANY_DATA, Gson().toJson(clientAdapterData))
            return intent
        }
    }
}
