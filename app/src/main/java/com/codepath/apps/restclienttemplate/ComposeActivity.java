package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    JsonHttpResponseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        EditText simpleEditText = (EditText) findViewById(R.id.et_simple);
        String strValue = simpleEditText.getText().toString();
        handler = new JsonHttpResponseHandler();

        client = new TwitterClient(this);
        client.sendTweet(strValue,handler);
    }


}
