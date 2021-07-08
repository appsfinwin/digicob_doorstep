package com.finwin.doorstep.digicob.home.transactions.cash_transfer

import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.GetAccountHolderResponse
import com.finwin.doorstep.digicob.home.transactions.cash_transfer.pojo.CashTransferResponse
import com.finwin.doorstep.digicob.home.transactions.cash_transfer.pojo.OtpGenerateResponse

class CashTransferAction {
    companion object{
        public var DEFAULT: Int = -1;
        public var GET_DEBIT_ACCOUNT_HOLDER_SUCCESS: Int = 1;
        public var API_ERROR: Int = 2;
        public var CLICK_SEARCH_DEBIT_ACCOUNT: Int = 3;
        public var CLICK_SEARCH_CREDIT_ACCOUNT: Int = 4;
        public var GET_CREDIT_ACCOUNT_HOLDER_SUCCESS: Int = 5;
        public var OTP_GENERATE_SUCCESS: Int = 6;
        public var CASH_TRANSFER_SUCCESS: Int = 7;
    }

    lateinit var error :String
    var action: Int? = null
    lateinit var getAccountHolderResponse: GetAccountHolderResponse
    lateinit var otpGenerateResponse: OtpGenerateResponse
    lateinit var cashTransferResponse: CashTransferResponse

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

    constructor(action: Int?, otpGenerateResponse: OtpGenerateResponse) {
        this.action = action
        this.otpGenerateResponse = otpGenerateResponse
    }

    constructor(action: Int?, cashTransferResponse: CashTransferResponse) {
        this.action = action
        this.cashTransferResponse = cashTransferResponse
    }


}