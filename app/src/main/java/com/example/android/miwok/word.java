package com.example.android.miwok;

import android.support.annotation.VisibleForTesting;

/**
 * Created by user on 03-08-2017.
 * This class contains the Miwok and Default translation of the wordthe user wants to learn
 */

public class word {

    //Default translation
    private String mDefaultTranslation; //state

    //Miwok translation
    private String mMiwokTranslation;  //state

    private static final int NO_IMAGE_PROVIDED= -1;

    @Override
    public String toString() {
        return "word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioClipId=" + mAudioClipId +
                '}';
    }

    private int mImageResourceId  = NO_IMAGE_PROVIDED;

    private int mAudioClipId;

/*This constructor method is public so it can be accessed by other classes
* It does not have any return type because it's a constructor and it doesnt have any return type*/
 public word(String defaultTranslation, String miwokTranslation, int audioClipId)
 {
     mDefaultTranslation=defaultTranslation;
     mMiwokTranslation=miwokTranslation;
     mAudioClipId= audioClipId;

 }

    public word(String defaultTranslation, String miwokTranslation , int imageResourceId , int audioClipId)  //constructor
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation= miwokTranslation ;
        mImageResourceId = imageResourceId;
        mAudioClipId= audioClipId;

    }


    /*Get default translation of the word*/
    public String getDefaultTranslation()     //method
    {
        return mDefaultTranslation;
    }

    /*Get Miwok translation of the word*/
    public String getMiwokTranslation()    //method
    {
        return mMiwokTranslation;
    }

    /*These methods won't accept any parameters because their sole purpose is to
    * returninformation only*/

    public  int getImageResourceId()
    {
        return mImageResourceId;
    }

    //boolean method is used because it will return a value either true or false

    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioClipId()
    {
        return mAudioClipId;
    }

}
