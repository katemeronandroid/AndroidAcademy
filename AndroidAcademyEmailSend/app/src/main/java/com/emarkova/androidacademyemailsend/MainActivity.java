package com.emarkova.androidacademyemailsend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_EMAIL = "email_address";
    private static final String KEY_MESSAGE = "message";
    private EditText emailTo;
    private EditText messageText;
    private Button buttonPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_MESSAGE, messageText.getText().toString());
        outState.putString(KEY_EMAIL,emailTo.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        emailTo.setText(savedInstanceState.getString(KEY_EMAIL));
        messageText.setText(savedInstanceState.getString(KEY_MESSAGE));
    }

    private void init(Bundle savedInstanceState) {
        emailTo = findViewById(R.id.editTextEmail);
        messageText = findViewById(R.id.editTextMessage);
        if(savedInstanceState != null) {
            emailTo.setText(savedInstanceState.getString(KEY_EMAIL));
            messageText.setText(savedInstanceState.getString(KEY_MESSAGE));
        }
        buttonPreview = findViewById(R.id.buttonPreview);
        buttonPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreview();
            }
        });
    }

    private void openPreview() {
        if(messageText.getText().length() != 0){
            MessageActivity.start(MainActivity.this, emailTo.getText().toString(), messageText.getText().toString());
        }
    }

}
