package serio.tim.android.com.fragmentdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SectionsStatePagerAdapter sectionsStatePagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager)findViewById(R.id.container);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "FragmentOne");
        adapter.addFragment(new FragmentTwo(), "FragmentTwo");
        adapter.addFragment(new FragmentThree(), "FragmentThree");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }
}
