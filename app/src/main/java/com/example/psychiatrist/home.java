package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {
    Button b1,b2,b4,b5,b6,b9,b10,b11,b12;
    SharedPreferences sh;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        b1=findViewById(R.id.button8);
        b2=findViewById(R.id.button9);
//        b3=findViewById(R.id.button10);
        b4=findViewById(R.id.button11);
        b5=findViewById(R.id.button12);
        b6=findViewById(R.id.button13);
//        b7=findViewById(R.id.button14);
//        b8=findViewById(R.id.button15);
        b9=findViewById(R.id.button16);
        b10=findViewById(R.id.button18);
        b11=findViewById(R.id.button20);
        b12=findViewById(R.id.button15);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),uploadpost.class);
                startActivity(i);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewpostncomments.class);
                startActivity(i);


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewpsy.class);
                startActivity(i);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),chat.class);
                startActivity(i);


            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                RequestQueue queue = Volley.newRequestQueue(home.this);
                url = "http://" + sh.getString("ip", "") + ":5000/delete_chatbot";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {


//
                                Intent i=new Intent(getApplicationContext(),chatbot.class);
                                startActivity(i);
                                Toast.makeText(home.this, "deleted!!", Toast.LENGTH_SHORT).show();


                            } else if (res.equalsIgnoreCase("ok")) {
                                Intent i=new Intent(getApplicationContext(),chatbot.class);
                                startActivity(i);
                                Toast.makeText(home.this, "welcome ", Toast.LENGTH_SHORT).show();



                            } else {

                                Intent i=new Intent(getApplicationContext(),chatbot.class);
                                startActivity(i);
                                Toast.makeText(home.this, "welcome!", Toast.LENGTH_SHORT).show();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("lid", sh.getString("lid", ""));
//                        params.put("rating", rating);


                        return params;
                    }
                };
                queue.add(stringRequest);
//                Intent i=new Intent(getApplicationContext(),chatbot.class);
//                startActivity(i);


            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),sendcomplaint.class);
                startActivity(i);


            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),sendrating.class);
                startActivity(i);

            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login1.class);
                startActivity(i);

            }
        });b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewbooking.class);
                startActivity(i);

            }
        });

    }
}