package alzawaj.europe.marry.dating.dating;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class AcceptTermsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PREF_ACCEPTED = "termsAccepted";

    private CheckBox checkBoxAccept;
    private Button btnProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_terms);

        checkBoxAccept = findViewById(R.id.checkBoxAccept);
        btnProceed = findViewById(R.id.btnProceed);

        // Load the flag from SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean accepted = settings.getBoolean(PREF_ACCEPTED, false);

        // If the terms were already accepted, mark the checkbox as checked and enable the button
        if (accepted) {
            checkBoxAccept.setChecked(true);
            btnProceed.setEnabled(true);
        }

        checkBoxAccept.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Enable or disable the button based on checkbox state
            btnProceed.setEnabled(isChecked);
        });

        btnProceed.setOnClickListener(v -> {
            // Store the flag in SharedPreferences when the button is clicked
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(PREF_ACCEPTED, true);
            editor.apply();

            // Proceed to the next activity or perform any necessary actions
            Intent intent = new Intent(AcceptTermsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

            // ...

            finish(); // Close the "Accept Terms" activity
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if the terms were already accepted
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean accepted = settings.getBoolean(PREF_ACCEPTED, false);

        if (accepted) {
            // Terms already accepted, proceed to the next activity or perform any necessary actions
            Intent intent = new Intent(AcceptTermsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();            // ...

            finish(); // Close the "Accept Terms" activity
        }
    }
}
