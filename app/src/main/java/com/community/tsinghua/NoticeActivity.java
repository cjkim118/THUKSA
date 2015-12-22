package com.community.tsinghua;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.community.tsinghua.Notice1Fragment;
import com.community.tsinghua.Notice2Fragment;
import com.community.tsinghua.Notice3Fragment;
import com.community.tsinghua.Notice4Fragment;
import com.community.tsinghua.fragment.FloatingButtonFragment;

public class NoticeActivity extends AppCompatActivity {

        private Toolbar toolbar;
        private TabLayout tabLayout;
        private ViewPager viewPager;
        private FloatingActionButton fragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.notice_main);

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                viewPager = (ViewPager) findViewById(R.id.viewpager);
                setupViewPager(viewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
        }

        private void setupViewPager(ViewPager viewPager) {
                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(new Notice1Fragment(), "학생회");
                adapter.addFragment(new Notice2Fragment(), "교내");
                adapter.addFragment(new Notice3Fragment(), "채용");
                adapter.addFragment(new Notice4Fragment(), "기타");
                viewPager.setAdapter(adapter);
        }

        class ViewPagerAdapter extends FragmentPagerAdapter {
                private final List<Fragment> mFragmentList = new ArrayList<>();
                private final List<String> mFragmentTitleList = new ArrayList<>();

                public ViewPagerAdapter(FragmentManager manager) {
                        super(manager);
                }

                @Override
                public Fragment getItem(int position) {
                        return mFragmentList.get(position);
                }

                @Override
                public int getCount() {
                        return mFragmentList.size();
                }

                public void addFragment(Fragment fragment, String title) {
                        mFragmentList.add(fragment);
                        mFragmentTitleList.add(title);
                }

                @Override
                public CharSequence getPageTitle(int position) {
                        return mFragmentTitleList.get(position);
                }
        }
}
