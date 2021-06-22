package com.finwin.doorstep.digicob.home.bc_report.daily_report


import android.accounts.Account
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.login.LoginActivity
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Services
import com.finwin.doorstep.digicob.utils.Constants

import com.finwin.doorstep.digicob.home.bc_report.daily_report.action.DailyReportAction
import com.finwin.doorstep.digicob.home.bc_report.daily_report.pojo.BcReportData

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.HashMap

class DailyReportViewModel : ViewModel() {
    lateinit var apiInterface: ApiInterface
    var repository: DailyReportRepository = DailyReportRepository()
    var mAction: MutableLiveData<DailyReportAction> = MutableLiveData()

    var date: ObservableField<String> = ObservableField("")
    var branchName: ObservableField<String> = ObservableField("")
    var agentName: ObservableField<String> = ObservableField("")
    var agentId: ObservableField<String> = ObservableField("")
    var depositCollected: ObservableField<String> = ObservableField("")
    var depositCollectedAmount: ObservableField<String> = ObservableField("")
    var withdrawals: ObservableField<String> = ObservableField("")
    var withdrawalAmount: ObservableField<String> = ObservableField("")
    var transfers: ObservableField<String> = ObservableField("")
    var transferAmount: ObservableField<String> = ObservableField("")
    var accountsCreated: ObservableField<String> = ObservableField("")
    var customersCreated: ObservableField<String> = ObservableField("")
    var cashInHand: ObservableField<String> = ObservableField("")
    var transactions: ObservableField<String> = ObservableField("")


    init {
        repository.mAction=mAction
    }

    var loading: SweetAlertDialog? = null
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
    public fun getDailyReport() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()

        jsonParams["agent_id"] = LoginActivity.sharedPreferences.getString(Constants.AGENT_ID, "")
        jsonParams["type"] = "D"

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        repository.getDailyReport(apiInterface, body)
    }

    fun setData(data: BcReportData) {

        date.set(data.REPORT_GENERATED_DATE)
        branchName.set(LoginActivity.sharedPreferences.getString(Constants.BRANCH_NAME, ""))
        agentName.set(LoginActivity.sharedPreferences.getString(Constants.AGENT_NAME, ""))
        agentId.set(LoginActivity.sharedPreferences.getString(Constants.AGENT_ID, ""))

        depositCollected.set(data.NO_OF_DEPOSITS)
        depositCollectedAmount.set(data.TOTAL_DEPOSITS)
        withdrawals.set(data.NO_OF_WITHDRAWAL)
        withdrawalAmount.set(data.TOTAL_WITHDRAWAL)
        transfers.set(data.NO_OF_TRANSFER)
        transferAmount.set(data.TOTAL_TRANSFER)
        accountsCreated.set(data.NO_OF_ACC_CREATED)
        customersCreated.set(data.NO_OF_CUST_CREATED)
        transactions.set(data.NO_OF_TRANSACTIONS)
        cashInHand.set(data.CASH_IN_HAND)
    }
}