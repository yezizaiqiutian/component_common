package com.gh.component_common.logininterceptor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gh.component_common.R
import com.gh.lib_core.utils.LogUtils
import com.gh.lib_core.utils.interceptor.CheckStateListener
import com.gh.lib_core.utils.interceptor.Interceptor
import com.gh.lib_core.utils.interceptor.InterceptorCheck

/**
 * @author: gh
 * @description:
 * @date: 2022/3/23.
 * @from:
 */
class LoginInterceptorActivity : AppCompatActivity() {

    lateinit var tv_state: TextView


    companion object {
        fun access(context: Context) {
            val intent = Intent(context, LoginInterceptorActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logininterceptor)

        tv_state = findViewById<TextView>(R.id.tv_state)
        val btn_needlogin = findViewById<View>(R.id.btn_needlogin)
        val btn_login = findViewById<View>(R.id.btn_login)
        val btn_logout = findViewById<View>(R.id.btn_logout)
        val btn_vip = findViewById<View>(R.id.btn_vip)
        val btn_novip = findViewById<View>(R.id.btn_novip)



        btn_needlogin.setOnClickListener {

            InterceptorCheck.instance
                .setTarget {
                    Toast.makeText(
                        this@LoginInterceptorActivity,
                        "所有条件均满足......",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addInterceptor(
                    LoginInterceptor(this@LoginInterceptorActivity),
                    VipInterceptor(this@LoginInterceptorActivity)
                )
                .setCheckStateListener(object : CheckStateListener {

                    override fun onOneConditionSuccess(interceptor: Interceptor) {
                        LogUtils.d("条件不满足${interceptor.javaClass.name}")
                    }

                    override fun onOneConditionError(interceptor: Interceptor) {
                        LogUtils.d("条件满足${interceptor.javaClass.name}")
                    }

                    override fun onCheckAbort() {
                        LogUtils.d("条件检查流程中断")
                    }

                })
                .startCheck()

        }
        btn_login.setOnClickListener {
            UserBean.isLogin = true
            refreshState()
        }
        btn_logout.setOnClickListener {
            UserBean.isLogin = false
            refreshState()
        }
        btn_vip.setOnClickListener {
            UserBean.isVip = true
            refreshState()
        }
        btn_novip.setOnClickListener {
            UserBean.isVip = false
            refreshState()
        }

    }

    override fun onResume() {
        super.onResume()
        refreshState()
    }

    fun refreshState() {
        tv_state.text = "是否登录:${UserBean.isLogin} \n 是否VIP/:${UserBean.isVip}"
    }

}