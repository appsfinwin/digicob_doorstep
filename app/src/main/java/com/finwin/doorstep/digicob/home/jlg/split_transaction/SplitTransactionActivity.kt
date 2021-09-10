package com.finwin.doorstep.digicob.home.jlg.split_transaction

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat

import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.ActivitySplitTransactionBinding
import com.finwin.doorstep.digicob.logout_listner.BaseActivity
import com.finwin.doorstep.digicob.utils.CustomViewPager

class SplitTransactionActivity : BaseActivity() {
    lateinit var tabs: TabLayout
    lateinit var viewPager: CustomViewPager
    lateinit var viewModel: SplitTransactionViewmodel
    lateinit var binding: ActivitySplitTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= DataBindingUtil.setContentView(this, R.layout.activity_split_transaction)
        viewModel=ViewModelProvider(this)[SplitTransactionViewmodel::class.java]
        binding.viewModel=viewModel


        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager
            //, viewModel.mAction
        )
        viewPager =binding.viewPager

        viewPager.adapter = sectionsPagerAdapter
        tabs =binding.tabs
        viewPager.offscreenPageLimit = 3

        tabs.setupWithViewPager(viewPager)

    }

    fun disableTab(tabNumber: Int) {
        val vg = tabs.getChildAt(0) as ViewGroup
        val vgTab = vg.getChildAt(tabNumber) as ViewGroup
        vgTab.isEnabled = false
        viewPager.setPagingEnabled(false)
    }

    fun enableTab(tabNumber: Int) {
        val vg = tabs.getChildAt(0) as ViewGroup
        val vgTab = vg.getChildAt(tabNumber) as ViewGroup
        vgTab.isEnabled = true
        viewPager.setPagingEnabled(true)
    }


    fun gotoNext() {
        viewPager.setCurrentItem(getItem(+1), true)
    }
    fun gotoPrevious() {
        viewPager.setCurrentItem(getItem(-1), true)
    }
    private fun getItem(i: Int): Int {
        return viewPager.currentItem + i
    }

    public fun getAccountsLiveData(): MutableLiveData<ArrayList<Dat>>
    {
        return viewModel.accountsLiveData
    }
    public fun getSelectedAccountsLiveData():ObservableArrayList<Dat>
    {
        //viewModel.selectedAccountsData.value=viewModel.selectedAccountsObservable
        return viewModel.selectedAccountsObservable
    }
    public fun insertData(dat: Dat)
    {
        //OtherDetailsFragment.newInstance().insertData(dat)
        viewModel.selectedAccountsObservable.add(dat)
    }

    public fun removeData(dat: Dat)
    {
        if ( viewModel.selectedAccountsObservable.contains(dat)){
        viewModel.selectedAccountsObservable.remove(dat)}
            //OtherDetailsFragment.newInstance().removeData(dat)

    }
}