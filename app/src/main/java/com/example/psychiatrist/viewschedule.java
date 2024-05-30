package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewschedule extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> date,fromtime,totime,id;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewschedule);
        l1=findViewById(R.id.viewschedule);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/viewschedule";
        RequestQueue queue = Volley.newRequestQueue(viewschedule.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewschedule.this, "welcome"+response, Toast.LENGTH_SHORT).show();

                    date= new ArrayList<>();
                    fromtime= new ArrayList<>();
                    totime= new ArrayList<>();
                    id= new ArrayList<>();
//
//
//                    photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        date.add(jo.getString("date"));
                        fromtime.add(jo.getString("fromtime"));
                        totime.add(jo.getString("totime"));
                        id.add(jo.getString("id"));




                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_viewschedule(viewschedule.this,date,fromtime,totime,id));
//                    l1.setOnItemClickListener(viewworkassigned.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewschedule.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pyid",getIntent().getStringExtra("pyid"));
                return params;
            }
        };
        queue.add(stringRequest);


    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),viewpsy.class);
        startActivity(ii);
    }
}