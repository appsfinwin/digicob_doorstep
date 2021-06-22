package com.finwin.doorstep.digicob.home.agent_management.change_password


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
import com.finwin.doorstep.digicob.utils.Constants

import com.finwin.doorstep.digicob.databinding.ChangePasswordFragmentBinding
import com.finwin.doorstep.digicob.home.agent_management.change_password.action.ChangePasswordAction

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.HashMap

class ChangePasswordViewModel : ViewModel() {

    var repository: ChangePasswordRepository = ChangePasswordRepository()
    var mAction :MutableLiveData<ChangePasswordAction> = MutableLiveData()

    var currentPassword: ObservableField<String> =ObservableField("")
    var newPassword: ObservableField<String> =ObservableField("")
    var reEnterNewPassword: ObservableField<String> = ObservableField("")
    lateinit var apiInterface: ApiInterface
    lateinit var binding: ChangePasswordFragmentBinding
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
    public fun changePassword() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()

        jsonParams["userid"] = LoginActivity.sharedPreferences.getString(Constants.AGENT_ID, "")
        jsonParams["oldPassword"] = currentPassword.get().toString()
        jsonParams["new_password"] = newPassword.get().toString()
        jsonParams["re_password"] = reEnterNewPassword.get().toString()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        repository.changePassword(apiInterface, body)
    }

    public fun clickSubmit(view : View)
    {
        if (currentPassword.get().equals(""))
        {
            Services.showSnakbar("Current password cannot be empty",view)
            binding.etCurrentPassword.editText?.error="Current password cannot be empty"
        }else if (newPassword.get().equals(""))
        {
            Services.showSnakbar("New password cannot be empty",view)
            binding.etNewPassword.editText?.error="New password cannot be empty"
        }else if (reEnterNewPassword.get().equals(""))
        {
            Services.showSnakbar("Please re-enter new password",view)
            binding.etReEnterPassword.editText?.error="Please re-enter new password"
        }else
        {
            initLoading(view.context)
            changePassword()
        }

    }


    public fun reset() {
        currentPassword.set("")
        newPassword.set("")
        reEnterNewPassword.set("")

    }

    fun setDataBinding(bindin: ChangePasswordFragmentBinding?) {
        if (bindin != null) {
            this.binding=bindin
        }

    }
}