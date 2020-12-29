package com.timecat.page.base.friend.main

import android.content.Context
import android.view.View
import com.timecat.page.base.base.OnFragmentOpenDrawerListener
import com.timecat.page.base.friend.toolbar.BaseToolbarStatefulRefreshListSupportFragment

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/24
 * @description Theme -> Support -> Event -> LazyLoad -> Simple -> List -> Refresh -> Stateful -> Main(Drawer)
 * @usage null
 */
abstract class BaseMainStatefulRefreshListFragment : BaseToolbarStatefulRefreshListSupportFragment() {
    protected var mOpenDrawerListener: OnFragmentOpenDrawerListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentOpenDrawerListener) {
            mOpenDrawerListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mOpenDrawerListener = null
    }

    override fun onNavIconClick(v: View?) {
        mOpenDrawerListener?.onOpenDrawer(v)
    }
}