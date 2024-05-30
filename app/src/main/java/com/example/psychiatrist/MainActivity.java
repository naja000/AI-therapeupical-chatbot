package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button b1;
    SharedPreferences sh;
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editTextTextPersonName);
        b1=findViewById(R.id.button);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ip=e1.getText().toString();
                if (ip.equalsIgnoreCase("")) {
                    e1.setError("Enter IP Address");

                } else {

                    SharedPreferences.Editor edp = sh.edit();
                    edp.putString("ip", ip);
                    edp.commit();

                    Intent i = new Intent(getApplicationContext(), Login1.class);
                    startActivity(i);
                    Toast.makeText(MainActivity.this, ip + "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}