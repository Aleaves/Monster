package com.app.monster;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import com.app.monster.ui.activity.BaseActivity;
import com.app.monster.ui.fragment.HomeFragment;
import com.app.monster.ui.fragment.TabFiveFragment;
import com.app.monster.ui.fragment.TabFourFragment;
import com.app.monster.ui.fragment.TabThreeFragment;
import com.app.monster.ui.fragment.TabTwoFragment;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private List<Fragment> pages;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        pages = new ArrayList<>();
        pages.add(new HomeFragment());
        pages.add(new TabTwoFragment());
        pages.add(new TabThreeFragment());
        pages.add(new TabFourFragment());
        pages.add(new TabFiveFragment());
        mViewPager.setAdapter(new MainActivityViewPagerAdapter(getSupportFragmentManager()));
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.bottom_home:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.bottom_book:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.bottom_movie:
                        mViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.bottom_collection:
                        mViewPager.setCurrentItem(3,false);
                        break;
                    case R.id.bottom_setting:
                        mViewPager.setCurrentItem(4,false);
                        break;

                }
                return true;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        disableShiftMode(mNavigationView);
    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class  MainActivityViewPagerAdapter extends FragmentPagerAdapter{

        public MainActivityViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}
