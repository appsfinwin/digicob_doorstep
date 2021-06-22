package com.finwin.doorstep.digicob.home.jlg

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JlgViewModel : ViewModel() {

    var mAction: MutableLiveData<Int> = MutableLiveData()

    public fun clickCenterCreation(view: View)
    {
        mAction.value= JlgFragment.CLICK_SPLIT_TRANSACTIONS

    }
}