package com.timecat.page.base.base.theme;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.timecat.page.base.theme.ThemeSkin;
import com.timecat.identity.skin.SkinEnable;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/10
 * @description null
 * @usage null
 */
public abstract class BaseThemeActivity extends AppCompatActivity implements SkinEnable {
    protected boolean needCommonTheme() {
        return true;
    }

    protected boolean needEventBus() {
        return false;
    }

    protected boolean canBack() {
        return true;
    }

    protected Context getContext() {
        return this;
    }

    @Override
    public void applySkin() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (needCommonTheme()) {
            ThemeSkin.INSTANCE.apply(this);
        }
        super.onCreate(savedInstanceState);
        if (needEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 销毁并回收内存
     *
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    protected void onDestroy() {
        if (needEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (canBack()) {
                onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
