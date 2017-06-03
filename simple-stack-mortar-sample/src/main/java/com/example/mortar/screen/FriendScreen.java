/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mortar.screen;

import android.support.annotation.Nullable;

import com.example.mortar.R;
import com.example.mortar.core.SingletonComponent;
import com.example.mortar.model.Chats;
import com.example.mortar.model.User;
import com.example.mortar.util.BaseKey;
import com.example.mortar.util.DaggerService;
import com.example.mortar.util.Subscope;
import com.example.mortar.util.ViewPresenter;
import com.example.mortar.view.FriendView;
import com.google.auto.value.AutoValue;
import com.zhuinden.servicetree.ServiceTree;
import com.zhuinden.statebundle.StateBundle;

import javax.inject.Inject;

import dagger.Provides;

@AutoValue
public abstract class FriendScreen
        extends BaseKey {
    public abstract int index();

    public static FriendScreen create(int index) {
        return new AutoValue_FriendScreen(index);
    }

    @Override
    public void bindServices(ServiceTree.Node node) {
        SingletonComponent singletonComponent = DaggerService.get(node);
        node.bindService(DaggerService.SERVICE_NAME, //
                DaggerFriendScreen_Component.builder() //
                        .singletonComponent(singletonComponent) //
                        .module(new Module(index())) //
                        .build());
        node.bindService("PRESENTER", DaggerService.<Component>get(node).presenter()); // <-- for Bundleable callback
    }

    @Override
    public int layout() {
        return R.layout.friend_view;
    }

    @dagger.Component(dependencies = {SingletonComponent.class}, modules = {Module.class})
    @Subscope
    public interface Component extends SingletonComponent {
        Presenter presenter();

        void inject(FriendView friendView);
    }

    @dagger.Module
    public static class Module {
        private final int index;

        public Module(int index) {
            this.index = index;
        }

        @Provides
        @Subscope
        User friend(Chats chats) {
            return chats.getFriend(index);
        }
    }

    @Subscope
    public static class Presenter
            extends ViewPresenter<FriendView> {
        private final User friend;

        @Inject
        Presenter(User friend) {
            this.friend = friend;
        }

        @Override
        public void onLoad(@Nullable StateBundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            if(!hasView()) {
                return;
            }
            getView().setText(friend.name);
        }
    }

    @Override
    public String title() {
        return "Friend";
    }
}
