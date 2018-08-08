package serio.tim.android.com.snapchatclone.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import serio.tim.android.com.snapchatclone.R;

/**
 * Created by Tim on 10/23/17.
 */

public class StoryFragment extends BaseFragment {

    public static StoryFragment create() {
        return new StoryFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_story;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }
}
