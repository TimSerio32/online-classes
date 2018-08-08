package serio.tim.android.com.redditclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import serio.tim.android.com.redditclone.Account.LoginActivity;
import serio.tim.android.com.redditclone.Comments.CommentsActivity;
import serio.tim.android.com.redditclone.Model.Entry.Entry;
import serio.tim.android.com.redditclone.Model.Feed;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btnRefresh;
    private EditText mFeedName;
    private String currentFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRefresh = (Button)findViewById(R.id.btnRefreshFeed);
        mFeedName = (EditText)findViewById(R.id.editTextFeed);

        setUpToolbar();

        //init();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
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

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch(item.getItemId()) {
                    case R.id.navLogin:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                }

                return false;
            }
        });
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLS.BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create()).build();

        FeedAPI feedAPI = retrofit.create(FeedAPI.class);

        Call<Feed> call = feedAPI.getFeed(currentFeed);

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: feed: " + response.body().toString());
                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                List<Entry> entrys = response.body().getEntries();
                Log.d(TAG, "onResponse: entrys: " + entrys);

//                Log.d(TAG, "onResponse: author: " + entrys.get(0).getAuthor().getName());
//                Log.d(TAG, "onResponse: updated: " + entrys.get(0).getUpdated());
//                Log.d(TAG, "onResponse: title: " + entrys.get(0).getTitle());


                final ArrayList<Post> posts = new ArrayList<>();

                for(int i = 0; i < entrys.size(); i++) {
                    ExtractXML extractXml1 = new ExtractXML(entrys.get(i).getContent(), "<a href=");
                    List<String> postContent = extractXml1.start();

                    ExtractXML extractXml2 = new ExtractXML(entrys.get(i).getContent(), "<img src=");
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
                                postContent.get(lastPosition),
                                entrys.get(i).getId()
                        ));
                    } catch(NullPointerException e) {
                        posts.add(new Post(
                                entrys.get(i).getTitle(),
                                "None",
                                entrys.get(i).getUpdated(),
                                postContent.get(0),
                                postContent.get(lastPosition),
                                entrys.get(i).getId()
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
//                //Log.i("StatusRSS", "onResponse: feed: " + response.body().toString());
//                Log.i("StatusRSS", "onResponse: Server Response: " + response.toString());
//
//                List<Entry> entries = response.body().getEntries();
//
//                Log.i("StatusRSS", "onResponse: entries: " + response.body().getEntries());
//
////                Log.i("StatusRSS", "onResponse: author: " + entries.get(0).getAuthor());
////                Log.i("StatusRSS", "onResponse: updated: " + entries.get(0).getUpdated());
////                Log.i("StatusRSS", "onResponse: title: " + entries.get(0).getTitle());
//                final ArrayList<Post> posts = new ArrayList<Post>();
//
//                for(int i = 0; i < entries.size(); i++) {
//                    ExtractXML extractXML1 = new ExtractXML(entries.get(i).getContent(), "<a href=");
//                    List<String> postContent = extractXML1.start();
//
//                    ExtractXML extractXML2 = new ExtractXML(entries.get(i).getContent(), "<img src==");
//                    try {
//                        postContent.add(extractXML2.start().get(0));
//                    } catch(NullPointerException e) {
//                        postContent.add(null);
//                        Log.e("Thumbnail", "onResponse: NullPointerException(thumbnail): " + e.getMessage());
//                    } catch(IndexOutOfBoundsException e) {
//                        postContent.add(null);
//                        Log.e("Thumbnail", "onResponse: NullPointerException(thumbnail): " + e.getMessage());
//                    }
//
//                    int lastPosition = postContent.size() - 1;
//
//                    try {
//                        posts.add(new Post(
//                                entries.get(i).getTitle(), entries.get(i).getAuthor().getName(), entries.get(i).getUpdated(), postContent.get(0), postContent.get(lastPosition), entries.get(i).getId()
//                        ));
//                    } catch(NullPointerException e) {
//                        posts.add(new Post(
//                                entries.get(i).getTitle(), "None", entries.get(i).getUpdated(), postContent.get(0), postContent.get(lastPosition), entries.get(i).getId()
//                        ));
//
//                        Log.i("NullPointer", "onResponse: NullPointerException: " + e.getMessage());
//                    }
//                }
//    //                for(int j = 0; j < posts.size(); j++) {
//    //                    Log.d("Info", "onResponse: \n " +
//    //                            "PostURL: " + posts.get(j).getPostURL() + "\n " +
//    //                            "ThumbnailURL: " + posts.get(j).getThumbnailURL() + "\n " +
//    //                            "Title: " + posts.get(j).getTitle() + "\n " +
//    //                            "Author: " + posts.get(j).getAuthor() + "\n " +
//    //                            "Updated: " + posts.get(j).getDate_updated() + "\n " +
//    //                            "Id: " + posts.get(j).getId() + "\n ");
//    //                }
//
//                ListView listView = (ListView)findViewById(R.id.listView);
//                CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, R.layout.card_layout_main, posts);
//                listView.setAdapter(customListAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                        intent.putExtra("@string/post_url", posts.get(i).getPostURL());
                        intent.putExtra("@string/post_thumbnail", posts.get(i).getThumbnailURL());
                        intent.putExtra("@string/post_title", posts.get(i).getTitle());
                        intent.putExtra("@string/post_author", posts.get(i).getAuthor());
                        intent.putExtra("@string/post_updated", posts.get(i).getDate_updated());
                        intent.putExtra("@string/post_id", posts.get(i).getId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.i("StatusRSS", "onFailure: Unable to retrieve RSS: " + t.getMessage());
                Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_manu, menu);
        return true;
    }
}
