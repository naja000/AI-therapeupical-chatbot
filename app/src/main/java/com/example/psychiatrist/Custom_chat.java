package com.example.psychiatrist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_chat extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
//    ArrayList<String> d;
//    ArrayList<String> e;
//    ArrayList<String> f;
//    ArrayList<String> g;

    SharedPreferences sh;
    String url;



    public Custom_chat(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
//        this.d=d;
//        this.e=e;
//        this.f=f;
//        this.g=g;

        sh=PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_customchat, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//        TextView tv1=(TextView)gridView.findViewById(R.id.tvroom);
        ImageView i1=(ImageView) gridView.findViewById(R.id.imgaprtmnt2);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView13);
//        TextView tv3=(TextView)gridView.findViewById(R.id.tvbathroom);
//        TextView tv4=(TextView)gridView.findViewById(R.id.tvbalcony);
//        TextView tv5=(TextView)gridView.findViewById(R.id.phone1);
//        TextView tv6=(TextView)gridView.findViewById(R.id.email1);


        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+b.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i1.setImageDrawable(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

//        b1.setOnClickListener(new View.OnClickListener() {
//                                  @Override
//                                  public void onClick(View view) {
//
//
//                                          RequestQueue queue = Volley.newRequestQueue(context);
//                                          url = "http://" + sh.getString("ip", "") + ":5000/addappointment";
//
//                                          // Request a string response from the provided URL.
//                                          StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                                              @Override
//                                              public void onResponse(String response) {
//                                                  // Display the response string.
//                                                  Log.d("+++++++++++++++++", response);
//                                                  try {
////                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
//                                                      JSONObject json = new JSONObject(response);
//                                                      String res = json.getString("task");
////                            Toast.makeText(Login.this, sh.getString("ip","")+"", Toast.LENGTH_SHORT).show();
//
//
//                                                      if (res.equalsIgnoreCase("valid")) {
//
////
//
//                                                          Intent ik = new Intent(context, viewpsy.class);
//                                                          context.startActivity(ik);
//                                                          Toast.makeText(context, "Booked successfully", Toast.LENGTH_SHORT).show();
//
////
//
//                                                      } else {
//
//                                                          Toast.makeText(context, "not Booked ", Toast.LENGTH_SHORT).show();
//
//                                                      }
//                                                  } catch (JSONException e) {
//                                                      e.printStackTrace();
//                                                  }
//
//
//                                              }
//                                          }, new Response.ErrorListener() {
//                                              @Override
//                                              public void onErrorResponse(VolleyError error) {
//
//
//                                                  Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
//                                              }
//                                          }) {
//                                              @Override
//                                              protected Map<String, String> getParams() {
//                                                  Map<String, String> params = new HashMap<String, String>();
//                                                  params.put("shid",e.get(position));
//                                                  params.put("lid", sh.getString("lid", ""));
//
//                                                  return params;
//                                              }
//                                          };
//                                          queue.add(stringRequest);
//
//                                      }
//
//        });




//        tv1.setText(a.get(position));
        tv2.setText(c.get(position));
//        tv3.setText(c.get(position));
//        tv4.setText(e.get(position));
//        tv5.setText(f.get(position));
//        tv6.setText(g.get(position));





//        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
//        tv3.setTextColor(Color.BLACK);
//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//        tv6.setTextColor(Color.BLACK);




        return gridView;

    }

}





