package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.colors;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        
        final ArrayList<Word> familyMembers = new ArrayList<Word>();
        familyMembers.add(new Word("father","baba", R.drawable.family_father, R.raw.family_father));
        familyMembers.add(new Word("mother","anne", R.drawable.family_mother, R.raw.family_mother));
        familyMembers.add(new Word("son","oğul", R.drawable.family_son, R.raw.family_son));
        familyMembers.add(new Word("daughter","kız",R.drawable.family_daughter, R.raw.family_daughter));
        familyMembers.add(new Word("older brother","abi",R.drawable.family_older_brother, R.raw.family_older_brother));
        familyMembers.add(new Word("younger brother","küçük erkek kardeş", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyMembers.add(new Word("older sister","abla", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyMembers.add(new Word("younger sister","küçük kız kardeş", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyMembers.add(new Word("grandfather","büyük baba", R.drawable.family_grandfather, R.raw.family_grandfather));
        familyMembers.add(new Word("grandmother","büyük anne", R.drawable.family_grandmother, R.raw.family_grandmother));

        WordAdapter itemsAdapter = new WordAdapter(this, familyMembers,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // on click play audio
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, familyMembers.get(i).getmAudioResourceId());
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
