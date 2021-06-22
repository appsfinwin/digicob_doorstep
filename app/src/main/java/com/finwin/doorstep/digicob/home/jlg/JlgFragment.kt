package com.finwin.doorstep.digicob.home.jlg

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
import com.finwin.doorstep.digicob.databinding.JlgFragmentBinding
import com.finwin.doorstep.digicob.home.jlg.jlg_center_creation.JlgCenterCreationFragment
import com.finwin.doorstep.digicob.home.jlg.split_transaction.SplitTransactionActivity

class JlgFragment : Fragment() {

    companion object {
        fun newInstance() = JlgFragment()
        var CLICK_CENTER_CREATION: Int=1
        var CLICK_SPLIT_TRANSACTIONS: Int=2
        var DEFAULT: Int=-1
    }

    private lateinit var viewModel: JlgViewModel
    private lateinit var binding: JlgFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.jlg_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(JlgViewModel::class.java)
        binding.viewModel=viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       viewModel.mAction.observe(viewLifecycleOwner, Observer {
           when(it)
           {
               CLICK_CENTER_CREATION ->
               {
                   val myFragment: Fragment =
                       JlgCenterCreationFragment.newInstance()
                       activity?.getSupportFragmentManager()?.beginTransaction()?.replace(
                       R.id.frame_layout,
                       myFragment
                   )?.addToBackStack(null)?.commit()
               }

               CLICK_SPLIT_TRANSACTIONS ->
               {
                   var  intent=Intent(activity, SplitTransactionActivity::class.java)
                   startActivity(intent)
               }
           }
       })
    }
    override fun onStop() {
        super.onStop()
        viewModel.mAction.value= DEFAULT
    }

}