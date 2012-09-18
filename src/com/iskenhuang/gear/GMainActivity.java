package com.iskenhuang.gear;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.iskenhuang.gear.ConnectMySQL.MyObj;

public class GMainActivity extends Activity {
	TextView textResult;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmain_activity);
        init();
    }

    private void init(){
    	textResult = (TextView) findViewById(R.id.textResult);
    	textResult.setText("Waitting connected.");
    	
    	Button b = (Button) findViewById(R.id.buttonResult);
    	b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.sendEmptyMessage(0);		
			}
		});
    }
    
    private void query(){
    	if (android.os.Build.VERSION.SDK_INT > 9) {
    	      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    	      StrictMode.setThreadPolicy(policy);
    	}
    	ConnectMySQL mysql = new ConnectMySQL();
    	ArrayList results = new ArrayList();
    	try {
			results = mysql.getList();
			String tempString = "";
	    	
	    	for(int i = 0; i < results.size(); i++){
	    		MyObj obj = (MyObj) results.get(i);
	    		tempString += obj.name;
	    	}
	    	
	    	textResult.setText(tempString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			textResult.setText("Connected error!!");
		}
    }
    
    private Handler handler = new Handler() {
    	@Override
    	public void handleMessage(Message msg) {
    		query();
    	}
    };
}
