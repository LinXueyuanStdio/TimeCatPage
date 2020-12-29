package com.timecat.page.base.friend.toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.timecat.page.base.R;
import com.timecat.page.base.base.simple.BaseSimpleFragment;
import com.timecat.page.base.utils.MenuTintUtils;
import com.timecat.page.base.utils.ToolbarUtils;
import com.timecat.page.base.view.BlurringToolbar;
import com.timecat.page.base.view.OnDebouncedClickListener;

import org.jetbrains.annotations.NotNull;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/24
 * @description Theme -> Event -> LazyLoad -> Simple -> Toolbar
 * @usage null
 */
public abstract class BaseToolbarFragment extends BaseSimpleFragment
        implements Toolbar.OnMenuItemClickListener {

    public BlurringToolbar toolbar;
    public View background;
    protected Menu menu;

    @Override
    protected void bindView(@NotNull View view) {
        super.bindView(view);
        background = view.findViewById(R.id.background);
        toolbar = view.findViewById(R.id.toolbar);
        setUpToolbar();
        initView();
    }

    @Override
    protected int layout() {
        return R.layout.base_blur_toolbar_stateful_container;
    }

    protected void setBlurredToolbar() {
        toolbar.setBlurredView(background);
    }

    protected void setPaddingStatusBar() {
        toolbar.setPaddingStatusBar(getContext());
    }

    protected void setBaseToolbar() {
        ToolbarUtils.initToolbarNav(toolbar, canBack(), new OnDebouncedClickListener() {
            @Override
            public void onDebouncedClick(View v) {
                onNavIconClick(v);
            }
        });
        if (getMenuId() != 0) {
            toolbar.inflateMenu(getMenuId());
            menu = toolbar.getMenu();
            MenuTintUtils.tintAllIcons(menu, requireContext());
            toolbar.setOnMenuItemClickListener(this);
        }
    }

    protected void setToolbarTitle() {
        setTitle(title());
    }

    protected void setUpToolbar() {
        setBlurredToolbar();
        setPaddingStatusBar();
        setBaseToolbar();
        setToolbarTitle();
    }


    public void setTitle(@NonNull CharSequence title) {
        toolbar.setTitle(title);
    }

    @NonNull
    protected abstract String title();

    protected abstract void initView();

    protected abstract void onNavIconClick(View v);

    public boolean canBack() {
        return false;
    }

    @MenuRes
    protected int getMenuId() {
        return 0;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
