package com.example.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (TextView)findViewById(R.id.number);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.B0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "0");
            }
        });

        findViewById(R.id.B1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "1");
            }
        });

        findViewById(R.id.B2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "2");
            }
        });

        findViewById(R.id.B3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "3");
            }
        });

        findViewById(R.id.B4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "4");
            }
        });

        findViewById(R.id.B5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "5");
            }
        });

        findViewById(R.id.B6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "6");
            }
        });

        findViewById(R.id.B7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "7");
            }
        });

        findViewById(R.id.B8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "8");
            }
        });

        findViewById(R.id.B9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "9");
            }
        });


        findViewById(R.id.Bstar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "*");
            }
        });

        findViewById(R.id.Bhash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText() + "#");
            }
        });

        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setText(number.getText().toString().substring(0, number.getText().toString().length() - 1));
            }
        });
    }
}
