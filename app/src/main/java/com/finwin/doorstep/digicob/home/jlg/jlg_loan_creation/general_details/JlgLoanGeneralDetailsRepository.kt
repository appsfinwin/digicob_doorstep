package com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.general_details

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.CashDepositAction
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object JlgLoanGeneralDetailsRepository {

    lateinit var mAction: MutableLiveData<JlgLoanGeneralDetailsAction>
}