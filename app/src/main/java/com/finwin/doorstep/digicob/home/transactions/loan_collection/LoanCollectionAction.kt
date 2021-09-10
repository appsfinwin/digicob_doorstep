package com.finwin.doorstep.digicob.home.transactions.loan_collection

import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.DummyData
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.Error
import com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo.GetLoanAccountHolderResponse

class LoanCollectionAction {
    companion object{
        const val DEFAULT = -1;
        const val GET_LOAN_ACCOUNT_HOLDER_SUCCESS = 1;
        const val API_ERROR = 2;
    }

    lateinit var loanAccountHolderResponse : GetLoanAccountHolderResponse
    lateinit var dummyData: DummyData

    var action: Int? = null
    var error: String? = ""

    constructor(action: Int?, error: String?) {
        this.action = action
        this.error = error
    }

    constructor(action: Int?) {
        this.action = action
    }

    constructor( action: Int?,loanAccountHolderResponse: GetLoanAccountHolderResponse) {
        this.loanAccountHolderResponse = loanAccountHolderResponse
        this.action = action
    }

    constructor( action: Int?,dummyData: DummyData) {
        this.dummyData = dummyData
        this.action = action
    }


}