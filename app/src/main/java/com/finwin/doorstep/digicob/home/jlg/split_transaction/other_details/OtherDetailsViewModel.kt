package com.finwin.doorstep.digicob.home.jlg.split_transaction.other_details

import android.view.View
import android.widget.AdapterView
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import  com.finwin.doorstep.digicob.BR
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat
import java.util.ArrayList

class OtherDetailsViewModel : ViewModel() , Observable {


    public var accountList: ObservableArrayList<String> = ObservableArrayList();
    public var accountListData: ObservableArrayList<Dat> = ObservableArrayList();
    public var chargeList: ObservableArrayList<String> = ObservableArrayList();
    private var selectedAccountNumber = 0
    private var selectedCharges = 0
    private val registry = PropertyChangeRegistry()
    @Bindable
    fun getSelectedAccountNumber(): Int {
        return selectedAccountNumber
    }

    fun setSelectedAccountNumber(selectedDistrict: Int) {
        this.selectedAccountNumber = selectedDistrict
        registry.notifyChange(this, BR.selectedBranch)
    }

    fun onSelectedAccountNumber(parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {
       // branchId.set( branchListData[position].ID.toString())

    }

    @Bindable
    fun getSelectedCharges(): Int {
        return selectedCharges
    }

    fun setSelectedCharges(selectedDistrict: Int) {
        this.selectedCharges = selectedDistrict
        registry.notifyChange(this, BR.selectedBranch)
    }

    fun onSelectedCharges(parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {
        //branchId.set( branchListData[position].ID.toString())
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    fun setAccountList(value: ArrayList<Dat>?) {

        if (value != null) {
            for (account in value) {
                accountList.add(account.AccountNumber)
            }
        }
    }
}