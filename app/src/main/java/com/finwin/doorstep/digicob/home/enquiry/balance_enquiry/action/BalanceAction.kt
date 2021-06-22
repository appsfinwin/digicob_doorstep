package com.finwin.doorstep.digicob.home.enquiry.balance_enquiry.action

import com.finwin.doorstep.digicob.home.enquiry.balance_enquiry.pojo.BalanceEnquiryResponse
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.GetAccountHolderResponse


class BalanceAction {
    companion object{
        public var DEFAULT: Int = -1;
        public var API_ERROR: Int = 1;
        public var CLICK_SEARCH: Int = 4;
        public var BALANCE_ENQUIRY_SUCCESS: Int = 5;
        public var GET_ACCOUNT_HOLDER_SUCCESS: Int = 6;

    }
    var action: Int? = null
    lateinit var error:String
    lateinit var balanceEnquiryResponse: BalanceEnquiryResponse
    lateinit var getAccountHolderResponse: GetAccountHolderResponse


    constructor(action: Int?, balanceEnquiryResponse: BalanceEnquiryResponse) {
        this.action = action
        this.balanceEnquiryResponse = balanceEnquiryResponse
    }

    constructor(action: Int?, error: String) {
        this.action = action
        this.error = error
    }

    constructor(action: Int?) {
        this.action = action
    }

    constructor(action: Int?, getAccountHolderResponse: GetAccountHolderResponse) {
        this.action = action
        this.getAccountHolderResponse = getAccountHolderResponse
    }


}