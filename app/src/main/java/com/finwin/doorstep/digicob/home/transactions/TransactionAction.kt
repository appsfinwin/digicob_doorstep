package com.finwin.doorstep.digicob.home.transactions

class TransactionAction {
    companion object{
        public var DEFAULT: Int = -1;
        public var CLICK_CASH_DEPOSIT: Int = 1;
        public var CLICK_CASH_WITHDRAWAL: Int = 2;
        public var CLICK_TRANSFER: Int = 3;

    }
    var action: Int? = null

    constructor(action: Int?) {
        this.action = action
    }
}