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

public class TabOneFragment extends Fragment {

    private Button testBtn1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        testBtn1 = (Button)view.findViewById(R.id.testBtn1);

        testBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Testing Button Click Tab 1", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
