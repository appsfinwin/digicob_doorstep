package com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.Display
import android.view.View
import android.widget.AdapterView
import androidx.databinding.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.BR
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.action.SearchLoanAction
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.GetLoanNumbers
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.GetSchemes
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.Scheme
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.SearchLoanData
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchAction
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchRepository
import com.finwin.doorstep.digicob.login.LoginActivity
import com.finwin.doorstep.digicob.login.pojo.getMasters.Data
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Constants
import com.finwin.doorstep.digicob.utils.Services
import io.reactivex.disposables.CompositeDisposable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.HashMap

class SearchLoanNumberViewModel(application: Application) : AndroidViewModel(application),Observable {

    var mAction: MutableLiveData<SearchLoanAction> = MutableLiveData()
    var sharedPreferences :SharedPreferences =
        application.getSharedPreferences(Constants.PREFERENCE_NAME,Context.MODE_PRIVATE)

    var schemeList = ObservableArrayList<Any>()
    var schemeListData = ObservableArrayList<Scheme>()

    lateinit var accountTypeId: String
    lateinit var apiInterface: ApiInterface
    var name: ObservableField<String> = ObservableField("")
    var schemeCode: ObservableField<String> = ObservableField("")
    var accountNumber: ObservableField<String> = ObservableField("")
    var searchLoanNumber: LiveData<GetLoanNumbers>? = null


    private var selectedScheme  =0


    var accountType = ObservableArrayList<Any>()
    var accountTypeData = ObservableArrayList<Data>()
    private val registry = PropertyChangeRegistry()

    lateinit var compositeDisposable: CompositeDisposable
    var loading: SweetAlertDialog? = null

    init {
        SearchLoanNumberRepository.mAction = mAction
        schemeList.add("--Select Scheme--")
        schemeListData.add(Scheme("","--Select Scheme--"))
    }
    fun initLoading(context: Context?) {
        loading = Services.showProgressDialog(context)
    }

    fun cancelLoading() {
        if (loading != null) {
            loading!!.cancel()
            loading = null
        }
    }


    @Bindable
    fun getSelectedScheme(): Int {
        return selectedScheme
    }


    fun setSelectedScheme(selectedScheme: Int) {
        this.selectedScheme = selectedScheme
        registry.notifyChange(this, BR.selectedScheme)
    }

    fun onSelectedScheme(
        parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {

            schemeCode.set(schemeListData[position].schemeCode.toString())


    }

    fun search() {
        val jsonParams: MutableMap<String?, Any?> = HashMap()
//        jsonParams["agent_id"] = sharedPreferences.getString(Constants.AGENT_ID, "")
//        jsonParams["branch_id"] =sharedPreferences.getString(Constants.BRANCH_ID, "")

        jsonParams["agent_id"] = ""
        jsonParams["branch_id"] = ""
        jsonParams["acno"] = accountNumber.get()
        jsonParams["sch_code"] = schemeCode.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        SearchLoanNumberRepository.getLoanNumbers(apiInterface = apiInterface, body = body)
    }

    fun getLoanSchemes() {
        val jsonParams: MutableMap<String?, Any?> = HashMap()
//        jsonParams["agent_id"] = ""
//        jsonParams["branch_id"] = ""
//        jsonParams["acno"] = ""
//        jsonParams["sch_code"] = ""

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        var apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!

        SearchLoanNumberRepository.getLoanSchemes(apiInterface = apiInterface, body = body)
    }

    fun clickSearch(view: View?) {
        initLoading(view?.context)
        search()

    }



    override fun onCleared() {
        super.onCleared()
        SearchLoanNumberRepository.cancelJobs()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    fun setLoanSchemes(loanSchemes: GetSchemes) {

        for (scheme in loanSchemes.scheme){
            schemeList.add(scheme.schemeName)
            schemeListData.add(scheme)
        }
    }
}