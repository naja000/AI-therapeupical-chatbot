package com.example.psychiatrist;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Custom_viewcomment extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> id;
//    ArrayList<String> f;
//    ArrayList<String> g;



    SharedPreferences sh;


    public Custom_viewcomment(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> id) {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.id = id;
//        this.g = g;
//        this.f = f;


        sh = PreferenceManager.getDefaultSharedPreferences(applicationContext);
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


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertview == null) {
            gridView = new View(context);
            gridView = inflator.inflate(R.layout.activity_custom_viewcomment, null);

        } else {
            gridView = (View) convertview;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView13);
        ImageView i1 = (ImageView) gridView.findViewById(R.id.imgaprtmnt2);
        ImageView i2 = (ImageView) gridView.findViewById(R.id.imgaprtmnt);
        ImageView i3 = (ImageView) gridView.findViewById(R.id.imageView5);
//        ImageView i4 = (ImageView) gridView.findViewById(R.id.imageView7);
        TextView tv2 = (TextView) gridView.findViewById(R.id.tvbalcony);
//        TextView tv3 = (TextView) gridView.findViewById(R.id.textView15);
//        CardView c1= (CardView) gridView.findViewById(R.id.card2);

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent ik = new Intent(context.getApplicationContext(), viewcmmntss.class);
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("pid", id.get(position));

                edp.commit();
                context.startActivity(ik);
            }

        });



//        RatingBar r1 = (RatingBar) gridView.findViewById(R.id.ratingBar2);
//        r1.setIsIndicator(true);
//        r1.setEnabled(false);
//        r1.setClickable(false);b
//        r1.setOutlineSpo tShadowColor(Color.TRANSPARENT);

//        c1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent ik = new Intent(context.getApplicationContext(), viewfooditems.class);
//                ik.putExtra("hid",g.get(position));
//                context.startActivity(ik);
//            }
//        });
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        java.net.URL thumb_u;

        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://" + sh.getString("ip", "") + ":5000" +b.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i1.setImageDrawable(thumb_d);

        } catch (Exception e) {
            Log.d("errsssssssssssss", "" + e);
        } try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://" + sh.getString("ip", "") + ":5000" +d.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i2.setImageDrawable(thumb_d);

        } catch (Exception e) {
            Log.d("errsssssssssssss", "" + e);
        }

        tv1.setText(c.get(position));
        tv2.setText(a.get(position));
//        tv3.setText(g.get(position));

//        tv5.setText(f.get(position));
//        r1.setRating(Float.parseFloat(f.get(position)));




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
//        tv3.setTextColor(Color.BLACK);

//        tv5.setTextColor(Color.BLACK);


        return gridView;



    }

}





