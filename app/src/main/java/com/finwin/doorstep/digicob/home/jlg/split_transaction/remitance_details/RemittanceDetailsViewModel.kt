package com.finwin.doorstep.digicob.home.jlg.split_transaction.remitance_details

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finwin.doorstep.digicob.home.jlg.JlgAction

class RemittanceDetailsViewModel : ViewModel() {

    var mAction : MutableLiveData<JlgAction>

    init {
        mAction= MutableLiveData()
    }
    fun clickNext(view: View)
    {
        mAction.value= JlgAction(JlgAction.CLICK_NEXT_FROM_REMITTANCE_DETAILS)
    }

    fun clickPrevious(view: View)
    {
        mAction.value= JlgAction(JlgAction.CLICK_PREVIOUS_FROM_REMITTANCE_DETAILS)
    }
}