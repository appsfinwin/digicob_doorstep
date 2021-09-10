package com.finwin.doorstep.digicob.home.transactions.loan_collection.search_loan_number.pojo

import com.google.gson.annotations.SerializedName

data class GetSchemes(
    @SerializedName("Scheme")
    val scheme: List<Scheme>
)

data class Scheme(
    @SerializedName("Sch_Code")
    val schemeCode: String,

    @SerializedName("Sch_Name")
    val schemeName: String
)