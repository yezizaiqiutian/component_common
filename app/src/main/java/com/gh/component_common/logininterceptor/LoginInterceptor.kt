package com.gh.component_common.logininterceptor

import android.app.Activity
import com.gh.lib_common.module.UserBean
import com.gh.lib_core.utils.interceptor.Interceptor
import com.gh.lib_core.utils.interceptor.InterceptorCheck
import java.lang.ref.WeakReference

/**
 * @author: gh
 * @description:
 * @date: 2022/3/23.
 * @from:
 */
class LoginInterceptor(activity: Activity) : Interceptor() {

    init {
        weakActivity = WeakReference(activity)
    }

    override fun getName(): String = "登录拦截器"

    override fun checkCondition() = UserBean.isLogin

    override fun toGetCondition() {
        weakActivity?.get()?.let {
            LoginActivity.access(it)
        }
    }
}