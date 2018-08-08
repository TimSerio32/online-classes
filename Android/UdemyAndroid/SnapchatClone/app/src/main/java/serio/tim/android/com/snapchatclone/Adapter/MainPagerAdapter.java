package serio.tim.android.com.snapchatclone.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import serio.tim.android.com.snapchatclone.Fragments.ChatFragment;
import serio.tim.android.com.snapchatclone.Fragments.EmptyFragment;
import serio.tim.android.com.snapchatclone.Fragments.StoryFragment;

/**
 * Created by Tim on 10/23/17.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return ChatFragment.create();
            case 1:
                return EmptyFragment.create();
            case 2:
                return StoryFragment.create();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Chat";
            case 1:
                return "Search";
            case 2:
                return "Story";
        }

        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
