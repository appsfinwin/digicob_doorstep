package com.finwin.doorstep.digicob.home.enquiry.mini_statement.adapter

import android.content.Context
import androidx.databinding.BaseObservable
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.LayoutItemMiniStatementBinding
import com.finwin.doorstep.digicob.home.enquiry.mini_statement.pojo.TRANSACTONS


class MiniStatementItemViewmodel(
    get: TRANSACTONS,
    binding: LayoutItemMiniStatementBinding,
    context: Context
) : BaseObservable() {
    val binding =binding
    val context=context
    var trassaction: TRANSACTONS = get
    var transactionDate:String= trassaction.TXN_DATE
    var transactionId:String= trassaction.TXN_ID
    var amount:String= trassaction.TXN_AMOUNT
    var creditAmountIndicator:String= setTextColor(trassaction.TRAN_TYPE)
    var credit:String= "C"
    var debit:String= "D"

    public fun setTextColor( tranType : String ): String{
        if (tranType.equals("C")) {
            binding.tvIndicator.setTextColor(context.resources.getColor(R.color.green))
        }else if (tranType.equals("D")) {
            binding.tvIndicator.setTextColor(context.resources.getColor(R.color.red))
        }
        return tranType
    }

    // android:textColor="@{viewmodel.creditAmountIndicator.equals('C')  ? @color/red : @color/green}"

}