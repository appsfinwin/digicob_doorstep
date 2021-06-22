package com.finwin.doorstep.digicob.home.jlg.split_transaction

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat
import kotlin.collections.ArrayList

class SplitTransactionViewmodel : ViewModel() {

    var selectedAccountsData:MutableLiveData<ObservableArrayList<Dat>> = MutableLiveData()

    var selectedAccountsObservable: ObservableArrayList<Dat> = ObservableArrayList()
    var mAction: MutableLiveData<JlgAction> = MutableLiveData()
    var accountsLiveData:MutableLiveData<ArrayList<Dat>> = MutableLiveData()


    lateinit var apiInterface: ApiInterface
    lateinit var repository: SplitTransactionRepository

    init {
        repository = SplitTransactionRepository().getInstance()

    }

}