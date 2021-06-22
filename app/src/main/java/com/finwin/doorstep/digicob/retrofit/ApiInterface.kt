package com.finwin.doorstep.digicob.retrofit

import com.finwin.doorstep.digicob.home.agent_management.change_password.pojo.ChangePasswordResponse
import com.finwin.doorstep.digicob.home.bc_report.daily_report.pojo.BcReportResponse
import com.finwin.doorstep.digicob.home.customer_creation.pojo.*
import com.finwin.doorstep.digicob.home.enquiry.balance_enquiry.pojo.BalanceEnquiryResponse
import com.finwin.doorstep.digicob.home.enquiry.mini_statement.pojo.MiniStatementResponse
import com.finwin.doorstep.digicob.home.jlg.jlg_center_creation.pojo.JlgCreateCenterResponse
import com.finwin.doorstep.digicob.home.jlg.jlg_center_creation.pojo.getjLgCenterResponse
import com.finwin.doorstep.digicob.home.jlg.search_account_group.pojo.SearchGroupAccountResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.CodeMasterResponse
import com.finwin.doorstep.digicob.home.jlg.split_transaction.pojo.GroupAccountDetails
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.CashDepositResponse
import com.finwin.doorstep.digicob.home.transactions.cash_deposit.pojo.GetAccountHolderResponse
import com.finwin.doorstep.digicob.home.transactions.cash_withdrawal.pojo.CashWithdrawalResponse
import com.finwin.doorstep.digicob.home.transactions.cash_withdrawal.pojo.OtpResponse
import com.finwin.doorstep.digicob.home.transactions.search_account.SearchResponse
import com.finwin.doorstep.digicob.login.pojo.LoginResponse


import com.finwin.doorstep.digicob.login.pojo.getMasters.GetMasters
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("userLogin")
    fun login(@Body body: RequestBody?): Observable<LoginResponse>

    @POST("getCustUnderAgent")
    fun getSearchData(@Body body: RequestBody?): Observable<SearchResponse>

    @POST("getMastersAll")
    fun getMasters(): Observable<GetMasters>

    @POST("getAccountHolder")
    fun getAccountHolder(@Body body: RequestBody?): Observable<GetAccountHolderResponse>

    @POST("cashDeposit")
    fun cashDeposit(@Body body: RequestBody?): Observable<CashDepositResponse>

    @POST("OTPGenerate")
    fun generateOtp(@Body body: RequestBody?): Observable<OtpResponse>

    @POST("CashWithdrawal")
    fun cashWithdrawal(@Body body: RequestBody?): Observable<CashWithdrawalResponse>

    @POST("getMiniStatement")
    fun miniStatement(@Body body: RequestBody?): Observable<MiniStatementResponse>

    @POST("balanceEnqury")
    fun balanceEnqury(@Body body: RequestBody?): Observable<BalanceEnquiryResponse>

    @POST("BCReport")
    fun bcReport(@Body body: RequestBody?): Observable<BcReportResponse>

    @POST("passwordChange")
    fun changePassword(@Body body: RequestBody?): Observable<ChangePasswordResponse>

    @POST("GetAllLoanCentreByBranch")
    fun getJlgBranches(@Body body: RequestBody?): Observable<getjLgCenterResponse>

    @POST("LoanCentreCreator")
    fun jlgCreateCenter(@Body body: RequestBody?): Observable<JlgCreateCenterResponse>

    @POST("LoanCentreUpdater")
    fun jlgUpdateCenter(@Body body: RequestBody?): Observable<JlgCreateCenterResponse>


    @POST("JLGFetchAccNo")
    fun getGroupSearch(@Body body: RequestBody?): Observable<SearchGroupAccountResponse>

    @GET("RefCodeMaster")
    fun getCodeMasters(): Observable<CodeMasterResponse>

    @POST("JLGTransGroupSelect")
    fun groupAccountDetails(@Body body: RequestBody?): Observable<GroupAccountDetails>

    @POST("GetRefCodes_Api")
    fun getReferenceCodes(@Body body: RequestBody?): Observable<ReferenceCodesResponse>

    @GET("{pincode}")
    fun getPostOffice(@Path("pincode") ifsc: String?): Single<getPostOfficeResponse?>?

    @POST("CustomerManager_Api")
    fun customerCreation(@Body body: RequestBody?): Observable<CustomerCreationResponse>

    @POST("OTPCreation")
    fun createOtp(@Body body: RequestBody?): Single<CreateOtpResponse?>?

    @POST("OTPCreation")
    fun validateOtp(@Body body: RequestBody?): Single<ValidateOtpResponse?>?


    @POST("getAccountHolder")
    suspend fun getAccount(@Body body: RequestBody?): GetAccountHolderResponse
}