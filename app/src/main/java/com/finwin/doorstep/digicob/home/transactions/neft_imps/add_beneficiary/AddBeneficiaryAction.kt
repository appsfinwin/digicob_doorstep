package com.finwin.doorstep.digicob.home.transactions.neft_imps.add_beneficiary

class AddBeneficiaryAction {

    companion object {
        const val DEFAULt: Int = -1
    }

    var action: Int? = null
    var error: String = ""

    constructor(action: Int?) {
        this.action = action
    }
    constructor(action: Int?, error: String)
    {
        this.action= action
        this.error= error
    }
}