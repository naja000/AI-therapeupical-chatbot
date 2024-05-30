package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class viewcmmntss extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    String url;
    ArrayList<String> comments,id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcmmntss);
        l1=findViewById(R.id.viewcommentss);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        url ="http://"+sh.getString("ip", "") + ":5000/viewcomments";
        RequestQueue queue = Volley.newRequestQueue(viewcmmntss.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewcmmntss.this, ""+response, Toast.LENGTH_SHORT).show();

                    comments= new ArrayList<>();
                    id= new ArrayList<>();
//
//                    photo=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        comments.add(jo.getString("user")+" "+jo.getString("lname")+":"+"   "+jo.getString("comment"));

                        id.add(jo.getString("id"));




                    }

                     ArrayAdapter<String> ad=new ArrayAdapter<>(viewcmmntss.this,android.R.layout.simple_list_item_1,comments);
                    l1.setAdapter(ad);

//                    l1.setAdapter(new Custom_viewtips(viewtips.this,tip,details));
//                    l1.setOnItemClickListener(viewworkassigned.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewcmmntss.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pid",sh.getString("pid",""));
                return params;
            }
        };
        queue.add(stringRequest);




    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),uploadpost.class);
        startActivity(ii);
    }
}