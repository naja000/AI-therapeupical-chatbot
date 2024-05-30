package com.example.psychiatrist;

import androidx.appcompat.app.AppCompatActivity;

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

public class viewbooking extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> schedule,psy,status,time1,time2,date;
    String url1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbooking);
        l1=findViewById(R.id.viewbooking);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url1 ="http://"+sh.getString("ip", "") + ":5000/viewbooking";
        RequestQueue queue = Volley.newRequestQueue(viewbooking.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
//                    Toast.makeText(view_property.this, response+"", Toast.LENGTH_SHORT).show();

                    JSONArray ar=new JSONArray(response);
                    schedule= new ArrayList<>();
                    psy= new ArrayList<>();
                    status= new ArrayList<>();
                    date=new ArrayList<>();
                    time1=new ArrayList<>();
                    time2=new ArrayList<>();

//



                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
//                        name.add(jo.getString("fname")+ "" +jo.getString("lname"));
                        schedule.add(jo.getString("schedule"));
                        psy.add(jo.getString("psy"));
                        status.add(jo.getString("status"));
                        date.add(jo.getString("date"));
                        time1.add(jo.getString("time1"));
                        time2.add(jo.getString("time2"));






//


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_viewbooking(viewbooking.this,psy,schedule,date,time1,time2,status));
//                    l1.setOnItemClickListener(view_notification.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewbooking.this, "err"+error, Toast.LENGTH_SHORT).show();
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
}