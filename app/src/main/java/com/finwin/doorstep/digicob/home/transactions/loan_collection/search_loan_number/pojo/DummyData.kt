package com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo

data class DummyData(
    val account: Account
)

data class Account(
    val error: Error
)

data class Error(
    val msg: String
)