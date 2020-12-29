package com.timecat.page.base.base.rxevent;

import com.timecat.page.base.base.theme.BaseThemeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2019-10-27
 * @description Theme -> Event
 * @usage null
 */
public class BaseEventFragment extends BaseThemeFragment {
    //region EventBus
    public boolean needEventBus() {
        return true;
    }

    protected boolean needScopeEventBus() {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (needEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        if (needEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }


    @Subscribe
    public void onEvent(DefaultEvent e) {}
    //endregion
}
