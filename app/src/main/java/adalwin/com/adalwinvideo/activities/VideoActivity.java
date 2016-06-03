package adalwin.com.adalwinvideo.activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.loopj.android.http.AsyncHttpClient;

import adalwin.com.adalwinvideo.R;

/*public class VideoActivity extends AppCompatActivity implements
        SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnVideoSizeChangedListener,
        MediaController.MediaPlayerControl {*/
public class VideoActivity extends Activity{
        private WebView myWebView;
    String movieUrl;
    private MediaController mcontroller;
    private Handler handler = new Handler();
    boolean mIsVideoReadyToBePlayed;
    private SurfaceHolder vidHolder;
    private SurfaceView vidSurface;
    String vidAddress = "http://75.126.5.180/einthusancom/cold/B2537.mp4?st=YyGmbCda4WbRwjNN_TVhYQ&e=1464743392";
    MediaPlayer mediaPlayer;


    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        AsyncHttpClient client = new AsyncHttpClient();
        VideoView vidView = (VideoView)findViewById(R.id.myVideo);
        //myWebView.loadUrl("http://169.45.89.79/einthusancom/hot/D2914.mp4?st=uPsYnBL_isUoCaCNloojaQ&e=1464475016");
        //vidSurface=(SurfaceView)findViewById(R.id.myVideo);

        Uri vidUri = Uri.parse(vidAddress);

        playVideo(vidUri,vidView);
        /*handler = new Handler();
        vidHolder = vidSurface.getHolder();
        vidHolder.addCallback(this);
        playVideo();*/




        /*Movie book = (Movie) getIntent().getParcelableExtra("movieData");
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
        });*/


    }



    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void playVideo(Uri vidUri, VideoView vidView) {
        try {
            vidView.setVideoURI(vidUri);
            MediaController vidControl = new MediaController(this);
            vidControl.setAnchorView(vidView);
            vidView.setMediaController(vidControl);
            vidView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }


    private void playVideo() {
        try {
            mcontroller = new MediaController(this);
            mediaPlayer = MediaPlayer.create(this, Uri.parse(vidAddress));
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mcontroller.show();
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        mediaPlayer.setDisplay(vidHolder);
        try {
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO Auto-generated method stub
        progressBar1.setVisibility(View.GONE);
        mcontroller.setMediaPlayer(this);
        mcontroller.setAnchorView(findViewById(R.id.myVideo));
        mcontroller.setEnabled(true);

        handler.post(new Runnable() {
            public void run() {
                mcontroller.show();
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        // TODO Auto-generated method stub
    }

    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public void surfaceDestroyed (SurfaceHolder holder)
    {}
*/

}
