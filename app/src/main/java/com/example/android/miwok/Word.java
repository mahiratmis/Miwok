package com.example.android.miwok;

/**
 * Created by mahir on 14.07.2017.
 */

public class Word {
    private  String mDefaultTranslation;
    private  String mMiwokTranslation;
    private  int imageResourceID;
    private int mAudioResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imageResourceID, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imageResourceID = imageResourceID;
        this.mAudioResourceId = mAudioResourceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imageResourceID = NO_IMAGE_PROVIDED;
        this.mAudioResourceId = mAudioResourceId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceID() { return imageResourceID; }

    public boolean hasImage() { return imageResourceID != NO_IMAGE_PROVIDED ; }

    public int getmAudioResourceId() { return mAudioResourceId; }
}
