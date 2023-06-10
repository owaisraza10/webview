package alzawaj.europe.marry.dating.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.AdSize;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import alzawaj.europe.marry.dating.dating.ads.Admob;

public class MainActivity extends AppCompatActivity {
    private Dialog exitDialog;



private AdView madview;

private com.facebook.ads.AdView adView;
//    private static final String RSS_URL = "https://mantowf.com/feed/";


    private static final String CHANNEL_ID = "Notification Channel";



    private AlarmManager alarmManager;
    private PendingIntent notificationIntent;







    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String[] permissions={"android.permission.RECORD_AUDIO",
            "android.permission.POST_NOTIFICATIONS",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.CAMERA",
            "android.permission.READ_EXTERNAL_STORAGE"};

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NewsFragment newsFragment = new NewsFragment();
    ServicesFragment servicesFragment = new ServicesFragment();
    VoiceFragment voiceFragment = new VoiceFragment();
    RssFragment rssFragment = new RssFragment();
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    Fragment3 fragment3 = new Fragment3();
    Fragment4 fragment4 = new Fragment4();

    Fragment5 fragment5 = new Fragment5();

    Fragment6 fragment6 = new Fragment6();

    Fragment7 fragment7 = new Fragment7();

    Fragment8 fragment8 = new Fragment8();

    Fragment9 fragment9 = new Fragment9();

    Fragment10 fragment10 = new Fragment10();

    Fragment11 fragment11 = new Fragment11();

    Fragment12 fragment12 = new Fragment12();

    Fragment13 fragment13 = new Fragment13();
    FollowFragment followFragment = new FollowFragment();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);

        setContentView(R.layout.activity_main);
        requestPermissions(permissions,80);

        //RSS NOTIFICATION
//        new RssNotificationService(this, RSS_URL).execute();

        //PUSH NOTIFICATION
        FirebaseMessaging.getInstance().subscribeToTopic("notification");

        createNotificationChannel();

        NotificationScheduler notificationScheduler = new NotificationScheduler(this);
        notificationScheduler.startNotifications();



        //TOOLBAR

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //SIDEMENU

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //BANNER AD

        madview = findViewById(R.id.adView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);

        //INTERSTITIAL AD

//        loadInterstitialAd();


        //BANNER AD FACEBOOK
        adView = new com.facebook.ads.AdView(this, "630513408967139_630517158966764", AdSize.BANNER_HEIGHT_50);
        LinearLayout bannerContainer = findViewById(R.id.banner_container);
        bannerContainer.addView(adView);
        adView.loadAd();



        //ADMOB APP OPEN
        Admob.showOpen(MainActivity.this);

        //BOTTOM NAVIGATION

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemIconSize(80);
         getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

         bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected( MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.home:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                         return true;
                     case R.id.news:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
                         overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);

                         return true;
                     case R.id.voice:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,voiceFragment).commit();
                         overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);

                         return true;
                     case R.id.services:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,servicesFragment).commit();
                         overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);

                         return true;
                     case R.id.AI:
                         startActivity(new Intent(MainActivity.this,BotActivity.class));
                         return true;
                     default:
                         return false;
                 }

             }
         });

         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id = item.getItemId();
                 drawerLayout.closeDrawer(GravityCompat.START);
                 switch (id){
                     case R.id.item1:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

                         return true;
                     case R.id.item2:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();

                         return true;
                     case R.id.item3:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();

                         return true;
                     case R.id.item4:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment4).commit();

                         return true;

                     case R.id.item5:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment5).commit();

                         return true;
                     case R.id.item6:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment6).commit();

                         return true;
                     case R.id.item7:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment7).commit();

                         return true;
                     case R.id.item8:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment8).commit();

                         return true;
                     case R.id.item9:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment9).commit();

                         return true;
                     case R.id.item10:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment10).commit();

                         return true;
                     case R.id.item11:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment11).commit();

                         return true;
                     case R.id.item12:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment12).commit();

                         return true;
                     case R.id.item13:
                         getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment13).commit();

                         return true;
                 }
                 return false;
             }
         });

    }




    //RANDOM NOTIFICATIONS

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Notification Channel";
            String channelDescription = "Notification_Channel_for_moqem_App";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);

            // Set vibration pattern
            long[] vibrationPattern = {0, 250, 250, 250};
            channel.enableVibration(true);
            channel.setVibrationPattern(vibrationPattern);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //INTERSTITIAL AD



    //EXIT AD

    private void showExitDialog() {
        exitDialog = new Dialog(this);
        exitDialog.setContentView(R.layout.dialog_alert);

        // Load native ad
        AdLoader adLoader = new AdLoader.Builder(this, getString(R.string.admob_native_ad_unit_id))
                .forNativeAd(nativeAd -> {
                    // Populate your custom exit dialog UI with the native ad data
                    populateNativeAd(exitDialog, nativeAd);
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        // Handle ad loading failure
                    }

                    @Override
                    public void onAdClicked() {
                        // Handle ad click event
                        // Add your desired behavior here
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder().build())
                .build();

        AdRequest adRequest = new AdRequest.Builder().build();
        adLoader.loadAd(adRequest);

        exitDialog.show();
    }


    private void populateNativeAd(Dialog exitDialog, NativeAd nativeAd) {
        // Populate your custom exit dialog UI with the native ad data
        // For example, you can access the layout elements from `exitDialog` and set the native ad's assets

        // Example:
        ImageView adImageView = exitDialog.findViewById(R.id.adImageView);
        TextView headlineTextView = exitDialog.findViewById(R.id.headlineTextView);
        TextView exit = exitDialog.findViewById(R.id.dialog_exit);
        TextView cancel = exitDialog.findViewById(R.id.dialog_cancel);

        // Set click listener for the "Yes" text
        exit.setOnClickListener(v -> {
            // Kill the app
            finishAffinity();
        });

        // Set click listener for the "No" text
        cancel.setOnClickListener(v -> {
            // Dismiss the dialog
            exitDialog.dismiss();
        });


        headlineTextView.setText(nativeAd.getHeadline());

        // Load the ad's image into the ImageView using a library like Picasso or Glide
        Picasso.get().load(nativeAd.getImages().get(0).getUri()).into(adImageView);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }



//    @Override
//    public void onBackPressed() {
//        String exitMessage = getString(R.string.exit);
//
//        new AlertDialog.Builder(this)
//                .setMessage(exitMessage)
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finishAffinity();
//                        System.exit(0);
//                    }
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //TOOLBAR ITEM
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String sharemsg = getString(R.string.sharemsg);

        switch (item.getItemId()){
            case R.id.share:             //Share Button
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.putExtra(Intent.EXTRA_SUBJECT,"hi , joing me and download this cool app , im online there\n" +
                        "\n" +
                        "\n");
                intent.putExtra(Intent.EXTRA_TEXT,sharemsg);
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
                startActivity(new Intent(MainActivity.this,RssActivity.class));
            case R.id.follow:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,followFragment).commit();

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

}


