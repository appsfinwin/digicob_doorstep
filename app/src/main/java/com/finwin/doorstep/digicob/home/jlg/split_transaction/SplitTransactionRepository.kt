package com.finwin.doorstep.digicob.home.jlg.split_transaction

class SplitTransactionRepository {


    public lateinit var INSTANCE: SplitTransactionRepository

    public fun getInstance(): SplitTransactionRepository
    {
        if (!:: INSTANCE.isInitialized)
        {
            INSTANCE= SplitTransactionRepository()
        }

        return INSTANCE
    }

}