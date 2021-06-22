package com.finwin.doorstep.digicob.home.jlg.search_account_group

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.ActivitySearchGroupBinding
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.search_account_group.adapter.GroupSearchAdapter

class SearchGroupActivity : AppCompatActivity() {

    lateinit var viewModel: SearchGroupViewModel
    lateinit var binding: ActivitySearchGroupBinding
    lateinit var adapter: GroupSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_search_group)
        viewModel=ViewModelProvider(this)[SearchGroupViewModel::class.java]
        binding.viewmodel=viewModel

        setRecyclerview(binding.rvGroupAccounts)

        viewModel.mAction.observe(this, Observer {
            when(it.action)
            {
                JlgAction.JLG_GET_GROUP_ACCOUNT_SUCCESS->{

                    adapter.setSearchData(it.searchGroupAccountResponse.data)
                    adapter.notifyDataSetChanged()

                }
            }
        })
    }

    private fun setRecyclerview(rvGroupAccounts: RecyclerView) {

        rvGroupAccounts.layoutManager=LinearLayoutManager(this)
        rvGroupAccounts.setHasFixedSize(true)
        adapter= GroupSearchAdapter()
        rvGroupAccounts.adapter=adapter
        observeAdapter(adapter)
    }

    private fun observeAdapter(adapter: GroupSearchAdapter) {

        adapter.mAction.observe(this, Observer {
            when(it.action)
            {
                JlgAction.JLG_CLICK_GROUP_ACCOUNT->
                {
                    val intent = intent
                    intent.putExtra("account_number", it.groupAccountData.Ln_GlobalAccNo)
                    setResult(Activity.RESULT_OK, intent)
                    finish()

                }
            }
        })

    }
}