package com.timecat.page.base.base.theme;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import com.timecat.identity.skin.SkinEnable;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/15
 * @description Theme
 * @usage null
 */
public abstract class BaseThemeFragment extends Fragment implements SkinEnable {
    protected boolean needEventBus() {
        return false;
    }

    @Override
    public void applySkin() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    protected View getThemedView(@LayoutRes int layout, LayoutInflater inflater, @Nullable ViewGroup container) {
        Context origin = requireContext();
        Context contextThemeWrapper = new ContextThemeWrapper(origin, origin.getTheme());
        LayoutInflater themeAwareInflater = inflater.cloneInContext(contextThemeWrapper);
        return themeAwareInflater.inflate(layout, container, false);
    }

    /**
     * 销毁并回收内存
     *
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    public void onDestroy() {
        if (needEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

}
