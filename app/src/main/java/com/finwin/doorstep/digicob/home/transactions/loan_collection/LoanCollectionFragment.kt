package com.finwin.doorstep.digicob.home.transactions.loan_collection

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.LoanCollectionFragmentBinding
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.SearchLoanNumberActivity
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchActivity
import com.finwin.doorstep.digicob.utils.Constants

class LoanCollectionFragment : Fragment() {

    lateinit var binding: LoanCollectionFragmentBinding
    companion object {
        fun newInstance() = LoanCollectionFragment()
    }

    private lateinit var viewModel: LoanCollectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding=  DataBindingUtil.inflate(inflater,R.layout.loan_collection_fragment,container,false)
        viewModel = ViewModelProvider(this).get(LoanCollectionViewModel::class.java)
        binding.viewModel= viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnSearchLoan.setOnClickListener {
            var intent = Intent(activity, SearchLoanNumberActivity::class.java)
            startActivityForResult(intent,Constants.INTENT_SEARCH_ACCOUNT_FROM_LOAN_COLLECTION)
        }

        viewModel._mAction.observe(viewLifecycleOwner, Observer {
            viewModel.cancelLoading()
            when(it.action)
            {
                LoanCollectionAction.API_ERROR->{

                    var dialog: SweetAlertDialog = SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    dialog.titleText = "ERROR!"
                    dialog.contentText = it.error
                    dialog.setCancelable(false)
                    dialog.setConfirmClickListener {
                        it.cancel()
                    }
                    dialog.show()

                }
                LoanCollectionAction.GET_LOAN_ACCOUNT_HOLDER_SUCCESS->{}
               // LoanCollectionAction.API_ERROR->{}
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.INTENT_SEARCH_ACCOUNT_FROM_LOAN_COLLECTION) {
            if (data != null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.accountNumber.set(accountNumber)
            } else {
                viewModel.accountNumber.set("")
            }
        } else if (requestCode == Constants.INTENT_RECIEPT_FROM_CASH_DEPOSIT) {
            viewModel.reset()
        }
    }

}