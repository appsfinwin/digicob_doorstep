package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.Account
import com.finwin.doorstep.digicob.login.LoginActivity
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Constants
import com.finwin.doorstep.digicob.utils.Services
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.*

class CashTransferViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var mAction: MutableLiveData<CashTransferAction> = MutableLiveData()
    var creditAccountNumber: ObservableField<String> = ObservableField("")
    var debitAccountNumber: ObservableField<String> = ObservableField("")
    var etTransferAmount: ObservableField<String> = ObservableField("")
    var etOtp: ObservableField<String> = ObservableField("")
    var otpId: ObservableField<String> = ObservableField("")

    var creditName: ObservableField<String> = ObservableField("")
    var debitName: ObservableField<String> = ObservableField("")

    var creditAccountStatus: ObservableField<String> = ObservableField("")
    var debitAccountStatus: ObservableField<String> = ObservableField("")
    var creditMobile: ObservableField<String> = ObservableField("")
    var debitMobile: ObservableField<String> = ObservableField("")
    var amount: ObservableField<String> = ObservableField("")
    var etDebitBalance: ObservableField<String> = ObservableField("")
    var debitDetailsVisibility: ObservableField<Int> = ObservableField(View.GONE)
    var creditDetailsVisibility: ObservableField<Int> = ObservableField(View.GONE)
    var creditLayoutVisibility: ObservableField<Int> = ObservableField(View.GONE)
    var otpLayoutVisibility: ObservableField<Int> = ObservableField(View.GONE)
    var amountLayoutVisibility: ObservableField<Int> = ObservableField(View.GONE)

    lateinit var account: Account

    init {
        CashTransferRepository.mAction = mAction
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


    fun clickSearchDebitAccount(view: View) {
        mAction.value = CashTransferAction(CashTransferAction.DEFAULT)
        debitDetailsVisibility.set(View.GONE)

        mAction.value =
            CashTransferAction(
                CashTransferAction.CLICK_SEARCH_DEBIT_ACCOUNT
            )
    }

    fun clickSearchCreditAccount(view: View) {
//        mAction.value = CashTransferAction(CashTransferAction.DEFAULT)
        creditDetailsVisibility.set(View.GONE)

        mAction.value =
            CashTransferAction(
                CashTransferAction.CLICK_SEARCH_CREDIT_ACCOUNT
            )
    }

    fun clickCreditSubmit(view: View) {
        if (creditAccountNumber.get().equals("")) {
            Services.showSnakbar("Account number cannot be empty", view)
        } else {
            amount.set("")
            initLoading(view.context)
            getCreditAccountHolder()
        }

    }


    fun clickDebitSubmit(view: View) {
        if (debitAccountNumber.get().equals("")) {
            Services.showSnakbar("Account number cannot be empty", view)
        } else {
            amount.set("")
            initLoading(view.context)
            getDebitAccountHolder()
        }

    }

    fun clickDeposit(view: View) {
        mAction.value = CashTransferAction(CashTransferAction.DEFAULT)
        if (amount.get().equals("")) {
            Services.showSnakbar("Amount cannot be empty", view)
        } else {
            //print()

            //mAction.value = CashDepositAction(CashDepositAction.CLICK_DEPOSIT)
        }
    }

    fun getCreditAccountHolder() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] = creditAccountNumber.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        CashTransferRepository.getCreditAccountHolder(apiInterface, body)
    }

    fun getDebitAccountHolder() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] = debitAccountNumber.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        CashTransferRepository.getDebitAccountHolder(apiInterface, body)
    }

    fun setCreditAccountHolderData(account: Account) {
        this.account = account
        creditDetailsVisibility.set(View.VISIBLE)
        creditName.set(account.data.NAME)
        creditAccountNumber.set(account.data.ACNO)
        creditAccountStatus.set(account.data.ACC_STATUS)
        creditMobile.set(account.data.MOBILE)
    }

    fun setDebitAccountHolderData(account: Account) {
        this.account = account
        debitDetailsVisibility.set(View.VISIBLE)
        debitName.set(account.data.NAME)
        debitAccountNumber.set(account.data.ACNO)
        debitAccountStatus.set(account.data.ACC_STATUS)
        debitMobile.set(account.data.MOBILE)
        etDebitBalance.set(account.data.CURRENT_BALANCE)
    }

    fun clickSubmitAmount(view: View)
    {
        if (etTransferAmount.get().equals(""))
        {
            Toast.makeText(view.context, "Please enter amount", Toast.LENGTH_SHORT).show()
        }else
        {
            initLoading(view.context)
            generateOtp()
        }
    }

    fun clickSubmitOtp(view: View)
    {
        if (etOtp.get().equals(""))
        {
            Toast.makeText(view.context, "Please enter OTP", Toast.LENGTH_SHORT).show()
        }else
        {
            initLoading(view.context)
            transferAmount()
        }
    }



    private fun generateOtp() {
        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] = debitAccountNumber.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        CashTransferRepository.generateOtp(apiInterface, body)

    }

    private fun transferAmount() {
        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] = debitAccountNumber.get()
        jsonParams["beni_account_no"] = creditAccountNumber.get()
        jsonParams["process_amount"] = etTransferAmount.get()
        jsonParams["otp_val"] = etOtp.get()
        jsonParams["otp_id"] = otpId.get()
        jsonParams["agent_id"] = LoginActivity.sharedPreferences.getString(Constants.AGENT_ID, "")
        jsonParams["transferType"] = "own"

        val body = JSONObject(jsonParams).toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        CashTransferRepository.cashTransfer(apiInterface, body)
    }



    fun reset() {
        creditLayoutVisibility.set(View.GONE)
        amountLayoutVisibility.set(View.GONE)
        otpLayoutVisibility.set(View.GONE)
        debitDetailsVisibility.set(View.GONE)
    }
}