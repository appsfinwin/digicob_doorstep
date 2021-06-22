package com.finwin.doorstep.digicob.home.enquiry.mini_statement

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.home.enquiry.mini_statement.action.MiniStatementAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class MiniStatementRepository {
    lateinit var mAction: MutableLiveData<MiniStatementAction>

    @SuppressLint("CheckResult")
    fun getAccountHolder(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.getAccountHolder(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.account != null) {
                        mAction.value = MiniStatementAction(
                            MiniStatementAction.GET_ACCOUNT_HOLDER_SUCCESS,
                            response
                        )
                    } else {

                        mAction.value = MiniStatementAction(MiniStatementAction.API_ERROR, response)
                    }
                }, { error ->
                    mAction.value =
                        MiniStatementAction(MiniStatementAction.API_ERROR, error.message.toString())
                }
            )

    }

}