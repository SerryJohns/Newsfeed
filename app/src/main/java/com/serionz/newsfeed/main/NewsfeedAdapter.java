package com.serionz.newsfeed.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.serionz.newsfeed.main.global_news.GlobalNewsFragment;

/**
 * Created by johnpaulseremba on 17/11/2017.
 */

public class NewsfeedAdapter extends FragmentStatePagerAdapter {
	private int numOfTabs;

	public NewsfeedAdapter(FragmentManager fm, int numOfTabs) {
		super(fm);
		this.numOfTabs = numOfTabs;
	}

	@Override public Fragment getItem(int position) {
		switch (position) {
			case 0:
				GlobalNewsFragment globalNewsFragment = new GlobalNewsFragment();
				return globalNewsFragment;
			case 1:
				SportsNewsFragment sportsNewsFragment = new SportsNewsFragment();
				return sportsNewsFragment;
			case 2:
				TechnologyNewsFragment technologyNewsFragment = new TechnologyNewsFragment();
				return technologyNewsFragment;
			default:
				return null;
		}
	}

	@Override public int getCount() {
		return this.numOfTabs;
	}

}