package adalwin.com.adalwinvideo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import adalwin.com.adalwinvideo.R;
import adalwin.com.adalwinvideo.models.Movie;
import adalwin.com.adalwinvideo.net.MovieDetailClient;
import cz.msebera.android.httpclient.Header;

public class VideoActivity extends AppCompatActivity {
    private WebView myWebView;
    String movieUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        AsyncHttpClient client = new AsyncHttpClient();


        Movie book = (Movie) getIntent().getParcelableExtra("movieData");
        MovieDetailClient.get(book.getOpenLibraryId().trim()+ "/hd/San%2CDallas%2CWashington%2CToronto%2CLondon%2CSydney/",new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                // called when response HTTP status is "200 OK"



                myWebView = (WebView) findViewById(R.id.wvArticle);
                // Configure related browser settings
                // Enable responsive layout
                myWebView.getSettings().setUseWideViewPort(true);
                // Zoom out if the content width is greater than the width of the veiwport
                myWebView.getSettings().setLoadWithOverviewMode(true);
                myWebView.getSettings().setSupportZoom(true);
                myWebView.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
                myWebView.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page
                myWebView.getSettings().setLoadsImagesAutomatically(true);
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.setScrollBarStyle(myWebView.SCROLLBARS_INSIDE_OVERLAY);
                // Configure the client to use when opening URLs
                myWebView.setWebViewClient(new MyBrowser());
                //Load the initial URL
                myWebView.loadUrl("http://169.45.89.79/einthusancom/hot/D2914.mp4?st=uPsYnBL_isUoCaCNloojaQ&e=1464475016");
                //myWebView.loadUrl("http://einthusan.com/movies/watch.php?tamilmoviesonline=Thuppakki&lang=tamil&id=1327");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String response,Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });









        }


        private class MyBrowser extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }





}
