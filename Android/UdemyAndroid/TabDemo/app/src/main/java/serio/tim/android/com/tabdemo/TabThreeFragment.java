package serio.tim.android.com.tabdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Tim on 11/18/17.
 */

public class TabThreeFragment extends Fragment {

    private Button testBtn3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment, container, false);
        testBtn3 = (Button)view.findViewById(R.id.testBtn3);

        testBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Testing Button Click Tab 3", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
