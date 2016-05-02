package com.example.rss_1312663_1312664;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ArrayAdapter<String> adapterMainSubjects;
	ListView myMainListView;
	Context context;
	SingleItem selectedNewsItem;
	
	String[][] myUrlCaptionMenu = {
	{"http://tuoitre.vn/rss/tt-tin-moi-nhat.rss", "Tin Mới Nhất"},
	{"http://tuoitre.vn/rss/tt-the-gioi.rss", "Thế Giới"},
	{"http://tuoitre.vn/rss/tt-kinh-te.rss", "Kinh Tế"},
	{"http://tuoitre.vn/rss/tt-chinh-tri-xa-hoi.rss", "Chính Trị - Xã Hội"},
	{"http://tuoitre.vn/rss/tt-the-thao.rss", "Thể Thao"},
	{"http://tuoitre.vn/rss/tt-nhip-song-so.rss", "Công Nghệ"}
	 };
	
	String [] myUrlCaption = new String[myUrlCaptionMenu.length];
	String [] myUrlAddress = new String[myUrlCaptionMenu.length]; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i=0; i< myUrlCaptionMenu.length; i++) {
			 myUrlAddress[i] = myUrlCaptionMenu[i][0];
			 myUrlCaption[i] = myUrlCaptionMenu[i][1];
		}
		
		context = getApplicationContext();
		this.setTitle("Tuổi Trẻ " + niceDate() );

		 myMainListView = (ListView)this.findViewById(R.id.myListView);
		 myMainListView.setOnItemClickListener(new OnItemClickListener() {
			 public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
				 String urlAddress = myUrlAddress[_index];
				 String urlCaption = myUrlCaption[_index];

				 Intent callShowHeadlines = new Intent( MainActivity.this,
						 ShowHeadlines.class);

				 Bundle myData = new Bundle();
				 myData.putString("urlAddress", urlAddress);
				 myData.putString("urlCaption", urlCaption);
				 callShowHeadlines.putExtras(myData);
				 startActivity(callShowHeadlines);
				 }
			 });

		adapterMainSubjects = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myUrlCaption);
		myMainListView.setAdapter(adapterMainSubjects);
	 }
	 public static String niceDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MM-yyyy ", Locale.US);
		return sdf.format(new Date() );
	 }
}
