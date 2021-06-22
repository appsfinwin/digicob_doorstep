package com.finwin.doorstep.digicob.home.agent_management

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

class AgentManagementViewModel : ViewModel() {

     var mAction: MutableLiveData<Int> = MutableLiveData()

    public fun clickChangePassword(view: View)
    {
        mAction.value=AgentManagementFragment.CLICK_CHANGE_PASSWORD
    }

    override fun onCleared() {
        super.onCleared()
        mAction.value=
           AgentManagementFragment.DEFAULT
    }

    class AgentManegementViewmodelFactory: ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AgentManagementViewModel::class.java))
            {
                return AgentManagementViewModel() as T
            }
            throw IllegalStateException("unknown viewmodel class")
        }

    }
}