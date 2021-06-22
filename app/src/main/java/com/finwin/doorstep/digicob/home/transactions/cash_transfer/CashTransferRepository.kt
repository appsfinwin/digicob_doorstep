package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.home.enquiry.mini_statement.action.MiniStatementAction
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.GetAccountHolderResponse
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.RequestBody

object CashTransferRepository {

    lateinit var mAction: MutableLiveData<CashTransferAction>

    @SuppressLint("CheckResult")
    fun getAccountHolder(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.getAccountHolder(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.account != null) {
                        mAction.value = CashTransferAction(
                            CashTransferAction.GET_ACCOUNT_HOLDER_SUCCESS,
                            response
                        )
                    } else {

                        mAction.value = CashTransferAction(CashTransferAction.API_ERROR, response)
                    }
                }, { error ->
                    mAction.value =
                        CashTransferAction(CashTransferAction.API_ERROR, error.message.toString())
                }
            )

    }


}