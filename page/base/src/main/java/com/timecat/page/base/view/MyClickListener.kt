package com.timecat.page.base.view

import android.view.View

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/22
 * @description null
 * @usage null
 */
open class MyClickListener(val click: () -> Unit) : OnDebouncedClickListener() {
    override fun onDebouncedClick(v: View?) {
        click()
    }
}