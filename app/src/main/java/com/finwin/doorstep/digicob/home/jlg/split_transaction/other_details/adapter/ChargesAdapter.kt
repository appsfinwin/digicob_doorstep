package com.finwin.doorstep.digicob.home.jlg.split_transaction.other_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.finwin.doorstep.digicob.home.jlg.JlgAction
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Charges

import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.LayoutItemChargesBinding
import kotlin.collections.ArrayList


class ChargesAdapter : RecyclerView.Adapter<ChargesAdapter.Viewholder>() {

    lateinit var chargeList: ArrayList<Charges>
    var mAction : MutableLiveData<JlgAction> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        var binding: LayoutItemChargesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_item_charges,parent,false)
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.setBindData(chargeList[position],mAction,position)
    }

    public fun removeItem(position: Int)
    {
       chargeList.removeAt(position)
        notifyDataSetChanged()
    }

    public fun setChargeData(chargesData: ArrayList<Charges>)
    {
        this.chargeList.clear()
        this.chargeList=chargesData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return chargeList.size
    }
    class Viewholder(val binding: LayoutItemChargesBinding) : RecyclerView.ViewHolder(binding.root) {

        public fun setBindData(
            chargesData: Charges,
            mAction: MutableLiveData<JlgAction>,
            position: Int
        ){
            binding.apply {
                binding.viewModel = ChargesViewwmodel(chargesData,mAction,position)
            }
        }

    }


}