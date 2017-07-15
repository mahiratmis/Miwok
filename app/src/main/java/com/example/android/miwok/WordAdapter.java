package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mahir on 14.07.2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int mColorResourceId) {
        // Here, we initialize the WordAdapter's internal storage for the context and the list.
        // the second argument is used when the WordAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.mColorResourceId = mColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord= getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok translation and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        // Find the Imageview in the list_item.xml layout with the ID version_number
        ImageView resourceImageView = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()){
            // Get the image id and
            // set image resource
            resourceImageView.setVisibility(View.VISIBLE);
            resourceImageView.setImageResource(currentWord.getImageResourceID());
        }else{
            resourceImageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews, 1 ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
