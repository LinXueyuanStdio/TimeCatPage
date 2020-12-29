package com.timecat.page.base.friend.compact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.timecat.page.base.R;
import com.timecat.page.base.base.support.BaseSupportActivity;


/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/19
 * @description 单个 fragment，什么也没有，仅仅是容器
 * @usage 直接在 createFragment 里提供实例化对象，一行搞定
 */
public abstract class BaseFragmentActivity extends BaseSupportActivity {
    /**
     * 抽象方法：创建Fragment
     *
     * @return Fragment
     */
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fragment_dialog);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_containers);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_containers, fragment).commit();
        }
    }
}
