package com.finwin.doorstep.digicob.logout_listner

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.finwin.doorstep.digicob.R
import com.finwin.doorstep.digicob.home.jlg.jlg_loan_creation.room.MemberApplication
import com.finwin.doorstep.digicob.login.LoginActivity
import com.finwin.doorstep.digicob.utils.Constants

abstract class BaseActivity : AppCompatActivity(), LogoutListener {
   lateinit var sharedPreferences: SharedPreferences
   lateinit var editor: SharedPreferences.Editor
    var activityCount = 0
    var backgroundTime: Long = 0
    var foregroundTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        activityCount = 1
        MemberApplication.registerSessionListener(this)
    }

    override fun doLogout() {}

    override fun foreground() {
        foregroundTime = System.currentTimeMillis()
        val total: Long =
            foregroundTime - sharedPreferences!!.getLong(Constants.BACKGROUND_TIME, 0)
        //        total=foregroundTime-backgroundTime;
//        if (activityCount!=1) {


//            boolean isLogin = sharedPreferences.getBoolean(ConstantClass.IS_LOGIN, false);
//            if (!isLogin) {
        if (total > 300000) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            //overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
        //}
//        }else {
//            activityCount=2;
//        }
    }

    override fun background() {
//        editor.putBoolean(ConstantClass.BACKGROUND,true);
//        editor.apply();
        backgroundTime = System.currentTimeMillis()
        editor!!.putLong(Constants.BACKGROUND_TIME, backgroundTime)
        editor!!.apply()
    }

    override fun onSessionLogout() {
        editor!!.putBoolean(Constants.IS_LOGIN, false)
        editor!!.apply()
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        //overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        finish()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        //reset session when user interact
        MemberApplication.resetSession()
    }


    override fun onRestart() {
        super.onRestart()
    }


}