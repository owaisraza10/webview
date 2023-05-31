package alzawaj.europe.marry.dating.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);

        setContentView(R.layout.activity_main);
        requestPermissions(permissions,80);


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




        //BOTTOM NAVIGATION

        bottomNavigationView = findViewById(R.id.bottom_nav);
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
                         startActivity(new Intent(getApplicationContext(), BotActivity.class));


                         return true;

                 }
                 return false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //TOOLBAR ITEM
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.share:             //Share Button
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Check Out This Cool Application");
                intent.putExtra(Intent.EXTRA_TEXT,"https://bit.ly/zawaj_moqem_europe");
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
                getSupportFragmentManager().beginTransaction().replace(R.id.container,rssFragment).commit();
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
    }
    private void showLanguageSelectionDialogar() {
        String selectedLanguage = "ar";
        changeLanguage(selectedLanguage);
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


