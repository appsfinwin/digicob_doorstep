package com.finwin.doorstep.digicob.home.transactions.neft_imps

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finwin.doorstep.digicob.R

class NeftFragment : Fragment() {

    companion object {
        fun newInstance() = NeftFragment()
    }

    private lateinit var viewModel: NeftViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.neft_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeftViewModel::class.java)
        // TODO: Use the ViewModel
    }

}