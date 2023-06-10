package alzawaj.europe.marry.dating.dating;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;

public class RSSWebViewActivity extends AppCompatActivity {
    private AdView madview;
    private com.facebook.ads.AdView adView;
    BottomNavigationView bottomNavigationView;
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

        bottomNavigationView = findViewById(R.id.bottom_navweb);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemIconSize(80);

        Toolbar toolbar = findViewById(R.id.toolbarss);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //BANNER AD
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);

        //BANNER AD FACEBOOK
        adView = new com.facebook.ads.AdView(this, "630513408967139_630517158966764", AdSize.BANNER_HEIGHT_50);
        LinearLayout bannerContainer = findViewById(R.id.banner_container);
        bannerContainer.addView(adView);
        adView.loadAd();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.AI:
                        return true;
                    case R.id.news:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        return true;
                    case R.id.voice:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        return true;
                    case R.id.services:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.share:             //Share Button
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.putExtra(Intent.EXTRA_SUBJECT,"hi , joing me and download this cool app , im online there\n" +
                        "\n" +
                        "\n");
                intent.putExtra(Intent.EXTRA_TEXT,"الزواج من مقيم في أوروبا أكثر من 20,000 الف متابع متفاعل معك , مربوطة مع منصة اجتماعية بديلة لفيسبوك واضافة\n" +
                        "ChatGPT مجانية لك.\n" +
                        "كل لطيفا وابحث عن شريك حياتك في بلدك او اوروبا لمستقبل افضل\n" +
                        "أضغط هنا\n" +
                        "https://wp.me/pcG8So-cr5\n" +
                        "او أضغط هنا\n" +
                        "https://googleyoutube.page.link/Zawaj-Europe\n" +
                        "مجانا\n\nhttps://bit.ly/zawaj_moqem_europe");
                startActivity(Intent.createChooser(intent,"Share Via"));


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

        switch (item.getItemId()){      //Rss Feed Button
            case R.id.rss:
                startActivity(new Intent(RSSWebViewActivity.this,RssActivity.class));
            case R.id.follow:
                startActivity(new Intent(RSSWebViewActivity.this,MainActivity.class));
        }


        return super.onOptionsItemSelected(item);
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