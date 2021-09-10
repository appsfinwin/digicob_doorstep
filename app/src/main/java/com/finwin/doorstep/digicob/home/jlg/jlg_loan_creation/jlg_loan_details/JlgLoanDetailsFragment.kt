package com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.jlg_loan_details

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.JlgLoanDetailsFragmentBinding
import com.finwin.doorstep.digicob.databinding.JlgLoanGeneralDetailsFragmentBinding
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.JlgLoanCreationAction
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.JlgLoanCreationActivity
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.room.MemberApplication
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.JlgLoanSelectGroupViewModel
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.MemberViewmodelFactory
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchActivity
import com.finwin.doorstep.digicob.utils.Constants

class JlgLoanDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = JlgLoanDetailsFragment()
    }

    //lateinit var viewModel: JlgLoanDetailsViewModel

    private val viewModel: JlgLoanDetailsViewModel by viewModels {
        LoanDetailsViewmodelFactory((activity?.application as MemberApplication).loanDetailsRepository)
    }
    lateinit var binding: JlgLoanDetailsFragmentBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.jlg_loan_details_fragment, container, false)
        sharedPreferences=activity?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)!!
        editor= sharedPreferences.edit()
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.initLoading(view?.context)
        viewModel.getCodeMasters()
        viewModel.disbursementAmount.set(sharedPreferences.getString("disbursement_amount","0"))
        viewModel.etLoanAmount.set(sharedPreferences.getString("disbursement_amount","0"))
        viewModel.schemeCode.set(sharedPreferences.getString("JLG_SCHEME_CODE",""))
        viewModel.branchCode.set(sharedPreferences.getString(Constants.BRANCH_ID,""))
        viewModel.mAction.observe(viewLifecycleOwner, Observer {
            viewModel.cancelLoading()

            when (it.action) {
                JlgLoanDetailsAction.GET_REF_CODES_SUCCESS -> {

                }
                JlgLoanDetailsAction.GET_LOAN_PERIOD_SUCCESS -> {

                }
                JlgLoanDetailsAction.CLICK_SUBMIT->{
                    (activity as JlgLoanCreationActivity).viewModel.loanPeriodDays.set(viewModel.loanPeriodDays.get())
                    (activity as JlgLoanCreationActivity).viewModel.loanPeriodType.set(viewModel.loanPeriodType.get())
                    (activity as JlgLoanCreationActivity).viewModel.etLoanAmount.set(viewModel.etLoanAmount.get())
                    (activity as JlgLoanCreationActivity).viewModel.etInterestRate.set(viewModel.etInterestRate.get())
                    (activity as JlgLoanCreationActivity).viewModel.etPenalInterest.set(viewModel.etPenalInterest.get())
                    (activity as JlgLoanCreationActivity).viewModel.etInstallmentNumber.set(viewModel.etInstallmentNumber.get())
                    (activity as JlgLoanCreationActivity).viewModel.etInstallmentAmount.set(viewModel.etInstallmentAmount.get())
                    (activity as JlgLoanCreationActivity).viewModel.etResolutionNumber.set(viewModel.etResolutionNumber.get())
                    (activity as JlgLoanCreationActivity).viewModel.applicationDate.set(viewModel.applicationDate.get())
                    (activity as JlgLoanCreationActivity).viewModel.resolutionDate.set(viewModel.resolutionDate.get())
                    (activity as JlgLoanCreationActivity).viewModel.etLotNumber.set(viewModel.etLotNumber.get())
                    (activity as JlgLoanCreationActivity).viewModel.collectionDay.set(viewModel.collectionDay.get())
                    (activity as JlgLoanCreationActivity).viewModel.collectionstaff.set(viewModel.collectionstaff.get())
                    (activity as JlgLoanCreationActivity).viewModel.etEmiType.set(viewModel.etEmiType.get())
                    (activity as JlgLoanCreationActivity).viewModel.paymentMode.set(viewModel.paymentMode.get())
                    (activity as JlgLoanCreationActivity).viewModel.accountNumber.set(viewModel.accountNumber.get())
                    (activity as JlgLoanCreationActivity).viewModel.etNarration.set(viewModel.etNarration.get())
                    (activity as JlgLoanCreationActivity).viewModel.etMoretoriumPeriod.set(viewModel.etMoretoriumPeriod.get())

                    view?.let { it1 ->
                        (activity as JlgLoanCreationActivity).viewModel.createGroupLoan(
                            it1
                        )
                    }

                }
                JlgLoanDetailsAction.CLICK_SEARCH_ACCOUNT->
                {
                    var intent: Intent = Intent(activity, SearchActivity::class.java)
                    startActivityForResult(
                        intent,
                        Constants.INTENT_SEARCH_ACCOUNT_FROM_JLG_LOAN_CREATION
                    )
                }
            }
        })

        (activity as JlgLoanCreationActivity).viewModel.mAction.observe(
            viewLifecycleOwner,
            Observer {
                (activity as JlgLoanCreationActivity).viewModel.cancelLoading()
                viewModel.cancelLoading()
                when (it.action) {
                    JlgLoanCreationAction.GET_LOAN_PERIOD_SUCCESS -> {
                        viewModel.setLoanPeriodData(it.getLoanPeriodResponse.receipt.data)
                        viewModel.etEmiType.set((activity as JlgLoanCreationActivity).viewModel.selectedScheme.get()?.Lnp_EMIType.toString())
                        viewModel.calculationtype.set((activity as JlgLoanCreationActivity).viewModel.selectedScheme.get()?.Lnp_IntCalcType.toString())

                    }
                    JlgLoanCreationAction.CODE_MASTERS_SUCCESS -> {
                        viewModel.setCodeMasterData(it.codeMasterResponse.Scheme)
                        viewModel.setCollectionStaff(it.codeMasterResponse.CollectionStaff)
                        viewModel.setPaymentMode(it.codeMasterResponse.Mode)

                    }
                    JlgLoanCreationAction.API_ERROR -> {
                    }
                }
            })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.INTENT_SEARCH_ACCOUNT_FROM_JLG_LOAN_CREATION) {
            if (data != null) {
                var accountNumber: String? = data.getStringExtra("account_number")
                viewModel.accountNumber.set(accountNumber)
            } else {
                viewModel.accountNumber.set("")
            }
        }
    }


}