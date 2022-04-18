package com.gh.component_common.logininterceptor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gh.component_common.R
import com.gh.lib_common.module.UserBean
import com.gh.lib_core.utils.interceptor.InterceptorCheck

/**
 * @author: gh
 * @description:
 * @date: 2022/3/23.
 * @from:
 */
class VipActivity:AppCompatActivity() {

    companion object {
        fun access(context: Context) {
            val intent = Intent(context, VipActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.text = "VIP充值"
        btn_login.setOnClickListener {
            UserBean.isVip = true
            InterceptorCheck.instance.continueCheck()
            finish()
        }

    }

}