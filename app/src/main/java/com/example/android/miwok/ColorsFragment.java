package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    /** Handles playback of all the sound files */

    private MediaPlayer mediaPlayer;


    private AudioManager mAudioManager;

    //creating instance of AudioManager.OnAudioFocusChangeListener and implement callbacjk method
    //onAudoFocusChange is the callback method

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            //AUDIOFOCUS_LOSS_TRANSIENT state means we've lost the focus for a short time.
            //AUDIFOCUS_LOSS_TRANSIENT_DUCK state meabs that our app is allowed to continue playing sound but at a lower vvolume.
            //pause playback and reset the player to the start of the file.
            //That way we can play the word from the beginning when we resume playback.
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {//pausing the mediaplayer
                mediaPlayer.pause();

                //starting the mediaplayer from beginning . the parameter 0 indicates it will
                //start from the beginning
                mediaPlayer.seekTo(0);
            }

            //The AUDIOFOCUS_LOSS case means we've lost the audio focus
            //and must release the mediaplayer resources immediately

            else  if (focusChange == AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }

            // The AUDIO_FOCUS_gaincase means we 've regained the focus
            //and can resume playback

            else if (focusChange == AUDIOFOCUS_GAIN)
            {
                mediaPlayer.start();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */

    private MediaPlayer.OnCompletionListener mCompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };



    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        // Create and setup the {@link AudioManager} to request audio focus
        final ArrayList<word> words = new ArrayList<word>();

        // words.add("One");

       /* word w = new word("one", "lutti");
        words.add(w); */

        words.add(new word("Red", "wetetti" , R.drawable.color_red, R.raw.color_red));
        words.add(new word("Green", "chokokki" ,R.drawable.color_green , R.raw.color_green));
        words.add(new word("Brown", "takakki" , R.drawable.color_brown , R.raw.color_brown));
        words.add(new word("Gray", "topoppi" , R.drawable.color_gray , R.raw.color_gray));
        words.add(new word("Black", "kululli" , R.drawable.color_black , R.raw.color_black));
        words.add(new word("White", "kelelli" ,R.drawable.color_white , R.raw.color_white));
        words.add(new word("Dusty yellow", "topiise", R.drawable.color_dusty_yellow , R.raw.color_dusty_yellow));
        words.add(new word("Mustard yello", "chiwiite", R.drawable.color_mustard_yellow , R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(getActivity(),words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.

        ListView listview = (ListView) rootView.findViewById(R.id.List);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listview.setAdapter(adapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word WORD = words.get(position);

                //relaease the mediaplayer as ifit currently exists
                //we are about to play a different audio file

                releaseMediaPlayer();


                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(audioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);

                if (result == mAudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // we have gained audio focus


// Start playback

                    mediaPlayer = MediaPlayer.create(getActivity(), WORD.getAudioClipId());
                    mediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return  rootView;
    }




    private void releaseMediaPlayer()
    {

        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null)
        {

            // Regardless of the current state of the media player, release its resources
            // because we no longer need it
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.

            mediaPlayer= null;

            // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        //release the mediaplayer resources
        releaseMediaPlayer();
    }
}
