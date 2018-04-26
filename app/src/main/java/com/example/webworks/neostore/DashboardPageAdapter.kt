package com.example.webworks.neostore

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.swap_image_dashboard.view.*

class DashboardPageAdapter(val context: Context) :PagerAdapter() {

    val imageResourses = arrayOf(R.drawable.slider_img1, R.drawable.slider_img2, R.drawable.slider_img3, R.drawable.slider_img4)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun getCount(): Int {
        return imageResourses.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.swap_image_dashboard, container, false)
        view.img_swap_image.setImageResource(imageResourses[position])
        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}