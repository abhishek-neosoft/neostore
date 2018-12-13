package com.example.webworks.neostore.mycart

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class MyCartItemTouchHelper( dragDirs: Int, swipeDirs: Int, var context: Context ) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    var myCartItemTouchHelperListener: MyCartItemTouchHelperListener

    init {
        myCartItemTouchHelperListener = context as MyCartItemTouchHelperListener
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder?): Boolean {

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        myCartItemTouchHelperListener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }



    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder, actionState: Int) {

        val foregroundView = (viewHolder as MyCartAdapter.RecyclerViewHolder).viewForeground
        getDefaultUIUtil().onSelected(foregroundView)
    }

    override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        val foregroundView = (viewHolder as MyCartAdapter.RecyclerViewHolder).viewForeground
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)

    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView = (viewHolder as MyCartAdapter.RecyclerViewHolder).viewForeground
        getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as MyCartAdapter.RecyclerViewHolder).viewForeground
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    interface MyCartItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
    }

}
