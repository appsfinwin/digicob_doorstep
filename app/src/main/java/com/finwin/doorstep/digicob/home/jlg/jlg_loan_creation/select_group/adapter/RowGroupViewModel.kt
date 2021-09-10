package com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.EditText
import androidx.databinding.*
import androidx.lifecycle.MutableLiveData
import com.finwin.doorstep.digicob.BR
import com.finwin.doorstep.digicob.databinding.LayoutRowGroupBinding
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.room.Member
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.action.SelectGroupAction
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.pojo.ProductData
import java.util.*

class RowGroupViewModel(
    val member: Member,
    val b: Boolean,
    val binding: LayoutRowGroupBinding,
    val productData: ObservableArrayList<ProductData>,
    var mAction: MutableLiveData<SelectGroupAction>
) : BaseObservable() {


    var option: String = ""
    var slNumber:  ObservableField<String> =ObservableField( member.slno)
    var memberName:  ObservableField<String> = ObservableField(member.customerName)
    var customerId: ObservableField<String> = ObservableField(member.customerId)
    var accountNumber:  ObservableField<String> = ObservableField("")
    var amount:  ObservableField<String> =ObservableField( member.amount)
    var nomineeName:  ObservableField<String> = ObservableField("")
    var nomineeMobile:  ObservableField<String> = ObservableField("")
    var nomineeAddress:  ObservableField<String> = ObservableField("")
    var insuranceFee:  ObservableField<String> = ObservableField(member.insuranceFee)
    var documentationFee:  ObservableField<String> = ObservableField(member.documentationFee)
    var cgst:  ObservableField<String> =ObservableField( member.cgst)
    var sgst:  ObservableField<String> = ObservableField(member.sgst)
    var cess:  ObservableField<String> = ObservableField(member.cess)
    var disbursementAmount:  ObservableField<String> = ObservableField(member.disbursementAmount)
    var isOptionDisable: ObservableField<Boolean> = ObservableField(b)

    var registry: PropertyChangeRegistry = PropertyChangeRegistry()
    var selectedOption = 0
    var productId: ObservableField<String> = ObservableField("")
    var productList: ObservableArrayList<String> = ObservableArrayList()
    var productListListData: ObservableArrayList<ProductData> = ObservableArrayList()
    private var selectedProduct = 0
    var timer = Timer()

    @JvmName("getSelectedProduct1")
    @Bindable
    fun getSelectedProduct(): Int {
        return selectedProduct
    }

    @JvmName("setSelectedProduct1")
    fun setSelectedProduct(selectedProduct: Int) {
        this.selectedProduct = selectedProduct
        registry.notifyChange(this, BR.selectedProduct1)
    }

    init {
        productList.clear()
        productListListData.clear()
        productList.add("--select customer goods--")
        productListListData.add(ProductData("--select customer goods--", "0", "0"))

        for (i in productData.indices) {
            productList.add(productData[i].productName)
            productListListData.add(productData[i])
        }


    }


    var selectedOptions = binding.spinner21.setOnItemSelectedListener(object :
        OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
            if (pos != 0) {

            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    })

    fun clickMemberDelete(view: View) {

    }


    var temp_amount = binding.etAmount.editText?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            timer.cancel() //Terminates this timer,discarding any currently scheduled tasks.
            timer.purge() //Removes all cancelled tasks from this timer's task queue.
        }

        override fun afterTextChanged(s: Editable?) {
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {


                }
            }, 1000)
        }
    })

    fun clickSave(view: View) {

        disbursementAmount.set(

            ((amount.get()?.toDouble())?.minus(
                (
                        (insuranceFee.get()?.toDouble())!!
                                +(documentationFee.get()?.toDouble()!!)
                                +(cgst.get()?.toDouble()!!)
                                +(sgst.get()?.toDouble()!!)
                                +(cess.get()?.toDouble()!!))
            )
                    ).toString()

        )

        var member = Member(
            slno = slNumber.get().toString(),
            address = nomineeAddress.get().toString(),
            amount.get().toString(),
            cgst.get().toString(),
            cess.get().toString(),
            coApplicant = nomineeName.get().toString(),
            productId.get().toString(),
            customerId.get().toString(),
            memberName.get().toString(),
            disbursementAmount.get().toString(),
            documentationFee.get().toString(),
            insuranceFee.get().toString(),
            mobile = nomineeMobile.get().toString(),
            sgst.get().toString()
        )
        mAction.value=(SelectGroupAction(SelectGroupAction.CHANGE_DATA, member))

    }


}