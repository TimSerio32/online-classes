package serio.tim.android.com.redditclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Tim on 11/5/17.
 */

public class WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        WebView webView = (WebView)findViewById(R.id.webview);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.webviewLoadingProgressBar);

        final TextView loadingText = (TextView)findViewById(R.id.progressText);

        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                loadingText.setText("");
            }
        });
    }
}
