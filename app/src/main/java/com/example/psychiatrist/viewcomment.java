package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewcomment extends AppCompatActivity {
    ListView l1;
    EditText e1;
    Button b1;
    SharedPreferences sh;
    String url,url1,comment;
    ArrayList<String> comments,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomment);
        e1=findViewById(R.id.editTextTextPersonName20);
        b1=findViewById(R.id.button25);
        l1=findViewById(R.id.viewcommentsss);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        url1 ="http://"+sh.getString("ip", "") + ":5000/viewcomments1";
        RequestQueue queue = Volley.newRequestQueue(viewcomment.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
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

                    ArrayAdapter<String> ad=new ArrayAdapter<>(viewcomment.this,android.R.layout.simple_list_item_1,comments);
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

                Toast.makeText(viewcomment.this, "err"+error, Toast.LENGTH_SHORT).show();
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



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment = e1.getText().toString();


                if (comment.equalsIgnoreCase("")) {
                    Toast.makeText(viewcomment.this, "Please enter the comments", Toast.LENGTH_SHORT).show();
                } else {

//                Intent i=new Intent(getApplicationContext(),home.class);
//                startActivity(i);

                    RequestQueue queue = Volley.newRequestQueue(viewcomment.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/sendcomment";

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
                                    Intent ik = new Intent(getApplicationContext(), viewpostncomments.class);
                                    startActivity(ik);
                                    Toast.makeText(viewcomment.this, "Comment send!!", Toast.LENGTH_SHORT).show();


                                } else {

                                    Toast.makeText(viewcomment.this, "Not Send!!", Toast.LENGTH_SHORT).show();

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
                            params.put("pid", sh.getString("pid", ""));
                            params.put("comment", comment);


                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }
        });


//        url ="http://"+sh.getString("ip", "") + ":5000/viewcomment";
//        RequestQueue queue = Volley.newRequestQueue(viewcomment.this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // Display the response string.
//                Log.d("+++++++++++++++++",response);
//                try {
//
//                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewcomment.this, "welcome"+response, Toast.LENGTH_SHORT).show();
//
//                    post= new ArrayList<>();
////                    post= new ArrayList<>();
//                    comment= new ArrayList<>();
//
//
//                    for(int i=0;i<ar.length();i++)
//                    {
//                        JSONObject jo=ar.getJSONObject(i);
//                        post.add(jo.getString("user"));
////                        post.add(jo.getString("post"));
//                        comment.add(jo.getString("comment"));
//
//
//
//                    }
//
//                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
//                    //lv.setAdapter(ad);
//
//                    l1.setAdapter(new Custom_viewcomment(viewcomment.this,post,comment));
////                    l1.setOnItemClickListener(viewuser.this);
//
//                } catch (Exception e) {
//                    Log.d("=========", e.toString());
//                }
//
//
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(viewcomment.this, "err"+error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("pid",getIntent().getStringExtra("pid"));
//
//                return params;
//            }
//        };
//        queue.add(stringRequest);


    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),viewpostncomments.class);
        startActivity(ii);
    }
}