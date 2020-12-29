package com.timecat.page.base.friend.list;

import android.view.View;

import com.timecat.page.base.R;
import com.timecat.page.base.base.simple.BaseSimpleRxActivity;
import com.timecat.component.identity.Attr;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/15
 * @description 从这一层开始对布局有要求
 * @usage 要求 R.id.background，可空
 */
public abstract class BaseBackgroundActivity extends BaseSimpleRxActivity {

    @Override
    protected int layout() {
        return R.layout.base_fragment_detail;
    }

    @Override
    public void applySkin() {
        View v = findViewById(R.id.background);
        if (v != null) {
            v.setBackground(Attr.getWindowBackground(this));
        }
    }
}
