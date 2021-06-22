package com.finwin.doorstep.digicob.home.jlg.split_transaction.remitance_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.finwin.doorstep.digicob.home.jlg.JlgAction

import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.databinding.LayoutItemRemitanceDetailsBinding
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat
import java.util.*

class RemittanceDetailsAdapter : RecyclerView.Adapter<RemittanceDetailsAdapter.RemittanceCreditViewHolder>() {

    var dataList: List<Dat> = Collections.emptyList()
    var mAction: MutableLiveData<JlgAction> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemittanceCreditViewHolder {
        val binding: LayoutItemRemitanceDetailsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_remitance_details,
                parent,
                false
            )
        return RemittanceCreditViewHolder(binding)
    }

    fun setData(dataList: List<Dat>)
    {
        this.dataList= Collections.emptyList()
        this.dataList= dataList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
       return dataList.size
    }

    class RemittanceCreditViewHolder(val binding: LayoutItemRemitanceDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun setBindData(dat: Dat, mAction: MutableLiveData<JlgAction>) {

                binding.apply {
                    binding.viewModel= RemittanceItemViewmodel(dat,mAction)
                }

            }

    }

//    class RemittanceDebitViewHolder(val binding: LayoutRemittanceBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun setBindData(dat: Dat) {
//
//            binding.apply {
//                binding.viewModel= RemittanceCreditViewmodel(dat)
//            }
//
//        }
//
//    }

    override fun onBindViewHolder(holder: RemittanceCreditViewHolder, position: Int) {
        holder.setBindData(dataList[position],mAction)
    }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
////        when(holder.itemViewType)
////        {
//         //   1->{
//                val remitanceDebitViewholder: RemittanceDebitViewHolder = holder as RemittanceDebitViewHolder
//                remitanceDebitViewholder.setBindData(dataList[position])
////            } 2->{
////                val remitanceCreditViewholder: RemittanceCreditViewHolder = holder as RemittanceCreditViewHolder
////                holder.setBindData(dataList[position])
////            }
// //       }

 //   }


}