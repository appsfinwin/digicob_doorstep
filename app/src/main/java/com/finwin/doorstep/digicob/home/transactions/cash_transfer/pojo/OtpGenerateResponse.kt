package com.finwin.doorstep.digicob.home.transactions.cash_transfer.pojo

data class OtpGenerateResponse(
    val otp: Otp
)

data class Otp(
    val `data`: String,
    val otp_id: String
)