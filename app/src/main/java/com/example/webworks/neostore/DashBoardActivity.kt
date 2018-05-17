package com.example.webworks.neostore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.webworks.neostore.Product.ProductActivity
import com.example.webworks.neostore.mycart.MyCartActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_dashboard_page_adapter.*
import kotlinx.android.synthetic.main.toolbar_nav.*

class DashBoardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var userid: String? = null
    var userpass: String? = null
    var name: String? = null
    var email: String? = null
    var image: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_page_adapter)
        val url: String = "https://s3-ap-southeast-1.amazonaws.com/dev-image.auxiliababy.com/photo/1523267098_1a5e7e64c9d17e449e334bf1945c48b2.jpg"
        val sp = getSharedPreferences("userinfo", MODE_PRIVATE)
        userid = sp.getString("username", "")
        userpass = sp.getString("password", "")
        name = sp.getString("name", "")
        email = sp.getString("email", "")
        image = sp.getString("image", "")

        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false);
        val mToggle = ActionBarDrawerToggle(this, drawable, toolbar, R.string.open, R.string.close)
        drawable.addDrawerListener(mToggle)
        mToggle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)
        val pageAdaptor = DashboardPageAdapter(this)
        view_pager.adapter = pageAdaptor
        circle_indicator.setViewPager(view_pager)
        val headerView: View = navigation_view.inflateHeaderView(R.layout.nav_header)
        val circleImageView: CircleImageView = headerView.findViewById(R.id.header_image)
        headerView.findViewById<TextView>(R.id.txt_full_name).text = name
        headerView.findViewById<TextView>(R.id.txt_email).text = email
        Glide.with(this).load(url).into(circleImageView)
        dashboard_table.setOnClickListener({
            startActivity(Intent(this, ProductActivity::class.java).putExtra("id", 1)
                    .putExtra("catagoryName", "Table"))
        })
        dashboard_sofa.setOnClickListener({
            startActivity(Intent(this, ProductActivity::class.java)
                    .putExtra("id", 3)
                    .putExtra("catagoryName", "Sofa"))
        })
        dashboard_chair.setOnClickListener({
            startActivity(Intent(this, ProductActivity::class.java)
                    .putExtra("id", 2)
                    .putExtra("catagoryName", "Chair"))
        })
        dashboard_cupboard.setOnClickListener({
            startActivity(Intent(this, ProductActivity::class.java)
                    .putExtra("id", 4)
                    .putExtra("catagoryName", "Cupboard"))
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_cart -> {
                startActivity(Intent(this, MyCartActivity::class.java))
            }
            R.id.nav_table -> {

                startActivity(Intent(this, ProductActivity::class.java).putExtra("id", 1)
                        .putExtra("catagoryName", "Table"))

            }
            R.id.nav_sofa -> {

                startActivity(Intent(this, ProductActivity::class.java).putExtra("id", 3)
                        .putExtra("catagoryName", "Sofa"))

            }
            R.id.nav_chair -> {

                startActivity(Intent(this, ProductActivity::class.java).putExtra("id", 2)
                        .putExtra("catagoryName", "chair"))

            }
            R.id.nav_cupboard -> {

                startActivity(Intent(this, ProductActivity::class.java).putExtra("id", 4)
                        .putExtra("catagoryName", "Cupboard"))

            }
            R.id.nav_logout -> {
                var mPref = getSharedPreferences("userinfo", Context.MODE_PRIVATE)
                var editor = mPref.edit()
                editor.remove("username")
                editor.remove("password")
                editor.commit()
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return true
    }
}