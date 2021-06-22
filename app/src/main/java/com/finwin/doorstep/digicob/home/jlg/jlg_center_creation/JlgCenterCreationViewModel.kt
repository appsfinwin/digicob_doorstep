package com.finwin.doorstep.digicob.home.jlg.jlg_center_creation

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
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.utils.Constants
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.HashMap

class JlgCenterCreationViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var repository : JlgCentercreationRepository = JlgCentercreationRepository().getInstance()
    var mAction: MutableLiveData<JlgAction> = MutableLiveData()
    lateinit var apiInterface: ApiInterface
    var centerName: ObservableField<String> =ObservableField("")
    var centerCode: ObservableField<String> = ObservableField("")

    init {
        repository.mAction=mAction

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
    public fun getJlgCenter() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()

        jsonParams["Brcode"] = LoginActivity.sharedPreferences.getString(Constants.BRANCH_ID, "")

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        //repository.getJlgCenter(apiInterface, body)
    }

    public fun createJlgCenter() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()

        jsonParams["Brcode"] = LoginActivity.sharedPreferences.getString(Constants.BRANCH_ID, "")
        jsonParams["MakerId"] = LoginActivity.sharedPreferences.getString(Constants.AGENT_ID, "")
        jsonParams["CentreCode"] =centerCode.get()
        jsonParams["CentreName"] =centerName.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
       // repository.jlgCreateCenter(apiInterface, body)
    }

    public fun clickSubmit(view: View)
    {
        if (centerCode.get().equals(""))
        {
         Services.showSnakbar("Center code cannot be empty",view)
        }else if (centerName.get().equals(""))
        {
            Services.showSnakbar("Center name cannot be empty",view)
        }else
        {
            initLoading(view.context)
            createJlgCenter()
        }
    }

    fun resetText() {
        centerName.set("")
        centerCode.set("")
    }


}