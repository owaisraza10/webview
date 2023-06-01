package alzawaj.europe.marry.dating.dating;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RSSWebViewActivity extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssweb_view);



        // Get the post link from the intent
        String postLink = getIntent().getStringExtra("postLink");

        // Initialize WebView
        webView = findViewById(R.id.RsswebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(postLink);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button press inside the WebView
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}