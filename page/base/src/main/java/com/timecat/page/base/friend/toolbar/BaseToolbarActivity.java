package com.timecat.page.base.friend.toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.timecat.page.base.R;
import com.timecat.page.base.friend.list.BaseStatefulActivity;
import com.timecat.page.base.view.BlurringToolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2019-07-12
 * @description 要求 toolbar 不能为 background 的子 view
 * @usage 无刷新的、有状态的列表
 */
public abstract class BaseToolbarActivity extends BaseStatefulActivity {
    public BlurringToolbar toolbar;
    public View background;

    @Override
    protected int layout() {
        return R.layout.base_blur_toolbar_stateful_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        routerInject();
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        toolbar = findViewById(R.id.toolbar);
        background = findViewById(R.id.background);

        setUpToolbar();
        initView();
        toolbar.post(this::applySkin);
    }

    protected void setBlurredToolbar() {
        toolbar.setBlurredView(background);
    }

    protected void setPaddingStatusBar() {
        toolbar.setPaddingStatusBar(this);
    }

    protected void setSupportToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void setBaseToolbar() {
        if (canBack()) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
        } else {
            toolbar.setNavigationIcon(R.drawable.ic_menu);
        }
        toolbar.setNavigationOnClickListener(this::onToolbarIconClick);

        toolbar.setContentInsetEndWithActions(0);
        toolbar.setContentInsetsRelative(0, 0);
        toolbar.setContentInsetStartWithNavigation(0);
    }

    protected void setToolbarTitle() {
        setTitle(title());
    }

    protected void setUpToolbar() {
        setBlurredToolbar();
        setSupportToolbar();
        setPaddingStatusBar();
        setBaseToolbar();
        setToolbarTitle();
    }

    protected void onToolbarIconClick(@NonNull View view) {
        finish();
    }

    @Override
    public void setTitle(@NonNull CharSequence title) {
        super.setTitle(title);
        toolbar.setTitle(title);
    }

    @Override
    public void applySkin() {
        super.applySkin();
        if (toolbar != null) {
            toolbar.invalidate();
        }
    }

    /**
     * 务必不要在抽象类中实现
     * 因为抽象类无法实例化，导致注入失败
     */
    protected void routerInject() {
    }

    @NonNull
    protected abstract String title();

    protected abstract void initView();
}
