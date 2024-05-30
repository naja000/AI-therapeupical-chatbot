package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class sendrating extends AppCompatActivity {
    RatingBar e1;
    Button b1;
    SharedPreferences sh;
    String url,rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendrating);
        e1=findViewById(R.id.ratingBar);
        b1=findViewById(R.id.button7);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rating = String.valueOf(e1.getRating());

                if (rating.equalsIgnoreCase("0.0")) {
                    Toast.makeText(sendrating.this, "Please enter the rating", Toast.LENGTH_SHORT).show();
                } else {



                    RequestQueue queue = Volley.newRequestQueue(sendrating.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/sendrating1";

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


//                                /////////////////////////////////////
//                                String lid = json.getString("id");
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();
//                                /////////////////////////////////////
                                    Intent ik = new Intent(getApplicationContext(), home.class);
                                    startActivity(ik);
                                    Toast.makeText(sendrating.this, "Rating send!!", Toast.LENGTH_SHORT).show();


                                } else {

                                    Toast.makeText(sendrating.this, "Not Send!!", Toast.LENGTH_SHORT).show();

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
                            params.put("rating", rating);


                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),home.class);
        startActivity(ii);
    }
}