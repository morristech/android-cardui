package de.uvwxy.cardpager;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityLicenseDisplay extends Activity {
	public static final String LICENSE_KEY = "LICENSE_KEY";

	public static HashMap<String, String> availableLicenses = new HashMap<String, String>();
	public static HashMap<String, String> licenseName = new HashMap<String, String>();

	static {
		availableLicenses.put("square_otto", "file:///android_asset/square_otto.html");
		licenseName.put("square_otto", "Square Otto");
		availableLicenses.put("protobuf", "file:///android_asset/protobuf.html");
		licenseName.put("protobuf", "Google Protocol Buffers");
		availableLicenses.put("guava", "file:///android_asset/apachev2.html");
		licenseName.put("guava", "Google Guava");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();

		String res = i.getStringExtra(LICENSE_KEY);
		if (res == null) {
			res = "";
		}

		WebView wv = new WebView(getApplicationContext());
		MyWebViewClient mc = new MyWebViewClient();
		wv.setWebViewClient(mc);
		wv.loadUrl(availableLicenses.get(res));
		setTitle(licenseName.get(res));
		setContentView(wv);
	}

	class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);

			return true;
		}
	}
}
