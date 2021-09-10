package com.finwin.doorstep.digicob.home.jlg.split_transaction.general_details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.search_account_group.SearchGroupAccountActivity
import com.finwin.doorstep.digicob.home.jlg.split_transaction.SplitTransactionActivity
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchActivity
import com.finwin.doorstep.digicob.utils.Constants

import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.GeneralDetailsFragmentBinding

class GeneralDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = GeneralDetailsFragment()
    }

    private lateinit var viewModel: GeneralDetailsViewModel
    private lateinit var binding: GeneralDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(
            inflater,
            R.layout.general_details_fragment,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(GeneralDetailsViewModel::class.java)
        binding.viewModel=viewModel

        viewModel.initLoading(context)
        viewModel.getCodeMasters()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as SplitTransactionActivity?)?.disableTab(1)
        (activity as SplitTransactionActivity?)?.disableTab(2)

        viewModel.mAction.observe(viewLifecycleOwner, Observer {
            viewModel.cancelLoading()
            when (it.action) {
                JlgAction.JLG_CODE_MASTERS_SUCCESS -> {
                    viewModel.setSpinnerData(it.codeMasterResponse)
                }

                JlgAction.CLICK_SEARCH_GROUP -> {
                    var intent: Intent = Intent(activity, SearchGroupAccountActivity::class.java)
                    startActivityForResult(intent, Constants.INTENT_SEARCH_GROUP)
                }
                JlgAction.JLG_GET_GROUP_ACCOUNT_DETAILS_SUCCESS -> {
                    if (!it.groupAcccountDetails.data[0].AccountNo.isEmpty()) {
                       // DataHolder.dat= it.groupAcccountDetails.dat
                        (activity as SplitTransactionActivity?)?.getAccountsLiveData()?.value=it.groupAcccountDetails.dat
                        viewModel.setAccountDetails(it.groupAcccountDetails.data[0])
                    }
                }
                JlgAction.CLICK_NEXT_FROM_GENERAL_DETAILS->
                {
                    (activity as SplitTransactionActivity?)?.gotoNext()
                    (activity as SplitTransactionActivity?)?.enableTab(1)
                }

                JlgAction.CLICK_SEARCH_ACCCOUNT_NUMBER->
                {
                    var intent:Intent= Intent(activity, SearchActivity::class.java)
                    startActivityForResult(intent, Constants.INTENT_SEARCH_ACCOUNT_FROM_JLG)
                }

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== Constants.INTENT_SEARCH_GROUP)
        {
            if(data!=null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.groupAccountNumber.set(accountNumber)
            }else{
                viewModel.groupAccountNumber.set("")
            }
        }else if (requestCode== Constants.INTENT_SEARCH_ACCOUNT_FROM_JLG)
        {
            if(data!=null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.accountNumber.set(accountNumber)
            }else{
                viewModel.accountNumber.set("")
            }
        }
    }

}