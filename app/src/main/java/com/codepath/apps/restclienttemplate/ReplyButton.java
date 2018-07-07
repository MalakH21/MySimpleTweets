package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
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
        setText();
        //EditText et_simple = findViewById(R.id.et_simple);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        //        Log.d("ReplyButton", String.format("Showing details for %s", tweet.getClass()));
        //id = tweet.uid;

//        et_simple.setText(tweet.user.screenName);

        button = findViewById(R.id.button);
    }

    public void onClick(View view) {

        client.ReplyButton(simpleEditText.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent data = new Intent();
                    data.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK, data);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getApplicationContext(), "something wrong", Toast.LENGTH_LONG).show();
            }
        });
    }


//            public void onClick(View v) {
//                client.ReplyButton(simpleEditText.getText().toString(), new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        try {
//                            Tweet tweet = Tweet.fromJSON(response);
//                            Intent data = new Intent();
//                            data.putExtra("tweet", Parcels.wrap(tweet));
//                            setResult(RESULT_OK, data);
//                            finish();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        Toast.makeText(getApplicationContext(), "something wrong", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                client.ReplyButton(simpleEditText.getText().toString(), new JsonHttpResponseHandler(){
//
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                        super.onFailure(statusCode, headers, responseString, throwable);
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//                    }
//
//
//                });
//            }
//        });
    }








