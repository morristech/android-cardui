package de.uvwxy.cardpager;

import de.uvwxy.cardstyle.R;
import de.uvwxy.helper.BitmapTools;
import de.uvwxy.helper.IntentTools;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentAbout extends Fragment {

	private String title;
	private String aboutApp;
	private String marketUrl;
	private String devUrl = "https://plus.google.com/u/0/b/100408039918745574299/";
	private String mail = "app@uvwxy.de";
	private String packageName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAboutApp() {
		return aboutApp;
	}

	public void setAboutApp(String aboutApp) {
		this.aboutApp = aboutApp;
	}

	public String getMarketUrl() {
		return marketUrl;
	}

	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}

	public String getDevUrl() {
		return devUrl;
	}

	public void setDevUrl(String devUrl) {
		this.devUrl = devUrl;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about, container, false);
		TextView tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
		TextView tvAboutApp = (TextView) rootView.findViewById(R.id.tvAboutApp);
		ImageView ivLogo = (ImageView) rootView.findViewById(R.id.ivLogo);
		ImageView ivAppIcon = (ImageView) rootView.findViewById(R.id.ivAppIcon);
		// TODO: continue here: ivMailLink
		tvAboutApp.setText(aboutApp);
		tvTitle.setText(title);
		Drawable d = BitmapTools.getPackageIcon(getActivity(), packageName);
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
				IntentTools.showURL(getActivity(), marketUrl);
			}
		});
		ivLogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				IntentTools.showURL(getActivity(), devUrl);
			}
		});
		return rootView;
	}
}
