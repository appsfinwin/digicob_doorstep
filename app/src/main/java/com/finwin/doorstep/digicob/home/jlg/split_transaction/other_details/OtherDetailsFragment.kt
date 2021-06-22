package com.finwin.doorstep.digicob.home.jlg.split_transaction.other_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.finwin.doorstep.digicob.home.jlg.split_transaction.SplitTransactionActivity
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat

import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.OtherDetailsFragmentBinding

class OtherDetailsFragment : Fragment() {

    companion object {
        fun newInstance() =
            OtherDetailsFragment()
    }

    private lateinit var viewModel: OtherDetailsViewModel
    private lateinit var binding : OtherDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(OtherDetailsViewModel::class.java)
        binding= DataBindingUtil.inflate(inflater, R.layout.other_details_fragment, container, false)
        binding.viewModel=viewModel
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       viewModel.setAccountList( (activity as SplitTransactionActivity?)?.getSelectedAccountsLiveData())

    }

    public fun getAccountsLiveData(): ObservableArrayList<Dat>
    {
        return viewModel.accountListData
    }

}