package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.GetAccountHolderResponse

class CashTransferAction {
    companion object{
        public var DEFAULT: Int = -1;
        public var GET_ACCOUNT_HOLDER_SUCCESS: Int = 1;
        public var API_ERROR: Int = 2;
        public var CLICK_SEARCH: Int = 3;
    }

    lateinit var error :String
    var action: Int? = null
    lateinit var getAccountHolderResponse: GetAccountHolderResponse

    constructor(action: Int?) {
        this.action = action
    }

    constructor(action: Int?, getAccountHolderResponse: GetAccountHolderResponse) {
        this.action = action
        this.getAccountHolderResponse = getAccountHolderResponse
    }

    constructor( action: Int? , error: String) {
        this.error = error
        this.action = action
    }


}