package com.finwin.doorstep.digicob.print_reciept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.ActivityReceiptBinding
import com.finwin.doorstep.digicob.logout_listner.BaseActivity

import com.finwin.doorstep.digicob.utils.Constants


class ReceiptActivity : BaseActivity() {

    lateinit var binding: ActivityReceiptBinding
    lateinit var viewmodel: RecieptViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_receipt)
        viewmodel=ViewModelProvider(this).get(RecieptViewmodel::class.java)
        binding.viewmodel=viewmodel

        viewmodel.from.set(intent.getStringExtra(Constants.FROM).toString())
        if (intent.getStringExtra(Constants.FROM).toString().equals(Constants.CASH_DEPOSIT)) {
            viewmodel.setLayoutForCashDeposit()
            viewmodel.setReceiptCashDeposit(
                intent.getStringExtra(Constants.DEPOSIT_DATE).toString(),
                intent.getStringExtra(Constants.ACCOUNT_NUMBER).toString(),
                intent.getStringExtra(Constants.TRANSACTION_ID).toString(),
                intent.getStringExtra(Constants.NAME).toString(),
                intent.getStringExtra(Constants.MOBILE).toString(),
                intent.getStringExtra(Constants.PREVIOUS_BALANCE).toString(),
                intent.getStringExtra(Constants.DEPOSIT_AMOUNT).toString(),
                intent.getStringExtra(Constants.CURRENT_BALANCE).toString()
            )
        }else if (intent.getStringExtra(Constants.FROM).toString().equals(Constants.BALANCE_ENQUIRY))
        {
            viewmodel.setLayoutForBalance()
            viewmodel.setReceiptBalanceEnquiry(
                intent.getStringExtra(Constants.DEPOSIT_DATE).toString(),
                intent.getStringExtra(Constants.ACCOUNT_NUMBER).toString(),
                intent.getStringExtra(Constants.NAME).toString(),
                intent.getStringExtra(Constants.MOBILE).toString(),
                intent.getStringExtra(Constants.CURRENT_BALANCE).toString()
            )
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}