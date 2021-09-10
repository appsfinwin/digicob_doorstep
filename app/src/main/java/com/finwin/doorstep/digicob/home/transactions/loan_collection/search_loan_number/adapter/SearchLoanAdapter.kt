package com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.ItemLayoutSearchLoanBinding
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.action.SearchLoanAction
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.action.SearchLoanItemViewModel
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.SearchLoanData
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchData

class SearchLoanAdapter : RecyclerView.Adapter<SearchLoanAdapter.ViewHolder>() {

    var searchDataList: List<SearchLoanData> = emptyList<SearchLoanData>()
    var mAction= MutableLiveData<SearchLoanAction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       var binding: ItemLayoutSearchLoanBinding = DataBindingUtil
           .inflate(LayoutInflater.from(parent.context),
           R.layout.item_layout_search_loan,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.setBindData(searchDataList[position],mAction)
    }

    override fun getItemCount(): Int {
        return searchDataList.size
    }

    fun setSearchData(searchData: List<SearchLoanData>)
    {
        this.searchDataList= emptyList<SearchLoanData>()
        this.searchDataList=searchData
        notifyDataSetChanged()
    }

    fun EmptyList()
    {
        this.searchDataList= emptyList<SearchLoanData>()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemLayoutSearchLoanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setBindData(
            searchLoanData: SearchLoanData,
            mAction: MutableLiveData<SearchLoanAction>
        ) {

            binding.apply {
                binding.viewmodel= SearchLoanItemViewModel(searchLoanData,mAction)
            }

        }

    }

}