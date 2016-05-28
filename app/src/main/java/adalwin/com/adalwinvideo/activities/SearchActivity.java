package adalwin.com.adalwinvideo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import adalwin.com.adalwinvideo.R;
import adalwin.com.adalwinvideo.adapters.MovieAdapter;
import adalwin.com.adalwinvideo.models.Movie;
import adalwin.com.adalwinvideo.net.MovieClient;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {
    private ListView lvMovies;
    private MovieAdapter movieAdapter;
    private MovieClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        final ArrayList<Movie> aMovies = new ArrayList<>();
        // initialize the adapter
        movieAdapter = new MovieAdapter(this, aMovies);
        // attach the adapter to the ListView
        lvMovies.setAdapter(movieAdapter);
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie movie = aMovies.get(position);
                Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
                System.out.println(movie.getBookUrl());
                intent.putExtra("movieData",(Parcelable)movie);
                startActivity(intent);
            }
        });

        // Fetch the data remotely
        fetchMovies("");

    }


    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchMovies(String query) {
        client = new MovieClient();
        client.getMovies(query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {
                    super.onSuccess(statusCode, headers,response);
                    JSONArray docs;
                    System.out.println("The respose is "+response.toString());


                    if(response != null) {
                        // Get the docs json array
                        //JSONArray json = jParser.getJSONFromUrl(url)
                       // Parse json array into array of model objects
                        final ArrayList<Movie> movies = Movie.movieList(response);

                        // Remove all books from the adapter
                        movieAdapter.clear();
                        // Load model objects into the adapter
                        for (Movie movie : movies) {
                            movieAdapter.add(movie); // add book through the adapter
                        }
                        movieAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchMovies(query);
                Toast.makeText(SearchActivity.this, "Inside the text", Toast.LENGTH_SHORT).show();
                // perform query here
                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Expand the search view and request focus
        searchItem.expandActionView();
        searchView.requestFocus();

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Movie book = (Movie)item.getMenuInfo();
        Toast.makeText(this, "book"+book.getAuthor(), Toast.LENGTH_SHORT).show();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



}
