package com.loconav.configurator.activity;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;

import com.loconav.configurator.ActiveFragment;
import com.loconav.configurator.DoneFragment;
import com.loconav.configurator.R;
import com.loconav.configurator.app.BaseActivity;

import butterknife.ButterKnife;

public class LandingActivity extends BaseActivity {


    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;


    @Override
    public Integer setView() {
        return R.layout.activity_landing;
    }

    @Override
    public void activityCreated() {
        ButterKnife.bind(this);
//        toolbar.setNavigationIcon(R.mipmap.app_l);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ActiveFragment();
                case 1:
                    return new DoneFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ACTIVE";
                case 1:
                    return "DONE";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if(mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else
            setCurrentItem(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public ViewPager getViewPager () {
        return mViewPager;
    }

    public void setCurrentItem(int item){
        mViewPager.setCurrentItem(item);
    }

    public SectionsPagerAdapter getmSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }


}
