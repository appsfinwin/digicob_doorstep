package com.finwin.doorstep.digicob.home.jlg.split_transaction.remitance_details.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat

class RemittanceCreditViewmodel(
   var  dataItem: List<Dat>,
    var position: Int,
    var dataListLiveData: MutableLiveData<List<Dat>>
) : BaseObservable()  {


    var customerId: String=dataItem[position].CustomerID
    var customerName: String=dataItem[position].CustomerName
    var accountNumber: String=dataItem[position].accountNumber
    var amount: String=dataItem[position].Interest


    val isChecked = ObservableField(getIsChecked(dataItem[position].IsClosing))

    fun getIsChecked(value: String): Boolean{
        var check=false
        check = !value.equals("F")
        return check
    }

    fun onTypeChecked(checked: Boolean, i: Int) {
        if (checked) {
            dataItem[position].IsClosing="T"
            dataListLiveData.value=dataItem
        } else {
            dataItem[position].IsClosing="F"
            dataListLiveData.value=dataItem
        }
    }

}