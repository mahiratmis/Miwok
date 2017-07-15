package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("one","bir", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("two","iki", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("three","üç", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("four","dört", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("five","beş", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("six","altı", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("seven","yedi", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("eight","sekiz", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("nine","dokuz", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("ten","on", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, numbers, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // on click play audio
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, numbers.get(i).getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
