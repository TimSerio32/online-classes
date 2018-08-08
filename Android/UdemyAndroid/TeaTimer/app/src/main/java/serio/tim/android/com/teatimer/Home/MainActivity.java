package serio.tim.android.com.teatimer.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import serio.tim.android.com.teatimer.R;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager llm;
    ArrayList<TeaType> teaTypes;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    TextView teaTypeName;
    int teaTypeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        teaTypes = TeaTypeList.getList();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(MainActivity.this, teaTypes);
        recyclerView.setAdapter(recyclerAdapter);

    }
}
