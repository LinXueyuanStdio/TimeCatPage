package com.timecat.page.base.extension

import android.os.Build

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/10/7
 * @description null
 * @usage null
 */
fun versionOverM(work: () -> Unit) = versionOver(Build.VERSION_CODES.M, work)
fun versionOverO(work: () -> Unit) = versionOver(Build.VERSION_CODES.O, work)
fun versionOver(version: Int, work: () -> Unit) {
    if (android.os.Build.VERSION.SDK_INT >= version) {
        work()
    }
}