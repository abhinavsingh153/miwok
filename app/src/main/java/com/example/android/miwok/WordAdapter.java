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

import static android.R.id.list;

/**
 * Created by user on 03-08-2017.
 */

public class WordAdapter extends ArrayAdapter <word> {

    //we take a variable for storing the color id for differnt categories

    private int mColorResourceId;


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of word objects to display in a list */

    public WordAdapter(Activity context, ArrayList<word> words , int colorResourceId)
    {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super ( context , 0 , words);

        mColorResourceId = colorResourceId;

    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        if (listItemView== null)
        {
           listItemView = LayoutInflater.from( getContext()).inflate(R.layout.list_item, parent , false);
        }

        // Get the {@link word} object located at this position in the list
        word currentword = getItem(position);

        // Find the TextView in the list_item.xml layout with the Miwok word



        TextView miwoknumTextView = (TextView) listItemView.findViewById(R.id.Miwok_Number);

        miwoknumTextView.setText(currentword.getMiwokTranslation());

        TextView engnumTextView= (TextView) listItemView.findViewById(R.id.English_Number);

        engnumTextView.setText(currentword.getDefaultTranslation());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_icon_item);


        if(currentword.hasImage()) {

            iconView.setImageResource(currentword.getImageResourceId());

            //make sure that the image is visible
            iconView.setVisibility(View.VISIBLE);
        }
        else
        {
            iconView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.




        return listItemView;
    }





}
