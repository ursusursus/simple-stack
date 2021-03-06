package com.zhuinden.simplestackdemonestedstack.presentation.paths.other;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.zhuinden.simplestack.navigator.ViewChangeHandler;
import com.zhuinden.simplestack.navigator.changehandlers.FadeViewChangeHandler;
import com.zhuinden.simplestackdemonestedstack.R;
import com.zhuinden.simplestackdemonestedstack.application.Key;

/**
 * Created by Owner on 2017. 02. 27..
 */
@AutoValue
public abstract class OtherKey
        extends Key {
    @Override
    public int layout() {
        return R.layout.path_other;
    }

    @Override
    public String stackIdentifier() {
        return "";
    }

    public static OtherKey create() {
        return new AutoValue_OtherKey();
    }

    @NonNull
    @Override
    public ViewChangeHandler viewChangeHandler() {
        return new FadeViewChangeHandler();
    }
}
