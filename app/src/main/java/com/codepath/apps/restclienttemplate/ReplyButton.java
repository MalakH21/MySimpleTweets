package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ReplyButton extends AppCompatActivity {
    Tweet tweet;
    TwitterClient client;
    JsonHttpResponseHandler handler;
    String strValue;
    EditText simpleEditText;
    long id;
    Button button;

    public void setText(){
        String username = "@" + Parcels.unwrap(getIntent().getParcelableExtra("username"));
        simpleEditText.setText(username);
        simpleEditText.setSelection(username.length());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_button);
        simpleEditText = (EditText) findViewById(R.id.et_simple);
        String strValue = simpleEditText.getText().toString();
        client = TwitterApp.getRestClient(this);

        EditText et_simple = findViewById(R.id.et_simple);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        //        Log.d("ReplyButton", String.format("Showing details for %s", tweet.getClass()));
        //id = tweet.uid;

//        et_simple.setText(tweet.user.screenName);

        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.ReplyButton(simpleEditText.getText().toString(), new JsonHttpResponseHandler(){


                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                    }


                });
            }
        });


    }
}






