package com.finwin.doorstep.digicob.home.transactions.loan_collection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Constants
import com.finwin.doorstep.digicob.utils.Services
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*


class LoanCollectionViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    var accountNumber: ObservableField<String> = ObservableField("")
    var etTransferAmount: ObservableField<String> = ObservableField("")
    var debitName: ObservableField<String> = ObservableField("")
    var debitAccountStatus: ObservableField<String> = ObservableField("")
    var debitMobile: ObservableField<String> = ObservableField("")
    var etDebitBalance: ObservableField<String> = ObservableField("")
    var debitDetailsVisibility: ObservableField<Int> = ObservableField(View.GONE)

    var sharedPreferences : SharedPreferences =
        application.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    var _mAction: MutableLiveData<LoanCollectionAction> = MutableLiveData()
    var mAction: LiveData<LoanCollectionAction> =_mAction
    init {
        LoanCollectionRepository.mAction= _mAction
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
    fun getLoanAccountHolder() {
        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["account_no"] = accountNumber.get()
        jsonParams["demandDate"] = ""
        jsonParams["flag"] = ""
        jsonParams["branch_id"] = sharedPreferences.getString(Constants.BRANCH_ID, "")
        jsonParams["sch_code"] = ""

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        LoanCollectionRepository.getLoanAccountHolder(apiInterface = apiInterface, body = body)
    }



    fun clickSubmit(view: View)
    {
        if(accountNumber.get().equals(""))
        {
            Toast.makeText(view.context, "Account number cannot be empty", Toast.LENGTH_SHORT).show()
        }else{
            initLoading(view.context)
            getLoanAccountHolder()
        }
    }

    fun reset() {

    }
}