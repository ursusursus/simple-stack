package com.zhuinden.simplestackdemomultistack.presentation.paths.main.mail;

import android.content.Context;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.zhuinden.simplestack.BackstackDelegate;
import com.zhuinden.simplestackdemomultistack.R;
import com.zhuinden.simplestackdemomultistack.application.Key;
import com.zhuinden.simplestackdemomultistack.application.MainActivity;
import com.zhuinden.simplestackdemomultistack.util.ServiceLocator;

/**
 * Created by Zhuinden on 2017.02.19..
 */
@AutoValue
public abstract class MailKey
        implements Key {
    @Override
    public int layout() {
        return R.layout.path_mail;
    }

    public static Parcelable create() {
        return null;
    }

    @Override
    public BackstackDelegate selectDelegate(Context context) {
        return ServiceLocator.getService(context, MainActivity.StackType.MAIL.name());
    }
}