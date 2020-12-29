package com.timecat.page.base.friend.toolbar;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.timecat.page.base.R;
import com.timecat.page.base.friend.list.BaseBackgroundActivity;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/8
 * @description 占位 status bar
 * @usage null
 */
public abstract class BaseStatusBarActivity extends BaseBackgroundActivity {
    @Nullable
    protected View virtualStatusBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        virtualStatusBar = findViewById(R.id.tint_statusbar);
        if (virtualStatusBar != null) {
            // 设置状态栏透明主题时，布局整体会上移，所以给头部 View 设置 StatusBar 的高度
            virtualStatusBar.getLayoutParams().height = getStatusbarHeight();
        }
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
}
