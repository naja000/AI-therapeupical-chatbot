package com.example.psychiatrist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class uploadpost extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> posts,comment,details1,image,id;
    String title,url,url2,post,details;
    String PathHolder="";
    byte[] filedt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpost);
        e1 = findViewById(R.id.editTextTextPersonName16);
        e2 = findViewById(R.id.editTextTextPersonName17);
        b1 = findViewById(R.id.button21);
        b2 = findViewById(R.id.button22);
        l1=findViewById(R.id.uploadpost);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url2 ="http://"+sh.getString("ip", "") + ":5000/viewcomment";
        RequestQueue queue = Volley.newRequestQueue(uploadpost.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(uploadpost.this, "welcome"+response, Toast.LENGTH_SHORT).show();

                    posts= new ArrayList<>();
                    image= new ArrayList<>();
                    id= new ArrayList<>();
                    comment= new ArrayList<>();
                    details1= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        id.add(jo.getString("id"));
                        image.add(jo.getString("user"));
                        posts.add(jo.getString("details"));
                        comment.add(jo.getString("image"));
                        details1.add(jo.getString("post"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_viewcomment(uploadpost.this,posts,comment,image,details1,id));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(uploadpost.this, "err"+error, Toast.LENGTH_SHORT).show();
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


                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 7);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                post = b1.getText().toString();
                details = e2.getText().toString();


                url = "http://" + sh.getString("ip", "") + ":5000/uploadpost";

                post = b1.getText().toString();


                if (post.equalsIgnoreCase("")) {
                    e1.setError("Enter your post");

                }
                else if (details.equalsIgnoreCase("")) {
                    e2.setError("Enter your details");

                } else {

                    uploadBitmap(title);
                }
            }

        });

    }

    ProgressDialog pd;
    private void uploadBitmap(final String title) {
        pd=new ProgressDialog(uploadpost.this);
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        pd.dismiss();
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(uploadpost.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),uploadpost.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("lid", sh.getString("lid", ""));
                params.put("details", details);



                return params;

            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file", new DataPart(PathHolder , filedt ));


                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        PathHolder = FileUtils.getPathFromURI(this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();

                        filedt = getbyteData(PathHolder);
                        Log.d("filedataaa", filedt + "");
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        e1.setText(PathHolder);
                    }
                    catch (Exception e){
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private byte[] getbyteData(String pathHolder) {
        Log.d("path", pathHolder);
        File fil = new File(pathHolder);
        int fln = (int) fil.length();
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(fil);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[fln];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byteArray = bos.toByteArray();
            inputStream.close();
        } catch (Exception e) {
        }
        return byteArray;


    }
    @Override
    public void onBackPressed() {
        Intent ii= new Intent(getApplicationContext(),home.class);
        startActivity(ii);
    }



//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                details = e2.getText().toString();
//                post = b1.getText().toString();
//
//                if (details.equalsIgnoreCase("")) {
//                    e1.setError("Enter the complaint");
//                } else {
//
////                Intent i=new Intent(getApplicationContext(),home.class);
////                startActivity(i);
//
//                    RequestQueue queue = Volley.newRequestQueue(uploadpost.this);
//                    url = "http://" + sh.getString("ip", "") + ":5000/uploadpost";
//
//                    // Request a string response from the provided URL.
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // Display the response string.
//                            Log.d("+++++++++++++++++", response);
//                            try {
//                                JSONObject json = new JSONObject(response);
//                                String res = json.getString("task");
//
//                                if (res.equalsIgnoreCase("valid")) {
//
//
////                                /////////////////////////////////////
////                                String lid = json.getString("id");
////                                SharedPreferences.Editor edp = sh.edit();
////                                edp.putString("lid", lid);
////                                edp.commit();
////                                /////////////////////////////////////
//                                    Intent ik = new Intent(getApplicationContext(), home.class);
//                                    startActivity(ik);
//                                    Toast.makeText(uploadpost.this, "Post Uploaded!!", Toast.LENGTH_SHORT).show();
//
//
//                                } else {
//
//                                    Toast.makeText(uploadpost.this, "Not Uploaded!!", Toast.LENGTH_SHORT).show();
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//
//                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
//                        }
//                    }) {
//                        @Override
//                        protected Map<String, String> getParams() {
//                            Map<String, String> params = new HashMap<String, String>();
//                            params.put("lid", sh.getString("lid", ""));
//                            params.put("details", details);
//
//
//                            return params;
//                        }
//
//                    };
//
//
//
//                    queue.add(stringRequest);
//
//                }
//            }
//        });
//

}