package com.timecat.page.base.extension

import android.content.Context
import com.timecat.component.setting.Config
import com.timecat.component.setting.DEF

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/7/25
 * @description null
 * @usage null
 */
val Context.config: Config get() = DEF.config()
val Context.baseConfig: Config get() = DEF.config()
