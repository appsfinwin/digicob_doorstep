package com.finwin.doorstep.digicob.home.jlg.split_transaction.remitance_details.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat

class RemittanceItemViewmodel(dat: Dat, mAction: MutableLiveData<JlgAction>) : BaseObservable() {

    var dataItem= dat
    var mAction=mAction
    var customerId: String=dataItem.CustomerID
    var customerName: String=dataItem.CustomerName
    var accountNumber: String=dataItem.AccountNumber
    var interest: String=dataItem.Interest
    var principalBalance: String=dataItem.PrincipalBalance
    var penalInterest: String=dataItem.PenalInterest
    var principalDue: String=dataItem.PrincipalDue
    var totalInterest: String=dataItem.TotalInterest


    val isChecked = ObservableField(getIsChecked(dataItem.IsClosing))
    val remitance : ObservableField<String> = ObservableField(dataItem.Remittance)

    fun getIsChecked(value: String): Boolean{
        var check=false
        check = !value.equals("N")
        return check
    }
    fun onTypeChecked(checked: Boolean, i: Int) {
        if (checked) {

            mAction.value= JlgAction(JlgAction.SELECT_ACCOUNT,dataItem)

            var totalAmount: Double =
               interest.toDouble()+principalBalance.toDouble() + penalInterest.toDouble() + totalInterest.toDouble()+ principalDue.toDouble()
            remitance.set(totalInterest.toString())

        } else {
            mAction.value= JlgAction(JlgAction.DE_SELECT_ACCOUNT,dataItem)
            remitance.set(dataItem.Remittance)

        }
    }
}