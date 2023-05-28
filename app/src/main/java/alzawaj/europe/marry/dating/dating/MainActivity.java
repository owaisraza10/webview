package alzawaj.europe.marry.dating.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    String[] permissions={"android.permission.RECORD_AUDIO"};
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NewsFragment newsFragment = new NewsFragment();
    ServicesFragment servicesFragment = new ServicesFragment();
    VoiceFragment voiceFragment = new VoiceFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);

        setContentView(R.layout.activity_main);
        requestPermissions(permissions,80);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);







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
                         startActivity(new Intent(getApplicationContext(), Bot.class));


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
            case R.id.settings:
                Toast.makeText(this, "Settings Is Selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}