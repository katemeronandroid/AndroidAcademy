package com.emarkova.androidacademymybusinesscard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String email = "katemeron@mail.ru";
    private static final String subject = "Business Card Message";
    private static final String uriEmail = "mailto:";
    private static final String uriTelegram = "http://telegram.me/djisachan";
    private static final String uriVK = "http://vk.com/katrin_markova";
    private static final String uriInstagram = "http://instagram.com/_u/djisachan";
    private static final String appTelegram = "org.telegram.messenger";
    private static final String appVK = "com.vkontakte.android";
    private static final String appInstagram = "com.instagram.android";
    private EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_main);
        init();
    }

    private void init() {
        messageText = findViewById(R.id.messageEditText);
        Button sendButton = findViewById(R.id.sendButton);
        ImageView telegramImageView = findViewById(R.id.telegramImageView);
        ImageView vkImageView = findViewById(R.id.vkImageView);
        ImageView instaImageView = findViewById(R.id.instaImageView);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(messageText.getText().length() != 0){
                    sendEmail(messageText.getText().toString());
                }
            }
        });
        telegramImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialLink(uriTelegram, appTelegram);
            }
        });
        vkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialLink(uriVK, appVK);
            }
        });
        instaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialLink(uriInstagram, appInstagram);
            }
        });
    }

    private void sendEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(uriEmail));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {

            Toast.makeText(MainActivity.this, R.string.emailapp_not_found, Toast.LENGTH_LONG).show();
        }
    }

    private void openSocialLink(String uri, String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        intent.setPackage(packageName);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, R.string.app_not_found, Toast.LENGTH_LONG).show();
        }
    }
}
