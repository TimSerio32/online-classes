package serio.tim.android.com.redditapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import serio.tim.android.com.redditapp.model.Feed;
import serio.tim.android.com.redditapp.model.entry.Entry;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String BASE_URL = "https://www.reddit.com/r/";

    private Button btnRefreshFeed;
    private EditText mFeedName;
    private String currentFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRefreshFeed = (Button) findViewById(R.id.btnRefreshFeed);
        mFeedName = (EditText) findViewById(R.id.etFeedName);

        init();

        btnRefreshFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedName = mFeedName.getText().toString();
                if(!feedName.equals("")) {
                    currentFeed = feedName;
                    init();
                } else {
                    init();
                }
            }
        });
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<Feed> call = feedApi.getFeed(currentFeed);

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
//                Log.d(TAG, "onResponse: feed: " + response.body().toString());
                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                List<Entry> entrys = response.body().getEntrys();
                Log.d(TAG, "onResponse: entrys: " + entrys);

//                Log.d(TAG, "onResponse: author: " + entrys.get(0).getAuthor().getName());
//                Log.d(TAG, "onResponse: updated: " + entrys.get(0).getUpdated());
//                Log.d(TAG, "onResponse: title: " + entrys.get(0).getTitle());


                ArrayList<Post> posts = new ArrayList<>();

                for(int i = 0; i < entrys.size(); i++) {
                    ExtractXml extractXml1 = new ExtractXml(entrys.get(i).getContent(), "<a href=");
                    List<String> postContent = extractXml1.start();

                    ExtractXml extractXml2 = new ExtractXml(entrys.get(i).getContent(), "<img src=");
                    try {
                        postContent.add(extractXml2.start().get(0));
                    } catch (NullPointerException e) {
                        postContent.add(null);
                        Log.e(TAG, "onResponse: NullPointerException(thumbnail): " + e.getMessage());
                    } catch (IndexOutOfBoundsException e) {
                        postContent.add(null);
                        Log.e(TAG, "onResponse: IndexOutOfBoundsException(thumbnail): " + e.getMessage());
                    }

                    int lastPosition = postContent.size() - 1;
                    try {
                        posts.add(new Post(
                                entrys.get(i).getTitle(),
                                entrys.get(i).getAuthor().getName(),
                                entrys.get(i).getUpdated(),
                                postContent.get(0),
                                postContent.get(lastPosition)
                        ));
                    } catch(NullPointerException e) {
                        posts.add(new Post(
                                entrys.get(i).getTitle(),
                                "None",
                                entrys.get(i).getUpdated(),
                                postContent.get(0),
                                postContent.get(lastPosition)
                        ));
                        Log.e(TAG, "onResponse: NullPointerException: " + e.getMessage());
                    }
                }

//                for(int j = 0; j < posts.size(); j++) {
//                    Log.d(TAG, "onResponse: \n" +
//                            "PostUrl: " + posts.get(j).getPostUrl() + "\n" +
//                            "ThumbnailUrl: " + posts.get(j).getThumbnailUrl() + "\n" +
//                            "Title: " + posts.get(j).getTitle() + "\n" +
//                            "Author: " + posts.get(j).getAuthor() + "\n" +
//                            "Updated: " + posts.get(j).getDate_updated() + "\n"
//                    );
//                }

                ListView listView = (ListView) findViewById(R.id.listView);
                CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, R.layout.card_layout_main, posts);
                listView.setAdapter(customListAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.getMessage());
                Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
