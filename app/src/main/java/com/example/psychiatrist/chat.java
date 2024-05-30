package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class chat extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> id,psy,image;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        l1=findViewById(R.id.chat);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/chatwithpsy";
        RequestQueue queue = Volley.newRequestQueue(chat.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    id=new ArrayList<>();
                    psy=new ArrayList<>();
                    image=new ArrayList<>();




                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        id.add(jo.getString("id"));
                        psy.add(jo.getString("name"));
                        image.add(jo.getString("image"));





                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(chat.this,android.R.layout.simple_list_item_1,psy);
//                    l1.setAdapter(ad);

                    l1.setAdapter(new Custom_chat(chat.this,id,image,psy));

//                    s1.setOnItemSelectedListener(sendcomplaint.this);
                    l1.setOnItemClickListener(chat.this);




                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chat.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent ik = new Intent(getApplicationContext(), Test.class);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("uid", id.get(i));
        edp.putString("name", psy.get(i));
        edp.commit();
        startActivity(ik);

    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),home.class);
        startActivity(ii);
    }
}