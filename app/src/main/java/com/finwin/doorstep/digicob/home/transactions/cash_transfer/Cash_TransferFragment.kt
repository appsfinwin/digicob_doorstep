package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.CashTransferFragmentBinding
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.CashDepositAction
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchActivity
import com.finwin.doorstep.digicob.utils.Constants

class Cash_TransferFragment : Fragment() {

    companion object {
        fun newInstance() = Cash_TransferFragment()
    }

    private lateinit var viewModel: CashTransferViewModel
    private lateinit var binding: CashTransferFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.cash__transfer_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CashTransferViewModel::class.java)
        binding.viewModel=viewModel
        // TODO: Use the ViewModel

        viewModel.mAction.observe(viewLifecycleOwner, Observer {
            viewModel.cancelLoading()
            when(it.action)
            {
//                CashTransferAction.GET_ACCOUNT_HOLDER_SUCCESS->{}
                CashTransferAction.API_ERROR->{}
                CashTransferAction.CLICK_SEARCH -> {
                    var intent: Intent = Intent(activity, SearchActivity::class.java)
                    startActivityForResult(
                        intent,
                        Constants.INTENT_SEARCH_ACCOUNT_FOR_DEBIT_ACCOUNT
                    )
                }
                CashTransferAction.GET_ACCOUNT_HOLDER_SUCCESS -> {
                    viewModel.setDebitAccountHolderData(it.getAccountHolderResponse.account)
                }
//                CashTransferAction.CLICK_DEPOSIT -> {
//                    //showDialog()
//                }
                CashTransferAction.API_ERROR -> {
                    val customView: View =
                        LayoutInflater.from(context).inflate(R.layout.layout_error_layout, null)
                    val tv_error = customView.findViewById<TextView>(R.id.tv_error)
                    tv_error.setText(it.error)
                    SweetAlertDialog(context, SweetAlertDialog.BUTTON_NEGATIVE)
                        .setTitleText("ERROR")
                        .setCustomView(customView)
                        .show()
                }
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.INTENT_SEARCH_ACCOUNT_FOR_CREDIT_ACCOUNT) {
            if (data != null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.creditAccountNumber.set(accountNumber)
            } else {
                viewModel.creditAccountNumber.set("")
            }
        } else if (requestCode == Constants.INTENT_SEARCH_ACCOUNT_FOR_DEBIT_ACCOUNT) {
            if (data != null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.debitAccountNumber.set(accountNumber)
            } else {
                viewModel.debitAccountNumber.set("")
            }
        }
    }

}