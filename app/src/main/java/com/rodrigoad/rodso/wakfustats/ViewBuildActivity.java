package com.rodrigoad.rodso.wakfustats;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigoad.rodso.wakfustats.Layouts.DecksFragment;
import com.rodrigoad.rodso.wakfustats.Layouts.PointsBuildFragment;
import com.rodrigoad.rodso.wakfustats.Layouts.ViewBuildFragment;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.Build;
import com.google.gson.Gson;
import com.rodrigoad.rodso.wakfustats.Utils.LocaleHelper;

import java.util.Locale;


public class ViewBuildActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public static Build build;
    private Context context;
    private static ViewBuildActivity viewBuildActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_build);
        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.viewscreens));

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.viewtabs);
        mTabLayout.setupWithViewPager(mViewPager);

        String sBuild="";
        Bundle b = getIntent().getExtras();
        if(b!=null){
            sBuild = b.getString("build");
        }
        build = new Gson().fromJson(sBuild, Build.class);
        if(!build.isOnline()){
            build = new BD(getApplicationContext()).getBuild(build.getCodigo());
        }
        viewBuildActivity = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    public static void dispose(){
        viewBuildActivity.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_build, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_view_build, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String[] tittles;
        public ViewBuildFragment viewBuildFragment;
        private PointsBuildFragment pointsBuildFragment;
        private DecksFragment decksFragment;

        public SectionsPagerAdapter(FragmentManager fm, String[] tittles) {
            super(fm);
            this.tittles=tittles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return viewBuildFragment==null ? viewBuildFragment= ViewBuildFragment.newInstance("A","B") : viewBuildFragment;
                case 1:
                    return pointsBuildFragment==null ? pointsBuildFragment= PointsBuildFragment.newInstance(viewBuildFragment,"B") : pointsBuildFragment;
                case 2:
                    return decksFragment==null ? decksFragment = DecksFragment.newInstance(viewBuildFragment, "B") : decksFragment;
                default:
                    return MainActivity.PlaceholderFragment.newInstance(1);
            }

        }

        @Override
        public int getCount() {
            return tittles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tittles[position];
        }
    }
}
