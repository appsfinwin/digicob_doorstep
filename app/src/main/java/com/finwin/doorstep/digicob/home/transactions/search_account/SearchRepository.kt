package com.finwin.doorstep.digicob.home.transactions.search_account

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class SearchRepository
    (
    compositeDisposable: CompositeDisposable,
    mAction: MutableLiveData<SearchAction>

) {
    lateinit var mAction: MutableLiveData<SearchAction>
    lateinit var compositeDisposable: CompositeDisposable

    @SuppressLint("CheckResult")
    fun searchAccount(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.getSearchData(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.customer_list.data!=null) {
                        mAction.value = SearchAction(SearchAction.SEARCH_ACCOUNT_SUCCESS, response)
                    } else {

                        mAction.value = SearchAction(SearchAction.API_ERROR,response)
                    }
                }, { error ->
                    mAction.value = SearchAction(SearchAction.API_ERROR, error.message.toString())
                }
            )

    }
}