package com.vivacollege.medikart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by indianrenters on 1/10/17.
 */

public class MedDetailPage extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_detail);
        // Locate the ViewPager in viewpager_main.xml
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Pass results to ViewPagerAdapter Class
        PagerAdapter adapter = new MedPagerAdapter(this);

        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        // ViewPager Indicator
        CirclePageIndicator mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);
    }
}
