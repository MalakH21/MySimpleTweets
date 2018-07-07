package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    // the tweet to display
    Tweet tweet;
    ImageView imageView;
    TextView userName;
    TextView tweetMessage;
    TextView userHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = (ImageView) findViewById(R.id.imageView);
        userName = (TextView) findViewById(R.id.userName);
        tweetMessage = (TextView) findViewById(R.id.tweetMessage);
        userHandler = (TextView) findViewById(R.id.userHandler);


        // unwrap the movie passed in via intent, using its simple name as a key
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        Log.d("DetailsActivity", String.format("Showing details for '%s'", tweet.user.name));

        //tweet.User.name();
        // set the user name, and text
        userName.setText(tweet.user.name);
        userHandler.setText(tweet.user.screenName);

        tweetMessage.setText(tweet.body);

        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .into(imageView);

    }
}
