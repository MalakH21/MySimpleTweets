package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>  {


    private List<Tweet> mTweets;
    Button reply;
    EditText simpleEditText;
    Context context;
    TwitterClient client;
    // pass in Tweets array in the constructor
    public TweetAdapter(List<Tweet> tweets){
        mTweets = tweets;

    }

    public static final int REPLY_REQUEST = 1;
    // for each row, inflate the layout and cache references into ViewHolder


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //simpleEditText = (EditText) findViewById(R.id.et_simple);

        //client = TwitterApp.getRestClient(this);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }


    // bind the values based on the position of the element

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get the data according to position
        Tweet tweet = mTweets.get(position);
        // populate the views according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvDate.setText(ParseRelativeDate.getRelativeTimeAgo(tweet.createdAt));
        final String username = tweet.user.screenName;


        Button replyButton = holder.replyButton;


        Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfileImage);

        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReplyButton.class);
                intent.putExtra("username", Parcels.wrap(username));
                ((Activity)v.getContext()).startActivityForResult(intent, REPLY_REQUEST);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //create ViewHolder class
     public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         public ImageView ivProfileImage;
         public Button replyButton;
         public TextView tvUsername;
         public TextView tvBody;
         public TextView tvDate;
        //public Button reply;

        public ViewHolder(View itemView){
            super(itemView);

            // perform findViewById lookups

            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            replyButton = (Button) itemView.findViewById(R.id.replyButton);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();

            // ensure t
            // make sure the position is valid, i.e. actually exists in the view
            if (position == RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Tweet tweet = mTweets.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, ReplyButton.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                // show the activity
                context.startActivity(intent);
            }
            else{
                Tweet tweet = mTweets.get(position);
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                // show the activity
                context.startActivity(intent);
            }

        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
