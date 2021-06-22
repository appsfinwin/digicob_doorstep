package com.finwin.doorstep.digicob.home.jlg.split_transaction.general_details

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.databinding.*
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.retrofit.ApiInterface
import com.finwin.doorstep.digicob.retrofit.RetrofitClient
import com.finwin.doorstep.digicob.utils.Services
import java.util.*
import  com.finwin.doorstep.digicob.BR
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.CodeMasterResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Data
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Mode

import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.SubTranType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class GeneralDetailsViewModel : ViewModel(), Observable {

    var transactionTypeList: ObservableArrayList<String> = ObservableArrayList()
    var transactionTypeListData: ObservableArrayList<Mode> = ObservableArrayList()
    var subTransactionTypeList: ObservableArrayList<String> = ObservableArrayList()
    var subTransactionTypeListData: ObservableArrayList<SubTranType> = ObservableArrayList()
    lateinit var apiInterface: ApiInterface
    var repository: GeneralDetailsRepository = GeneralDetailsRepository().getInstance()
    lateinit var mAction : MutableLiveData<JlgAction>

    var date: ObservableField<String> = ObservableField("--Select Date--")
    var effectiveDate: ObservableField<String> = ObservableField("--Select Effective Date--")
    var groupAccountNumber: ObservableField<String> = ObservableField("")
    var accountNumber: ObservableField<String> = ObservableField("")
    var subTransTYpe: ObservableField<String> = ObservableField("Cr")
    var tranType: ObservableField<String> = ObservableField("-1")
    var branch: ObservableField<String> = ObservableField("")
    var loanType: ObservableField<String> = ObservableField("")
    var scheme: ObservableField<String> = ObservableField("")
    var loanAmount: ObservableField<String> = ObservableField("")
    var loanDate: ObservableField<String> = ObservableField("")
    var dueDate: ObservableField<String> = ObservableField("")
    var roi: ObservableField<String> = ObservableField("")
    var accountVisibility: ObservableField<Int> = ObservableField(View.GONE)
    var groupDetailsVisibility: ObservableField<Int> = ObservableField(View.GONE)

    init {
        mAction= MutableLiveData()
        repository.mAction= mAction
    }
    fun onClickDate(view: View) {
        val myCalendar = Calendar.getInstance()
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                //Toast.makeText(view.context,year.toString()+"-"+(monthOfYear+1).toString()+"-"+dayOfMonth.toString(),Toast.LENGTH_LONG).show()

                date.set(year.toString() + "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth.toString())

            }
        val datePickerDialog: DatePickerDialog
        datePickerDialog = DatePickerDialog(
            view.context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH])
        datePickerDialog.show()
    }


    fun onClickEffectiveDate(view: View) {
        val myCalendar = Calendar.getInstance()
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                //Toast.makeText(view.context,year.toString()+"-"+(monthOfYear+1).toString()+"-"+dayOfMonth.toString(),Toast.LENGTH_LONG).show()

               effectiveDate.set(year.toString() + "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth.toString())

            }
        val datePickerDialog: DatePickerDialog
        datePickerDialog = DatePickerDialog(
            view.context, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH])
        datePickerDialog.show()
    }

    private var selectedTransactionType= 0
    private var selectedSubTransactionType= 0
    private val registry = PropertyChangeRegistry()

    @Bindable
    fun getSelectedTransactionType(): Int {
        return selectedTransactionType
    }

    fun setSelectedTransactionType(selectedTransactionType: Int) {
        this.selectedTransactionType = selectedTransactionType
        registry.notifyChange(this, BR.selectedTransactionType)
    }

    fun onSelectedTransactionType(parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {
      if (transactionTypeListData[position].Code.equals("T"))
      {
          accountNumber.set("")
          accountVisibility.set(View.VISIBLE)
      }else{
          accountVisibility.set(View.GONE)
      }
        tranType.set(transactionTypeListData[position].Code)

    }

    public fun getCodeMasters() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        repository.getCodeMasters(apiInterface)
    }

    public fun groupAccountDetails() {

        val jsonParams: MutableMap<String?, Any?> = HashMap()
        jsonParams["Accno"] =groupAccountNumber.get()
        jsonParams["SubTranType"] =subTransTYpe.get()

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface = RetrofitClient().RetrofitClient()?.create(ApiInterface::class.java)!!
        repository.groupAccountDetails(apiInterface,body)
    }

    @Bindable
    fun getSelectedSubTransactionType(): Int {
        return selectedSubTransactionType
    }

    fun setSelectedSubTransactionType(selectedSubTransactionType: Int) {
        this.selectedSubTransactionType = selectedSubTransactionType
        registry.notifyChange(this, BR.selectedSubTransactionType)
    }

    fun onSelectedSubTransactionType(parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {

        subTransTYpe.set(subTransactionTypeListData[position].Code)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
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

    fun setSpinnerData(codeMasterResponse: CodeMasterResponse) {

        for (transactionType in codeMasterResponse.Mode) {
            transactionTypeList.add(transactionType.Name)
            transactionTypeListData.add(transactionType)
        }

        for (subTransactionType in codeMasterResponse.SubTranType) {
            subTransactionTypeList.add(subTransactionType.Name)
            subTransactionTypeListData.add(subTransactionType)
        }
    }

    init {
        transactionTypeList.add("--Select Transaction Type--")
        transactionTypeListData.add(Mode("-1","--Select Transaction Type--"))

        subTransactionTypeList.add("--Select Sub-Transaction Type--")
        subTransactionTypeListData.add(SubTranType("-1","--Select Sub-Transaction Type--"))


    }
    public fun clickSearchGroupAccountNumber(view: View)
    {
        groupDetailsVisibility.set(View.GONE)
        mAction.value= JlgAction(JlgAction.CLICK_SEARCH_GROUP)
    }

    public fun clickAccountNumber(view: View)
    {
        mAction.value= JlgAction(JlgAction.CLICK_SEARCH_ACCCOUNT_NUMBER)
    }

    public fun clickSubmitAccountNumber(view: View)
    {
        if (groupAccountNumber.get().equals(""))
        {
            Services.showSnakbar("Account number cannot be empty!", view)
        }else if (subTransTYpe.get().equals("-1"))
        {
            Services.showSnakbar("Please select Sub- transaction type", view)
        }else
        {
            initLoading(view.context)
            groupAccountDetails()
        }
    }

    fun setAccountDetails(data: Data) {
        groupDetailsVisibility.set(View.VISIBLE)
        branch.set(data.Branch)
        loanType.set(data.Type)
        scheme.set(data.Scheme)
        loanAmount.set(data.Ln_LoanAmount)
        loanDate.set(data.Ln_LoanDate)
        dueDate.set(data.Ln_DueDate)
        roi.set(data.Ln_IntRate)
    }

    fun clickNext(view: View)
    {
        mAction.value= JlgAction(JlgAction.CLICK_NEXT_FROM_GENERAL_DETAILS)
    }
}