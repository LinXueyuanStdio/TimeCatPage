package com.timecat.page.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/5/21
 * @description 在 manifest 里使用 Theme.NoDisplay
 * @usage null
 */
public abstract class BaseNoDisplayActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        run();
        finish();
    }

    /**
     * 写具体业务（如跳转）即可，不用自己调用 finish() 了
     */
    protected abstract void run();

    @Override
    protected void onStart() {
        super.onStart();
        setVisible(true);
    }
}
