package com.finwin.doorstep.digicob.home.transactions.cash_transfer.pojo

data class CashTransferResponse(
    val receipt: Receipt
)

data class Receipt(
    val `data`: Data
)

data class Data(
    val ACC_NO: String,
    val BEN_ACC_NO: String,
    val CURRENT_BALANCE: String,
    val MOBILE: String,
    val NAME: String,
    val OLD_BALANCE: String,
    val TRANSFER_DATE: String,
    val TRAN_ID: String,
    val WITHDRAWAL_AMOUNT: String
)