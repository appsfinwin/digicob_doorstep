package com.finwin.doorstep.digicob.utils

import android.content.Context
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


public class Services {
    companion object{
//        public fun showProgressDialog(context: Context?): Dialog? {
//            val warningDialog = Dialog(context!!)
//            warningDialog.setContentView(R.layout.layout_progress_dialog)
//            warningDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            warningDialog.setCanceledOnTouchOutside(false)
//            warningDialog.setCancelable(false)
//            warningDialog.show()
//            return warningDialog
//    }

        fun showProgressDialog(context: Context?): SweetAlertDialog? {
            val sweetAlertDialog =
                SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            sweetAlertDialog.setCancelable(false)
            sweetAlertDialog.setTitleText("Please wait")
                .show()
            return sweetAlertDialog
        }

         fun showSnakbar(message: String, view: View) {
            val snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        fun convertDate(_date: String): String
        {
            var submitDate=""
            val theDate: String = _date
            val firstFormatter = SimpleDateFormat("MM-dd-yyyy")
            try {
                val date: Date = firstFormatter.parse(theDate)
                val sd = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
                submitDate = sd.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return  submitDate
        }


    }
}