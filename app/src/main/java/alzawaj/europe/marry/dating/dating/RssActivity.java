package alzawaj.europe.marry.dating.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import alzawaj.europe.marry.dating.dating.Common.HTTPdataHandler;
import alzawaj.europe.marry.dating.dating.model.RSSObject;

public class RssActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;

    //RSS Link
    private final String RSS_LINK ="https://mantowf.com/feed/";
    private final String RSS_to_Json_API ="https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("RSS FEED");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
         loadRSS();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.refresh)
            loadRSS();
        return true;
    }

    private void loadRSS() {
        AsyncTask<String, String, String > loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog mDialog = new ProgressDialog(RssActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please Wait...");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {

                String result;
                HTTPdataHandler http = new HTTPdataHandler();
                result = http.GetHTTPData(params[0]);
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_LINK);
        loadRSSAsync.execute(url_get_data.toString());

    }
}