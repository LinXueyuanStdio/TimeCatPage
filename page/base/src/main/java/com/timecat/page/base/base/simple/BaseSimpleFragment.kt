package com.timecat.page.base.base.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.timecat.page.base.base.lazyload.BaseLazyLoadFragment

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/16
 * @description Theme -> Event -> LazyLoad ->Simple
 * @usage null
 */
abstract class BaseSimpleFragment : BaseLazyLoadFragment() {
    lateinit var v: View

    protected open fun bindView(view: View) {
    }

    @LayoutRes
    protected abstract fun layout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = getThemedView(layout(), inflater, container)
        bindView(v)
        return v
    }
}