package alzawaj.europe.marry.dating.dating;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.gson.Gson;

import java.util.Locale;

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
        String title = getString(R.string.rssfeed);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadRSS();




    }



    //REWARDED AD ON BACK PRESS
    @Override
    public void onBackPressed() {
        startActivity(new Intent(RssActivity.this,MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.english:           //English Translation
                showLanguageSelectionDialogen();
                return true;
            case R.id.fr:                //French Translation
                showLanguageSelectionDialog();
                return true;
            case R.id.ar:                //Arabic Translation
                showLanguageSelectionDialogar();
                return true;
        }
        if(item.getItemId() == R.id.refresh)
            loadRSS();
        return true;

    }

    private void showLanguageSelectionDialog() {
        String selectedLanguage = "fr";
        changeLanguage(selectedLanguage);
    }
    private void showLanguageSelectionDialogen() {
        String selectedLanguage = "eng";
        changeLanguage(selectedLanguage);
        switchScreenToLTR(selectedLanguage);

    }
    private void showLanguageSelectionDialogar() {
        String selectedLanguage = "ar";
        changeLanguage(selectedLanguage);
        switchScreenToRTL(selectedLanguage);
    }
    private void switchScreenToRTL(String languageCode) {
        if (languageCode.equals("ar")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                // Set the layout direction to RTL
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }
    }
    private void switchScreenToLTR(String languageCode) {
        if (languageCode.equals("eng") || languageCode.equals("fr")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                // Set the layout direction to RTL
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }
    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Restart the activity or recreate relevant views
        recreate();
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