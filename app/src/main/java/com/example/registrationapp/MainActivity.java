package com.example.registrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.fee);
        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void insert() {
        try {
            String name = ed1.getText().toString();
            String course = ed2.getText().toString();
            String fee = ed3.getText().toString();

            Server.request("insert:"+name+":"+course+":"+fee);
            Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record failed", Toast.LENGTH_LONG).show();
        }
    }
}
