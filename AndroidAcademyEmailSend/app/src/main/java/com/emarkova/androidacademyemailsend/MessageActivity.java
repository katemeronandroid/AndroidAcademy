package com.emarkova.androidacademyemailsend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MessageActivity extends AppCompatActivity {
    private static final String KEY_EMAIL = "email_address";
    private static final String KEY_MESSAGE = "message";
    private static final String subject = "Android Academy First";
    private String address = "katemeron@mail.ru";
    private TextView textView;
    private Button buttonSend;

    public static void start(@NotNull Context context, String emailTo, String message) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(KEY_EMAIL, emailTo);
        intent.putExtra(KEY_MESSAGE, message);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_MESSAGE, textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString(KEY_MESSAGE));
    }

    private void init(Bundle savedInstanceState) {
        textView = findViewById(R.id.textViewMessage);
        if(savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(KEY_MESSAGE));
        } else {
            textView.setText(getIntent().getStringExtra(KEY_MESSAGE));
        }
        address = getIntent().getStringExtra(KEY_EMAIL);
        buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String message = textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MessageActivity.this, R.string.emailapp_not_found, Toast.LENGTH_LONG).show();
        }
    }
}
