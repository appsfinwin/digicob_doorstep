package com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation

import androidx.databinding.ObservableField
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.jlg_loan_details.pojo.GetLoanPeriodResponse
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.pojo.CreateJlGLoanResponse
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.pojo.GetJlgProductResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.CodeMasterResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Scheme

class JlgLoanCreationAction {
    companion object{
        const val  DEFAULT =-1
        const val  GET_LOAN_PERIOD_SUCCESS=1
        const val  API_ERROR=2
        const val  CODE_MASTERS_SUCCESS=3
        const val CHANGE_SCHEME_CODE=4
        const val GET_JLG_PRODUCTS_SUCCESS = 5
        const val CREATE_JLG_LOAN_SUCCESS = 6
    }

    var action: Int = 0
    var error: String =""
    lateinit var getLoanPeriodResponse : GetLoanPeriodResponse
    lateinit var codeMasterResponse: CodeMasterResponse
    lateinit var selectedScheme: Scheme
    lateinit var getJlgProductResponse: GetJlgProductResponse
    lateinit var createJlGLoanResponse: CreateJlGLoanResponse

    constructor(action: Int) {
        this.action = action
    }

    constructor(action: Int, error: String) {
        this.action = action
        this.error = error
    }

    constructor(action: Int, getLoanPeriodResponse: GetLoanPeriodResponse) {
        this.action = action
        this.getLoanPeriodResponse = getLoanPeriodResponse
    }

    constructor(action: Int, codeMasterResponse: CodeMasterResponse) {
        this.action = action
        this.codeMasterResponse = codeMasterResponse
    }

    constructor(action: Int, selectedScheme: Scheme) {
        this.action = action
        this.selectedScheme = selectedScheme
    }

    constructor(action: Int, getJlgProductResponse: GetJlgProductResponse) {
        this.action = action
        this.getJlgProductResponse = getJlgProductResponse
    }

    constructor(action: Int, createJlGLoanResponse: CreateJlGLoanResponse) {
        this.action = action
        this.createJlGLoanResponse = createJlGLoanResponse
    }


}