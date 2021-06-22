package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.CashDepositAction
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.Account
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.GetAccountHolderResponse
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Services
import kotlinx.coroutines.newFixedThreadPoolContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.HashMap

class   CashTransferViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var mAction: MutableLiveData<CashTransferAction> = MutableLiveData()
    var creditAccountNumber: ObservableField<String> = ObservableField("")
    var debitAccountNumber: ObservableField<String> = ObservableField("")

    var creditName:ObservableField<String> = ObservableField("")
    var debitName:ObservableField<String> = ObservableField("")

    var creditAccountStatus:ObservableField<String> = ObservableField("")
    var debitAccountStatus:ObservableField<String> = ObservableField("")
    var creditMobile:ObservableField<String> = ObservableField("")
    var debitMobile:ObservableField<String> = ObservableField("")
    var amount:ObservableField<String> = ObservableField("")
    var debitDetailsVisibility:ObservableField<Int> = ObservableField(View.GONE)
    var creditDetailsVisibility:ObservableField<Int> = ObservableField(View.GONE)

    lateinit var account: Account
    init {
        CashTransferRepository.mAction=mAction
    }

    var loading: SweetAlertDialog? = null
    fun initLoading(context: Context?) {
        loading = Services.showProgressDialog(context)
    }

    fun cancelLoading() {
        if (loading != null) {
            loading!!.cancel()
            loading = null
        }
    }


    fun clickSearch(view:View)
    {
        mAction.value = CashTransferAction(CashTransferAction.DEFAULT)
        debitDetailsVisibility.set(View.GONE)

        mAction.value=
            CashTransferAction(
                CashTransferAction.CLICK_SEARCH
            )
    }

    fun clickSubmit(view:View)
    {
        if(debitAccountNumber.get().equals(""))
        {
            Services.showSnakbar("Account number cannot be empty",view)
        }else {
            amount.set("")
            initLoading(view.context)
            getCreditAccountHolder()
        }

    }

    fun clickDeposit(view:View)
    {
        mAction.value = CashTransferAction(CashTransferAction.DEFAULT)
        if (amount.get().equals(""))
        {
            Services.showSnakbar("Amount cannot be empty",view)
        }else {
            //print()

            //mAction.value = CashDepositAction(CashDepositAction.CLICK_DEPOSIT)
        }
    }

    fun getCreditAccountHolder() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] =debitAccountNumber.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        CashTransferRepository.getAccountHolder(apiInterface,body)
    }

    fun setCreditAccountHolderData(account: Account) {
            this.account=account
           // visibility.set(View.VISIBLE)
            creditName.set(account.data.NAME)
            creditAccountNumber.set(account.data.ACNO)
            creditAccountStatus.set(account.data.ACC_STATUS)
            creditMobile.set(account.data.MOBILE)
    }

    fun setDebitAccountHolderData(account: Account) {
            this.account=account
            debitDetailsVisibility.set(View.VISIBLE)
            debitName.set(account.data.NAME)
            debitAccountNumber.set(account.data.ACNO)
            debitAccountStatus.set(account.data.ACC_STATUS)
            debitMobile.set(account.data.MOBILE)
    }
}