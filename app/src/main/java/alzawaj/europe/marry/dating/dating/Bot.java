package alzawaj.europe.marry.dating.dating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bot extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView welcomeText;
    EditText messageEditText;
    ImageButton sendBtn;
    List<Message> messageList;
    MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        messageList = new ArrayList<>();

        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);

        recyclerView = findViewById(R.id.recycler_view);
        welcomeText = findViewById(R.id.welcome_text);
        messageEditText = findViewById(R.id.message_edit_text);
        sendBtn = findViewById(R.id.send_btn);

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        sendBtn.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            Toast.makeText(this,question, Toast.LENGTH_SHORT).show();
        });
    }
}