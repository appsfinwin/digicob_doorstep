package com.finwin.doorstep.digicob.home.transactions.neft_imps

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.finwin.doorstep.digicob.home.transactions.cash_transfer.CashTransferAction
import com.finwin.doorstep.digicob.utils.Services

class NeftViewModel : ViewModel() {
    var tvDebitAccountNumber: ObservableField<String> = ObservableField("")
    var tvDebitName: ObservableField<String> = ObservableField("")
    var debitDetailsVisibility: ObservableField<Int> = ObservableField(View.GONE)
    var tvDebitAccountStatus: ObservableField<String> = ObservableField("")
    var tvDebitMobile: ObservableField<String> = ObservableField("")
    var tvDebitBalance: ObservableField<String> = ObservableField("")

    fun clickSearchDebitAccount(view: View) {
//        mAction.value = CashTransferAction(CashTransferAction.DEFAULT)
//        debitDetailsVisibility.set(View.GONE)
//
//        mAction.value =
//            CashTransferAction(
//                CashTransferAction.CLICK_SEARCH_DEBIT_ACCOUNT
//            )
    }

    fun clickDebitSubmit(view: View) {
        if (tvDebitAccountNumber.get().equals("")) {
            Services.showSnakbar("Account number cannot be empty", view)
//        } else {
//            amount.set("")
//            initLoading(view.context)
//            getDebitAccountHolder()
//        }

        }
    }
}