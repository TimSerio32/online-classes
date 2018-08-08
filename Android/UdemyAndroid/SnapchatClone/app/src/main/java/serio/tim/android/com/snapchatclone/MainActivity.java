package serio.tim.android.com.snapchatclone;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import serio.tim.android.com.snapchatclone.Adapter.MainPagerAdapter;
import serio.tim.android.com.snapchatclone.View.SnapTabsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View background = findViewById(R.id.mn_background_view);
        ViewPager viewPager = (ViewPager)findViewById(R.id.mn_view_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        SnapTabsView snapTabsView = (SnapTabsView)findViewById(R.id.snap_tabs);
        snapTabsView.setViewPager(viewPager);
        //snapTabsView.setUpWithViewPager(viewPager);

        viewPager.setCurrentItem(1);

        final int colorBlue = ContextCompat.getColor(this, R.color.light_blue);
        final int colorPurple = ContextCompat.getColor(this, R.color.light_purple);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0) {
                    background.setBackgroundColor(colorBlue);
                    background.setAlpha(1 - positionOffset);
                } else if(position == 1) {
                    background.setBackgroundColor(colorPurple);
                    background.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
