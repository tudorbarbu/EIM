package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EditText> extra = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null) {
            String phone = intent.getStringExtra("com.example.myapplication.PHONE_NUMBER_KEY");
            if (phone != null) {
                ((EditText)findViewById(R.id.phone)).setText(phone);
            } else {
                Toast.makeText(this, "Error <3", Toast.LENGTH_LONG).show();
            }
        }

        extra.add((EditText) findViewById(R.id.job));
        extra.add((EditText) findViewById(R.id.company));
        extra.add((EditText) findViewById(R.id.website));
        extra.add((EditText) findViewById(R.id.im));

        for (EditText e: extra) {
            e.setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.addition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(extra.get(0).getVisibility() == View.INVISIBLE) {
                    for (EditText e : extra) {
                        e.setVisibility(View.VISIBLE);
                    }
                    ((Button)findViewById(R.id.addition)).setText("Hide Additonal");
                }
                else {
                    for (EditText e : extra) {
                        e.setVisibility(View.INVISIBLE);
                    }
                    ((Button)findViewById(R.id.addition)).setText("Show Additonal");
                }
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.name)).getText().toString();
                String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
                String email = ((EditText)findViewById(R.id.email)).getText().toString();
                String address = ((EditText)findViewById(R.id.address)).getText().toString();
                String jobTitle = ((EditText)findViewById(R.id.job)).getText().toString();
                String company = ((EditText)findViewById(R.id.company)).getText().toString();
                String website = ((EditText)findViewById(R.id.website)).getText().toString();
                String im = ((EditText)findViewById(R.id.im)).getText().toString();

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivityForResult(intent, 4);
            }
        });


        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case 4:
                setResult(resultCode, new Intent());
                finish();
                break;
        }
    }
}
