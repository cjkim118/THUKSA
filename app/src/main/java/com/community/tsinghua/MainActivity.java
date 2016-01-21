package com.community.tsinghua;

<<<<<<< HEAD
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
=======
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
=======
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

import butterknife.ButterKnife;
import butterknife.InjectView;

<<<<<<< HEAD
import com.community.tsinghua.R;
import com.community.tsinghua.app.AppConfig;
import com.community.tsinghua.fragment.FloatingButtonFragment;
import com.community.tsinghua.fragment.FloatingLabelFragment;
import com.community.tsinghua.fragment.MainFragment;
import com.community.tsinghua.fragment.TabsFragment;
import com.community.tsinghua.helper.ParseUtils;
import com.community.tsinghua.helper.PrefManager;
import com.community.tsinghua.model.Message;
import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.ArrayList;
import java.util.List;
=======
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

import com.community.tsinghua.fragment.FloatingButtonFragment;
import com.community.tsinghua.fragment.MainFragment;
import com.community.tsinghua.fragment.TabsFragment;

import com.community.tsinghua.helper.ParseUtils;
import com.community.tsinghua.helper.PrefManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

public class MainActivity extends AppCompatActivity
	implements NavigationView.OnNavigationItemSelectedListener {

	@InjectView(R.id.main_tool_bar)
	Toolbar toolBar;
	@InjectView(R.id.main_drawer_view)
	NavigationView navigationView;
	@InjectView(R.id.drawer_layout)
	DrawerLayout drawerLayout;

	private ActionBarDrawerToggle mDrawerToggle;

<<<<<<< HEAD
	private static String TAG = MainActivity.class.getSimpleName();

	private Toolbar mToolbar;
	private ListView listView;
	private List<Message> listMessages = new ArrayList<>();
	private MessageAdapter adapter;
	private PrefManager pref;
=======
	private PrefManager pref;
	JSONObject json = null;
	JSONArray contacts = null;

	private static final String TAG_USER = "HeWeather data service 3.0";
	private static final String TAG_ID = "now";
	private static final String TAG_NAME = "cond";
	private static final String TAG_EMAIL = "txt";
	private static final String TAG_TMP = "tmp";
	private static final String TAG_PM1 = "aqi";
	private static final String TAG_PM2 = "city";
	private static final String TAG_PM3 = "pm25";

>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
<<<<<<< HEAD
		ButterKnife.inject(this);

		Parse.initialize(this, AppConfig.PARSE_APPLICATION_ID, AppConfig.PARSE_CLIENT_KEY);
		ParseInstallation.getCurrentInstallation().saveInBackground();
=======
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		apiTest();
		ButterKnife.inject(this);
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

		setSupportActionBar(toolBar);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
<<<<<<< HEAD
		listView = (ListView) findViewById(R.id.list_view);
		adapter = new MessageAdapter(this);
		pref = new PrefManager(getApplicationContext());

		listView.setAdapter(adapter);
=======

		pref = new PrefManager(getApplicationContext());
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

		Intent intent = getIntent();

		String email = intent.getStringExtra("email");

		if (email != null) {
			ParseUtils.subscribeWithEmail(pref.getEmail());
		}


		mDrawerToggle
			= new ActionBarDrawerToggle(this, drawerLayout, toolBar,
										R.string.app_name, R.string.app_name);
		mDrawerToggle.setDrawerIndicatorEnabled(true);
		drawerLayout.setDrawerListener(mDrawerToggle);

		navigationView.setNavigationItemSelectedListener(this);
<<<<<<< HEAD
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		String message = intent.getStringExtra("message");

		Message m = new Message(message, System.currentTimeMillis());
		listMessages.add(0, m);
		adapter.notifyDataSetChanged();
	}

	private class MessageAdapter extends BaseAdapter {

		LayoutInflater inflater;

		public MessageAdapter(Activity activity) {
			inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return listMessages.size();
		}

		@Override
		public Object getItem(int position) {
			return listMessages.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.layout.list_row, null);
			}

			TextView txtMessage = (TextView) view.findViewById(R.id.message);
			TextView txtTimestamp = (TextView) view.findViewById(R.id.timestamp);

			Message message = listMessages.get(position);
			txtMessage.setText(message.getMessage());

			CharSequence ago = DateUtils.getRelativeTimeSpanString(message.getTimestamp(), System.currentTimeMillis(),
					0L, DateUtils.FORMAT_ABBREV_ALL);

			txtTimestamp.setText(String.valueOf(ago));

			return view;
		}
=======

		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, "wgUZ4S4ICFmyUdAoGZPCsChr");
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();

		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
			   .replace(R.id.main_frame, MainFragment.newInstance())
			   .commit();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		int id = menuItem.getItemId();

		Fragment fragment = null;
		switch (id) {
			case R.id.navi_group1_item0:
				fragment = MainFragment.newInstance();
				break;
			case R.id.navi_group1_item1:
<<<<<<< HEAD
				fragment = FloatingLabelFragment.newInstance();
=======
				Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
				startActivity(intent);
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6
				break;
			case R.id.navi_group1_item2:
				fragment = FloatingButtonFragment.newInstance();
				break;
			case R.id.navi_group1_item3:
				fragment = TabsFragment.newInstance();
				break;
			case R.id.navi_group1_item4:
				startActivity(new Intent(this, AppBarActivity.class));
				break;
			case R.id.action_logout:
				pref.logout();
<<<<<<< HEAD
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
=======
				intent = new Intent(MainActivity.this, LoginActivity.class);
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				break;
		}

		if (fragment != null) {
			FragmentManager manager = getSupportFragmentManager();
			manager.beginTransaction()
<<<<<<< HEAD
				   .replace(R.id.main_frame, fragment)
				   .commit();
=======
					.replace(R.id.main_frame, fragment)
					.commit();
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

			drawerLayout.closeDrawers();
			menuItem.setChecked(true);
		}
		return true;
	}
<<<<<<< HEAD
=======
	boolean mFlag;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getAction()==KeyEvent.ACTION_DOWN){

			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				if(!mFlag){
					Toast.makeText(MainActivity.this, "한번 더 누르면 앱이 종료 됩니다.", Toast.LENGTH_SHORT).show();
					mFlag = true;
					mKillHandler.sendEmptyMessageDelayed(0,2000);
					return false;
				} else {
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			}
		}
		return false;
	}
	Handler mKillHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if(msg.what ==0){
				mFlag=false;
			}
		}
	};

	private void apiTest() {

		Parameters para = new Parameters();

		para.put("city", "beijing");
		ApiStoreSDK.execute("http://apis.baidu.com/heweather/weather/free",
				ApiStoreSDK.GET,
				para,
				new ApiCallBack() {

					@Override
					public void onSuccess(int status, String responseString) {
						Log.i("sdkdemo", "onSuccess");
						try {
							json = new JSONObject(responseString);

							contacts = json.getJSONArray(TAG_USER);

							JSONObject c = contacts.getJSONObject(0);
							// Storing  JSON item in a Variable
							JSONObject test1 = c.getJSONObject(TAG_ID);
							JSONObject PM1 = c.getJSONObject(TAG_PM1);
							JSONObject PM2 = PM1.getJSONObject(TAG_PM2);
							String PM3 = PM2.getString(TAG_PM3);

							JSONObject test2 = test1.getJSONObject(TAG_NAME);

							String tmp = test1.getString(TAG_TMP);
							//Importing TextView
							final TextView pm25 = (TextView) findViewById(R.id.PM25);

							final TextView tmp1 = (TextView) findViewById(R.id.temperature);
							//Set JSON Data in TextView

							tmp1.setText(tmp + "℃");
							pm25.setText("PM 2.5 " + PM3);
						}catch (JSONException e) {
							e.printStackTrace();
							Log.e("JSON Parser", "Error parsing data " + e.toString());
						}
					}
					//mTextView.setText(responseString);


					@Override
					public void onComplete() {
						Log.i("sdkdemo", "onComplete");
					}

					@Override
					public void onError(int status, String responseString, Exception e) {
						Log.i("sdkdemo", "onError, status: " + status);
						Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
						//mTextView.setText(getStackTrace(e));
					}

				});

	}
>>>>>>> 5887ef0075624b536befdfb9505eb731337dd3c6

}
