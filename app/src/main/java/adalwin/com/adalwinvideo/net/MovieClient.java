package adalwin.com.adalwinvideo.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
public class MovieClient {
    private static final String API_BASE_URL = "http://service-desiflix.rhcloud.com/VideoContentServie/videoservice/";
    private AsyncHttpClient client;


    public MovieClient() {
        this.client = new AsyncHttpClient();
    }
    String movieUrl;

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    // Method for accessing the search API
    public void getMovies(final String query,int page, JsonHttpResponseHandler handler) {
        try {
            String url = getApiUrl("videoList?videoType=HD&language=tamil&organize=Cast&pageNumber=1&filter="+query+"&pageNumber="+page);
           // client.get("http://service-desiflix.rhcloud.com/VideoContentServie/videoservice/videoList?videoType=HD&language=tamil&organize=Cast&filter=vijay&pageNumber=1", handler);
            if(query.isEmpty()){
                System.out.println("The respose is "+page);
                client.get("http://service-desiflix.rhcloud.com/VideoContentServie/videoservice/videoList?videoType=HD&language=tamil&pageNumber="+page+"",handler);
            }else{
                client.get(url,handler);
            }


            System.out.println(handler.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
