package com.finwin.doorstep.digicob.home.jlg

import com.finwin.doorstep.digicob.home.jlg.jlg_center_creation.pojo.CenterData
import com.finwin.doorstep.digicob.home.jlg.jlg_center_creation.pojo.JlgCreateCenterResponse
import com.finwin.doorstep.digicob.home.jlg.jlg_center_creation.pojo.getjLgCenterResponse
import com.finwin.doorstep.digicob.home.jlg.search_account_group.pojo.GroupAccountData
import com.finwin.doorstep.digicob.home.jlg.search_account_group.pojo.SearchGroupAccountResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Charges
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.CodeMasterResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.GroupAccountDetails

class JlgAction {
    companion object {

        const val DEFAULT: Int = -1
        const val API_ERROR: Int = 1
        const val JLG_GET_GROUP_ACCOUNT_SUCCESS: Int = 2
        const val JLG_CLICK_GROUP_ACCOUNT: Int = 3
        const val JLG_CODE_MASTERS_SUCCESS: Int = 4
        const val CLICK_SEARCH_GROUP: Int = 5
        const val JLG_GET_GROUP_ACCOUNT_DETAILS_SUCCESS: Int = 6
        const val CLICK_NEXT_FROM_GENERAL_DETAILS: Int = 7
        const val CLICK_SEARCH_ACCCOUNT_NUMBER: Int = 8
        const val CLICK_NEXT_FROM_REMITTANCE_DETAILS: Int = 9
        const val CLICK_PREVIOUS_FROM_REMITTANCE_DETAILS: Int = 10
        const val CLICK_PREVIOUS_FROM_OTHER_DETAILS: Int = 10
        const val SELECT_ACCOUNT: Int = 11
        const val DE_SELECT_ACCOUNT: Int = 12
        const val CLICK_DELETE: Int = 13

    }

    var action: Int? = null
    var position: Int? = null
    var error: String = ""
    lateinit var jlgCenterResponse: getjLgCenterResponse
    lateinit var jlgCreateCenterResponse: JlgCreateCenterResponse
    lateinit var centerData: CenterData
    lateinit var  groupAccountData: GroupAccountData
    lateinit var searchGroupAccountResponse: SearchGroupAccountResponse
    lateinit var codeMasterResponse: CodeMasterResponse
    lateinit var groupAcccountDetails: GroupAccountDetails
    lateinit var dat: Dat
    lateinit var charges: Charges



    constructor(action: Int?) {
        this.action = action
    }

    constructor(action: Int?, error: String) {
        this.action = action
        this.error = error
    }

    constructor(action: Int?, jlgCenterResponse: getjLgCenterResponse) {
        this.action = action
        this.jlgCenterResponse = jlgCenterResponse
    }

    constructor(action: Int?, jlgCreateCenterResponse: JlgCreateCenterResponse) {
        this.action = action
        this.jlgCreateCenterResponse = jlgCreateCenterResponse
    }

    constructor(action: Int?, centerData: CenterData) {
        this.action = action
        this.centerData = centerData
    }

    constructor(action: Int?, groupAccountData: GroupAccountData) {
        this.action = action
        this.groupAccountData = groupAccountData
    }

    constructor(action: Int?, searchGroupAccountResponse: SearchGroupAccountResponse) {
        this.action = action
        this.searchGroupAccountResponse = searchGroupAccountResponse
    }

    constructor(action: Int?, codeMasterResponse: CodeMasterResponse) {
        this.action = action
        this.codeMasterResponse = codeMasterResponse
    }

    constructor(action: Int?, groupAcccountDetails: GroupAccountDetails) {
        this.action = action
        this.groupAcccountDetails = groupAcccountDetails
    }

    constructor(action: Int?, dat: Dat) {
        this.action = action
        this.dat = dat
    }

    constructor(action: Int?, charges: Charges, position:Int?) {
        this.action = action
        this.charges = charges
        this.position=position
    }


}