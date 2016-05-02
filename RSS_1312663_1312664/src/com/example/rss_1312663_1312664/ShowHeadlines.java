package com.example.rss_1312663_1312664;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ShowHeadlines extends Activity {
	ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
	ListView myListView;
	String urlAddress = "";
	String urlCaption = "";
	SingleItem selectedNewsItem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main);
		 myListView = (ListView)this.findViewById(R.id.myListView);


		 Intent callingIntent = getIntent();


		 Bundle myBundle = callingIntent.getExtras();
		 urlAddress = myBundle.getString("urlAddress");
		 urlCaption = myBundle.getString("urlCaption"); 
		 
		 this.setTitle("Tuổi Trẻ - " + urlCaption + " \t" + MainActivity.niceDate());
		 myListView = (ListView)this.findViewById(R.id.myListView);
		 myListView.setOnItemClickListener(new OnItemClickListener() {
		 public void onItemClick(AdapterView<?> av, View v, int index, long id) {
				 selectedNewsItem = newsList.get(index);
				 showNiceDialogBox(selectedNewsItem, getApplicationContext());
			 }
		 });


		DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
		downloader.execute(urlAddress, urlCaption);

	 }

	 public void showNiceDialogBox(SingleItem selectedStoryItem, Context context){
			 String title = selectedStoryItem.getTitle(); 
			 String description = selectedStoryItem.getDescription();
			 if (title.toLowerCase().equals(description.toLowerCase())){
			 description = "";
		 }
		 try {
			final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
			AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
			myBuilder
			.setIcon(R.drawable.logo_tuoitre)
			.setTitle(Html.fromHtml(urlCaption) )
			.setMessage( title + "\n\n" + Html.fromHtml(description) + "\n" )
			.setPositiveButton("Đóng", null)
			.setNegativeButton("Đọc tiếp...", new OnClickListener() {
			public void onClick(DialogInterface dialog, int whichOne) {
				Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
				startActivity(browser);
			}
			}).show();
		} 
		catch (Exception e) {
			Log.e("Error DialogBox", e.getMessage() );
		}
	}
} 
