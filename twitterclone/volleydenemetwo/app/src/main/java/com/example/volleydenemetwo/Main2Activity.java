package com.example.volleydenemetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    Button button;
    TextView textview;
    EditText edittextone,edittexttwo;
    String res = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = findViewById(R.id.button);
        textview = findViewById(R.id.textview);
        edittextone = findViewById(R.id.edittextone);
        edittexttwo = findViewById(R.id.edittexttwo);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kisiekle();

            }
        });

    }
    public void kisiekle(){
        String url = "https://goldgym.pro/login.php";
        final String username = edittextone.getText().toString().trim();
        final String name = edittexttwo.getText().toString().trim();


        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Cevap",response);

                try{

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                   String sesion = jsonObject.getString("sesion");


                    if (success.equals("1")){
                        textview.setText(sesion);
                        Intent i = new Intent(getBaseContext(), Main3Activity.class);
                        i.putExtra("sesion",sesion);
                       startActivity(i);
                    }

                } catch (JSONException e){
                    e.printStackTrace();

                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("kisi_ad",username);
                params.put("kisi_tel",name);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(istek);


    }
    public void acil(){


       // Intent i = new Intent(getBaseContext(), Main3Activity.class);
      //  startActivity(i);

        //textview.setText("tamamdır");
    }
}
