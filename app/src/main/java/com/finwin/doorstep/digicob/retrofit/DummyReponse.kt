package com.finwin.doorstep.digicob.retrofit

data class DummyReponse(
    val receipt: Receipt
)

data class Receipt(
    val error: Error
)

data class Error(
    val msg: String
)