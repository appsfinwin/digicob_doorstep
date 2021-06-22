package com.finwin.doorstep.digicob.home.jlg.split_transaction.general_details

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class GeneralDetailsRepository {
    public lateinit var INSTANCE: GeneralDetailsRepository

    public fun getInstance(): GeneralDetailsRepository
    {
        if (!:: INSTANCE.isInitialized)
        {
            INSTANCE= GeneralDetailsRepository()
        }

        return INSTANCE
    }
    lateinit var mAction : MutableLiveData<JlgAction>

    @SuppressLint("CheckResult")
    fun getCodeMasters(apiInterface: ApiInterface) {
        val observable = apiInterface.getCodeMasters()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.status.equals("1")) {
                        mAction.value = JlgAction(
                            JlgAction.JLG_CODE_MASTERS_SUCCESS,
                            response
                        )
                    } else {

                        mAction.value = JlgAction(JlgAction.API_ERROR,"Something error" )
                    }
                }, { error ->
                    mAction.value =
                        JlgAction(JlgAction.API_ERROR, error.message.toString())
                }
            )

    }

    @SuppressLint("CheckResult")
    fun groupAccountDetails(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.groupAccountDetails(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.status.equals("1")) {
                        mAction.value = JlgAction(
                            JlgAction.JLG_GET_GROUP_ACCOUNT_DETAILS_SUCCESS,
                            response
                        )
                    } else {
                        mAction.value = JlgAction(JlgAction.API_ERROR,response.msg )
                    }
                }, { error ->
                    mAction.value =
                        JlgAction(JlgAction.API_ERROR, error.message.toString())
                }
            )

    }

}