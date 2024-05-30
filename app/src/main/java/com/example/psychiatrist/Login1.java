   package com.example.psychiatrist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

   public class Login1 extends AppCompatActivity {
       EditText e1,e2;
       Button b1,b2;
       SharedPreferences sh;
       String username,password,url;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_login1);
           e1=findViewById(R.id.username);
           e2=findViewById(R.id.pswd);
           b1=findViewById(R.id.button2);
           b2=findViewById(R.id.registernow);
   //        b3=findViewById(R.id.button7);
   //        b3=findViewById(R.id.button8);

           sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
           b1.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                   username = e1.getText().toString();
                   password = e2.getText().toString();
                   if (username.equalsIgnoreCase("")) {
                       e1.setError("enter your username");
                   } else if (password.equalsIgnoreCase("")) {
                       e2.setError("enter your password");
                   } else
                   {
                       RequestQueue queue = Volley.newRequestQueue(Login1.this);
                       url = "http://" + sh.getString("ip", "") + ":5000/logincode";
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
                                       String lid = json.getString("id");


                                           SharedPreferences.Editor edp = sh.edit();
                                           edp.putString("lid", lid);
                                           edp.putString("t", "user");
                                           edp.commit();

                                           Intent ik = new Intent(getApplicationContext(), home.class);
                                           startActivity(ik);
                                       }

                                   else {

                                       Toast.makeText(Login1.this,"Invalid username or password", Toast.LENGTH_SHORT).show();

                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }

                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {

                               e1.setText(error+"");
                               Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                           }

                       }) {

                           @Override
                           protected Map<String, String> getParams() {
                               Map<String, String> params = new HashMap<String, String>();
                               params.put("uname", username);
                               params.put("pswd", password);
                               return params;
                           }
                       };
                       queue.add(stringRequest);
                   }
               }
           });

           b2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent ik = new Intent(getApplicationContext(), register.class);
                   startActivity(ik);
               }
           });
   //        b3.setOnClickListener(new View.OnClickListener() {
   //            @Override
   //            public void onClick(View view) {
   //                Intent ik = new Intent(getApplicationContext(), drregistration.class);
   //                startActivity(ik);
   //            }
   //        });
       }
   //        b3.setOnClickListener(new View.OnClickListener() {
   //            @Override
   //            public void onClick(View view) {
   //                Intent i=new Intent(getApplicationContext(),worker_reg.class);
   //                startActivity(i);
   //
   //            }
   //        });





       public void onBackPressed() {
           // TODO Auto-generated method stub
           AlertDialog.Builder ald=new AlertDialog.Builder(Login1.this);
           ald.setTitle("Do you want to Exit")
                   .setPositiveButton(" YES ", new DialogInterface.OnClickListener() {

                       @Override
                       public void onClick(DialogInterface arg0, int arg1) {
                           Intent in=new Intent(Intent.ACTION_MAIN);
                           in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           in.addCategory(Intent.CATEGORY_HOME);
                           startActivity(in);
                       }
                   })

                   .setNegativeButton(" NO ", new DialogInterface.OnClickListener() {

                       @Override
                       public void onClick(DialogInterface arg0, int arg1) {

                       }
                   });

           AlertDialog al=ald.create();
           al.show();

       }
   }