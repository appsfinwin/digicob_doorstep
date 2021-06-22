package com.finwin.doorstep.digicob.home.enquiry.mini_statement

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.login.LoginActivity
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Services
import com.finwin.doorstep.digicob.home.enquiry.mini_statement.action.MiniStatementAction
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.Account
import com.finwin.doorstep.digicob.utils.Constants
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.HashMap

class MiniStatementViewmodel : ViewModel() {

     var repository: MiniStatementRepository = MiniStatementRepository()
    var mAction: MutableLiveData<MiniStatementAction> = MutableLiveData()
    var accountNumber: ObservableField<String> = ObservableField("")
    lateinit var apiInterface: ApiInterface
    var name: ObservableField<String> = ObservableField("")
    var acountNUmber: ObservableField<String> = ObservableField("")
    var accountStatus: ObservableField<String> = ObservableField("")
    var mobile: ObservableField<String> = ObservableField("")
    var amount: ObservableField<String> = ObservableField("")
    var visibility: ObservableField<Int> = ObservableField(View.GONE)
    var loading: SweetAlertDialog? = null
    var BILL = ""
    lateinit var account: Account
    fun initLoading(context: Context?) {
        loading = Services.showProgressDialog(context)
    }

    fun cancelLoading() {
        if (loading != null) {
            loading!!.cancel()
            loading = null
        }
    }
    init {
        repository.mAction=mAction
    }

    fun clickSearch(view: View)
    {
        visibility.set(View.GONE)
        mAction.value=
            MiniStatementAction(
                MiniStatementAction.CLICK_SEARCH
            )
    }

    fun clickSubmit(view: View)
    {
        if(accountNumber.get().equals(""))
        {
            Services.showSnakbar("Account number cannot be empty",view)
        }else {
            initLoading(view.context)
            getAccountHolder()
        }

    }

    fun clickSowMiniStatement(view: View)
    {

            mAction.value = MiniStatementAction(MiniStatementAction.CLICK_MINI_STATEMENT)

    }

    private fun getAccountHolder() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] =accountNumber.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        repository.getAccountHolder(apiInterface, body)
    }


    private fun cashDeposit() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] =accountNumber.get()
        jsonParams["deposit_amount"] =amount.get()
        jsonParams["agent_id"] = LoginActivity.sharedPreferences.getString(Constants.AGENT_ID,"")
        jsonParams["particular"] ="FromApp"

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        //repository.cashDeposit(apiInterface, body)
    }

    fun setAccountHolderData(account: Account) {
        this.account=account
        visibility.set(View.VISIBLE)
        name.set(account.data.NAME)
        accountNumber.set(account.data.ACNO)
        accountStatus.set(account.data.ACC_STATUS)
        mobile.set(account.data.MOBILE)
    }
    override fun onCleared() {
        super.onCleared()
        reset()

    }

    fun clickCashDeposit(it: View?) {

        if (it != null) {
            initLoading(it.context)
            cashDeposit()
        }
    }

    fun reset()
    {
        accountNumber.set("")
        visibility.set(View.GONE)
    }
}