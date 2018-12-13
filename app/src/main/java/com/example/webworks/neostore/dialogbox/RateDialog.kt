package com.example.webworks.neostore.dialogbox

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.webworks.neostore.R
import com.example.webworks.neostore.descriptionmodel.DescriptionResponseModel


@SuppressLint("ValidFragment")
class RateDialog(var positon: Int?, var descriptionResponseModel: DescriptionResponseModel) : AppCompatDialogFragment() {

    var builder: AlertDialog.Builder? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.rate_dialog_box, null)

        val rateTitleName = view.findViewById<TextView>(R.id.rate_title_name)
        val rateImage = view.findViewById<ImageView>(R.id.rate_image)
        val ratingBar = view.findViewById<RatingBar>(R.id.rate_ratingbar)
        val rateButton = view.findViewById<Button>(R.id.rate_btn)
        rateTitleName.text = descriptionResponseModel.data!!.name

        if (positon == null) {

            Glide.with(context).load(descriptionResponseModel.data!!.productImage!![0].image).into(rateImage)
        } else {
            Glide.with(context).load(descriptionResponseModel.data!!.productImage!![positon!!].image).into(rateImage)
        }
        rateButton.setOnClickListener {
            ratingBar.rating
            Toast.makeText(context, "okatClicked"+ratingBar.rating, Toast.LENGTH_LONG).show()

            dismiss()
        }


        builder = AlertDialog.Builder(context)
        builder!!.setView(view)

        return builder!!.create()
    }


}