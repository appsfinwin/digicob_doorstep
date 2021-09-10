package com.finwin.doorstep.digicob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finwin.doorstep.digicob.logout_listner.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}