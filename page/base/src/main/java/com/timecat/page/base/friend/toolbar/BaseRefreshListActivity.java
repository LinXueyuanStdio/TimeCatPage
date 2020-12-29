package com.timecat.page.base.friend.toolbar;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.timecat.page.base.R;
import com.timecat.component.identity.Attr;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2019-07-12
 * @description 要求有 SwipeRefreshLayout : R.id.refreshLayout
 * @usage 有刷新的、有状态的列表
 */
public abstract class BaseRefreshListActivity extends BaseListActivity implements SwipeRefreshLayout.OnRefreshListener {
    public SwipeRefreshLayout mRefreshLayout;

    @Override
    protected int layout() {
        return R.layout.base_toolbar_refresh_rv_empty_bg;
    }

    @Override
    protected void bindView() {
        super.bindView();
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setColorSchemeColors(Attr.getAccentColor(this));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(false);
    }
}
