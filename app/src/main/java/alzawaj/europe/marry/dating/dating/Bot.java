package alzawaj.europe.marry.dating.dating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

public class Bot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);
    }
}