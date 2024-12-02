package com.example.practiceset3;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText emailAddress, editSubject, editContent;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        emailAddress = findViewById(R.id.emailAddress);
        editSubject = findViewById(R.id.editSubject);
        editContent = findViewById(R.id.editContent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to_email, subject, content;
                to_email = emailAddress.getText().toString();
                subject = editSubject.getText().toString();
                content = editContent.getText().toString();
                if(to_email.isEmpty() || subject.isEmpty() || content.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendEmail(to_email, subject, content);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void sendEmail(String to_email, String subject, String content){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose email client"));

    }
}