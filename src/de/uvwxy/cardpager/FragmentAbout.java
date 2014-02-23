package de.uvwxy.cardpager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.uvwxy.cardstyle.R;
import de.uvwxy.helper.BitmapTools;
import de.uvwxy.helper.IntentTools;

public class FragmentAbout extends Fragment {
	ImageView ivLogo;
	ImageView ivAppIcon;
	ImageView ivWwwLink;
	ImageView ivMailLink;
	TextView tvTitle;
	TextView tvAboutApp;
	TextView tvLicenseExplanation;
	LinearLayout llLicenses;

	private String mTitle;
	private String mAboutApp;
	private String mMarketUrl;
	private String mDevUrl = "https://plus.google.com/u/0/b/100408039918745574299/";
	private String mDevMail = "app@uvwxy.de";
	private String mPackageName;

	private String[] mLicenses;

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getAboutApp() {
		return mAboutApp;
	}

	public void setAboutApp(String aboutApp) {
		this.mAboutApp = aboutApp;
	}

	public String getMarketUrl() {
		return mMarketUrl;
	}

	public void setMarketUrl(String marketUrl) {
		this.mMarketUrl = marketUrl;
	}

	public String getDevUrl() {
		return mDevUrl;
	}

	public void setDevUrl(String devUrl) {
		this.mDevUrl = devUrl;
	}

	public String getDevMail() {
		return mDevMail;
	}

	public void setDevMail(String mail) {
		this.mDevMail = mail;
	}

	public String getPackageName() {
		return mPackageName;
	}

	public void setPackageName(String packageName) {
		this.mPackageName = packageName;
	}

	public String[] getLicenses() {
		return mLicenses;
	}

	public void setLicenses(String[] licenses) {
		this.mLicenses = licenses;
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about, container, false);

		tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
		tvAboutApp = (TextView) rootView.findViewById(R.id.tvAboutApp);
		tvLicenseExplanation = (TextView) rootView.findViewById(R.id.tvLicenseExplanation);

		llLicenses = (LinearLayout) rootView.findViewById(R.id.llLicenses);

		ivLogo = (ImageView) rootView.findViewById(R.id.ivLogo);
		ivAppIcon = (ImageView) rootView.findViewById(R.id.ivAppIcon);
		ivWwwLink = (ImageView) rootView.findViewById(R.id.ivWwwLink);
		ivMailLink = (ImageView) rootView.findViewById(R.id.ivMailLink);

		tvAboutApp.setText(mAboutApp);
		tvTitle.setText(mTitle);

		Drawable d = BitmapTools.getPackageIcon(getActivity(), mPackageName);
		if (d != null) {
			if (IntentTools.isApiLarger(15)) {
				ivAppIcon.setBackground(d);
			} else {
				ivAppIcon.setBackgroundDrawable(d);
			}

		}
		ivAppIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentTools.showURL(getActivity(), mMarketUrl);
			}
		});
		ivWwwLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				IntentTools.showURL(getActivity(), mDevUrl);
			}
		});

		ivMailLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				IntentTools.email(getActivity(), mDevMail, null, mPackageName + ": ", null);
			}
		});

		if (mLicenses != null) {
			for (final String key : mLicenses) {
				TextView v = new TextView(getActivity());
				v.setLayoutParams(tvLicenseExplanation.getLayoutParams());
				v.setText(ActivityLicenseDisplay.licenseName.get(key));
				v.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent i = new Intent(getActivity().getApplicationContext(), ActivityLicenseDisplay.class);
						i.putExtra(ActivityLicenseDisplay.LICENSE_KEY, key);
						startActivity(i);
					}
				});
				llLicenses.addView(v);
			}
		}

		return rootView;
	}
}
