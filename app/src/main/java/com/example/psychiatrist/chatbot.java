package com.example.psychiatrist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

public class chatbot extends AppCompatActivity {
    EditText e1;

    Button b2;
    SharedPreferences sp;
    LinearLayout lt;
    Handler hd;
    static String prv="";

    String msg="",ans="",ip="",url="",lid="",url2="",id="";

    public static ArrayList<PackageInfo> pkgeList;
    ArrayList<String>msgg,date,from_id,toid;

    ProgressDialog mProgressDialog;
    private PowerManager.WakeLock mWakeLock;
    static final int DIALOG_DOWNLOAD_PROGRESS = 2;
    String mgg="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

//        b1=findViewById(R.id.button15);

//        b1.setOnClickListener();



        e1 = (EditText) findViewById(R.id.editText1);//message
//        v=(TextView)findViewById(R.id.textView8);//answer
//        b1=(Button)findViewById(R.id.button4);//speach
        b2=(Button)findViewById(R.id.button1);//send
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lt=(LinearLayout)findViewById(R.id.linear1);


        hd=new Handler();
        hd.post(r);


        //The following 3 lines are needed in every onCreate method of a ListeningActivit
        ip=sp.getString("ip","");
        id=sp.getString("lid","");



        try
        {
            if(android.os.Build.VERSION.SDK_INT>9)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        }
        catch(Exception e)
        {

        }



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg=e1.getText().toString();
                if(msg.equals(""))
                {
                    e1.setError("enter message");
                }
                else
                {
                    final  RequestQueue queue = Volley.newRequestQueue(chatbot.this);
                    String url ="http://"+ip+":5000/insertchatbot";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++",response);
                            try {
                                JSONObject json=new JSONObject(response);
                                String res=json.getString("task");


                                //pDialog.hide
                                if(res.equals("ok")) {
                                    e1.setText("");

                                    queue.stop();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            // pDialog.hide();
//                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("lid", sp.getString("lid",""));
                            params.put("msg",msg);

                            return params;
                        }
                    };
                    stringRequest.setRetryPolicy( new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(stringRequest);
                }

            }
        });


    }
    @Override
    public void onBackPressed(){
//        stopListening();
        e1.setText("");
        Intent i=new Intent(getApplicationContext(),home.class);
        startActivity(i);
    }



    public Runnable r=new Runnable() {

        @Override
        public void run() {

            url2="http://"+sp.getString("ip", "")+":5000/response";
            final RequestQueue mqueu=Volley.newRequestQueue(chatbot.this);

            StringRequest string=new StringRequest(Request.Method.POST,url2,
                    new Response.Listener<String>() {
                        public void onResponse(String respo)

                        {
//                            Toast.makeText(chatbot.this, ""+respo, Toast.LENGTH_SHORT).show();
                            try {
                                JSONArray arr = new JSONArray(respo);
                                if(respo.length() > 0){


                                    from_id=new ArrayList<String>();
                                    toid=new ArrayList<String>();
                                    msgg=new ArrayList<String>();
                                    date=new ArrayList<String>();

                                    lt.removeAllViews();
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject c = arr.getJSONObject(i);

                                        from_id.add(c.getString("fromid"));
                                        toid.add(c.getString("toid"));
                                        msgg.add(c.getString("msg"));
                                        date.add(c.getString("date"));

                                        TextView tv=new TextView(getApplicationContext());
                                        TextView tv1=new TextView(getApplicationContext());


//                                        if(!c.getString("date").equals(prv))
//                                        {
                                        //Toast.makeText(getApplicationContext(), "result is"+prv, Toast.LENGTH_LONG).show();
//                                            tv1.setText(c.getString("date"));
                                        tv1.setGravity(Gravity.CENTER);
                                        tv1.setTextColor(Color.BLACK);
                                        tv1.setBackgroundColor(Color.YELLOW);
//                                            prv=c.getString("date");
//                                        }

                                        if(from_id.get(i).equalsIgnoreCase(id)){


                                            tv.setTextColor(Color.BLACK);
                                            tv.setText("Me"+": "+msgg.get(i));
                                            tv.setTextSize(20);
                                            tv.setGravity(Gravity.RIGHT);

                                            tv.setBackgroundColor(Color.WHITE);

                                            //tv1.setTextColor(Color.RED);
                                            //tv1.setText(date.get(i)+"");


                                            tv1.setBackgroundColor(Color.WHITE);

                                        }
                                        else{
                                            tv.setTextColor(Color.BLACK);
                                            tv.setGravity(Gravity.LEFT);
                                            tv.setTextSize(20);
                                            tv.setBackgroundColor(Color.LTGRAY);
                                            //tv1.setTextColor(Color.BLACK);
                                            //tv1.setText(date.get(i));
                                            //tv1.setGravity(Gravity.CENTER);
                                            tv1.setBackgroundColor(Color.LTGRAY);
                                            mgg=msgg.get(i);
                                            try {
//                                                String aa[] = mgg.split(".");
//                                                Integer len = aa.length;
//                                                String ext = aa[len - 1];
//                                                if (ext == ".pdf" || ext == ".doc" || ext == ".txt") {

                                                tv.setText("  "+msgg.get(i));


//                                                } else {
//                                                    tv.setText(msgg.get(i));
//                                                }
                                            }
                                            catch (Exception e)
                                            {

                                            }

                                        }

                                        lt.addView(tv);
                                        lt.addView(tv1);


                                    }

                                }
                            }
                            catch (JSONException e) {
                                // TODO Auto-generated catch block
                                Toast.makeText(getApplicationContext(), "err"+e, Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                            hd.postDelayed(r, 2000);
                        }
                    }

                    , new Response.ErrorListener() {
                public void  onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(), "err"+error, Toast.LENGTH_LONG).show();
                }
            })

            {
                protected Map<String,String> getParams() throws AuthFailureError
                {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("lid", sp.getString("lid",""));

                    return params;
                }
            }
                    ;
            mqueu.add(string);

        }

    };






}

