package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class viewpostncomments extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> user,image,post,details,id,c;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpostncomments);
        l1=findViewById(R.id.viewpostncomment);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/viewpostncomment";
        RequestQueue queue = Volley.newRequestQueue(viewpostncomments.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewpostncomments.this, "welcome"+response, Toast.LENGTH_SHORT).show();

                    user= new ArrayList<>();
                    post= new ArrayList<>();
                    image= new ArrayList<>();
                    details= new ArrayList<>();
                    id= new ArrayList<>();
                    c= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        user.add(jo.getString("user"));
                        post.add(jo.getString("image"));
                        image.add(jo.getString("post"));
                        details.add(jo.getString("details"));
                        id.add(jo.getString("id"));
                        c.add(jo.getString("c"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custompost(viewpostncomments.this,user,post,details,image,id,c));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewpostncomments.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));

                return params;
            }
        };
        queue.add(stringRequest);

    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),home.class);
        startActivity(ii);
    }
}