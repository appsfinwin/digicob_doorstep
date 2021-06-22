package com.finwin.doorstep.digicob.home.jlg.split_transaction.remitance_details.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat

class RemittanceCreditViewmodel (dat: Dat) : BaseObservable()  {
    var dataItem= dat

    var customerId: String=dataItem.CustomerID
    var customerName: String=dataItem.CustomerName
    var accountNumber: String=dataItem.AccountNumber
    var amount: String=dataItem.Interest


    val isChecked = ObservableField(getIsChecked(dataItem.IsClosing))

    fun getIsChecked(value: String): Boolean{
        var check=false
        check = !value.equals("N")
        return check
    }
    fun onTypeChecked(checked: Boolean, i: Int) {
        if (checked) {

        } else {

        }
    }

}