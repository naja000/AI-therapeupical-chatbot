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

public class sendcomplaint extends AppCompatActivity {
    EditText e1;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    String complaint,url,url1;
    ArrayList<String> complaints,date,reply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendcomplaint);
        e1=findViewById(R.id.editTextTextPersonName15);
        b1=findViewById(R.id.button6);
//        b2=findViewById(R.id.button14);
        l1=findViewById(R.id.viewreply);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



//

        url1 ="http://"+sh.getString("ip", "") + ":5000/viewreply";
        RequestQueue queue = Volley.newRequestQueue(sendcomplaint.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
//                    Toast.makeText(view_property.this, response+"", Toast.LENGTH_SHORT).show();

                    JSONArray ar=new JSONArray(response);
                    complaints= new ArrayList<>();
                    date=new ArrayList<>();

                    reply= new ArrayList<>();
//                    Address= new ArrayList<>();
//                    phone=new ArrayList<>();
//
//                    email=new ArrayList<>();
//                    details=new ArrayList<>();
//                    oname= new ArrayList<>();

//                    phone=new ArrayList<>();
//                    email=new ArrayList<>();
//                    owid=new ArrayList<>();




                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
//                        name.add(jo.getString("fname")+ "" +jo.getString("lname"));
                        complaints.add(jo.getString("complaint"));

                        date.add(jo.getString("date"));
//                        Address.add(jo.getString("place")+ " \n "  + jo.getString("pin")+ " \n " +jo.getString("post"));
                        reply.add(jo.getString("reply"));
//                        email.add(jo.getString("email"));


//                        details.add(jo.getString("details"));
//                        oname.add(jo.getString("name"));
//                        phone.add(jo.getString("phone"));
//                        email.add(jo.getString("email"));
//                        owid.add(jo.getString("owner_id"));




//


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_viewreply(sendcomplaint.this,complaints,date,reply));
//                    l1.setOnItemClickListener(view_notification.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(sendcomplaint.this, "err"+error, Toast.LENGTH_SHORT).show();
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






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaint = e1.getText().toString();


                if (complaint.equalsIgnoreCase("")) {
                    e1.setError("Enter your complaint");

                } else {

                    RequestQueue queue = Volley.newRequestQueue(sendcomplaint.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/sendcomplaint";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
//                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");
//                            Toast.makeText(Login.this, sh.getString("ip","")+"", Toast.LENGTH_SHORT).show();


                                if (res.equalsIgnoreCase("valid")) {

//

                                    Intent ik = new Intent(getApplicationContext(), sendcomplaint.class);
                                    startActivity(ik);
                                    Toast.makeText(sendcomplaint.this, "Sended successfully", Toast.LENGTH_SHORT).show();

//

                                } else {

                                    Toast.makeText(sendcomplaint.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("complaint", complaint);
                            params.put("lid", sh.getString("lid", ""));

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