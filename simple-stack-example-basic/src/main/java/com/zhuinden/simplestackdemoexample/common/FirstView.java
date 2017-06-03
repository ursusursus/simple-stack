package com.zhuinden.simplestackdemoexample.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.Bundleable;
import com.zhuinden.simplestackdemoexample.R;
import com.zhuinden.statebundle.StateBundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by Owner on 2017. 01. 12..
 */

public class FirstView
        extends RelativeLayout
        implements Bundleable {
    private static final String TAG = "FirstView";

    public FirstView(Context context) {
        super(context);
        init(context);
    }

    public FirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public FirstView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    Backstack backstack;

    FirstKey firstKey;

    private void init(Context context) {
        if(!isInEditMode()) {
            backstack = BackstackService.get(context);
            firstKey = Backstack.getKey(context);
        }
    }

    @OnClick(R.id.first_button)
    public void clickButton(View view) {
        backstack.goTo(SecondKey.create());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public StateBundle toBundle() {
        StateBundle bundle = new StateBundle();
        bundle.putString("HELLO", "WORLD");
        return bundle;
    }

    @Override
    public void fromBundle(@Nullable StateBundle bundle) {
        if(bundle != null) {
            Log.i(TAG, bundle.getString("HELLO"));
        }
    }
}
