package adalwin.com.adalwinvideo.net;

import com.loopj.android.http.*;
/**
 * Created by aramar1 on 5/28/16.
 */
public class MovieDetailClient {

    private static final String BASE_URL = "http://cdn.einthusan.com/geturl/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, ResponseHandlerInterface responseHandler) {
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        System.out.println("thasjkdhfskjdhfqiuahsakjfhdskjnw;irehjfdskjanqewiuhrbf;"+BASE_URL + relativeUrl);
        return BASE_URL + relativeUrl;

    }
}
