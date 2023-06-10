package alzawaj.europe.marry.dating.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BotActivity extends AppCompatActivity {
    private com.facebook.ads.AdView adView;
    private AdView madview;
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NewsFragment newsFragment = new NewsFragment();
    ServicesFragment servicesFragment = new ServicesFragment();
    VoiceFragment voiceFragment = new VoiceFragment();
    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);
        setContentView(R.layout.activity_bot);
        messageList = new ArrayList<>();


        //BANNER AD

        madview = findViewById(R.id.adViewbot);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);

        //BANNER AD FACEBOOK
        adView = new com.facebook.ads.AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        LinearLayout bannerContainer = findViewById(R.id.banner_container2);
        bannerContainer.addView(adView);
        adView.loadAd();

        //Bottom Nav

        bottomNavigationView = findViewById(R.id.bottom_navbot);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemIconSize(80);


        Toolbar toolbar = findViewById(R.id.toolbarbot);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


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



        recyclerView = findViewById(R.id.recycler_view);
        welcomeTextView = findViewById(R.id.welcome_text);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_btn);

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        sendButton.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            addToChat(question,Message.SENT_BY_ME);
            messageEditText.setText("");
            callAPI(question);
            welcomeTextView.setVisibility(View.GONE);
        });
    }



    void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message,sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,Message.SENT_BY_BOT);
    }

    void callAPI(String question){
        //okhttp
        messageList.add(new Message("Typing... ",Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model","gpt-3.5-turbo");

            JSONArray messageArray = new JSONArray();
            JSONObject obj = new JSONObject();
            obj.put("role","user");
            obj.put("content",question);
            messageArray.put(obj);

            jsonBody.put("messages",messageArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions\n" +
                        "\n")
                .header("Authorization","Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject  jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0)
                                        .getJSONObject("message")
                                                .getString("content");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{
                    addResponse("Failed to load response due to "+response.body().toString());
                }
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

        switch (item.getItemId()) {
            case R.id.share:             //Share Button
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                intent.putExtra(Intent.EXTRA_SUBJECT, "hi , joing me and download this cool app , im online there\n" +
                        "\n" +
                        "\n");
                intent.putExtra(Intent.EXTRA_TEXT, "hi , joing me and download this cool app , im online there\n\nhttps://bit.ly/zawaj_moqem_europe");
                startActivity(Intent.createChooser(intent, "Share Via"));


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

        switch (item.getItemId()) {      //Rss Feed Button
            case R.id.rss:
                startActivity(new Intent(BotActivity.this, RssActivity.class));

        }
        return super.onOptionsItemSelected(item);

    }

    //Language Change

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




















