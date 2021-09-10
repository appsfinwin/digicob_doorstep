package com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.select_group.pojo

import com.google.gson.annotations.SerializedName

data class CreateJlGLoanResponse(
    val AccountNo: String,
    val dt: List<Dt>,
    val dt1: List<Any>,
    val dt2: List<Any>,
    val msg: String,
    val status: String
)

data class Dt(
    @SerializedName("Due Date")
    val dueDate: String,

    @SerializedName("Group ID")
    val groupId: String,

    @SerializedName("Interest Rate")
    val interestRate: String,

    @SerializedName("Loan Amount")
    val loanAmount: String,

    @SerializedName("Loan Date")
    val loanDate: String,

    @SerializedName("Loan Type")
    val loanType: String,

    @SerializedName("Penal Interest Rate")
    val penalInterestRate: String,

    @SerializedName("Period")
    val period: Int,

    @SerializedName("Period Type")
    val periodType: String,

    @SerializedName("")
    val Scheme: String
)