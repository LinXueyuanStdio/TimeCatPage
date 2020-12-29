package com.timecat.page.base.friend.toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.timecat.page.base.R;
import com.timecat.page.base.base.simple.BaseSimpleSupportFragment;
import com.timecat.page.base.utils.MenuTintUtils;
import com.timecat.page.base.utils.ToolbarUtils;
import com.timecat.page.base.view.OnDebouncedClickListener;

import org.jetbrains.annotations.NotNull;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/24
 * @description Theme -> Support -> Event -> LazyLoad -> Simple -> Toolbar
 * @usage null
 */
public abstract class BaseToolbarSupportFragment extends BaseSimpleSupportFragment
        implements Toolbar.OnMenuItemClickListener {
    @Nullable
    protected View tint_statusbar;
    protected Toolbar toolbar;
    protected Menu menu;

    @Override
    protected void bindView(@NotNull View view) {
        super.bindView(view);
        toolbar = view.findViewById(R.id.toolbar);
        tint_statusbar = view.findViewById(R.id.tint_statusbar);
        ToolbarUtils.initToolbarNav(toolbar, true, new OnDebouncedClickListener() {
            @Override
            public void onDebouncedClick(View v) {
                onNavIconClick(v);
            }
        });

        if (tint_statusbar != null) {
            ToolbarUtils.initTintStatusBar(tint_statusbar, _mActivity);
        }

        if (getMenuId() != 0) {
            toolbar.inflateMenu(getMenuId());
            menu = toolbar.getMenu();
            MenuTintUtils.tintAllIcons(menu, requireContext());
            toolbar.setOnMenuItemClickListener(this);
        }
    }

    protected abstract void onNavIconClick(View v);

    @MenuRes
    protected int getMenuId() {
        return 0;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

}
