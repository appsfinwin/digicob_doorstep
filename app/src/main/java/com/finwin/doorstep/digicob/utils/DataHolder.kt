package com.finwin.doorstep.digicob.utils



import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.jlg_loan_details.pojo.GetLoanPeriodResponse
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.jlg_loan_details.pojo.LoanPeriodData
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.Dat
import com.finwin.doorstep.digicob.login.pojo.getMasters.Data
import java.util.*

class DataHolder {
    companion object
    {
        var nameTitleList: List<Data> = ArrayList<Data>()
        var genderList: List<Data> = ArrayList<Data>()
        var maritalStatusList: List<Data> = ArrayList<Data>()
        var occupationList: List<Data> = ArrayList<Data>()
        var accountType: List<Data> = ArrayList<Data>()
        var modeOperation: List<Data> = ArrayList<Data>()
        var constitution: List<Data> = ArrayList<Data>()
        var branchdetails: List<Data> = ArrayList<Data>()
        var accountCtg: List<Data> = ArrayList<Data>()
        var accountRelation: List<Data> = ArrayList<Data>()
        var dat: List<Dat> = ArrayList<Dat>()

    }

}