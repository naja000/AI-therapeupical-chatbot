package com.example.psychiatrist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class viewpsy extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView l1;
    EditText e1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> name, age, gender, address, phone, email, image,id;
    String url,PID,url1,pname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpsy);
        l1 = findViewById(R.id.viewpsy);
        e1=findViewById(R.id.editTextTextPersonName18);
        b1=findViewById(R.id.button17);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pname = e1.getText().toString();
                if (pname.equalsIgnoreCase("")) {
                    e1.setError("Enter name");

                } else {


                    url = "http://" + sh.getString("ip", "") + ":5000/viewpsychartist_search";
                    RequestQueue queue = Volley.newRequestQueue(viewpsy.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONArray ar = new JSONArray(response);
//                    Toast.makeText(viewpsy.this, "err123"+response, Toast.LENGTH_SHORT).show();

                                name = new ArrayList<>();
                                image = new ArrayList<>();

                                age = new ArrayList<>();
                                gender = new ArrayList<>();
                                address = new ArrayList<>();
                                phone = new ArrayList<>();
                                email = new ArrayList<>();

                                id = new ArrayList<>();

                                for (int i = 0; i < ar.length(); i++) {
                                    JSONObject jo = ar.getJSONObject(i);
                                    name.add(jo.getString("name"));
                                    image.add(jo.getString("image"));

                                    age.add(jo.getString("age"));
                                    gender.add(jo.getString("gender"));

                                    address.add(jo.getString("address"));
                                    phone.add(jo.getString("phone"));
                                    email.add(jo.getString("email"));
                                    id.add(jo.getString("id"));


                                }

                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);

                                l1.setAdapter(new Custom_viewpsy(viewpsy.this, name, image, age, gender, address, phone, email, id));
//                    l1.setOnItemClickListener(viewuser.this);

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
                                Log.d("=========", e.toString());
                            }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(viewpsy.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("psychartist", pname);

                            return params;
                        }
                    };
                    queue.add(stringRequest);


                }
            }
        });


        url1 = "http://" + sh.getString("ip", "") + ":5000/viewpsychartist";

        RequestQueue queue = Volley.newRequestQueue(viewpsy.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);

                    name = new ArrayList<>();
                    age = new ArrayList<>();
                    gender = new ArrayList<>();
                    address = new ArrayList<>();
                    phone = new ArrayList<>();
                    email = new ArrayList<>();
                    image = new ArrayList<>();


                    id=new ArrayList<>();

                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        name.add(jo.getString("fname")+ " " +jo.getString("lname"));
                        age.add(jo.getString("age"));
                        gender.add(jo.getString("gender"));

                        address.add(jo.getString("place")+ " \n" +jo.getString("post")+ " " +jo.getString("pin"));
                        phone.add(jo.getString("phone"));
                        email.add(jo.getString("email"));
                        image.add(jo.getString("image"));
                        id.add(jo.getString("id"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_viewpsy(viewpsy.this, name, image, age, gender, address, phone, email,id));
                    l1.setOnItemClickListener(viewpsy.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewpsy.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("lid", sh.getString("lid",""));
                return params;
            }
        };
        queue.add(stringRequest);


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        PID=id.get(i);




        AlertDialog.Builder ald=new AlertDialog.Builder(viewpsy.this);
        ald.setTitle("File")
                .setPositiveButton(" view schedule ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try
                        {

                            Intent ii=new Intent(getApplicationContext(),viewschedule.class);
                            ii.putExtra("phyid",PID);
                            startActivity(ii);
//                            SharedPreferences.Editor edp = sh.edit();
//                            edp.putString("oid", oid.get(position));
//                            edp.putString("lid", sh.getString("lid",""));
//                            edp.commit();


                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(" cancel ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

//                        Intent i=new Intent(getApplicationContext(),viewworkassigned.class);
//                        startActivity(i);
                    }
                });

        AlertDialog al=ald.create();
        al.show();

    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),home.class);
        startActivity(ii);
    }
}
