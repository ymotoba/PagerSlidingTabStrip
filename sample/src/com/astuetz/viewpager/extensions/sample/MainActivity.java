/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.astuetz.viewpager.extensions.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener, NavigationDrawerFragment.SlideMenuItemClickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private NavigationDrawerFragment navigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationDrawerFragment.setUp(
                toolbar,
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.inflateMenu(R.menu.main);
        toolbar.showContextMenuForChild(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, Menu1Fragment.newInstance())
                .commit();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onSlideMenuItemClick(View v) {
        switch (v.getId()) {
            case android.R.id.text1:
                onClickText1(v);
                break;
            default:
                // nothing
        }
    }

    private void onClickText1(View v) {
        toolbar.inflateMenu(R.menu.main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, Menu1Fragment.newInstance())
                .commit();
    }
}