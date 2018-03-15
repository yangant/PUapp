package com.example.administrator.powerup;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager mainActivityViewPager;
    BottomNavigationView bottomNavView;
    MainActivityViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        获取到两个控件的对象
        mainActivityViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        bottomNavView = (BottomNavigationView) findViewById(R.id.main_bottom_nav_view);
//        为ViewPager设置Adapter
        adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());
//        为Adapter添加Fragment
        adapter.addFragment(new BlankFragmentOne());
        adapter.addFragment(new BlankFragmentTwo());
        mainActivityViewPager.setAdapter(adapter);
//        为 BottomNavigationView 的菜单项  设置监听事件
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//               获取到菜单项的Id
                int itemId = item.getItemId();
//                当第一项被选择时BlankFragmentOne显示，以此类推
                switch (itemId) {
                    case R.id.navigation_me:
                        mainActivityViewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_task:
                        mainActivityViewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_rank:
                        mainActivityViewPager.setCurrentItem(2);
                        break;

                }
                // true 会显示这个Item被选中的效果 false 则不会
                return true;
            }
        });
//        为 ViewPager 设置监听事件
        mainActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                当 ViewPager 滑动后设置BottomNavigationView 选中相应选项
                bottomNavView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}