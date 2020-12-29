package com.timecat.page.base.friend.compact;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.timecat.page.base.R;
import com.timecat.page.base.base.theme.BaseThemeActivity;

import java.util.List;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/22
 * @description null
 * @usage null
 */
public abstract class BaseToolbarFragmentActivity extends BaseThemeActivity {

    // 统一的 TopBar
    protected Toolbar toolbar;
    protected View virtualStatusBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.layoutId());
        initUI();
        initData();
    }
    protected abstract int layoutId();
    protected abstract void initData();

    protected void initUI() {
        setupTopBar();
    }
    public int getStatusbarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
    /**
     * 装载 TopBar
     */
    protected void setupTopBar() {
        if (virtualStatusBar != null) {
            // 设置状态栏透明主题时，布局整体会上移，所以给头部 View 设置 StatusBar 的高度
            virtualStatusBar.getLayoutParams().height = getStatusbarHeight();
        }
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            toolbar.setNavigationOnClickListener((View v) -> onBackPressed());
        }
    }

    /**
     * 设置标题
     */
    protected void setTopTitle(int resId) {
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle(resId);
    }

    /**
     * 设置标题
     */
    protected void setTopTitle(String title) {
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle(title);
    }

    /**
     * 解决Fragment中的onActivityResult()方法无响应问题。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 1.使用 getSupportFragmentManager().getFragments() 获取到当前 Activity 中添加的 Fragment 集合
         * 2.遍历 Fragment 集合，手动调用在当前 Activity 中的 Fragment 中的 onActivityResult() 方法。
         */
        if (getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
