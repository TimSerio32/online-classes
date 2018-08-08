package serio.tim.android.com.tabdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabOneFragment(), "TAB1");
        adapter.addFragment(new TabTwoFragment(), "TAB2");
        adapter.addFragment(new TabThreeFragment(), "TAB3");
        viewPager.setAdapter(adapter);
    }
}
