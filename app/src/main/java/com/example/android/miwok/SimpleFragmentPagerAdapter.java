
package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.miwok.ColorsFragment;
import com.example.android.miwok.FamilyFragment;
import com.example.android.miwok.NumbersFragment;
import com.example.android.miwok.PhrasesFragment;

import java.util.Locale;


/**
 * Provides the appropriate {@link Fragment} for a view pager.
 * In order for the ViewPager to display page 0,
 * the ViewPager asks the adapter for the 0th fragment.
 * See the SimpleFragmentPagerAdapter getItem(int position) method.
 * When the user swipes leftward, we move onto page 1, which means the ViewPager asks the adapter for the fragment at position 1.
 * When we get to page 2, the ViewPager asks the adapter for the fragment at position 2.
 * Thus, depending on which page (also known as position), the user has swiped to, the corresponding fragment gets shown.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else if (position == 3) {
            return new PhrasesFragment();
        }
        return null;

    }

    // launch the app on your device, first the ViewPager asks the adapter how many pages there will be.
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Numbers";

            case 1:
                return "Family";
            case 2:
                return "Colors";
            case 3:
                return "Phrases";
             default:
                 return null;

        }


    }
}


