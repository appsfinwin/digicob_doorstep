package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object CashTransferRepository {

    lateinit var mAction: MutableLiveData<CashTransferAction>

    @SuppressLint("CheckResult")
    fun getDebitAccountHolder(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.getAccountHolder(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.account != null) {
                        mAction.value = CashTransferAction(
                            CashTransferAction.GET_DEBIT_ACCOUNT_HOLDER_SUCCESS,
                            response
                        )
                    } else {

                        mAction.value = CashTransferAction(CashTransferAction.API_ERROR, response)
                    }
                }, { error ->
                    when (error) {
                        is SocketTimeoutException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "Timeout! Please try again later"
                            )
                        }
                        is UnknownHostException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "No Internet"
                            )
                        }
                        else -> {
                            mAction.value =
                                CashTransferAction(
                                    CashTransferAction.API_ERROR,
                                    error.message.toString()
                                )
                        }
                    }
                }
            )

    }

    @SuppressLint("CheckResult")
    fun getCreditAccountHolder(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.getAccountHolder(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.account != null) {
                        mAction.value = CashTransferAction(
                            CashTransferAction.GET_CREDIT_ACCOUNT_HOLDER_SUCCESS,
                            response
                        )
                    } else {

                        mAction.value = CashTransferAction(CashTransferAction.API_ERROR, "error")
                    }
                }, { error ->
                    when (error) {
                        is SocketTimeoutException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "Timeout! Please try again later"
                            )
                        }
                        is UnknownHostException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "No Internet"
                            )
                        }
                        else -> {
                            mAction.value =
                                CashTransferAction(
                                    CashTransferAction.API_ERROR,
                                    error.message.toString()
                                )
                        }
                    }
                }
            )

    }

    @SuppressLint("CheckResult")
    fun generateOtp(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.otpGenerate(body)
        observable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { response ->
                    if (response?.otp != null) {
                        mAction.value = response?.let {
                            CashTransferAction(
                                CashTransferAction.OTP_GENERATE_SUCCESS,
                                it
                            )
                        }
                    } else {

                        mAction.value = CashTransferAction(CashTransferAction.API_ERROR, "Error")
                    }
                }, { error ->
                    when (error) {
                        is SocketTimeoutException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "Timeout! Please try again later"
                            )
                        }
                        is UnknownHostException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "No Internet"
                            )
                        }
                        else -> {
                            mAction.value =
                                CashTransferAction(
                                    CashTransferAction.API_ERROR,
                                    error.message.toString()
                                )
                        }
                    }
                }
            )

    }

    @SuppressLint("CheckResult")
    fun cashTransfer(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.cashTransfer(body)
        observable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { response ->
                    if (response?.receipt?.data != null) {
                        mAction.value = response?.let {
                            CashTransferAction(
                                CashTransferAction.CASH_TRANSFER_SUCCESS,
                                it
                            )
                        }
                    } else {

                        mAction.value = CashTransferAction(CashTransferAction.API_ERROR, "Error")
                    }
                }, { error ->
                    when (error) {
                        is SocketTimeoutException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "Timeout! Please try again later"
                            )
                        }
                        is UnknownHostException -> {
                            mAction.value = CashTransferAction(
                                CashTransferAction.API_ERROR,
                                "No Internet"
                            )
                        }
                        else -> {
                            mAction.value =
                                CashTransferAction(
                                    CashTransferAction.API_ERROR,
                                    error.message.toString()
                                )
                        }
                    }

                }
            )

    }
}