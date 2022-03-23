package com.gh.component_common.logininterceptor

import android.app.Activity
import com.gh.lib_core.utils.interceptor.Interceptor
import java.lang.ref.WeakReference

/**
 * @author: gh
 * @description:
 * @date: 2022/3/23.
 * @from:
 */
class VipInterceptor(activity: Activity) : Interceptor() {

    init {
        weakActivity = WeakReference(activity)
    }

    override fun getName(): String = "VIP拦截器"

    override fun checkCondition() = UserBean.isVip

    override fun toGetCondition() {
        weakActivity?.get()?.let {
            VipActivity.access(it)
        }
    }
}