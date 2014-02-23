package de.uvwxy.cardpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import de.uvwxy.cardstyle.R;

public abstract class ActivityCardPager extends FragmentActivity {
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cardpager);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	public void moveToTab(int i) {
		if (i >= 0 && i < getFragmentCount() && mViewPager != null) {
			mViewPager.setCurrentItem(i);
		}
	}

	public Context ctx() {
		return this;
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return getFragment(position);
		}

		@Override
		public int getCount() {
			return getFragmentCount();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return getFragmentTitle(position);
		}
	}

	public abstract Fragment getFragment(int position);

	public abstract CharSequence getFragmentTitle(int position);

	public abstract int getFragmentCount();
}
