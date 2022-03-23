package com.gh.component_common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gh.component_common.logininterceptor.LoginInterceptorActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn_interceptor).setOnClickListener {
            LoginInterceptorActivity.access(this@MainActivity)
        }

    }
}