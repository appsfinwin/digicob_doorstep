package com.finwin.doorstep.digicob.home.enquiry.balance_enquiry

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.BalanceEnquiryFragmentBinding
import com.finwin.doorstep.digicob.home.enquiry.balance_enquiry.action.BalanceAction
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchActivity
import com.finwin.doorstep.digicob.print_reciept.ReceiptActivity
import com.finwin.doorstep.digicob.utils.Constants


class BalanceEnquiryFragment : Fragment() {

    companion object {
        fun newInstance() = BalanceEnquiryFragment()
    }

    private lateinit var viewModel: BalanceEnquiryViewModel
    private lateinit var binding: BalanceEnquiryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BalanceEnquiryViewModel::class.java)
        binding=DataBindingUtil.inflate(inflater,
            R.layout.balance_enquiry_fragment, container, false)
        binding.viewmodel=viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mAction.observe(viewLifecycleOwner, Observer {

            when(it.action)
            {
               
                BalanceAction.API_ERROR->{}
                BalanceAction.CLICK_SEARCH->
                {
                    var intent = Intent(activity, SearchActivity::class.java)
                    startActivityForResult(intent, Constants.INTENT_SEARCH_ACCOUNT_FROM_BALANCE_ENQUIRY)
                }

                BalanceAction.BALANCE_ENQUIRY_SUCCESS->
                {
                    viewModel.cancelLoading()
                    var intent= Intent(activity, ReceiptActivity::class.java)
                    intent.putExtra(Constants.FROM, Constants.BALANCE_ENQUIRY)
                    intent.putExtra(Constants.DEPOSIT_DATE,it.balanceEnquiryResponse.balance.data.DATE)
                    intent.putExtra(Constants.CURRENT_BALANCE,it.balanceEnquiryResponse.balance.data.CURRENT_BALANCE)
                    intent.putExtra(Constants.ACCOUNT_NUMBER,it.balanceEnquiryResponse.balance.data.ACC_NO)
                    intent.putExtra(Constants.NAME,it.balanceEnquiryResponse.balance.data.NAME)
                    intent.putExtra(Constants.MOBILE,it.balanceEnquiryResponse.balance.data.MOBILE)
                    startActivityForResult(intent, Constants.INTENT_RECIEPT_FROM_BALANCE_ENQUIRY)
                }
            
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== Constants.INTENT_SEARCH_ACCOUNT_FROM_BALANCE_ENQUIRY)
        {
            if(data!=null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.accountNumber.set(accountNumber)
            }else{
                viewModel.accountNumber.set("")
            }
        }else if (requestCode== Constants.INTENT_RECIEPT_FROM_BALANCE_ENQUIRY)
        {
            viewModel.accountNumber.set("")
        }
    }


}