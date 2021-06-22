package com.finwin.doorstep.digicob.home.home_activity

class HomeAction {

    companion object {

        public const val DEFAULT: Int = -1
        public const val CLICK_TRANSACTION: Int = 1
        public const val CLICK_ENQUIRY: Int = 2
        public const val CLICK_BC_REPORT: Int = 3
        public const val CLICK_AGENT: Int = 4
        public const val CLICK_LOGOUT: Int = 5
        public const val CLICK_JLG_LOAN: Int = 6
        public const val CLICK_CUSTOMER_CREATION: Int = 7
        public const val CLICK_JLG: Int = 8

    }

    var action: Int? = null

    constructor(action: Int) {
        this.action = action
    }
}