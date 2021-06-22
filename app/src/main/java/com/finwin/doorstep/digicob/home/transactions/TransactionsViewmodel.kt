package com.finwin.doorstep.digicob.home.transactions

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactionsViewmodel :ViewModel() {
    var mAction:MutableLiveData<TransactionAction> = MutableLiveData()

    fun clickCashDeposit(view:View)
    {
        mAction.value= TransactionAction(TransactionAction.CLICK_CASH_DEPOSIT)
    }

    fun clickCashWithdrawal(view:View)
    {
        mAction.value= TransactionAction(TransactionAction.CLICK_CASH_WITHDRAWAL)
    }

    fun clickTransfer(view:View)
    {
        mAction.value= TransactionAction(TransactionAction.CLICK_TRANSFER)
    }

    override fun onCleared() {
        super.onCleared()
        mAction.value= TransactionAction(TransactionAction.DEFAULT)
    }
}