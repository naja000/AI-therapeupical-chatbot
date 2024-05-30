package com.example.psychiatrist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11;
    RadioButton r1,r2,r3;
    Button b1,b2;
    SharedPreferences sh;
    String title,url,fname,lname,age,gender,place,post,pin,phone,email,image,username,password;
    String PathHolder="";
    byte[] filedt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=findViewById(R.id.editTextTextPersonName4);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName6);
        e4=findViewById(R.id.editTextTextPersonName7);
        e5=findViewById(R.id.editTextTextPersonName8);
        e6=findViewById(R.id.editTextTextPersonName9);
        e7=findViewById(R.id.editTextTextPersonName10);
        e8=findViewById(R.id.editTextTextPersonName11);
        e9=findViewById(R.id.editTextTextPersonName12);
        e10=findViewById(R.id.editTextTextPersonName13);
        e11=findViewById(R.id.editTextTextPersonName14);
        r1=findViewById(R.id.radioButton);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton3);
        b1=findViewById(R.id.button4);
        b2=findViewById(R.id.button5);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

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

                fname = e1.getText().toString();
                lname = e2.getText().toString();
                gender = "";
                if (r1.isChecked()) {
                    gender = r1.getText().toString();
                } else {
                    gender = r2.getText().toString();
                }
                age = e3.getText().toString();
                place = e4.getText().toString();
                post = e5.getText().toString();
                pin = e6.getText().toString();
                phone = e7.getText().toString();
                email = e8.getText().toString();
                image = b1.getText().toString();
                username = e10.getText().toString();
                password = e11.getText().toString();

                url = "http://" + sh.getString("ip", "") + ":5000/registration";

                if (fname.equalsIgnoreCase("")) {
                    e1.setError("Enter first name"); 
                } else if (lname.equalsIgnoreCase("")) {
                    e2.setError("Enter last name");
                }
                else if (age.equalsIgnoreCase("")) {
                    e3.setError("Enter Your Age");
                } else if (age.length() < 1) {
                    e3.setError("Minimum 1 nos required");
                    e3.requestFocus();
                }
                else if(place.equalsIgnoreCase("")) {
                    e4.setError("Enter Place");}
                else if(post.equalsIgnoreCase("")) {
                    e5.setError("Enter Post");}
                else if(pin.equalsIgnoreCase("")) {
                    e6.setError("Enter Pin");}


                else if (phone.equalsIgnoreCase("")) {
                    e7.setError("Enter Your Phone No");
                } else if (phone.length() < 10) {
                    e7.setError("Minimum 10 nos required");
                    e7.requestFocus();
                } else if (email.equalsIgnoreCase("")) {
                    e8.setError("Enter Your Email");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    e8.setError("Enter Valid Email");
                    e8.requestFocus();
                } else if (username.equalsIgnoreCase("")) {
                    e10.setError("Enter Your username");
                } else if (password.equalsIgnoreCase("")) {
                    e11.setError("Enter Your password");
                }
                else {

                    uploadBitmap(title);
                }

            }
        });

    }

    ProgressDialog pd;
    private void uploadBitmap(final String title) {
        pd=new ProgressDialog(register.this);
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

                                Toast.makeText(register.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),Login1.class);
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


                params.put("fname", fname);
                params.put("lname", lname);
                params.put("gender", gender);
                params.put("age", age);
                params.put("place", place);
                params.put("post", post);
                params.put("pin", pin);
                params.put("phone", phone);
                params.put("email", email);

                params.put("username", username);
                params.put("password", password);


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
                        e9.setText(PathHolder);
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
        Intent ii= new Intent(getApplicationContext(),Login1.class);
        startActivity(ii);
    }

}