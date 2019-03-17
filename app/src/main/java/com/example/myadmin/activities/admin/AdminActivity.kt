package com.example.myadmin.activities.admin

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView

import com.example.myadmin.R
import com.example.myadmin.activities.base.BaseActivity
import com.example.myadmin.activities.product.ProductActivity
import com.example.myadmin.adapters.AdminRecyclerAdapter
import com.example.myadmin.adapters.SectionsPagerAdapter
import com.example.myadmin.data.ClientAdapterData
import kotlinx.android.synthetic.main.content_main_new.*


class AdminActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, AdminContract.View {

    var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    var mViewPager: ViewPager? = null

    internal var presenter = AdminPresenter()

    internal var adapter: AdminRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val tabLayout = findViewById<TabLayout>(R.id.tabs1)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView1 = inflater.inflate(R.layout.content_main, null)
        val rootView2 = inflater.inflate(R.layout.content_main_new, null)
        val lvProducts = rootView2.findViewById<ListView>(R.id.lvProducts)
        val recyclerView = rootView1?.findViewById<RecyclerView>(R.id.rvCompanies)
        recyclerView!!.layoutManager = LinearLayoutManager(rootView1?.context)
        adapter = AdminRecyclerAdapter(this)
        recyclerView.adapter = adapter
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,
                arrayOf(rootView2, rootView1),
                arrayOf("PRODUCTS", "NEW COMPANIES"))
        mViewPager = findViewById(R.id.container1)
        mViewPager?.adapter = mSectionsPagerAdapter
        tabLayout.setupWithViewPager(mViewPager)
        presenter.attachView(this)
        presenter.getProductData()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClientDataFetched(clientAdapterData: ClientAdapterData) {
        adapter?.addItem(clientAdapterData)
    }

    override fun onNewProductDataFetched(dataMap: MutableMap<String, Any>?) {
        for (entry in dataMap!!.entries) {
            presenter.getClientDetails(entry)
        }
    }

    override fun onProductsFetched(dataMap: MutableMap<String, Any>?) {
        var productList = ArrayList<String>()
        dataMap?.entries?.forEach { entry ->
            productList.add(entry.key)
        }
        var productAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productList)
        lvProducts.adapter = productAdapter
        presenter.getDataFromFireBase()
    }

    fun launchProductActivity(clientAdapterData: ClientAdapterData) {
        val intent = ProductActivity.createIntent(this, clientAdapterData)
        startActivity(intent)
    }
}


