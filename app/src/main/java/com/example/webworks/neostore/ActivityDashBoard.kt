package com.example.webworks.neostore

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard_page_adapter.*
import kotlinx.android.synthetic.main.toolbar_nav.*

class ActivityDashBoard : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_page_adapter)

        setSupportActionBar(toolbar)
        navigation_view.menu.getItem(3).setActionView(R.layout.add_navigation_icon)
        val mToggle = ActionBarDrawerToggle(this, drawable, toolbar, R.string.open, R.string.close)
        drawable.addDrawerListener(mToggle)
        mToggle.syncState()

        var pageAdaptor = DashboardPageAdapter(this)

        view_pager.adapter = pageAdaptor

        circle_indicator.setViewPager(view_pager)
    }
}