package com.finwin.doorstep.digicob.home.jlg.jlg_group_creation.pojo

import com.google.gson.annotations.SerializedName

data class CreateGroupResponse(
    @SerializedName("msg")
    val message: String,
    val status: String
)