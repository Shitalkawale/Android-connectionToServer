package com.example.connectionwithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String web_url="https://shitalkawale.000webhostapp.com/connectionToServer/connect.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.checkstatus);
    }

    public void checkconnection(View view) {
        getConnection();
    }

    public  void getConnection(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, web_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            int sucess=jsonObject.getInt("sucess");

                            if (sucess==1)
                            {
                                textView.setText(jsonObject.getString("message"));
                            }
                            else {
                                textView.setText("Something went wrong...");
                            }
                        }
                        catch (Exception e)
                        {
                            textView.setText(e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                textView.setText(error.getMessage());
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}