package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.colors;

public class PhrasesActivity extends AppCompatActivity {

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

        final ArrayList<Word> phrases = new ArrayList<Word>();
        phrases.add(new Word("Where are you going?","Nereye gidiyorsun?", R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?","Adın nedir?", R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is...","Adım...", R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?","Nasıl hissediyorsun?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I’m feeling good.","İyi hissediyorum.", R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?","Geliyor musun?", R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.","Evet, geliyorum.", R.raw.phrase_yes_im_coming));
        phrases.add(new Word("I’m coming.","Geliyorum.", R.raw.phrase_im_coming));
        phrases.add(new Word("Let’s go.","Haydi gidelim.", R.raw.phrase_lets_go));
        phrases.add(new Word("Come here.","Buraya Gel", R.raw.phrase_come_here));

        WordAdapter itemsAdapter = new WordAdapter(this, phrases, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // on click play audio
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, phrases.get(i).getmAudioResourceId());
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
