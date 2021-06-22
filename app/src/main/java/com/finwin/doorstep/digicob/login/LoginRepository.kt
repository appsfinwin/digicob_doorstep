package com.finwin.doorstep.digicob.login

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.login.action.LoginAction
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class LoginRepository(
    public var mAction: MutableLiveData<LoginAction>
) {
    @SuppressLint("CheckResult")
    fun login(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.login(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { loginResponse ->
                if (loginResponse.user.data != null) {
                    mAction.value = LoginAction(LoginAction.LOGIN_SUCCESS, loginResponse)
                } else {
                    mAction.value = LoginAction(LoginAction.LOGIN_ERROR,loginResponse.user.error)
                }
            }, { error ->
                mAction.value = LoginAction(LoginAction.API_ERROR, error.message.toString())
            }
            )

    }

    @SuppressLint("CheckResult")
    fun getMasters(apiInterface: ApiInterface) {
        val observable = apiInterface.getMasters()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                if (response!=null) {
                    mAction.value = LoginAction(LoginAction.GET_MASTERS_SUCCESS, response)
                } else {
                    mAction.value = LoginAction(LoginAction.API_ERROR,"error")
                }
            }, { error ->
                mAction.value = LoginAction(LoginAction.API_ERROR, error.message.toString())
            }
            )

    }

}
