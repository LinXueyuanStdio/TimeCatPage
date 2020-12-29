package com.timecat.page.base.base.baseCard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.cardview.widget.CardView;

public abstract class AbsCard extends CardView {
    protected Context mContext;

    public AbsCard(Context context) {
        super(context);
        init(context);
    }

    public AbsCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AbsCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(layout(), this);
        initView(context);
    }

    protected abstract int layout();

    protected abstract void initView(Context context);
}
