package com.finwin.doorstep.digicob.home.jlg.split_transaction.other_details.adapter

import android.view.View
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Charges

class ChargesViewwmodel(chargesData: Charges, mAction: MutableLiveData<JlgAction>, position: Int) :  BaseObservable() {

    var chargeData=chargesData
    var mAction= mAction
    var position= position
    var accountNumber: String = chargeData.accountNumber
    var charge: String =  chargeData.charges
    var amount: String =  chargeData.amount

    public fun clickDelete(view: View)
    {
        mAction.value= JlgAction(JlgAction.CLICK_DELETE,chargeData,position)
    }
}