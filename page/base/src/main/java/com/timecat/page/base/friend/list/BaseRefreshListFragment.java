package com.timecat.page.base.friend.list;

import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.timecat.page.base.R;
import com.timecat.component.identity.Attr;

import org.jetbrains.annotations.NotNull;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/16
 * @description Theme -> Event -> LazyLoad -> Simple -> List -> Refresh
 * @usage SwipeRefreshLayout : R.id.refreshLayout
 */
public abstract class BaseRefreshListFragment
        extends BaseListFragment
        implements SwipeRefreshLayout.OnRefreshListener{

    public SwipeRefreshLayout mRefreshLayout;

    @Override
    protected int layout() {
        return R.layout.base_refresh_rv;
    }

    @Override
    protected void bindView(@NotNull View view) {
        super.bindView(view);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setColorSchemeColors(Attr.getAccentColor(requireContext()));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
    }

    /**
     * 默认直接调用一次 onRefresh()
     */
    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(false);
    }
}